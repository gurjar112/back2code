package org.back2code.asm.visitor;

import java.lang.annotation.Annotation;

import org.objectweb.asm.AnnotationVisitor;
import org.back2code.asm.utils.ASMUtils;
import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JType;

public class B2CAnnotationVisitor implements AnnotationVisitor {
	private JAnnotationUse jAnnotatitonUse;
	private JCodeModel jCodeModel;	
	private JAnnotationArrayMember jAnnotationArrayMember;
	private JDefinedClass jDefinedClass;

	public B2CAnnotationVisitor(JAnnotationUse jAnnotationUse,
			JDefinedClass jDefinedClass) {
		this.jAnnotatitonUse = jAnnotationUse;		
		this.jCodeModel = jDefinedClass.owner();
		this.jDefinedClass = jDefinedClass;		
	}

	private B2CAnnotationVisitor(JAnnotationUse jAnnotationUse,
			JDefinedClass jDefinedClass,
			JAnnotationArrayMember jAnnotationArrayMember) {
		this.jAnnotationArrayMember = jAnnotationArrayMember;		
		this.jAnnotatitonUse = jAnnotationUse;
		this.jCodeModel = jDefinedClass.owner();
		this.jDefinedClass = jDefinedClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void visit(String name, Object value) {
		// Visits a primitive value of the annotation.
		Class<? extends Annotation> annotationClass = null;
		
		try {
			annotationClass = (Class<? extends Annotation>)Class.forName(ASMUtils.processClassName(value.toString()));
			
			// In an attempt to set the class values as parameters
			// always use JType instead of class as param to the 
			// jAnnotationUse
			jAnnotatitonUse.param(name,jCodeModel._ref(annotationClass));			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block			
			jAnnotatitonUse.param(name, value.toString());
		}		
	}

	@SuppressWarnings("unchecked")
	@Override
	public AnnotationVisitor visitAnnotation(String name, String desc) {
		// Visits a nested annotation value of the annotation.
		/*
		 * Parameters: name - the value name. desc - the class descriptor of the
		 * nested annotation class.
		 */
		
		JAnnotationUse jAnnotationUse = jAnnotationArrayMember
				.annotate(jCodeModel.directClass(ASMUtils.processClassName(desc)));

		B2CAnnotationVisitor myAnnotationVisitor = new B2CAnnotationVisitor(
				jAnnotationUse, jDefinedClass, jAnnotationArrayMember);

		return myAnnotationVisitor;
	}

	@Override
	public AnnotationVisitor visitArray(String name) {
		// Visits an array value of the annotation.
		// Parameter:
		// name - the value name.
		JAnnotationArrayMember jAnnotationArrayMember = jAnnotatitonUse
				.paramArray(name);		
		this.jAnnotationArrayMember = jAnnotationArrayMember;
		B2CAnnotationVisitor myAnnotationVisitor = new B2CAnnotationVisitor(
				jAnnotatitonUse, jDefinedClass, jAnnotationArrayMember);
		return myAnnotationVisitor;
	}

	@Override
	public void visitEnd() {
		// Visits the end of the annotation.		
		
	}

	@Override
	public void visitEnum(String name, String desc, String value) {
		// Visits an enumeration value of the annotation.
		// Parameters:
		// name - the value name.
		// desc - the class descriptor of the enumeration class.
		// value - the actual enumeration value.
		
		// get the class of the descriptor
		
		// enum is little complex
		String baseClassName = ASMUtils.processClassName(desc);
		
		Class<?> baseEnumClass = null;
		
		Enum<?> enumObject = null;		
				
		try {
			baseEnumClass = Class.forName(baseClassName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(Object localEnumObject : baseEnumClass.getEnumConstants()){						
			if(localEnumObject.toString().equals(value)){
				enumObject = (Enum<?>) localEnumObject;				
			}
		}		
		
		if(name == null && jAnnotationArrayMember != null){
			// To have the import in declaration.
			jAnnotationArrayMember.param(enumObject,value);		
		}
		
		if(name != null && value != null){
			jAnnotatitonUse.param(name, enumObject);			
		}		 
		
	}
}
