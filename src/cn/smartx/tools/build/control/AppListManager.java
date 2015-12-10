package cn.smartx.tools.build.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.smartx.tools.build.entity.App;
import cn.smartx.tools.build.entity.AppList;
import cn.smartx.tools.build.util.BaseException;
import cn.smartx.tools.build.util.FileException;

public class AppListManager {
	public static String appspace;
	public AppList appsURL  = null;
	public AppList getAppsList() throws BaseException {
		getAppList();
		return appsURL;
	}
	public void importOrCreateApp(String url) throws BaseException{
		getAppList();
		List<String > appList = new ArrayList<String>();
		if(appsURL!=null)
			appList= appsURL.getAppsURL();
		else
			appsURL = new AppList();
		appList.add(url);
		appsURL.setAppsURL(appList);
		writeAppList();
	}
	public void removeAppURL(String url) throws BaseException{
		getAppList();
		List<String> appList= appsURL.getAppsURL();
		appList.remove(url);
		writeAppList();
	}
	public void getAppList() throws BaseException{
		FileInputStream fis;
		File listFile = new File(appspace+"\\.apps");
		try {
			if(!listFile.exists()){
				listFile.createNewFile();
			}
			if(listFile.length() != 0){
				fis = new FileInputStream(listFile);
				ObjectInputStream ois = new ObjectInputStream(fis); 
				appsURL = (AppList) ois.readObject();
				fis.close();
				ois.close();
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileException(e);
		}
	}
	private void writeAppList() throws BaseException{
		FileOutputStream fos;
		File listFile = new File(appspace+"\\.apps");
		if(!listFile.exists())
			try {
				listFile.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw new FileException(e1);
			}
		try {
			fos = new FileOutputStream(listFile); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(appsURL);
			oos.flush(); 
		    fos.close(); 
		    oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileException(e);
		} 
		
	}
	
}
