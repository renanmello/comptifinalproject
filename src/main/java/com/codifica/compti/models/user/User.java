package com.codifica.compti.models.user;

import com.codifica.compti.models.negotiation.Negotiation;
import com.codifica.compti.models.userproduct.UserProduct;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String name;
    private String password; // Armazenada criptografada
    private String whatsapp;
    private String socialMediaLink;
    private String zipCode;
    private String addressComplement;
    private Boolean type; // true = Individual, false = Business
    private String document; // CPF ou CNPJ
    private String photo; // Opcional


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastAccess;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<UserProduct> products;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Negotiation> negotiationsAsBuyer;

}