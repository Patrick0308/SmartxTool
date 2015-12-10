package cn.smartx.tools.build.entity;

import java.io.Serializable;

public class User implements Serializable{
		private String userLoginName;
		private String userName;
		private String password;
	
		public String getUserLoginName() {
			return userLoginName;
		}
		public void setUserLoginName(String userLoginName) {
			this.userLoginName = userLoginName;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
}
