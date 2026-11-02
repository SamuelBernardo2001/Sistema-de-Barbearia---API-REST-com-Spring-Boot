package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.Cargo;
import com.projeto.barbearia.Barberaria_Projeto.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {

    // Autenticação
    Optional<Funcionario> findByEmailAndSenha(String email, String senha);

    // Busca Por Cargo
    List<Funcionario> findByCargo (Cargo cargo);

    //Validações
    Boolean existsByEmail(String email);
    Boolean existsBySenha(String senha);

    // FUncionarios ativos
    List<Funcionario> findByAtivoTrue();

    // Query customizada com ativo
    @Query("SELECT f FROM Funcionario f WHERE f.email = :email AND f.senha = :senha AND f.ativo = true")
    Optional<Funcionario> autenticar(@Param("email") String email, @Param("senha") String senha);

}
