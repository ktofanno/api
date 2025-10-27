package br.senac.rj.api.service;

import br.senac.rj.api.exceptions.ResourceNotFoundException;
import br.senac.rj.api.model.Livro;
import br.senac.rj.api.repository.LivroRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            throw new ResourceNotFoundException("Não há livros cadastados");
        }
        return this.livroRepository.findAll();
    }

    public Livro incluirLivro(Livro livro) {
        return this.livroRepository.save(livro);
    }

    // remover o <Optional> => public Optional<Livro> buscarLivroPorCodigo(Long codigo) {
    public Livro buscarLivroPorCodigo(Long codigo) {
        String message = "Livro com codigo [" + codigo + "] não encontrado";
        return this.livroRepository.findById(codigo).orElseThrow(() -> new ResourceNotFoundException(message));
    }

    public void excluirLivro(Long codigo) {
        if (! this.livroRepository.existsById(codigo)) {
            throw new ResourceNotFoundException("Livro com codigo [" + codigo + "] não encontrado");
        }
        this.livroRepository.deleteById(codigo);
    }

    public Livro atualizarLivro(Long codigo, Livro livroAtualizado) {
        Optional<Livro> optionalLivro = this.livroRepository.findById(codigo);

        if (optionalLivro.isEmpty()) {
            throw new ResourceNotFoundException("Livro com codigo [" + codigo + "] não encontrado");
        } else {
            Livro livro = optionalLivro.get();
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setPreco(livroAtualizado.getPreco());

            return this.livroRepository.save(livro);
        }
    }

}
