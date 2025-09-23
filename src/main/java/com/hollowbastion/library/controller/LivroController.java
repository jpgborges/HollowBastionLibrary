package com.hollowbastion.library.controller;

import com.hollowbastion.library.model.Livro;
import com.hollowbastion.library.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarLivroPorId(@PathVariable Long id) {
        Livro livro = livroService.buscarLivroPorId(id);
        return ResponseEntity.ok(livro);
    }

    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroService.adicionarLivro(livro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerLivro(@PathVariable Long id) {
        livroService.removerLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/emprestar/{id}")
    public ResponseEntity<?> emprestarLivro(@PathVariable Long id) {
        livroService.emprestarLivro(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/devolver/{id}")
    public ResponseEntity<?> devolverLivro(@PathVariable Long id) {
        livroService.devolverLivro(id);
        return ResponseEntity.noContent().build();
    }
}
