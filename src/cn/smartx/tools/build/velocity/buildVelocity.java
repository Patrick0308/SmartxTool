/**
 * 
 */
/**
 * @author Administrator
 *
 */
package cn.smartx.tools.build.velocity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import cn.smartx.tools.build.control.AppManager;
import cn.smartx.tools.build.control.GroupManager;
import cn.smartx.tools.build.control.MenuManager;
import cn.smartx.tools.build.control.RoleManager;
import cn.smartx.tools.build.control.TemplateManager;
import cn.smartx.tools.build.control.UserManager;
import cn.smartx.tools.build.util.BaseException;

public class buildVelocity {
	public static void create(){
		 VelocityEngine ve = new VelocityEngine();
		 ve.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, "D:\\");
		 ve.init();
		 Template t = ve.getTemplate("installer.vm");
		 VelocityContext ctx = new VelocityContext();
		 
		 try {
			ctx.put("app", new AppManager().getApp(AppManager.nowAppURL));
			 MenuManager muManager = new MenuManager();
			 ctx.put("menulist", muManager.queryMenu());
			 TemplateManager tempManager = new TemplateManager();
			 ctx.put("templatelist", tempManager.queryTemplate());
			 UserManager userManager = new UserManager();
			 ctx.put("userlist", userManager.queryUser());
			 RoleManager roleManager = new RoleManager();
			 ctx.put("rolelist", roleManager.queryRole());
			 GroupManager gpManager = new GroupManager();
			 ctx.put("grouplist", gpManager.queryGroup());
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 
		 StringWriter sw = new StringWriter();
		 
		 t.merge(ctx, sw);
		 
		 File file = new File("D:\\installing.java");
		 try {
			 if (!file.exists()) {
				 file.createNewFile();
			 }
			 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(sw.toString());
			   bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println(sw.toString());
	}
}