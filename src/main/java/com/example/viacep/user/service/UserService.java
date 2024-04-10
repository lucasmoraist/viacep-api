package com.example.viacep.user.service;

import com.example.viacep.user.model.User;
import com.example.viacep.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User createUser(User user) throws Exception{
        this.verifyAge(user.getDtNasc());
        this.verifyEmail(user.getEmail());

        User newUser = User.builder()
                .nome(user.getNome())
                .email(user.getEmail())
                .password(user.getPassword())
                .dtNasc(user.getDtNasc())
                .build();
        this.repository.save(newUser);

        return newUser;
    }

    public List<User> findAll(){
        return this.repository.findAll();
    }

    private int verifyAge(LocalDate dtNasc) throws Exception{
        LocalDate today = LocalDate.now();

        if(today.compareTo(dtNasc) < 18){
            throw new Exception("Usuário menor de idade");
        }
        return today.compareTo(dtNasc);
    }

    private void verifyEmail(String email) throws Exception{
        var verified = this.repository.findByEmail(email);
        if(verified.isPresent()){
            throw new Exception("Este email já existe");
        }
    }

}
