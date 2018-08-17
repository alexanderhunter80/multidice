package com.epoch.multidice.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.epoch.multidice.tools.Handful;

@Entity
@Table(name="roles")
public class RollEvent {
	
    @Id
    @GeneratedValue
    private Long id;
    private ArrayList<Handful> handfuls;
    private Integer result;
    
    // many-to-one with User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
    
    
    
    
    
    
    public RollEvent() {}

}
