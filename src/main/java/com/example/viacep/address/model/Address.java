package com.example.viacep.address.model;

import com.example.viacep.user.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "t_endereco")
@Table(name = "t_endereco")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Address {

    @Id
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private int numero;

    @ManyToOne
    @JsonBackReference
    private User user;

    @Override
    public String toString() {
        return "Cep: " + cep +
                "\n" + "Logradouro: " + logradouro +
                "\n" + "Complemento: " + complemento +
                "\n"+ "Bairro=" + bairro +
                "\n" + "Localidade: " + localidade +
                "\n" + "Uf: " + uf;
    }

}
