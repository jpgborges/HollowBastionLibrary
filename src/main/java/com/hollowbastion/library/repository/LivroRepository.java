package com.hollowbastion.library.repository;

import com.hollowbastion.library.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável pelo acesso a dados da entidade Livro.
 * Fornece operações CRUD básicas através do JpaRepository.
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
