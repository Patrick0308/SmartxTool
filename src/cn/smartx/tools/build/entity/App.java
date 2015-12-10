package cn.smartx.tools.build.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class App implements Serializable{
		private String AppName;
		private String AppCode;
		private String InitPage;
		private List<Template> templatesList;
		private List<Menu> menusList;
		private List<Group> groupsList;
		private List<User> usersList;
		private List<Role> rolesList;
		
		public App(){
			menusList = new ArrayList<Menu>();
			groupsList = new ArrayList<Group>();
			usersList = new ArrayList<User>();
			rolesList = new ArrayList<Role>();
			templatesList = new ArrayList<Template>();
		}
		public List<Template> getTemplatesList() {
			return templatesList;
		}

		public void setTemplatesList(List<Template> templatesList) {
			this.templatesList = templatesList;
		}

		public List<Menu> getMenusList() {
			return menusList;
		}

		public void setMenusList(List<Menu> menusList) {
			this.menusList = menusList;
		}

		public List<Group> getGroupsList() {
			return groupsList;
		}

		public void setGroupsList(List<Group> groupsList) {
			this.groupsList = groupsList;
		}

		public List<User> getUsersList() {
			return usersList;
		}

		public void setUsersList(List<User> usersList) {
			this.usersList = usersList;
		}

		public List<Role> getRolesList() {
			return rolesList;
		}

		public void setRolesList(List<Role> rolesList) {
			this.rolesList = rolesList;
		}

		public String getInitPage() {
			return InitPage;
		}

		public void setInitPage(String initPage) {
			InitPage = initPage;
		}

		public String getAppCode() {
			return AppCode;
		}

		public void setAppCode(String appCode) {
			AppCode = appCode;
		}

		public String getAppName() {
			return AppName;
		}

		public void setAppName(String appName) {
			this.AppName = appName;
		}
		
}
