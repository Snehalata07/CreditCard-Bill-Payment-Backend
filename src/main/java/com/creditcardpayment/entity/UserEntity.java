package com.creditcardpayment.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
	@Table(name="users")
	public class UserEntity {
		
		@Id
		@Column(name="user_id")
		private String userId;
		@Column(name="password", nullable=false)
		private String password;
		@Column(name="role",nullable=false)
		private String role;
		
		@OneToOne(cascade=CascadeType.ALL)
		@JoinColumn(name="user_id")
		private CustomerEntity user;
		
		

		public UserEntity(String userId2, String password2, String role2) {
			// TODO Auto-generated constructor stub
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

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		
		@Override
		public String toString() {
			return String.format("userId=%s, password=%s, role=%s", userId, password, role);
		}
		
		
	
}

