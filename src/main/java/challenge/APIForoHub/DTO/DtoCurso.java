package challenge.APIForoHub.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record DtoCurso(
        @NotBlank
        String nombre,
        @NotBlank
        String categoria
) {
}
