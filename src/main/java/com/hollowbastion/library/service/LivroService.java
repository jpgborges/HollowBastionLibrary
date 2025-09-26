package com.hollowbastion.library.service;

import com.hollowbastion.library.exceptions.RecursoNaoEncontradoException;
import com.hollowbastion.library.model.Livro;
import com.hollowbastion.library.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela lógica de negócio relacionada a livros.
 */
@Service
public class LivroService {

    private final LivroRepository livroRepository;

    /**
     * Construtor com injeção do repositório de livros.
     *
     * @param livroRepository repositório para acesso a dados de livros
     */
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    /**
     * Lista todos os livros cadastrados.
     *
     * @return lista de livros
     */
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    /**
     * Busca um livro pelo seu ID.
     *
     * @param id identificador do livro
     * @return livro encontrado
     * @throws RecursoNaoEncontradoException se o livro não existir
     */
    public Livro buscarLivroPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Livro com ID " + id + " não encontrado."));
    }

    /**
     * Adiciona um novo livro ao sistema.
     *
     * @param livro objeto livro a ser adicionado
     * @return livro salvo
     */
    public Livro adicionarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    /**
     * Remove um livro pelo seu ID.
     *
     * @param id identificador do livro
     * @throws RecursoNaoEncontradoException se o livro não existir
     */
    public void removerLivro(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Livro com ID " + id + " não encontrado.");
        }
        livroRepository.deleteById(id);
    }

    /**
     * Marca um livro como emprestado.
     *
     * @param id identificador do livro
     * @throws RecursoNaoEncontradoException se o livro não existir ou já estiver emprestado
     */
    public void emprestarLivro(Long id) {
        Livro livro = buscarLivroPorId(id);

        if (!livro.isDisponivel()) {
            throw new RecursoNaoEncontradoException("Livro com ID " + id + " já está emprestado.");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);
    }

    /**
     * Marca um livro como devolvido.
     *
     * @param id identificador do livro
     * @throws RecursoNaoEncontradoException se o livro não existir ou já estiver disponível
     */
    public void devolverLivro(Long id) {
        Livro livro = buscarLivroPorId(id);

        if (livro.isDisponivel()) {
            throw new RecursoNaoEncontradoException("Livro com ID " + id + " já está disponível.");
        }

        livro.setDisponivel(true);
        livroRepository.save(livro);
    }
}

