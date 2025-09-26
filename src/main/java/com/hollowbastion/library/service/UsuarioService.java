package com.hollowbastion.library.service;

import com.hollowbastion.library.model.Usuario;
import com.hollowbastion.library.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio relacionada a usuários.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Construtor com injeção do repositório de usuários.
     * Inicializa o encoder de senhas BCrypt.
     *
     * @param usuarioRepository repositório para acesso a dados de usuários
     */
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registra um novo usuário com senha criptografada.
     *
     * @param username nome de usuário
     * @param password senha do usuário
     * @return usuário registrado
     */
    public Usuario registrarUsuario(String username, String password){
        String senhaCriptografada = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username, senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    /**
     * Busca um usuário pelo username.
     *
     * @param username nome de usuário
     * @return Optional contendo o usuário, se encontrado
     */
    public Optional<Usuario> buscarPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }
}

