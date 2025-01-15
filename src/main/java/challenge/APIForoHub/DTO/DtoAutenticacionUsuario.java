package challenge.APIForoHub.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoAutenticacionUsuario(
        @NotBlank
        @Email
        String correoElectronico,
        @NotBlank
        String contrasena
) {
}
