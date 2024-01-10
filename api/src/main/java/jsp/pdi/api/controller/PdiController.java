package jsp.pdi.api.controller;

import jakarta.validation.Valid;
import jsp.pdi.api.domain.pdi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pdi")
public class PdiController {

    @Autowired
    private PdiRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPdi dados, UriComponentsBuilder uriBuilder) {

        var novaPdi = new Pdi(dados);
        repository.save(novaPdi);

        var uri = uriBuilder.path("/pdi/{id}").buildAndExpand(novaPdi.getPdi_id()).toUri();

        return ResponseEntity.created(uri).body(new DadosConsultaPdi(novaPdi));

    }

    @GetMapping
    public ResponseEntity<Page<Pdi>> listar(@PageableDefault(sort = {"funcionario.nome"}) Pageable paginacao){
        var listarPdis = repository.findAll(paginacao);

        return ResponseEntity.ok(listarPdis);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUm(@PathVariable int id){
        var pdi = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosConsultaPdi(pdi));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizcaoPdi dados) {
        var pdi = repository.getReferenceById(dados.pdi_id());
        pdi.atualizarDados(dados);

        return ResponseEntity.ok(new DadosConsultaPdi(pdi));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable int id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

}
