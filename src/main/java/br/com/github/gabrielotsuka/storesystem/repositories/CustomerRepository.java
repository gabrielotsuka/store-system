package br.com.github.gabrielotsuka.storesystem.repositories;

import br.com.github.gabrielotsuka.storesystem.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
