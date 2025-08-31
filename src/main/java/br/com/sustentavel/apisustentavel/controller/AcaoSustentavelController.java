package br.com.sustentavel.apisustentavel.controller;

import br.com.sustentavel.apisustentavel.dtos.AcaoSustentavelRequest;
import br.com.sustentavel.apisustentavel.dtos.AcaoSustentavelResponse;
import br.com.sustentavel.apisustentavel.model.AcaoSustentavel;
import br.com.sustentavel.apisustentavel.model.CategoriaAcao;
import br.com.sustentavel.apisustentavel.exception.RecursoNaoEncontradoException;
import br.com.sustentavel.apisustentavel.repository.AcaoSustentavelRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/acoes")
public class AcaoSustentavelController {

    private final AcaoSustentavelRepository repository;

    public AcaoSustentavelController(AcaoSustentavelRepository repository) {
        this.repository = repository;
    }

    // Get / acoes -> Listar todas
    @GetMapping
    public List<AcaoSustentavelResponse> listarTodas() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Get / acoes / id -> buscar por ID

    @GetMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> buscarPorId(@PathVariable Long id) {
        AcaoSustentavel acao = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ação com ID " + id + " não encontrada"));
        return ResponseEntity.ok(toResponse(acao));
    }


    //Post / acoes -> cadastrar nova ação
    @PostMapping
    public ResponseEntity<AcaoSustentavelResponse> criar(@RequestBody @Valid AcaoSustentavelRequest request) {
        AcaoSustentavel acao = toEntity(request);
        AcaoSustentavel salva = repository.save(acao);
        return ResponseEntity.ok(toResponse(salva));

    }

    // pUT / acoes/{id} -> atualizar

    @PutMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> atualizar(@PathVariable Long id, @RequestBody @Valid AcaoSustentavelRequest request) {
        return repository.findById(id)
                .map(acao -> {
                    acao.setTitulo(request.getTitulo());
                    acao.setDescricao(request.getDescricao());
                    acao.setCategoria(request.getCategoria());
                    acao.setDataRealizacao(request.getDataRealizacao());
                    acao.setResponsavel(request.getResponsavel());
                    AcaoSustentavel atualizada = repository.save(acao);
                    return ResponseEntity.ok(toResponse(atualizada));
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ação com ID " + id + " não encontrada"));
    }

    //Delete / acoes/{id} -> excluir


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        return repository.findById(id)
                .map(acao -> {
                    repository.delete(acao);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ação com ID " + id + " não encontrada"));
    }


    // Método para conversão
    private AcaoSustentavelResponse toResponse(AcaoSustentavel acao) {
        return new AcaoSustentavelResponse(
                acao.getId(),
                acao.getTitulo(),
                acao.getDescricao(),
                acao.getCategoria(),
                acao.getDataRealizacao(),
                acao.getResponsavel()
        );
    }

    private AcaoSustentavel toEntity(AcaoSustentavelRequest request) {
        AcaoSustentavel acao = new AcaoSustentavel();
        acao.setTitulo(request.getTitulo());
        acao.setDescricao(request.getDescricao());
        acao.setCategoria(request.getCategoria());
        acao.setDataRealizacao(request.getDataRealizacao());
        acao.setResponsavel(request.getResponsavel());
        return acao;
    }


    @GetMapping("/categoria")
    public ResponseEntity<List<AcaoSustentavelResponse>> filtrarPorCategoria(@RequestParam CategoriaAcao tipo) {
        List<AcaoSustentavel> acoes = repository.findByCategoria(tipo);
        List<AcaoSustentavelResponse> resposta = acoes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resposta);
    }


}
