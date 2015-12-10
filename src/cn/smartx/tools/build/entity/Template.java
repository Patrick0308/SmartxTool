package cn.smartx.tools.build.entity;

import java.io.Serializable;

public class Template implements Serializable{
	private String TemplateCode;
	private String TemplateName;
	private String TemplateFile;
	private String TemplateTitle;

	public String getTemplateCode() {
		return TemplateCode;
	}
	public void setTemplateCode(String code) {
		TemplateCode = code;
	}
	public String getTemplateName() {
		return TemplateName;
	}
	public void setTemplateName(String name) {
		TemplateName = name;
	}
	public String getTemplateFile() {
		return TemplateFile;
	}
	public void setTemplateFile(String templateFile) {
		TemplateFile = templateFile;
	}
	public String getTemplateTitle() {
		return TemplateTitle;
	}
	public void setTemplateTitle(String title) {
		TemplateTitle = title;
	}
	
}
