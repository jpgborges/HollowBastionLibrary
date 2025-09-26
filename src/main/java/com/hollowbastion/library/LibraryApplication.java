package com.hollowbastion.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 * Inicializa o contexto da aplicação e executa o servidor.
 */
@SpringBootApplication
public class LibraryApplication {

	/**
	 * Método principal que inicia a aplicação.
	 *
	 * @param args argumentos da linha de comando
	 */
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}
}

