package org.back2code.asm.visitor;

import java.lang.annotation.Annotation;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.FieldVisitor;

import org.back2code.asm.utils.ASMUtils;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

public class B2CFieldVisitor implements FieldVisitor {
	private JFieldVar jFieldVar;
	private JCodeModel jCodeModel;
	private JDefinedClass jDefinedClass;

	public B2CFieldVisitor(JFieldVar jFieldVar, JDefinedClass jDefinedClass) {
		this.jFieldVar = jFieldVar;
		this.jDefinedClass = jDefinedClass;
		this.jCodeModel = jDefinedClass.owner();
	}

	@SuppressWarnings("unchecked")
	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// Visits an annotation of the field.
		// Declare an annotation to be used by our class

		/*
		 * Parameters: desc - the class descriptor of the annotation class.
		 * visible - true if the annotation is visible at runtime. Returns: a
		 * visitor to visit the annotation values, or null if this visitor is
		 * not interested in visiting this annotation.
		 */
		String classType = ASMUtils.processClassName(desc);

		JAnnotationUse jAnnotationUse = null;
		B2CAnnotationVisitor myAnnotationVisitor = null;
		try {
			jAnnotationUse = jFieldVar
					.annotate((Class<? extends Annotation>) Class
							.forName(classType));
			myAnnotationVisitor = new B2CAnnotationVisitor(jAnnotationUse,this.jDefinedClass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return myAnnotationVisitor;

	}

	@Override
	public void visitAttribute(Attribute attr) {
		// Visits a non standard attribute of the field.

	}

	@Override
	public void visitEnd() {
		// Visits the end of the field.

	}

}
