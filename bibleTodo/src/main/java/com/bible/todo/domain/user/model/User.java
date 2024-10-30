package com.bible.todo.domain.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long uno;
		
		private String userid;
		
		private String userpw;
		
		private String name;
		
		private String department; 
}
