package br.senac.rj.api.controller;

import br.senac.rj.api.exceptions.ResourceNotFoundException;
import br.senac.rj.api.model.Livro;
import br.senac.rj.api.service.LivroService;
import org.apache.coyote.Response;
import org.hibernate.annotations.SoftDelete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }
    @GetMapping("/livros")
    public List<Livro> listarLivros() {
        return this.livroService.listarLivros();
    }

    @PostMapping("/livros")
    public Livro incluirLivro(@RequestBody Livro livro) {
        return this.livroService.incluirLivro(livro);
    }

    @PutMapping("/livros/{codigo}")
    public Livro atualizarLivro(@PathVariable Long codigo, @RequestBody Livro livro){
        return this.livroService.atualizarLivro(codigo, livro);
    }


    @DeleteMapping("/livros/{codigo}")
    public void excluirLivro(@PathVariable Long codigo){
        livroService.excluirLivro(codigo);
    }

    @GetMapping("/livro/{codigo}")
        public ResponseEntity<?> buscarLivroPorCodigo(@PathVariable Long codigo){
            try {
                Livro livro = this.livroService.buscarLivroPorCodigo(codigo);
                return ResponseEntity.ok(livro);
            } catch (ResourceNotFoundException rnfe){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(rnfe.getMessage());
            }
        }
    }

