package challenge.APIForoHub.model;

import challenge.APIForoHub.DTO.DtoPerfil;
import challenge.APIForoHub.repository.PerfilRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "perfiles")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "perfil",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Usuario> autores;

    public Perfil(DtoPerfil dtoPerfil){
        this.nombre = dtoPerfil.nombre();
    }

    public Perfil() {
    }

    @Override
    public String toString() {
        return "Perfil: " + nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
