package br.senac.rj.api.controller;

import br.senac.rj.api.exceptions.ResourceNotFoundException;
import br.senac.rj.api.model.Livro;
import br.senac.rj.api.service.LivroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
        try {
            List<Livro> livros = this.livroService.listarLivros();
            return ResponseEntity.ok(livros);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @PostMapping("/livros")
    public ResponseEntity<Livro> incluirLivro(@RequestBody Livro livro) {
        Livro livroCriado = this.livroService.incluirLivro(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
    }

    @GetMapping("/livros/{codigo}")
    public ResponseEntity<?> buscarLivro(@PathVariable Long codigo) {
        try {
            Livro livro = this.livroService.buscarLivroPorCodigo(codigo);
            return ResponseEntity.ok(livro);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }
    }

    @DeleteMapping("/livros/{codigo}")
    public ResponseEntity<?> excluirLivro(@PathVariable Long codigo) {
        try {
            this.livroService.excluirLivro(codigo);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }

    }

    @PutMapping("/livros/{codigo}")
    public ResponseEntity<?> excluirLivro(@PathVariable Long codigo, @RequestBody Livro livro) {
        try {
            Livro livroAtualizado = this.livroService.atualizarLivro(codigo, livro);
            return ResponseEntity.ok(livroAtualizado);
        } catch (ResourceNotFoundException rnfe) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rnfe.getMessage());
        }

    }
}
