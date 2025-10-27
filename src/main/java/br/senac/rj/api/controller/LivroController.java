package br.senac.rj.api.controller;

import br.senac.rj.api.exceptions.ResourceNotFoundException;
import br.senac.rj.api.model.Livro;
import br.senac.rj.api.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/livros")
    public ResponseEntity<?> listarLivros() {
        List<Livro> livros = this.livroService.listarLivros();
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/livros")
    public ResponseEntity<Livro> incluirLivro(@RequestBody Livro livro) {
        Livro livroCriado = this.livroService.incluirLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
    }

    @GetMapping("/livros/{codigo}")
    public ResponseEntity<?> buscarLivro(@PathVariable Long codigo) {
        Livro livro = this.livroService.buscarLivroPorCodigo(codigo);
        return ResponseEntity.ok(livro);
    }

    @DeleteMapping("/livros/{codigo}")
    public ResponseEntity<?> excluirLivro(@PathVariable Long codigo) {
        this.livroService.excluirLivro(codigo);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/livros/{codigo}")
    public ResponseEntity<?> excluirLivro(@PathVariable Long codigo, @RequestBody Livro livro) {
        Livro livroAtualizado = this.livroService.atualizarLivro(codigo, livro);
        return ResponseEntity.ok(livroAtualizado);
    }
}
