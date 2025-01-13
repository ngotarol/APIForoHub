package challenge.APIForoHub.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DtoPerfil(
        @NotBlank(message = "El nombre del perfil no puede estar vacio")
        String nombre
) {
}
