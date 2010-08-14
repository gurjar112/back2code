package org.back2code.asm.visitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import org.back2code.asm.utils.ASMUtils;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

public class B2CClassVisitor implements ClassVisitor {
	private JDefinedClass jDefinedClass;
	private JCodeModel jCodeModel;

	public JDefinedClass getjDefinedClass() {
		return jDefinedClass;
	}

	public B2CClassVisitor(JDefinedClass jDefinedClass) {
		this.jDefinedClass = jDefinedClass;
		this.jCodeModel = jDefinedClass.owner();
	}

	@Override
	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
/*		Visits the header of the class.
 
	    version - the class version.
	    access - the class's access flags (see Opcodes). This parameter also indicates if the class is deprecated.
	    name - the internal name of the class (see getInternalName).
	    signature - the signature of this class. May be null if the class is not a generic one, and does not extend or implement generic classes or interfaces.
	    superName - the internal of name of the super class (see getInternalName). For interfaces, the super class is Object. May be null, but only for the Object class.
	    interfaces - the internal names of the class's interfaces (see getInternalName). May be null.
*/		
	}

	@Override
	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// Visits an annotation of the class.

		// Declare an annotation to be used by our class		
		JAnnotationUse jAnnotationUse = jDefinedClass.annotate(jCodeModel
				.directClass(ASMUtils.processClassName(desc)));

		B2CAnnotationVisitor myAnnotationVisitor = new B2CAnnotationVisitor(
				jAnnotationUse, jDefinedClass);

		return myAnnotationVisitor;
	}

	@Override
	public void visitAttribute(Attribute attr) {
		// Visits a non standard attribute of the class.

	}

	@Override
	public void visitEnd() {
		// Visits the end of the class.

	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		/*
		 * // Visits a field of the class. 
		 * access - the field's access flags
		 * (see Opcodes). This parameter also indicates if the field is
		 * synthetic and/or deprecated. 
		 * name - the field's name. desc - the
		 * field's descriptor (see Type). 
		 * signature - the field's signature.May
		 * be null if the field's type does not use generic types. 
		 * value - the * field's initial value. 
		 * This parameter, which may be null if the field
		 * does not have an initial value, must be an Integer, a Float, a Long,
		 * a Double or a String (for int, float, long or String fields
		 * respectively).* This parameter is only used for static fields. Its
		 * value is ignored for non static fields, which must be initialized
		 * through bytecode instructions in constructors or methods.
		 */
		
		JFieldVar jFieldVar = null;
		
		String typeClass = ASMUtils.processClassName(desc); 
		
		if(signature != null){
			
			String narrowName = ASMUtils.narrowClassName(desc, signature);
			String typeName = ASMUtils.processClassName(desc);			
			
			JClass jClass = jCodeModel.directClass(typeName);
			
			JClass jClass4FieldType = jCodeModel.directClass(narrowName);
			
			// make sure fields are provided private status
			jFieldVar = jDefinedClass.field(4, jClass.narrow(jClass4FieldType), name);
			
		}else{
			try {
				jFieldVar = jDefinedClass.field(4, Class.forName(typeClass), name);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block				
			}
		}
		
		if(jFieldVar == null){
			System.out.println(" Classfield Var turned out to be null;");
		}
		
		B2CFieldVisitor myFieldVisitor = new B2CFieldVisitor(jFieldVar, jDefinedClass);
		return myFieldVisitor;		
	}

	@Override
	public void visitInnerClass(String name, String outerName,
			String innerName, int access) {
		// Visits information about an inner class.

	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		/*// Visits a method of the class.
		JMethod jMethod = jDefinedClass.method(access, jCodeModel.ref(ASMUtils.processClassName(desc)), name);
		if(exceptions != null && exceptions.length > 0){
			for (String exceptionClass : exceptions) {
				try {
					jMethod._throws(Class.forName(ASMUtils
							.processClassName(exceptionClass)));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		
		B2CMethodVisitor myMethodVisitor = new B2CMethodVisitor(jMethod, jCodeModel);
		
		return myMethodVisitor;*/
		
		return null;
	}

	@Override
	public void visitOuterClass(String owner, String name, String desc) {
		// Visits the enclosing class of the class.

	}

	@Override
	public void visitSource(String source, String debug) {
		// Visits the source of the class.

	}

}
