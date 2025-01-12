package challenge.APIForoHub.repository;

import challenge.APIForoHub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico,Long> {
    Optional<Topico> findByTitulo(String titulo);

}
