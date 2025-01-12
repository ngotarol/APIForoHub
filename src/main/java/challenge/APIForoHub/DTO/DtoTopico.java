package challenge.APIForoHub.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UniqueElements;

public record DtoTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        @Valid
        DtoUsuario autor,
        @NotNull
        @Valid
        DtoCurso curso
) {
}
