package challenge.APIForoHub.DTO;

import challenge.APIForoHub.model.Perfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.UniqueElements;

public record DtoUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String correoElectronico,
        @NotBlank
        String contrasena,
        @NotNull
        @Valid
        DtoPerfil perfil
) {
}
