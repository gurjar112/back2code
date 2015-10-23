back2code is a decompiler API written in Java,which aims to regnerate java source code from a .class file. This API uses two different APIs for accomplishing this purpose.

1) ASM 3.x API to process the byte code.
http://asm.ow2.org/index.html

2) CodeModel 2.x API to regenerate the java source code using processed byte code.
https://codemodel.dev.java.net

The salient feature of this API is that it is completely written in Java and is able to process the Java 5 code or above.