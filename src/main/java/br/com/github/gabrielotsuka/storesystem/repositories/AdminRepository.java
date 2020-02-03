package br.com.github.gabrielotsuka.storesystem.repositories;

import br.com.github.gabrielotsuka.storesystem.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
