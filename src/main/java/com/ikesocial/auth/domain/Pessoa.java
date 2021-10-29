package com.ikesocial.auth.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true , callSuper=false)
@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends AbstractAggregateRoot<Pessoa> implements Serializable  {
	private static final long serialVersionUID = 1L;

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo", length = 35, nullable = false)
	private String codigo;

	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	
	@Column(name = "senha", length = 255, nullable = false)
	private String senha;

	@OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY,
			   cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Contato> contatos = new HashSet<Contato>();
	
	@ManyToMany
	@JoinTable(name = "pessoa_grupo", 
		joinColumns = @JoinColumn(name = "pessoa_id",
				foreignKey = @ForeignKey(name = "fk_pessoa_grupo")),
			inverseJoinColumns = @JoinColumn(name = "grupo_id",
				foreignKey = @ForeignKey(name = "fk_grupo_pessoa")))
	private Set<Grupo> grupos = new HashSet<Grupo>();
	
	public String getEmail () {
		  Optional<Contato> email = contatos.stream()
					.filter(c -> c.getTipoContato().equals(TipoContato.EMAIL))
					.findFirst();
		  
		  return email.get().getDescricao();
	}

}
