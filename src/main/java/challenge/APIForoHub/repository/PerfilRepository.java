package challenge.APIForoHub.repository;

import challenge.APIForoHub.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {

    Optional<Perfil> findByNombre(String nombre);
}
