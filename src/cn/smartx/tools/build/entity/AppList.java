package cn.smartx.tools.build.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppList implements Serializable{
	
	private List<String> appsURL = new ArrayList<String>();
	public List<String> getAppsURL() {
		return appsURL;
	}

	public void setAppsURL(List<String> appsURL) {
		this.appsURL = appsURL;
	}
	
}
