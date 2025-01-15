package challenge.APIForoHub.controller;

import challenge.APIForoHub.DTO.DtoActualizarTopico;
import challenge.APIForoHub.DTO.DtoListadoTopicos;
import challenge.APIForoHub.DTO.DtoTopico;
import challenge.APIForoHub.model.Topico;
import challenge.APIForoHub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


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
    public ResponseEntity<Page<DtoListadoTopicos>> listadoTopicos(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC) Pageable paginacion){
        return ResponseEntity.ok(topicoService.ListarTodosLosTopicos(paginacion));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DtoListadoTopicos> DetalleTopico(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoService.EncontrarTopicoPorId(id);
        if (topicoOptional.isPresent()) {
            return ResponseEntity.ok(new DtoListadoTopicos(topicoOptional.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> BorrarTopico(
            @PathVariable Long id){
        Optional<Topico> topicoOptional = topicoService.EncontrarTopicoPorId(id);
        if (topicoOptional.isPresent()) {
            topicoService.EliminarTopico(topicoOptional.get().getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DtoListadoTopicos> ActualizarTopico(
            @RequestBody @Valid DtoActualizarTopico nuevosDatos,
            @PathVariable Long id){
        Optional<Topico> topicoOptional = topicoService.EncontrarTopicoPorId(id);
        if (topicoOptional.isPresent()) {
            Topico topicoActualizado = topicoService.ActualizarTopico(topicoOptional.get().getId(),nuevosDatos);
            return ResponseEntity.ok(new DtoListadoTopicos(topicoActualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<DtoListadoTopicos> RegistrarTopico(
            @RequestBody @Valid DtoTopico dtoTopico,
            UriComponentsBuilder uriComponentsBuilder){
        if (topicoService.ValidarTopicoExiste(dtoTopico)){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
        }
        else {
            topico = topicoService.crearTopico(dtoTopico);
            URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(new DtoListadoTopicos(topico));
        }
    }



}
