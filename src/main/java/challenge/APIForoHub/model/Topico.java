package challenge.APIForoHub.model;

import challenge.APIForoHub.DTO.DtoTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,length = 1000,nullable = false)
    private String titulo;
    @Column(length = 3000,nullable = false)
    private String mensaje;
    @ManyToOne
    private Usuario autor;
    @ManyToOne
    private Curso curso;
    @OneToMany(mappedBy = "topico",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Respuesta> respuestas;

    public Topico(DtoTopico dtoTopico){
        this.titulo = dtoTopico.titulo();
        this.mensaje = dtoTopico.mensaje();
        //this.autor = new Usuario(dtoTopico.autor());
        //this.curso = new Curso(dtoTopico.curso());
    }

    @Override
    public String toString(){
        return "Titulo: " + titulo +
                "Mensaje: " + mensaje ;
    }
}
