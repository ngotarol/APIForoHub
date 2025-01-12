package challenge.APIForoHub.model;

import challenge.APIForoHub.DTO.DtoCurso;
import jakarta.persistence.*;
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

    @Column(unique = true,nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String categoria;

    @OneToMany(mappedBy = "curso",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Topico> topicos;

    public Curso(DtoCurso dtoCurso){
        this.nombre = dtoCurso.nombre();
        this.categoria = dtoCurso.categoria();
    }


}
