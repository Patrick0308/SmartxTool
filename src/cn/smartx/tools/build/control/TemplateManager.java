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
import cn.smartx.tools.build.entity.Template;
import cn.smartx.tools.build.util.BaseException;

public class TemplateManager {
	private AppManager appMan ; 
	private App nowApp ;
	private String appURL;
	private List<Template> templateList = null;
	public TemplateManager() throws BaseException{
		appMan = new AppManager();
		appURL = appMan.nowAppURL;
		nowApp = appMan.getApp(appURL);
		templateList = nowApp.getTemplatesList();
	}
	public boolean createTemplate(Template temp) throws BaseException{
		for(int i = 0; i < templateList.size(); i++){
			Template tmp =templateList.get(i); 
			if(tmp.getTemplateCode().equals(temp.getTemplateCode())){
				return false;
			}
		}
		templateList.add(temp);
		nowApp.setTemplatesList(templateList);
		appMan.writeApp(new File(appURL), nowApp);
		return true;
	}
	public List<Template> queryTemplate(){
		return templateList;
    }
	public void replaceTemplate(List<Template> temps) throws BaseException{
		nowApp.setTemplatesList(temps);
		appMan.writeApp(new File(appURL), nowApp);
	}
	public boolean removeTemplate(Template temp) throws BaseException{
		for(int i = 0; i < templateList.size(); i++){
			Template tmp =templateList.get(i); 
			if(tmp.getTemplateCode().equals(temp.getTemplateCode())){
				templateList.remove(i);
				nowApp.setTemplatesList(templateList);
				appMan.writeApp(new File(appURL), nowApp);
				return true;
			}
		}
		
		return false;
	}


}
