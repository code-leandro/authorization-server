package com.leandrosouza.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

	private String username;
	private String password;
}
