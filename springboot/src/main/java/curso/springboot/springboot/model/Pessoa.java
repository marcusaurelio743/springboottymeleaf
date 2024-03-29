package curso.springboot.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "nome não pode ser nulo")
	@NotEmpty(message = "nome não pode ser vazio")
	private String nome;
	
	@NotEmpty(message = "sobrenome não pode ser vazio")
	@NotNull(message = "sobrenome não pode ser nulo")
	private String sobrenome;
	private Integer idade;
	
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy = "pessoa" )
	private List<Telefone> telefones;
	
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public Integer getIdade() {
		return idade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

}
