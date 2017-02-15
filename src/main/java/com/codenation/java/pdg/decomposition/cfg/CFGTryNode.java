package com.codenation.java.pdg.decomposition.cfg;

import com.codenation.java.pdg.decomposition.AbstractStatement;
import com.codenation.java.pdg.decomposition.TryStatementObject;

public class CFGTryNode extends CFGBlockNode {
	private boolean hasResources;
	public CFGTryNode(AbstractStatement statement) {
		super(statement);
		TryStatementObject tryStatement = (TryStatementObject)statement;
		this.hasResources = tryStatement.hasResources();
	}
	
	public boolean hasResources() {
		return hasResources;
	}

	public boolean hasCatchClause() {
		return ((TryStatementObject)getStatement()).hasCatchClause();
	}
}
