package challenge.APIForoHub.repository;

import challenge.APIForoHub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso,Long> {

    Optional<Curso> findByNombre(String nombre);
}
