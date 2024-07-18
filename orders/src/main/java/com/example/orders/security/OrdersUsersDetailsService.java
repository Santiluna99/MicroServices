package com.example.orders.security;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class OrdersUsersDetailsService implements UserDetailsService {
    //CARGA USER
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = getById(username); // BUSCAR USER POR ID

        if (usuario == null) {
            throw new UsernameNotFoundException(username); // CASO CONTRARIO LARGA EXCEPTION
        }

        return User // RETORNA OBJETO CON DETALLES DE USER
                .withUsername(username)
                .password(usuario.password())
                .roles(usuario.roles().toArray(new String[0]))
                .build();
    }

    public record Usuario(String username, String password, Set<String> roles) {
    }

    ;

    //BUSQUEDA USER
    public static Usuario getById(String username) {

        var password = "$2a$10$gosn402nuWoA24s/7F9CJ.OJlgs4aJNCJ7yL3X29zeUP5axiEuucG"; // PASS ENCRIPTADA

        Usuario user = new Usuario(
                "SANTI",
                password,
                Set.of("USER")
        );

        var usuarios = List.of(user); // LISTA USER

        return usuarios  // RETORNA USER QUE COINCIDA CON USERNAME
                .stream()
                .filter(e -> e.username().equals(username))
                .findFirst()
                .orElse(null);
    }
}
