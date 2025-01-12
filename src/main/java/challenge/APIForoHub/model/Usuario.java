package challenge.APIForoHub.model;

import challenge.APIForoHub.DTO.DtoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)
    private String login;
    private String clave;
    @OneToMany(mappedBy = "autor",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Topico> topicos;
    @ManyToMany
    private List<Perfil> perfiles;

    public Usuario(DtoUsuario dtoUsuario){
        this.nombre = dtoUsuario.nombre();
        this.login = dtoUsuario.correoElectronico();
        this.clave = dtoUsuario.contrasena();
    }
}

