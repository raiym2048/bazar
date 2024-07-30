package com.example.bazar.controller;


import com.example.bazar.service.AdminService;
import com.example.bazar.service.impl.AdminServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/add/address_type")
    public void addAddressType(@RequestParam(required = true) String addressType){
         adminService.addAddressType(addressType);
    }
    @GetMapping("/change/user/password")
    public void changeUserPassword(@RequestParam(required = true)UUID userUID, @RequestParam(required = true) String newPassword){
        adminService.changePassword(userUID, newPassword);
    }
}
