package com.sean.taller.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class UserEntity {

	@Id
	private long id;
	
	@NotBlank
	private String username;
	
	private String password;
	
	private UserType type;
}