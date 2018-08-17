package com.epoch.multidice.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class RollEvent {
	
    @Id
    @GeneratedValue
    private Long id;
    
    // many-to-one with User
    
    
    
    
    
    
    public RollEvent() {}

}
