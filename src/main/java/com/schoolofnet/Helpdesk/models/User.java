package com.schoolofnet.Helpdesk.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@NotEmpty(message = "Não pode ser vazio")
	private String name;
	@Column
	@NotEmpty(message = "Não pode ser vazio")
	private String lastname;
	@Column
	@Email(message = "Por favor, forneça um email valido")
	@NotEmpty(message = "Não pode ser vazio")
	private String email;


	@Column
	@NotEmpty(message = "Não pode ser vazio")
	@Length(min = 5, message = "você precisa fornecer uma senha que contenha no mínimo 5 caracteres")
	private String password;
	@Column
	private Boolean active = true;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
			inverseJoinColumns = @JoinColumn(name = "role_id"),
			joinColumns = @JoinColumn(name = "user_id"))
	//JoinTable vai ser criado uma nova tabela com o relacionamento estrangeiro
	private Set<Role> roles;


	public User() {
	}

	public User(String name, String lastname, String email, String password, Boolean active) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.active = active;
	}

	public User(Long id, String name, String lastname, String email, String password, Boolean active) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
