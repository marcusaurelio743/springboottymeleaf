package curso.springboot.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.springboot.springboot.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
	
	@Query("select t from Telefone t where t.pessoa.id = ?1")
	public List<Telefone> getTelefones(Long pessoaId);
		
		
	

}
