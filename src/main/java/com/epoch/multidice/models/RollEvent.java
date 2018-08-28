package com.epoch.multidice.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.epoch.multidice.tools.Handful;

@Entity
@Table(name="rollevents")
public class RollEvent {
	
    @Id
    @GeneratedValue
    private Long id;
    private ArrayList<Handful> handfuls;
    private Integer result;
    
    @Transient
    @NotBlank
    private String inputstring;
    
    // many-to-one with User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
    
	
	// constructors
	
    public RollEvent() {}

    // get & set

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public ArrayList<Handful> getHandfuls() {
		return handfuls;
	}


	public void setHandfuls(ArrayList<Handful> handfuls) {
		this.handfuls = handfuls;
	}


	public Integer getResult() {
		return result;
	}


	public void setResult(Integer result) {
		this.result = result;
	}


	public String getInputstring() {
		return inputstring;
	}


	public void setInputstring(String inputstring) {
		this.inputstring = inputstring;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
   

}
