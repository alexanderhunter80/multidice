package com.epoch.multidice.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="rollevents")
public class RollEvent {
	
    @Id
    @GeneratedValue
    private Long id;
    
    @NotBlank
    private String inputString;
    
    private ArrayList<String> rawResults;
    private ArrayList<String> finalResults;
    private ArrayList<String> output;
    
    // many-to-one with User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
    
	@Column(updatable=false)
	private Date createdAt;
	
	// constructors
	
    public RollEvent() {}

    // get & set

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	public ArrayList<String> getRawResults() {
		return rawResults;
	}

	public void setRawResults(ArrayList<String> rawResults) {
		this.rawResults = rawResults;
	}

	public ArrayList<String> getFinalResults() {
		return finalResults;
	}

	public void setFinalResults(ArrayList<String> finalResults) {
		this.finalResults = finalResults;
	}

	public ArrayList<String> getOutput() {
		return output;
	}

	public void setOutput(ArrayList<String> output) {
		this.output = output;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	
	// other methods
	
	public void report() {
		System.out.println("RollEvent "+id+" reports:");
		try {
			System.out.println("User: "+user.getUsername()+"#"+user.getId().toString());
		} catch (NullPointerException e) {
			System.out.println("User: null");
		}
		System.out.println("Datetime: "+createdAt.toString());
		System.out.println("Raw results: "+rawResults.toString());
		System.out.println("Final results: "+finalResults.toString());
	}

}
