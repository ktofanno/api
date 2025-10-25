package br.senac.rj.api.controller;

import br.senac.rj.api.model.Livro;
import br.senac.rj.api.service.LivroService;
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
    public List<Livro> listarLivros() {
        return this.livroService.listarLivros();
    }

    @PostMapping("/livros")
    public Livro incluirLivro(@RequestBody Livro livro) {
        return this.livroService.incluirLivro(livro);
    }
}
