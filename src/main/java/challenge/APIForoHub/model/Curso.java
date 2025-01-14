package challenge.APIForoHub.model;

import challenge.APIForoHub.DTO.DtoCurso;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,updatable = false)
    private String nombre;
    @Column(nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Topico> topicos;

    public Curso(DtoCurso dtoCurso){
        this.nombre = dtoCurso.nombre();
        this.categoria = dtoCurso.categoria();
    }

    public Curso() {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void ActualizarCurso(@NotNull @Valid DtoCurso curso) {
        this.categoria = curso.categoria();
    }
}
