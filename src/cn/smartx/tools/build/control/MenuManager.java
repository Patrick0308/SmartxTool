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
import cn.smartx.tools.build.util.BaseException;

public class MenuManager {
	private AppManager appMan ; 
	public App nowApp ;
	public String appURL;
	public List<Menu> menuList = null;
	public MenuManager() throws BaseException{
		appMan = new AppManager();
		appURL = appMan.nowAppURL;
		nowApp = appMan.getApp(appURL);
		menuList = nowApp.getMenusList();
	}
	public boolean createMenu(Menu menu) throws BaseException{
		for(int i = 0; i < menuList.size(); i++){
			Menu tmp =menuList.get(i); 
			if(tmp.getMenuCode().equals(menu.getMenuCode())){
				return false;
			}
		}
		menuList.add(menu);
		nowApp.setMenusList(menuList);
		appMan.writeApp(new File(appURL), nowApp);
		return true;
	}
	public List<Menu> queryMenu(){
		return menuList;
    }
	
	public boolean removeMenu(String menuTitle, int level) throws BaseException{
		for(int i = 0; i < menuList.size(); i++){
			Menu tmp =menuList.get(i); 
			if(tmp.getMenuTitle().equals(menuTitle)&& Integer.parseInt(tmp.getMenuLevel())==level){
				menuList.remove(i);
				nowApp.setMenusList(menuList);
				appMan.writeApp(new File(appURL), nowApp);
				return true;
			}
		}
		return false;
	}
	
	public Menu queryMenuByTitle(String menuTitle, int level){
		for(int i = 0; i < menuList.size(); i++){
			Menu tmp =menuList.get(i); 
			if(tmp.getMenuTitle().equals(menuTitle)&&Integer.parseInt(tmp.getMenuLevel())== level){
				return tmp;
			}
		}
		return null;
	}
	
	public boolean modifyMenu(String menuTitle, int level,Menu newMenu) throws BaseException{
		boolean modifyRes;
		modifyRes = removeMenu(menuTitle, level);
		if(modifyRes)
			modifyRes = createMenu(newMenu);
		return modifyRes;
	}
	

}
