package curso.springboot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.springboot.springboot.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
