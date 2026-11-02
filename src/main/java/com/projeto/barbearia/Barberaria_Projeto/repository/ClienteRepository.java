package com.projeto.barbearia.Barberaria_Projeto.repository;

import com.projeto.barbearia.Barberaria_Projeto.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

    // Autenticação
    Optional<Cliente> findByEmailAndSenha (String email, String senha);

    // Busca por nome (case-insensitive)
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    // Relatório: total de clientes cadastrados
    @Query("SELECT COUNT(c) FROM Cliente c")
    Long countTotalClientes();

    // Relatório: clientes cadastrados no dia atual
    @Query("SELECT COUNT(c) FROM Cliente c WHERE DATE(c.dataCadastro) = CURRENT_DATE")
    Long countClientesHoje();

    // Relatório: novos clientes em uma semana
    @Query("SELECT COUNT(c) FROM Cliente c WHERE c.dataCadastro >= CURRENT_DATE - 7 day")
    Long countClientesUltimaSemana();
}