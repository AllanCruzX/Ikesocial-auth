package com.ikesocial.auth.core;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ikesocial.auth.domain.Pessoa;

import lombok.Getter;

@Getter
public class AuthUser extends User {

	private static final long serialVersionUID = 1L;
	
	private String fullName;
	private String codigo;
	
	public AuthUser(Pessoa usuario , Collection<? extends GrantedAuthority> authorities) {
		
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		
		this.fullName = usuario.getNome();
		this.codigo = usuario.getCodigo();
	}
	
}
