package com.example.bazar.mapper.impl;

import com.example.bazar.mapper.SellerMapper;
import com.example.bazar.model.domain.Address;
import com.example.bazar.model.domain.Seller;
import com.example.bazar.model.dto.seller.AddressRequest;
import com.example.bazar.model.dto.seller.SellerRequest;
import com.example.bazar.model.dto.seller.SellerResponse;
import com.example.bazar.repository.seller.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class SellerMapperImpl implements SellerMapper {
    private final AddressRepository addressRepository;

    public SellerMapperImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public SellerResponse toResponse(Seller seller) {
        SellerResponse response = new SellerResponse();
        response.setId(seller.getId());

        response.setAddress(setResponse(seller.getAddress()));
        response.setPhoneNumber(seller.getContact().getPhoneNumber());
        response.setCompanyName(seller.getName());

        response.setImage(seller.getImage());
        return response;
    }

    private String setResponse(List<Address> addresses) {
        StringBuilder response = new StringBuilder();
        for (Address address : addresses) {
            List<String> values = new ArrayList<>();
            collectValues(address, values);
            Collections.reverse(values);  // Reverse the list to start from the root
            for (String value : values) {
                response.append(value);
            }
        }
        return response.toString(); // todo
    }

    private void collectValues(Address address, List<String> values) {
        if (address != null) {
            values.add(address.getType().getValue()+": "+  address.getValue()+" ");
            if (address.getParentId() != null) {
                addressRepository.findById(address.getParentId()).ifPresent(parent -> collectValues(parent, values));
            }
        }
    }

    @Override
    public List<SellerResponse> toResponseList(List<Seller> sellers) {
        List<SellerResponse> responses = new ArrayList<>();
        for (Seller seller : sellers) {
            responses.add(toResponse(seller));
        }
        return responses;
    }

    @Override
    public Seller toSeller(Seller seller, SellerRequest request) {
        // Implement the mapping from SellerRequest to Seller
        seller.setName(request.getName());
        // Implement the rest of the mapping
        return seller;
    }

    @Override
    public List<Address> toDtoAddresses(List<AddressRequest> addressRequest) {
        // Implement the mapping from AddressRequest to Address
        return new ArrayList<>();
    }
}
