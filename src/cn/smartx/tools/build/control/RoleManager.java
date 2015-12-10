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
import cn.smartx.tools.build.entity.Menu;
import cn.smartx.tools.build.entity.Role;
import cn.smartx.tools.build.util.BaseException;

public class RoleManager {
	private AppManager appMan ; 
	public App nowApp ;
	public String appURL;
	public List<Role> roleList = null;
	public RoleManager() throws BaseException{
		appMan = new AppManager();
		appURL = appMan.nowAppURL;
		nowApp = appMan.getApp(appURL);
		roleList = nowApp.getRolesList();
	}
	public boolean createRole(Role role) throws BaseException{
		for(int i = 0; i < roleList.size(); i++){
			Role tmp =roleList.get(i); 
			if(role.getRoleMemo().equals(role.getRoleMemo())){
				return false;
			}
		}
		roleList.add(role);
		nowApp.setRolesList(roleList);
		appMan.writeApp(new File(appURL), nowApp);
		return true;
	}
	public List<Role> queryRole(){
		return roleList;
    }
	
	
	public boolean removeRole(Role role) throws BaseException{
		for(int i = 0; i < roleList.size(); i++){
			Role tmp =roleList.get(i); 
			if(tmp.getRoleMemo().equals(role.getRoleMemo())){
				roleList.remove(i);
				nowApp.setRolesList(roleList);
				appMan.writeApp(new File(appURL), nowApp);
				return true;
			}
		}
		
		return false;
	}
	

	
	public void modifyRoles(List<Role>roles) throws BaseException{
		nowApp.setRolesList(roles);
		appMan.writeApp(new File(appURL), nowApp);
	}
	
	
}
