package com.example.viacep.address.service;

import com.example.viacep.address.dto.AddressDTO;
import com.example.viacep.address.model.Address;
import com.example.viacep.address.repository.AddressRepository;
import com.example.viacep.address.repository.ViaCepRepository;
import com.example.viacep.user.model.User;
import com.example.viacep.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepRepository viaCepRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Método responsável por salvar endereço para o usuário
     *
     * @param cep este valor será recebido na url da requisição
     * @param idUser este valor será recebido na url da requisição
     * @param dto este valor será recebido no corpo da requisição e é opcional
     * @return irá retornar o endereço completo do CEP passado e irá salvar na lista de endereço do usuário
     * @throws Exception essa exceção está vindo do método de busca do usuário, e só ira ocorrer caso usuário não exista
     */
    public Address createAddressToUser(String cep, Long idUser, AddressDTO dto) throws Exception{
        var user = this.findUser(idUser);
        var buscaCep = this.findByAddress(cep);

        buscaCep.setComplemento(dto.complemento());

        Address address = Address.builder()
                .cep(buscaCep.getCep())
                .logradouro(buscaCep.getLogradouro())
                .complemento(buscaCep.getComplemento())
                .bairro(buscaCep.getBairro())
                .localidade(buscaCep.getLocalidade())
                .uf(buscaCep.getUf())
                .numero(dto.numero())
                .user(user)
                .build();

        this.addressRepository.save(address);

        return address;
    }

    /**
     * Método responsável pela chamada da API externa, ViaCEP
     *
     * @param cep este valor irá receber um CEP para fazer um fetch na API do ViaCEP
     * @return irá retornar as informações do CEP passsado
     */
    private Address findByAddress(String cep){
        return this.viaCepRepository.fetchViaCepApi(cep);
    }

    /**
     * Método responsável pela busca de usuário através do id
     *
     * @param idUser irá receber o id do usuário
     * @return irá retornar um objeto do tipo User, que contém  as informações do usuário
     * @throws Exception irá ocorrer caso não encontre o usuário com o id passado
     */
    private User findUser(Long idUser) throws Exception{
        Optional<User> optionalUser = this.userRepository.findById(idUser);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return user;
        }else{
            throw new Exception("Usuário não encontrado");
        }
    }
}
