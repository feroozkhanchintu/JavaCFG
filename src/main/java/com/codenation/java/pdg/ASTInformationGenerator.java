package com.codenation.java.pdg;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTInformationGenerator {

	private static CompilationUnit compilationUnit;

	public static void setCurrentCompilationUnit(CompilationUnit typeRoot) {
		compilationUnit = typeRoot;
	}

	public static CompilationUnit getCurrentCompilationUnit(){
		return compilationUnit;
	}

	public static ASTInformation generateASTInformation(ASTNode astNode) {
		return new ASTInformation(compilationUnit, astNode);
	}
}
