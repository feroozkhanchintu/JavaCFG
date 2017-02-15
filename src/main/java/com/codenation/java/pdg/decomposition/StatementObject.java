package com.codenation.java.pdg.decomposition;

import org.eclipse.jdt.core.dom.Statement;

import java.util.ArrayList;
import java.util.List;

/*
 * StatementObject represents the following AST Statement subclasses:
 * 1.	ExpressionStatement
 * 2.	VariableDeclarationStatement
 * 3.	ConstructorInvocation
 * 4.	SuperConstructorInvocation
 * 5.	ReturnStatement
 * 6.	AssertStatement
 * 7.	BreakStatement
 * 8.	ContinueStatement
 * 9.	SwitchCase
 * 10.	EmptyStatement
 * 11.	ThrowStatement
 */

public class StatementObject extends AbstractStatement {
	
	public StatementObject(Statement statement, StatementType type, AbstractMethodFragment parent) {
		super(statement, type, parent);

	}

	public String toString() {
		return getStatement().toString();
	}

	public List<String> stringRepresentation() {
		List<String> stringRepresentation = new ArrayList<>();
		stringRepresentation.add(this.toString());
		return stringRepresentation;
	}
}
