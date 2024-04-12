package com.example.viacep.address.controller;

import com.example.viacep.address.dto.AddressDTO;
import com.example.viacep.address.model.Address;
import com.example.viacep.address.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private AddressService service;

    @PostMapping("{idUser}/{cep}")
    public Address findByAddress(@PathVariable String cep, @PathVariable Long idUser, @RequestBody AddressDTO dto) throws Exception{
        return this.service.createAddressToUser(cep, idUser, dto);
    }
}
