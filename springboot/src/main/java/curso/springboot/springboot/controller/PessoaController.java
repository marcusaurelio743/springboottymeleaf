package curso.springboot.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.springboot.model.Pessoa;
import curso.springboot.springboot.repository.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	
	@GetMapping(value = "/cadastropessoa")
	public ModelAndView inicio() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	
	@PostMapping(value = "*/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {
		repository.save(pessoa);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		//busca no banco todad as pessoas
		Iterable<Pessoa> pessoasIt = repository.findAll();
		
		//relaciona no objeto modelAndView o atibuto pessoas com a lista de pessoas 
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}
	
	@GetMapping(value = "/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa")Long id) {
		
		Optional<Pessoa> pessoa = repository.findById(id);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoaobj", pessoa.get());
		return andView;
	}
	@GetMapping(value = "/listapessoas")
	public ModelAndView pessoas() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		//busca no banco todad as pessoas
		Iterable<Pessoa> pessoasIt = repository.findAll();
		
		//relaciona no objeto modelAndView o atibuto pessoas com a lista de pessoas 
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa());
		
		return andView;
		
	}

}
