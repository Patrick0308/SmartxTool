package cn.smartx.tools.build.entity;

import java.io.Serializable;

public class Role implements Serializable{

		private String roleName;
		private String roleMemo;
	
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public String getRoleMemo() {
			return roleMemo;
		}
		public void setRoleMemo(String roleMemo) {
			this.roleMemo = roleMemo;
		}
		
}
