package org.back2code.asm.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import org.back2code.asm.utils.ASMUtils;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;

public class B2CMethodVisitor implements MethodVisitor{
	private JMethod jMethod;
	private JCodeModel jCodeModel;
	private JBlock jBlock;
	
	public B2CMethodVisitor(JMethod jMethod,JCodeModel jCodeModel){
		this.jMethod = jMethod;
		this.jCodeModel = jCodeModel;
		this.jBlock = jMethod.body();
	}
	
	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// TODO Auto-generated method stub
/*		JAnnotationUse jAnnotationUse = jMethod.annotate(jCodeModel
				.directClass(ASMUtils.processClassName(desc)));

		B2CAnnotationVisitor myAnnotationVisitor = new B2CAnnotationVisitor(
				jAnnotationUse, jMethod.);

		return myAnnotationVisitor;*/
		
		return null;
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void visitAttribute(Attribute attr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitCode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitFieldInsn(int opcode, String owner, String name, String desc) {
		// Visits a field instruction.
	}

	@Override
	public void visitFrame(int type, int nLocal, Object[] local, int nStack, Object[] stack) {
		// Visits the current state of the local variables and operand stack elements.
	}

	@Override
	public void visitIincInsn(int var, int increment) {
		// Visits an IINC instruction.
		
	}

	@Override
	public void visitInsn(int opcode) {
		//  Visits a zero operand instruction.
		
	}

	@Override
	public void visitIntInsn(int opcode, int operand) {
		// Visits an instruction with a single int operand.
		
	}

	@Override
	public void visitJumpInsn(int opcode, Label label) {
		// Visits a jump instruction.
		
	}

	@Override
	public void visitLabel(Label label) {
		//  Visits a label.
		
	}

	@Override
	public void visitLdcInsn(Object cst) {
		// Visits a LDC instruction.
		
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		// Visits a line number declaration.
		
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
		// Visits a local variable declaration.
		
	}

	@Override
	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		//  Visits a LOOKUPSWITCH instruction.
		
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		// Visits the maximum stack size and the maximum number of local variables of the method.
		
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc) {
		// Visits a method instruction.
		
	}

	@Override
	public void visitMultiANewArrayInsn(String desc, int dims) {
		// Visits a MULTIANEWARRAY instruction.
		
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int parameter, String desc, boolean visible) {
	   /* Visits an annotation of a parameter this method.

	    Parameters:
	        parameter - the parameter index.
	        desc - the class descriptor of the annotation class.
	        visible - true if the annotation is visible at runtime. 
	    Returns:
	        a visitor to visit the annotation values, or null if this visitor is not 
	        interested in visiting this annotation.
	    */

		return null;
	}

	@Override
	public void visitTableSwitchInsn(int min, int max, Label dflt, Label[] labels) {
		// Visits a TABLESWITCH instruction.
		
	}

	@Override
	public void visitTryCatchBlock(Label start, Label end, Label handler, String type) {
		// Visits a try catch block.
		
	}

	@Override
	public void visitTypeInsn(int opcode, String type) {
		// Visits a type instruction.
		
	}

	@Override
	public void visitVarInsn(int opcode, int var) {
		// Visits a local variable instruction.
		
	}

}
