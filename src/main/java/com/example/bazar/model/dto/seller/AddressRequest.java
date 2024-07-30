package com.example.bazar.model.dto.seller;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
public class AddressRequest {

    private UUID parentId;

    private String type;
    private String value;

}
