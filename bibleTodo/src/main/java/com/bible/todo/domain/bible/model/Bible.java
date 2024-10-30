package com.bible.todo.domain.bible.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bible {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int testamentid;
	
	private String name;
	
	private String chapter;
	
	private String verse;
	
	private String content;
	
	
}
