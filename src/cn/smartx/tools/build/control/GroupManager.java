package cn.smartx.tools.build.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.smartx.tools.build.entity.App;
import cn.smartx.tools.build.entity.Group;
import cn.smartx.tools.build.entity.User;
import cn.smartx.tools.build.util.BaseException;

public class GroupManager {
		private AppManager appMan ; 
		private App nowApp ;
		private String appURL;
		private List<Group> groupList = null;
		public GroupManager() throws BaseException{
			appMan = new AppManager();
			appURL = appMan.nowAppURL;
			nowApp = appMan.getApp(appURL);
			groupList = nowApp.getGroupsList();
		}
		public boolean createGroup(Group group) throws BaseException{
			for(int i = 0; i < groupList.size(); i++){
				Group tmp =groupList.get(i); 
				if(tmp.getGroupCode().equals(group.getGroupCode())){
					return false;
				}
			}
			groupList.add(group);
			nowApp.setGroupsList(groupList);
			appMan.writeApp(new File(appURL), nowApp);
			return true;
		}
		public boolean removeGroup(Group group) throws BaseException{
			for(int i = 0; i < groupList.size(); i++){
				Group tmp =groupList.get(i); 
				if(tmp.getGroupCode().equals(group.getGroupCode())){
					groupList.remove(i);
					nowApp.setGroupsList(groupList);
					appMan.writeApp(new File(appURL), nowApp);
					return true;
				}
			}
			return false;
		}
		
		public List<Group> queryGroup(){
			return groupList;
		}
		
		public void modifyGroups(List<Group> groups) throws BaseException{
			boolean modifyRes;
			nowApp.setGroupsList(groups);
			appMan.writeApp(new File(appURL), nowApp);
		};
}

