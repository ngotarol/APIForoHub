package challenge.APIForoHub.DTO;

import challenge.APIForoHub.model.Usuario;


public record DtoListadoUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        String perfil
) {
    public DtoListadoUsuario(Usuario usuario){
        this(usuario.getId(),
                usuario.getNombre(),
                usuario.getLogin(),
                usuario.getPerfil().getNombre());
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public String nombre() {
        return nombre;
    }

    @Override
    public String correoElectronico() {
        return correoElectronico;
    }

    @Override
    public String perfil() {
        return perfil;
    }
}
