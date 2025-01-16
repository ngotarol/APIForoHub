package challenge.APIForoHub.controller;

import challenge.APIForoHub.DTO.DtoAutenticacionUsuario;
import challenge.APIForoHub.infraestructura.security.DtoToken;
import challenge.APIForoHub.infraestructura.security.TokenService;
import challenge.APIForoHub.model.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid DtoAutenticacionUsuario dtoAutenticacionUsuario){
        Authentication authenticationToken = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                dtoAutenticacionUsuario.correoElectronico(),dtoAutenticacionUsuario.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DtoToken(JWTtoken));
    }
}
