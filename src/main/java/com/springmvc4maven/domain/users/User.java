/**
 * 
 */
package com.springmvc4maven.domain.users;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author EUNSOO
 *
 */
public class User {
	/*
	@Size @Min(4) @Max(12)
	private String userId;
	@NotNull @Min(4) @Max(12)
	private String password;
	@NotNull
	private String name;
	*/
	// 사용자가 값을 전달할 때 빈문자가 전달되지 않도록 하기 위해 
	@NotEmpty(message = "Please enter your userId.")
    @Size(min = 6, max = 12, message = "Your userId must between 6 and 12 characters")
	private String userId;
	
	@NotEmpty(message = "Please enter your userId.")
    @Size(min = 6, max = 12, message = "Your userId must between 6 and 12 characters")
	private String password;
	@NotEmpty
	private String name;
	
	@Email
	private String email;

	// Default Constructor
	public User() {

	}

	public User(String userId, String password, String name, String email) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean matchPassword(Authenticate authenticate) {
		if(this.password == null) {
			return false;
		}
		// refactoring 전: return this.password.equals(authenticate.getPassword());
		// refactoring 후
		return authenticate.matchPassword(this.password);
	}
	
	public boolean matchUserId(String inputUserId) {
		if(inputUserId == null) {
			return false;
		}
		return inputUserId.equals(this.userId);
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
}
