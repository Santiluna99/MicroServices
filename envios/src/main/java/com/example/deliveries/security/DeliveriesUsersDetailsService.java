package com.example.deliveries.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DeliveriesUsersDetailsService implements UserDetailsService {
    //CARGA DE USER
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = getById(username); // BUSCA USER POR ID

        if (usuario == null) {
            throw new UsernameNotFoundException(username); //EN CASO DE NO ENCONTRAR USER LANZA EXCEPTION
        }

        return User //CONSTRUYE UN OBJETO Y RETORNA UserDetails CON DETALLES DEL USER
                .withUsername(username)
                .password(usuario.password())
                .roles(usuario.roles().toArray(new String[0]))
                .build();
    }

    public record Usuario(String username, String password, Set<String> roles) {
    }

    ;

    //BUSCAR USER
    public static Usuario getById(String username) {

        var password = "$2a$10$gosn402nuWoA24s/7F9CJ.OJlgs4aJNCJ7yL3X29zeUP5axiEuucG"; // PASS ENCRIPTADA

        Usuario user = new Usuario(
                "Flechi",
                password,
                Set.of("USER")
        );

        var usuarios = List.of(user); // LISTADO DE USERS

        return usuarios  // BUSCAR Y RETORNAR USER QUE COINCIDE CON USERNAME
                .stream()
                .filter(e -> e.username().equals(username))
                .findFirst()
                .orElse(null);
    }
}