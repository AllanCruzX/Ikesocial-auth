package com.ikesocial.auth.domain;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public enum TipoContato {

	TELEFONE(0L, "Telefone"),
	CELULAR(1L, "Celular"),
	EMAIL(2L, "E-mail"),
	FAX(3L, "Fax"),
	INSTAGRAN(4L, "Instagran"),
	LINKEDIN(5L, "Linkedin"),
	FACEBOOK(6L, "Facebook"),
	YOUTUBE(7L, "Youtube"),
	SITE(8L, "Site");

	private Long id;
	private String nome;

	private TipoContato(Long id, String nome) {

		this.id = id;
		this.nome = nome;

	}

	public static TipoContato getById(Long id) {

		if (id != null) {
			for (TipoContato tipo : TipoContato.values()) {
				if (tipo.id.equals(id)) {
					return tipo;
				}
			}

			throw new IllegalArgumentException("Não existe um tipo contato com o código " + id);
		}
		return null;
	}

	public static List<TipoContato> valores() {
		return Arrays.asList(values());
	}

}