package jsp.pdi.api.controller;

import jakarta.validation.Valid;
import jsp.pdi.api.domain.funcionario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroFuncionario dados, UriComponentsBuilder uriBuilder) {

        var novoFuncionario = new Funcionario(dados);
        repository.save(novoFuncionario);

        var uri = uriBuilder.path("/funcionari/{id}").buildAndExpand(novoFuncionario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosConsultaFuncionario(novoFuncionario));
    }

    @GetMapping
    public ResponseEntity<Page<Funcionario>> listar(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var listarFuncionarios = repository.findAllByAtivoTrue(paginacao);

        return ResponseEntity.ok(listarFuncionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarUm(@PathVariable int id){

        var funcionario = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosConsultaFuncionario(funcionario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoFuncionario dados) {
        var funcionario = repository.getReferenceById(dados.id());
        funcionario.atualizarDados(dados);

        return ResponseEntity.ok(new DadosConsultaFuncionario(funcionario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable int id){
        var funcionario = repository.getReferenceById(id);
        funcionario.excluir();

        return ResponseEntity.noContent().build();
    }

}
