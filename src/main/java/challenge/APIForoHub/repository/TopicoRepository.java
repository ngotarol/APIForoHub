package challenge.APIForoHub.repository;

import challenge.APIForoHub.model.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Optional<Topico> findByTitulo(String titulo);

    Optional<Topico> findByTituloIgnoreCase(@NotBlank @Size(message="Tamaño maximo de titulo: 1000",
                groups = {},
                max=1000
        ) String titulo);

    Optional<Topico> findByMensajeIgnoreCase(@NotBlank @Size(message="Tamaño maximo de mensaje: 3000",
                groups = {},
                max=3000
        ) String mensaje);
}
