package br.com.claudemirojr.ponto.inteligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "razao_social", nullable = false)
	private String razaoSocial;
	
	@Column(nullable = false)
	private String cnpj;

	@Column(name = "data_criacao", nullable = false)	
	private Date dataCriacao;
	
	@Column(name = "data_atualizacao", nullable = false)	
	private Date dataAtualizacao;
	
	
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Funcionario> funcionarios;
	
	
	
	//----------------
	@PreUpdate
	public void preUpdate() {
		this.dataAtualizacao = new Date();
	}
	
	
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		
		this.dataCriacao = atual;
		this.dataAtualizacao = atual;
	}

}
