package challenge.APIForoHub.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.UniqueElements;

public record DtoTopico(
        @NotBlank
        @Size(message="Tamaño maximo de titulo: 1000",
                groups = {},
                max=1000
        )
        String titulo,
        @NotBlank
        @Size(message="Tamaño maximo de mensaje: 3000",
                groups = {},
                max=3000
        )
        String mensaje,
        @NotNull
        @Valid
        DtoUsuario autor,
        @NotNull
        @Valid
        DtoCurso curso
) {
}
