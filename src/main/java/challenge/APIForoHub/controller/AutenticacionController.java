package challenge.APIForoHub.controller;

import challenge.APIForoHub.DTO.DtoAutenticacionUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DtoAutenticacionUsuario dtoAutenticacionUsuario){
        Authentication token = new UsernamePasswordAuthenticationToken(
                dtoAutenticacionUsuario.correoElectronico(),dtoAutenticacionUsuario.contrasena());
        authenticationManager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
