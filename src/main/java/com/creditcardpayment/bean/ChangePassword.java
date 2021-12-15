package com.creditcardpayment.bean;

public class ChangePassword {
	private String userId;
	private String currentPassword;
	private String newPassword;
	private String confirmPassword;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	/**
	 * @param userId
	 * @param currentPassword
	 * @param ChangePassword
	 * @param confirmPassword
	 */
	
	public ChangePassword() {
		
	}
	public ChangePassword(String userId, String currentPassword, String newPassword, String confirmPassword) {
		super();
		this.userId = userId;
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return String.format("ChangePassword [userId=%s, currentPassword=%s, changePassword=%s, confirmPassword=%s]",
				userId, currentPassword, newPassword, confirmPassword);
	}
	
	

}
