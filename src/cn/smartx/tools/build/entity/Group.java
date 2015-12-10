package cn.smartx.tools.build.entity;

import java.io.Serializable;

public class Group implements Serializable{
		private String groupName;
		private String groupCode;
		private String groupType;
	
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getGroupCode() {
			return groupCode;
		}
		public void setGroupCode(String groupCode) {
			this.groupCode = groupCode;
		}
		public String getGroupType() {
			return groupType;
		}
		public void setGroupType(String groupType) {
			this.groupType = groupType;
		}
		
}
