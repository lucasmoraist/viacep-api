package com.example.viacep.user.model;

import com.example.viacep.address.model.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "t_user")
@Table(name = "t_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dtNasc;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Address> address;

}
