package com.br.github.gabrielotsuka.storesystem.repositories;

import com.br.github.gabrielotsuka.storesystem.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
