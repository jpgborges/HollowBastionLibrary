package com.hollowbastion.library.controller;


import com.hollowbastion.library.model.Usuario;
import com.hollowbastion.library.security.JwtUtil;
import com.hollowbastion.library.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * Controlador responsável pelas operações de autenticação.
 * Fornece endpoints para relacionados a autenticação.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    /**
     * Construtor para injeção do serviço de usuários.
     *
     * @param usuarioService serviço de gerenciamento de usuários
     */
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Registra um novo usuário no sistema.
     *
     * @param request mapa contendo os campos "username" e "password"
     * @return usuário criado em caso de sucesso
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        Usuario usuario = usuarioService.registrarUsuario(request.get("username"), request.get("password"));
        return ResponseEntity.ok(usuario);
    }

    /**
     * Realiza a autenticação de um usuário.
     * Verifica as credenciais e retorna um token JWT se forem válidas.
     *
     * @param request mapa contendo os campos "username" e "password"
     * @return token JWT em caso de sucesso,
     * ou mensagem de erro com status 401 se inválidas
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        Optional<Usuario> usuario = usuarioService.buscarPorUsername(request.get("username"));
        if (usuario.isPresent() && new BCryptPasswordEncoder().matches(request.get("password"), usuario.get().getPassword())) {
            String token = JwtUtil.generateToken(usuario.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}