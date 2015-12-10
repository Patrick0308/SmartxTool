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
import cn.smartx.tools.build.entity.Role;
import cn.smartx.tools.build.entity.User;
import cn.smartx.tools.build.util.BaseException;

public class UserManager {
	private AppManager appMan ; 
	private App nowApp ;
	private String appURL;
	private List<User> userList = null;
	public UserManager() throws BaseException{
		appMan = new AppManager();
		appURL = appMan.nowAppURL;
		nowApp = appMan.getApp(appURL);
		userList = nowApp.getUsersList();
	}
	public boolean createUser(User user) throws BaseException{
		for(int i = 0; i < userList.size(); i++){
			User tmp =userList.get(i); 
			if(tmp.getUserLoginName().equals(user.getUserLoginName())){
				return false;
			}
		}
		userList.add(user);
		nowApp.setUsersList(userList);
		appMan.writeApp(new File(appURL), nowApp);
		return true;
	}
	public boolean removeUser(User user) throws BaseException{
		for(int i = 0; i < userList.size(); i++){
			User tmp =userList.get(i); 
			if(tmp.getUserLoginName().equals(user.getUserLoginName())){
				userList.remove(i);
				nowApp.setUsersList(userList);
				appMan.writeApp(new File(appURL), nowApp);
				return true;
			}
		}
		return false;
	}
	
	public List<User> queryUser(){
		return userList;
	}
	public void modifyUsers(List<User> users) throws BaseException{
		nowApp.setUsersList(users);
		appMan.writeApp(new File(appURL), nowApp);
	};
		
		
	
}
