/**
 * login을 처리하기 위한 별도의 클래스
 */
package com.springmvc4maven.domain.users;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author TrueLiteTrace
 *
 */
public class Authenticate {

	@NotEmpty
	@Size(min = 4, max = 12)
	private String userId;

	@NotEmpty
	@Size(min = 4, max = 12)
	private String password;
	
	// Default Constructor
	public Authenticate() {
		
	}
	
	/**
	 * @param userId
	 * @param password
	 */
	public Authenticate(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authenticate other = (Authenticate) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Authenticate [userId=" + userId + ", password=" + password + "]";
	}
	
	// User.java matchPassword method refactoring 후 생성
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
	
}
