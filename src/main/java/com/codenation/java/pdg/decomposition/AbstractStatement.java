package com.codenation.java.pdg.decomposition;

import com.codenation.java.pdg.ASTInformation;
import com.codenation.java.pdg.ASTInformationGenerator;
import org.eclipse.jdt.core.dom.ASTNode;

import java.util.List;

public abstract class AbstractStatement extends AbstractMethodFragment {

	private ASTInformation statement;
	private StatementType type;
	
    public AbstractStatement(ASTNode statement, StatementType type, AbstractMethodFragment parent) {
    	super(parent);
    	this.type = type;
    	this.statement = ASTInformationGenerator.generateASTInformation(statement);
    }

    public ASTNode getStatement() {
    	return this.statement.recoverASTNode();
    }

    public StatementType getType() {
		return type;
	}

	public abstract List<String> stringRepresentation();
}
