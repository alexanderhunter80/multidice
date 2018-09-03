package com.epoch.multidice.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")	
public class User {
	
	/*
	 * attributes and relationships
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	@Size(min=1, max=100)
	@NotBlank(message="Display name cannot be blank")
	private String username;
	
	@Email(message="Not a valid email address")
	@NotBlank(message="Email cannot be blank")
	private String email;
	
	@NotBlank(message="Password cannot be blank")
	@Size(min=8, max=255, message="Password must be between 8 and 255 characters.  You have plenty of space, use a real passphrase - see XKCD #936")
	private String password;
	@Transient
	private String passConfirm;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
	
	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<RollEvent> rollEvents;
	
	
	/*
	 * constructors
	 */
	
	public User(){}
	
	
	/*
	 * get & set
	 */


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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


	public String getPassConfirm() {
		return passConfirm;
	}


	public void setPassConfirm(String passConfirm) {
		this.passConfirm = passConfirm;
	}


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<RollEvent> getRollEvents() {
		return rollEvents;
	}

	public void setRollEvents(List<RollEvent> rollEvents) {
		this.rollEvents = rollEvents;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	

	
	
	
	
	/*
	 * other methods
	 */
	
	

}
