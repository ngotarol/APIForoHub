package challenge.APIForoHub.controller;

import challenge.APIForoHub.DTO.*;
import challenge.APIForoHub.model.Topico;
import challenge.APIForoHub.model.Usuario;
import challenge.APIForoHub.service.TopicoService;
import challenge.APIForoHub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private Usuario usuario;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<DtoListadoUsuario> RegistrarUsuario(
            @RequestBody @Valid DtoUsuario dtoUsuario,
            UriComponentsBuilder uriComponentsBuilder){
        if (usuarioService.ValidarUsuarioExiste(dtoUsuario)){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
        else {
            usuario = usuarioService.registrarUsuario(dtoUsuario);
            URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(new DtoListadoUsuario(usuario));
        }
    }
}
