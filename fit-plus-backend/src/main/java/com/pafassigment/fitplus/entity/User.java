package com.pafassigment.fitplus.entity;

import com.pafassigment.fitplus.enumeration.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String name;

    @Email
    @Indexed(unique = true)
    private String email;

    private String imageUrl;

    private Boolean emailVerified = false;

    private String password;
    @NotNull
    @Indexed
    private AuthProvider provider;

    private String providerId;
}
