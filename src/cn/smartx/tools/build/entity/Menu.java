package cn.smartx.tools.build.entity;

import java.io.Serializable;

public class Menu implements Serializable{

		private String menuTitle;
		private String menuURL;
		private String menuCode;
		private String menuLevel;
		private String menuPM;
		
		public String getMenuTitle() {
			return menuTitle;
		}
		public void setMenuTitle(String title) {
			menuTitle = title;
		}
		public String getMenuURL() {
			return menuURL;
		}
		public void setMenuURL(String URL) {
			menuURL = URL;
		}
		public String getMenuCode() {
			return menuCode;
		}
		public void setMenuCode(String code) {
			menuCode = code;
		}
		public String getMenuLevel() {
			return menuLevel;
		}
		public void setMenuLevel(String level) {
			this.menuLevel = level;
		}
		public String getMenuPM() {
			return menuPM;
		}
		public void setMenuPM(String menuPM) {
			this.menuPM = menuPM;
		}
		
}
