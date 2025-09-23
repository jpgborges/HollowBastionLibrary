package com.hollowbastion.library.service;

import com.hollowbastion.library.exceptions.RecursoNaoEncontradoException;
import com.hollowbastion.library.model.Livro;
import com.hollowbastion.library.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro com ID " + id + " não encontrado."));
    }

    public Livro adicionarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public void removerLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Livro com ID " + id + " não encontrado.");
        }
        livroRepository.deleteById(id);
    }

    public void emprestarLivro(Long id) {
        Livro livro = buscarLivroPorId(id);

        if (!buscarLivroPorId(id).isDisponivel()) {
            throw new RecursoNaoEncontradoException("Livro com ID " + id + " já está emprestado.");
        }

        livro.setDisponivel(false); // atualiza o campo
        livroRepository.save(livro); // salva no banco
    }

    public void devolverLivro(Long id) {
        Livro livro = buscarLivroPorId(id);

        if (livro.isDisponivel()) {
            throw new RecursoNaoEncontradoException("Livro com ID " + id + " já está disponível.");
        }

        livro.setDisponivel(true);
        livroRepository.save(livro);
    }
}
