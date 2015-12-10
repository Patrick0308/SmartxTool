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
import cn.smartx.tools.build.util.BaseException;
import cn.smartx.tools.build.util.FileException;


public class AppManager {
	public static String nowAppURL;
	
	
	public void createApp(App app) throws BaseException{
		File newAppFile = new File(AppListManager.appspace+'\\'+app.getAppName() );
		new AppListManager().importOrCreateApp(AppListManager.appspace+'\\'+app.getAppName());
		try {
			newAppFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileException(e);
		}
		writeApp(newAppFile,app);	
    }
	public App getApp(String appURL) throws BaseException{
		File appFile = new File(appURL);
		App resApp = null;
		FileInputStream fis;
		try {
			if(appFile.length() != 0){
				fis = new FileInputStream(appFile);
				ObjectInputStream ois = new ObjectInputStream(fis); 
				resApp = (App) ois.readObject();
				fis.close();
				ois.close();
			}else{
				resApp = new App();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileException(e);
		}finally{
			return resApp;
		}
    }
	
	public void removeApp(String appURL) throws BaseException{
		new AppListManager().removeAppURL(appURL);
		File appFile = new File(appURL);
		appFile.delete();
	}
	
	public void writeApp(File appFile,App app) throws BaseException{
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(appFile); 
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(app);
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
