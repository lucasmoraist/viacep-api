package com.example.viacep.address.service;

import com.example.viacep.address.dto.AddressDTO;
import com.example.viacep.address.model.Address;
import com.example.viacep.address.repository.AddressRepository;
import com.example.viacep.user.model.User;
import com.example.viacep.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Address findByAddress(String cep, Long idUser, AddressDTO dto) throws Exception{
        Address buscaCep = restTemplate.getForObject("https://viacep.com.br/ws/"+cep+"/json/", Address.class);
        System.out.println(buscaCep);
        Optional<User> optionalUser = this.userRepository.findById(idUser);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();

            buscaCep.setComplemento(dto.complemento());

            Address newEndereco = Address.builder()
                    .cep(buscaCep.getCep())
                    .logradouro(buscaCep.getLogradouro())
                    .complemento(buscaCep.getComplemento())
                    .bairro(buscaCep.getBairro())
                    .localidade(buscaCep.getLocalidade())
                    .uf(buscaCep.getUf())
                    .numero(dto.numero())
                    .user(user)
                    .build();

            this.repository.save(newEndereco);

            return newEndereco;
        }else{
            throw new Exception("Usuário não encontrado");
        }
    }

}
