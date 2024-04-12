package com.example.viacep.address.repository;

import com.example.viacep.address.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address", url = "https://viacep.com.br/ws/")
public interface ViaCepRepository {

    @GetMapping("{cep}/json/")
    Address fetchViaCepApi(@PathVariable String cep);

}
