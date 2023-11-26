package curso.springboot.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.springboot.springboot.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	@Query("select p from Pessoa p where p.nome like %?1%")
	List<Pessoa> findPessoaByName(String nome);
}
