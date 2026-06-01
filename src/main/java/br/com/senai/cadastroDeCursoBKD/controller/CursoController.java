package br.com.senai.cadastroDeCursoBKD.controller;

import br.com.senai.cadastroDeCursoBKD.cursos.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;


    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> listarCursos(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {

        var cursos = repository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemCurso::new);

        return ResponseEntity.ok(cursos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoCurso> detalharCurso(@PathVariable Long id) {
        var curso = repository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Curso não existe"
                ));

        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }


    @GetMapping("/periodos")
    public ResponseEntity<List<Curso.Periodo>> listarPeriodos() {
        return ResponseEntity.ok(Arrays.asList(Curso.Periodo.values()));
    }


    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> cadastrarCurso(
            @RequestBody @Valid DadosCadastroCurso dados) {

        var curso = new Curso(dados);
        repository.save(curso);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new DadosDetalhamentoCurso(curso));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoCurso> atualizarCurso(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizarCurso dados) {

        var curso = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Curso não encontrado"
                ));

        curso.atualizarCurso(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id) {
        var curso = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Curso não encontrado"
                ));

        curso.excluirCurso();

        return ResponseEntity.noContent().build();
    }
}