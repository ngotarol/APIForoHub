package challenge.APIForoHub.controller;

import challenge.APIForoHub.DTO.DtoTopico;
import challenge.APIForoHub.model.Topico;
import challenge.APIForoHub.repository.TopicoRepository;
import challenge.APIForoHub.service.ConvierteDatos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    private ConvierteDatos convierteDatos = new ConvierteDatos();

    @PostMapping
    public void RegistrarTopico(@RequestBody @Valid DtoTopico dtoTopico){
        System.out.println("el request llega");
        System.out.println(dtoTopico);
        Topico topico = new Topico(dtoTopico);
        System.out.println(topico);

        topicoRepository.save(topico);
    }
}
