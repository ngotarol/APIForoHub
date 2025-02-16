package challenge.APIForoHub.model;

import challenge.APIForoHub.DTO.DtoAutenticacionUsuario;
import challenge.APIForoHub.DTO.DtoUsuario;
import challenge.APIForoHub.infraestructura.security.SecurityConfigurations;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true,updatable = false)
    private String login;
    @Column(length = 300)
    private String clave;
    @OneToMany(mappedBy = "autor",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Topico> topicos;
    @ManyToOne()
    private Perfil perfil;

    public Usuario() {
    }

    public Usuario(DtoUsuario dtoUsuario, Perfil perfil){
        this.nombre = dtoUsuario.nombre();
        this.login = dtoUsuario.correoElectronico();
        this.clave = dtoUsuario.contrasena();
        this.perfil = perfil;
    }
    public Usuario(DtoAutenticacionUsuario dtoAutenticacionUsuario){
        this.login = dtoAutenticacionUsuario.correoElectronico();
        this.clave = dtoAutenticacionUsuario.contrasena();
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                "Correo Electronico: " + login;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public List<String> getTopicos() {
        List<String> titulos = new ArrayList<>();
        topicos.forEach(topico -> titulos.add(topico.getTitulo()));
        return titulos;
    }


    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public void ActualizarAutor(@NotNull @Valid DtoUsuario autor,Perfil perfil) {
        this.nombre = autor.nombre();
        this.perfil = perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return clave;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

