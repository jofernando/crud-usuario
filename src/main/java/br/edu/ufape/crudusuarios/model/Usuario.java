package br.edu.ufape.crudusuarios.model;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@MappedSuperclass
@SuperBuilder
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	@NotBlank
	private String login;
	@JsonIgnore
	private String senha;
	@JsonProperty(access = Access.READ_ONLY)
	private boolean gestao;
	@NotBlank
	private String nome;
	@NotBlank
	private String curso;
	@NotBlank
	private String celular;
	@NotBlank
	private String rg;
	@NotBlank
	private String orgaoExpedidorRg;
	@Past
	@NotNull
	private LocalDate emissaoRg;
	@NotBlank
	@CPF
	private String cpf;
	private String assinaturaPath;
	@NotNull
	private EstadoCivil estadoCivil;
	@NotBlank
	private String nacionalidade;
	@NotBlank
	private String naturalidade;
	@JsonProperty(access = Access.READ_ONLY)
	private boolean admin;
	@OneToOne(cascade = CascadeType.ALL)
	@NotNull
	private Endereco endereco;
}
