package curso.springboot.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.springboot.springboot.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
