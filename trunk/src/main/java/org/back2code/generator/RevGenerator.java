package org.back2code.generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.back2code.asm.visitor.B2CClassVisitor;
import org.objectweb.asm.ClassReader;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;

public class RevGenerator {
	private JCodeModel jCodeModel;
	
	public static RevGenerator getInstance(){
		return new RevGenerator();
	}
	
	private RevGenerator(){
		this.jCodeModel = new JCodeModel();
	}
	
	public void regenerate(String className,String packageName,String targetDirectory){
		ClassReader classReader = null;
		
		String completeClassName = packageName + "." + className;
		
		try {
			classReader = new ClassReader(loadInputStream(completeClassName,targetDirectory));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
		JPackage jPackage = jCodeModel._package(packageName);
		
		JDefinedClass jDefinedClass = null;
		
		try {			 
			jDefinedClass = jPackage._class(JMod.PUBLIC, className);
		} catch (JClassAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		B2CClassVisitor myClassVisitor = new B2CClassVisitor(jDefinedClass);
		
		classReader.accept(myClassVisitor,1);
	}
	
	public void writeJavaCode(String destDirectory){
		try {
			jCodeModel.build(new File(destDirectory));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private InputStream loadInputStream(String completeClassName,String targetDirectory){
		targetDirectory += "/";
		
		String fileName = completeClassName.replace(".", "/");
		
		String fullName = targetDirectory + fileName + ".class";
		
		InputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream(new File(fullName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inputStream;
		
	}
}
