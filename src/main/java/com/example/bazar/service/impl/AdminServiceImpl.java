package com.example.bazar.service.impl;

import com.example.bazar.exception.CustomException;
import com.example.bazar.model.domain.AddressType;
import com.example.bazar.repository.seller.AddressTypeRepository;
import com.example.bazar.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AddressTypeRepository addressTypeRepository;
    @Override
    public void addAddressType(String addressType) {
        if (!addressTypeRepository.findAllByValue(addressType).isEmpty())
            throw new CustomException("данный тип адреса уже существует!", HttpStatus.BAD_REQUEST);
        AddressType addressTypeEntity = new AddressType();
        addressTypeEntity.setValue(addressType);
        addressTypeRepository.save(addressTypeEntity);
    }
}
