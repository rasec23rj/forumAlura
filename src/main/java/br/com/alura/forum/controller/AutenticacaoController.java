package br.com.alura.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.config.security.TokenService;
import br.com.alura.forum.controller.form.LoginForm;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;

	private TokenService tokenService;
	
	
	@PostMapping
	public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {

		try {
			UsernamePasswordAuthenticationToken dadosLogin = form.converter();
			
			Authentication autentication = authenticationManager.authenticate(dadosLogin);
			System.out.println("autentication: " + autentication);
			String token = tokenService.gerarToken(autentication);
			
			System.out.println("gerarToken: " + token);
			return ResponseEntity.ok().build();

		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}

}
