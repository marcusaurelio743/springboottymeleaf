package curso.springboot.springboot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.springboot.model.Pessoa;
import curso.springboot.springboot.model.Telefone;
import curso.springboot.springboot.repository.PessoaRepository;
import curso.springboot.springboot.repository.TelefoneRepository;

@Controller
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	@Autowired
	private TelefoneRepository telefoneRepository;
	
	
	@GetMapping(value = "/cadastropessoa")
	public ModelAndView inicio() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		
		andView.addObject("pessoaobj", new Pessoa());
		andView.addObject("pessoas", repository.findAll());
		return andView;
	}
	
	@PostMapping(value = "/salvarpessoa")
	public ModelAndView salvar(@Valid Pessoa pessoa,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
			andView.addObject("pessoas", repository.findAll());
			andView.addObject("pessoaobj", pessoa);
			
			List<String> msg = new ArrayList<String>();
			
			for(ObjectError objectError :bindingResult.getAllErrors()) {
				msg.add(objectError.getDefaultMessage());//mensgagem vem das anotações de validações
				
			}
			andView.addObject("msg", msg);
			return andView;
		}
		
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
		andView.addObject("pessoas", repository.findAll());
		return andView;
	}
	
	@GetMapping(value = "/removerpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa")Long idpessoa) {
		
		 repository.deleteById(idpessoa);

		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoaobj", new Pessoa());
		andView.addObject("pessoas", repository.findAll());
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
	@PostMapping(value = "/pesquisarpessoa")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa) {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		
		andView.addObject("pessoas", repository.findPessoaByName(nomepesquisa));
		andView.addObject("pessoaobj", new Pessoa());
		
		return andView;
	}
	
	@GetMapping(value = "/telefones/{idpessoa}")
	public ModelAndView telefones(@PathVariable("idpessoa")Long id) {
		
		Optional<Pessoa> pessoa = repository.findById(id);
		
		ModelAndView andView = new ModelAndView("cadastro/telefones");
		andView.addObject("pessoaobj", pessoa.get());
		andView.addObject("telefones", telefoneRepository.getTelefones(id));
		return andView;
	}
	
	@PostMapping("/adicionarPessoa/{pessoaid}")
	public ModelAndView addFonePessoa( Telefone telefone, @PathVariable("pessoaid") Long pessoaid) {
		
		Pessoa pessoa = repository.findById(pessoaid).get();
		
		if(telefone != null
				&& (telefone.getNumero() != null && telefone.getNumero().isEmpty()) ||
				telefone.getNumero() == null) {
			
			ModelAndView andView = new ModelAndView("cadastro/telefones");
			andView.addObject("pessoaobj", pessoa);
			andView.addObject("telefones", telefoneRepository.getTelefones(pessoaid));
			
			List<String> msg = new ArrayList<String>();
			msg.add("Numero deve ser Informado!!!");
			andView.addObject("msg", msg);
			return andView;
			
			
		}
		
		telefone.setPessoa(pessoa);
		
		ModelAndView andView = new ModelAndView("cadastro/telefones");
		telefoneRepository.save(telefone);
		andView.addObject("pessoaobj", pessoa);
		andView.addObject("telefones", telefoneRepository.getTelefones(pessoaid));
		
		return andView;
	}
	
	@GetMapping(value = "/removerTelefone/{idtelefone}")
	public ModelAndView excluirTelefone(@PathVariable("idtelefone")Long idtelefone) {
		
		Pessoa pessoa = telefoneRepository.findById(idtelefone).get().getPessoa();
				
		telefoneRepository.deleteById(idtelefone); 
		
		ModelAndView andView = new ModelAndView("cadastro/telefones");
		andView.addObject("pessoaobj", pessoa);
		andView.addObject("telefones", telefoneRepository.getTelefones(pessoa.getId()));
		
		return andView;
	}

	
	
}
