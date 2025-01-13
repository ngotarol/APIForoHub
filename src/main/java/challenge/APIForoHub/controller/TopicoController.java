package challenge.APIForoHub.controller;

import challenge.APIForoHub.DTO.DtoTopico;
import challenge.APIForoHub.model.Topico;
import challenge.APIForoHub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    private final TopicoService topicoService;
    private Topico topico;

    @Autowired
    public TopicoController(TopicoService topicoService) {
        this.topicoService = topicoService;
    }

    @GetMapping
    public List<Topico> listadoTopicos(){
        var lista = topicoService.ListarTodosLosTopicos();
        System.out.println(lista);
        return lista;
    }

    @PostMapping
    public ResponseEntity<Topico> RegistrarTopico(@RequestBody @Valid DtoTopico dtoTopico){
        System.out.println("el request llega");
        System.out.println(dtoTopico);

        if (topicoService.ValidarTopicoExiste(dtoTopico)){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
        else {
            topico = topicoService.crearTopico(dtoTopico);
            return ResponseEntity.status(HttpStatus.CREATED).body(topico);
        }
    }


}
