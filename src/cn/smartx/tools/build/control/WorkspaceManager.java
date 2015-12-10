package cn.smartx.tools.build.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.smartx.tools.build.util.FileException;

public class WorkspaceManager {
	public static String workspace;
	private Properties spacePro;
	private InputStream proIns;
	public WorkspaceManager() throws FileException{
		spacePro = new Properties();
		proIns = this.getClass().getResourceAsStream("/cn/smartx/tools/build/properties/workspace.properties");
		try {
			spacePro.load(proIns);
			workspace = spacePro.getProperty("workspace"); 
			proIns.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new FileException(e);
		}
		
	}
	public boolean setSpace(String url) throws FileException{
		if(spacePro.setProperty("workspace", url) ==null)
			return false;
		else{
			String filePath=this.getClass().getResource("/cn/smartx/tools/build/properties/workspace.properties").getFile();  
			File file = new File(filePath);
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(file);
				spacePro.store(fos, null);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new FileException(e);
			}
			
			workspace = url;
			return true;
		}
	}

}
