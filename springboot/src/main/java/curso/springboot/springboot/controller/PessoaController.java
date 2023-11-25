package curso.springboot.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.springboot.model.Pessoa;
import curso.springboot.springboot.repository.PessoaRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	
	@GetMapping(value = "/cadastropessoa")
	public String inicio() {
		return "cadastro/cadastropessoa";
	}
	
	@PostMapping(value = "/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {
		repository.save(pessoa);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		//busca no banco todad as pessoas
		Iterable<Pessoa> pessoasIt = repository.findAll();
		
		//relaciona no objeto modelAndView o atibuto pessoas com a lista de pessoas 
		andView.addObject("pessoas", pessoasIt);
		
		return andView;
	}
	
	@GetMapping(value = "/listapessoas")
	public ModelAndView pessoas() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		//busca no banco todad as pessoas
		Iterable<Pessoa> pessoasIt = repository.findAll();
		
		//relaciona no objeto modelAndView o atibuto pessoas com a lista de pessoas 
		andView.addObject("pessoas", pessoasIt);
		
		return andView;
		
	}

}
