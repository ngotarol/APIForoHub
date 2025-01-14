package challenge.APIForoHub.service;

import challenge.APIForoHub.DTO.DtoActualizarTopico;
import challenge.APIForoHub.DTO.DtoListadoTopicos;
import challenge.APIForoHub.DTO.DtoTopico;
import challenge.APIForoHub.model.Curso;
import challenge.APIForoHub.model.Perfil;
import challenge.APIForoHub.model.Topico;
import challenge.APIForoHub.model.Usuario;
import challenge.APIForoHub.repository.CursoRepository;
import challenge.APIForoHub.repository.PerfilRepository;
import challenge.APIForoHub.repository.TopicoRepository;
import challenge.APIForoHub.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final PerfilRepository perfilRepository;


    @Autowired
    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, CursoRepository cursoRepository, PerfilRepository perfilRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
        this.perfilRepository = perfilRepository;
    }

    public Topico crearTopico(DtoTopico dtoTopico) {

        Perfil perfil = perfilRepository.findByNombre(dtoTopico.autor().perfil().nombre())
                .orElseGet(() -> perfilRepository.save(new Perfil(dtoTopico.autor().perfil())));

        Usuario autor = usuarioRepository.findByLoginIgnoreCase(dtoTopico.autor().correoElectronico())
                .orElseGet(() -> usuarioRepository.save(new Usuario(dtoTopico.autor(),perfil)));

        Curso curso = cursoRepository.findByNombre(dtoTopico.curso().nombre())
                .orElseGet(() -> cursoRepository.save(new Curso(dtoTopico.curso())));

        Topico topico = new Topico(dtoTopico, autor, curso);
        return topicoRepository.save(topico);
    }

    public Boolean ValidarTopicoExiste(DtoTopico dtoTopico) {
        boolean existe = false;

        Optional<Topico> tituloBuscado = topicoRepository.findByTituloIgnoreCase(dtoTopico.titulo());
        if (tituloBuscado.isPresent()){
            existe = true;
        }
        Optional<Topico> mensajeBuscado = topicoRepository.findByMensajeIgnoreCase(dtoTopico.mensaje());
        if (mensajeBuscado.isPresent()){
            existe = true;
        }
        return existe;
    }

    public Page<DtoListadoTopicos> ListarTodosLosTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DtoListadoTopicos::new);
    }

    public Optional<Topico> EncontrarTopicoPorId(Long id) {
        return topicoRepository.findById(id);
    }

    public Topico ActualizarTopico(Long id, @Valid DtoActualizarTopico dtoTopico) {
        Topico topicoParaActualizar = topicoRepository.getReferenceById(id);
        Usuario autor;
        Curso curso;

        Perfil perfil = perfilRepository.findByNombre(dtoTopico.autor().perfil().nombre()).
                orElseGet(() -> perfilRepository.save(new Perfil(dtoTopico.autor().perfil())));

        Optional<Usuario> autorOptional = usuarioRepository.findByLoginIgnoreCase(dtoTopico.autor().correoElectronico());
        if (autorOptional.isPresent()){
            autor = autorOptional.get();
            autor.ActualizarAutor(dtoTopico.autor(),perfil);
        }
        else {
            autor = new Usuario(dtoTopico.autor(),perfil);
            usuarioRepository.save(autor);
        }

        Optional<Curso> cursoOptional = cursoRepository.findByNombre(dtoTopico.curso().nombre());
        if (cursoOptional.isPresent()){
            curso = cursoOptional.get();
            curso.ActualizarCurso(dtoTopico.curso());
        }
        else{
            curso = new Curso(dtoTopico.curso());
            cursoRepository.save(curso);
        }

        topicoParaActualizar.ActualizarTopico(dtoTopico,autor,curso);
        return topicoParaActualizar;
    }

    public void EliminarTopico(Long id) {
         topicoRepository.deleteById(id); //TODO no esta borrando
    }
}