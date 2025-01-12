package challenge.APIForoHub.DTO;

import jakarta.validation.constraints.NotBlank;

public record DtoPerfil(
        @NotBlank
        String nombre
) {
}
