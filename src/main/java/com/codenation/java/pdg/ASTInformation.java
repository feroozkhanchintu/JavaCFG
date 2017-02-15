package com.codenation.java.pdg;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.NodeFinder;

public class ASTInformation {

	private CompilationUnit compilationUnit;
	private int startPosition;
	private int length;
	private int nodeType;
	private volatile int hashCode = 0;

	public ASTInformation(CompilationUnit compilationUnit, ASTNode astNode) {
		this.compilationUnit = compilationUnit;
		this.startPosition = astNode.getStartPosition();
		this.length = astNode.getLength();
		this.nodeType = astNode.getNodeType();
	}

	public ASTNode recoverASTNode() {
		CompilationUnit compilationUnit = CompilationUnitCache.getInstance().getCompilationUnit(this.compilationUnit);
		ASTNode astNode = NodeFinder.perform(compilationUnit, startPosition, length);
		return astNode;
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public int getStartPosition() {
		return startPosition;
	}

	public int getLength() {
		return length;
	}

	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}

		if(o instanceof ASTInformation) {
			ASTInformation astInformation = (ASTInformation)o;
			return this.compilationUnit.equals(astInformation.compilationUnit) &&
					this.startPosition == astInformation.startPosition &&
					this.length == astInformation.length &&
					this.nodeType == astInformation.nodeType;
		}
		return false;
	}

	public int hashCode() {
		if(hashCode == 0) {
			int result = 17;
			result = 37*result + compilationUnit.hashCode();
			result = 37*result + startPosition;
			result = 37*result + length;
			result = 37*result + nodeType;
			hashCode = result;
		}
		return hashCode;
	}
}
