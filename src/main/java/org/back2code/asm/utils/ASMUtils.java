package org.back2code.asm.utils;


public class ASMUtils {
	private ASMUtils(){
		
	}
	
	public static String processClassName(String classPath){
		String className = classPath.replace("/",".");
		className = className.replaceFirst("L","");
		className = className.replaceAll(";","");
		className = className.replace("<","");
		className = className.replace(">","");
		return className;
	}
	

	
	public static String narrowClassName(String type,String signature){
		String genericClassName = signature.replaceAll(type.replace(";",""),"");
		
		return processClassName(genericClassName);		
	}
}
