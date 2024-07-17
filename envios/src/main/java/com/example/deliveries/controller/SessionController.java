package com.example.deliveries.controller;

import com.example.deliveries.security.DeliveriesAutenticationReq;
import com.example.deliveries.security.DeliveriesJWTUtilService;
import com.example.deliveries.security.DeliveriesToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
public class SessionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private DeliveriesJWTUtilService deliveriesJWTUtilService;
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    /** METODO PARA MANEJAR SOLICITUDES DE AUTENTICACIÓN**/
    @PostMapping("/authenticate")
    public ResponseEntity<DeliveriesToken> authenticate(@RequestBody DeliveriesAutenticationReq deliverysAuthenticationReq) {
        logger.info("Autenticando al usuario {}", deliverysAuthenticationReq.getUsuario());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(deliverysAuthenticationReq.getUsuario(), deliverysAuthenticationReq.getClave()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(deliverysAuthenticationReq.getUsuario());

        final String jwt = deliveriesJWTUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new DeliveriesToken(jwt));
    }

}
