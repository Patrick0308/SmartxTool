package cn.smartx.tools.build.util;

public class FileException extends BaseException{
	public FileException(java.lang.Throwable ex){
		super("�ļ���������"+ex.getMessage());
	}
}
