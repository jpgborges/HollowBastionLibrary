package com.hollowbastion.library.controller;

import com.hollowbastion.library.model.Livro;
import com.hollowbastion.library.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pelo gerenciamento de livros.
 * Fornece endpoints para relacionados a livros.
 */
@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    /**
     * Construtor para injeção do serviço de livros.
     *
     * @param livroService serviço que contém a lógica de manipulação de livros
     */
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    /**
     * Lista todos os livros cadastrados.
     *
     * @return lista de livros
     */
    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    /**
     * Busca um livro pelo ID.
     *
     * @param id identificador do livro
     * @return livro encontrado ou erro caso não exista
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarLivroPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarLivroPorId(id);
        return ResponseEntity.ok(livro);
    }

    /**
     * Adiciona um novo livro ao sistema.
     *
     * @param livro objeto livro a ser adicionado
     * @return livro salvo
     */
    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroService.adicionarLivro(livro);
    }

    /**
     * Remove um livro pelo ID.
     *
     * @param id identificador do livro
     * @return resposta sem conteúdo em caso de sucesso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Marca um livro como emprestado.
     *
     * @param id identificador do livro
     * @return resposta sem conteúdo em caso de sucesso
     */
    @PostMapping("/emprestar/{id}")
    public ResponseEntity<?> emprestarLivro(@PathVariable Long id) {
        livroService.emprestarLivro(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Marca um livro como devolvido.
     *
     * @param id identificador do livro
     * @return resposta sem conteúdo em caso de sucesso
     */
    @PostMapping("/devolver/{id}")
    public ResponseEntity<?> devolverLivro(@PathVariable Long id) {
        livroService.devolverLivro(id);
        return ResponseEntity.noContent().build();
    }
}
