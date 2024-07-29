package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.mapper.AuthMapper;
import com.example.bazar.mapper.ManagerMapper;
import com.example.bazar.mapper.SellerMapper;
import com.example.bazar.model.domain.*;
import com.example.bazar.model.dto.auth.AuthResponse;
import com.example.bazar.model.dto.auth.SellerRegisterRequest;
import com.example.bazar.model.dto.manager.ManagerResponse;
import com.example.bazar.model.dto.seller.AddressRequest;
import com.example.bazar.model.dto.seller.ContactRequest;
import com.example.bazar.model.enums.Role;
import com.example.bazar.repository.ManagerRepository;
import com.example.bazar.repository.SellerRepository;
import com.example.bazar.repository.UserRepository;
import com.example.bazar.repository.seller.AddressRepository;
import com.example.bazar.repository.seller.AddressTypeRepository;
import com.example.bazar.repository.seller.ContactRepository;
import com.example.bazar.service.AuthService;
import com.example.bazar.service.ManagerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final AuthService authService;
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final AuthMapper authMapper;
    private final PasswordEncoder encoder;
    private final SellerMapper sellerMapper;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final AddressTypeRepository addressTypeRepository;


    @Override
    public List<ManagerResponse> all(int offset, int pageSize) {
        return managerMapper.toResponseList(managerRepository.findAll(PageRequest.of(offset, pageSize)).stream().toList());
    }

    @Override
    public ManagerResponse getById(UUID id) {
        return managerMapper.toResponse(managerRepository.findById(id).orElseThrow(() -> new CustomException("Manager not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public ManagerResponse getProfile(String token) {
        User user = authService.getUserFromToken(token);
        return managerMapper.toResponse(managerRepository.findById(user.getId()).orElseThrow(() -> new CustomException("Manager not found", HttpStatus.NOT_FOUND)));
    }

    @Override
    public AuthResponse registerSeller(SellerRegisterRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getContact().getPhoneNumber());
        if (userOptional.isPresent()) {
            throw new CustomException("Поставщик с этим номерем занят!", HttpStatus.CONFLICT);
        }
        User user = new User();
        user.setEmail(request.getContact().getPhoneNumber());
        user.setRole(Role.SELLER);

        String randPassword = authService.generateRandomPassword();
        user.setPassword(encoder.encode(randPassword));
        System.out.println("Password of the seller: " +  randPassword);

        Seller seller = new Seller();
        seller.setAddress(setAddresses(request.getAddressRequest()));
        seller.setContact(setContact(request.getContact()));
        seller.setName(request.getCompanyName());
        sellerRepository.save(seller);
        user.setSeller(seller);
        userRepository.save(user);
        //todo send email and send password: randPassword

        return authMapper.toDto(user);
    }

    @Override
    public Address addAddresses(AddressRequest request) {
        if (!addressRepository.findAllByValueAndType_Value(request.getValue(), request.getType()).isEmpty()){
            throw new CustomException("такой адрес уже существует! " + request.getValue()+" , " + request.getType(), HttpStatus.BAD_REQUEST);
        }
        Address address = new Address();
        address.setValue(request.getValue());
        address.setType(addressTypeRepository.findByValue(request.getType()));
        address.setParentId(request.getParentId()!=null? request.getParentId():null);
        return addressRepository.save(address);
    }

    private Contact setContact(ContactRequest request) {
        if (contactRepository.existsByPhoneNumber(request.getPhoneNumber()))
            throw new CustomException("данный телефонный номер занят другим поставщиком! " + request.getPhoneNumber(), HttpStatus.BAD_REQUEST);
        Contact contact = new Contact();
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setPhoneNumber(request.getPhoneNumber());
        contact.setAdditionalPhoneNumber(request.getAdditionalPhoneNumber());
        return contactRepository.save(contact);
    }

    private List<Address> setAddresses(List<AddressRequest> addressRequest) {
        List<Address> addressList = new ArrayList<>();
        for (AddressRequest request : addressRequest) {
           addressList.add(setAddress(request));
        }
        return addressList;
    }

    private Address setAddress(AddressRequest request) {
        if (!addressRepository.findAllByValueAndType_Value(request.getValue(), request.getType()).isEmpty()){
            throw new CustomException("данный адрес уже занят другим поставщиком! " + request.getValue()+" , " + request.getType(), HttpStatus.BAD_REQUEST);
        }
        Address address = new Address();
        address.setValue(request.getValue());
        if (addressTypeRepository.findAllByValue(request.getType()).isEmpty()){
            throw new CustomException("Данный тип адреса не существует! " + request.getType(), HttpStatus.BAD_REQUEST);
        }
        address.setType(addressTypeRepository.findByValue(request.getType()));
        if (request.getParentId() != null){
            Optional<Address> optionalAddress = addressRepository.findById(request.getParentId());
            if (optionalAddress.isPresent()){
                address.setParentId(request.getParentId());
            }else {
                throw new CustomException("Parent Id не существует " + request.getType(), HttpStatus.BAD_REQUEST);
            }
        }

        return addressRepository.save(address);
    }
}
