package challenge.APIForoHub.service;

import challenge.APIForoHub.DTO.DtoUsuario;
import challenge.APIForoHub.infraestructura.security.SecurityConfigurations;
import challenge.APIForoHub.model.Perfil;
import challenge.APIForoHub.model.Topico;
import challenge.APIForoHub.model.Usuario;
import challenge.APIForoHub.repository.PerfilRepository;
import challenge.APIForoHub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;
    private SecurityConfigurations securityConfigurations = new SecurityConfigurations();

    @Autowired
    public UsuarioService(PerfilRepository perfilRepository,UsuarioRepository usuarioRepository){
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(@Valid DtoUsuario dtoUsuario) {
        Perfil perfil = perfilRepository.findByNombre(dtoUsuario.perfil().nombre()).
                orElseGet(() -> perfilRepository.save(new Perfil(dtoUsuario.perfil())));
        Usuario usuario = new Usuario(dtoUsuario,perfil);
        usuario.setClave(securityConfigurations.passwordEncoder().encode(dtoUsuario.contrasena()).toString());
        return usuarioRepository.save(usuario);
    }

    public boolean ValidarUsuarioExiste(@Valid DtoUsuario dtoUsuario) {
        return usuarioRepository.findByLoginIgnoreCase(dtoUsuario.correoElectronico()).isPresent();
    }

}
