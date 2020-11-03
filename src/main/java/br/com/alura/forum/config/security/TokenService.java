package br.com.alura.forum.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class TokenService {

	
	@Value("${forum.jwt.expiration}")
	private String expiration; 
	
	@Value("${forum.jwt.secret}")
	private String secret; 
	
	public String gerarToken(Authentication autentication) {
	
		Usuario logado = (Usuario) autentication.getPrincipal();
		Date hoje = new Date();
		
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		System.out.println("dataExpiracao: " + dataExpiracao);
		
		return Jwts.builder()
				.setIssuer("API do fórum da Alura")
				.setSubject(logado.getId().toString())
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

}