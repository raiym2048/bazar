package com.example.bazar.service;

import java.util.UUID;

public interface AdminService {
    void addAddressType(String addressType);

    void changePassword(UUID userUID, String newPassword);
}
