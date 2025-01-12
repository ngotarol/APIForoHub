package challenge.APIForoHub.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoRespuesta(
        @NotBlank
        String mensaje,
        @NotNull
        @Valid
        DtoTopico topico,
        @NotNull
        @Valid
        DtoUsuario autor,
        @NotBlank
        String solucion
) {
}
