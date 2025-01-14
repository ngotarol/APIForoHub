package challenge.APIForoHub.model;

import challenge.APIForoHub.DTO.DtoActualizarTopico;
import challenge.APIForoHub.DTO.DtoTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
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

    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    private Boolean estado;

    public Topico() {
    }

    public Topico (DtoTopico dtoTopico, Usuario autor, Curso curso){
        this.titulo = dtoTopico.titulo();
        this.mensaje = dtoTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.autor = autor;
        this.curso = curso;
        this.estado = dtoTopico.estado();
    }

    public void ActualizarTopico(DtoActualizarTopico dtoTopico, Usuario autor, Curso curso){
        this.titulo = dtoTopico.titulo();
        this.mensaje = dtoTopico.mensaje();
        this.autor = autor;
        this.curso = curso;
        this.estado = dtoTopico.estado();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString(){
        return "Titulo: " + titulo +
                "Mensaje: " + mensaje +
                "Autor: " + autor +
                "Curso: " + curso +
                "Fecha de creacion: " + fechaCreacion;
    }
}
