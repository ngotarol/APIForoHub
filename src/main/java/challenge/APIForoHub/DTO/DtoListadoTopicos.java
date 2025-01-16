package challenge.APIForoHub.DTO;

import challenge.APIForoHub.model.Topico;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import java.time.LocalDateTime;

public record DtoListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean estado,
        String autor,
        String curso
) {
    public DtoListadoTopicos(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getEstado(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre());
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String titulo() {
        return titulo;
    }

    @Override
    public String mensaje() {
        return mensaje;
    }

    @Override
    public LocalDateTime fechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public Boolean estado() {
        return estado;
    }

    @Override
    public String autor() {
        return autor;
    }

    @Override
    public String curso() {
        return curso;
    }
}
