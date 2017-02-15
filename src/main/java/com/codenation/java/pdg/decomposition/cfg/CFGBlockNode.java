package com.codenation.java.pdg.decomposition.cfg;

import com.codenation.java.pdg.decomposition.AbstractStatement;

public class CFGBlockNode extends CFGNode {

	private CFGNode controlParent;

	public CFGBlockNode(AbstractStatement statement) {
		super(statement);
	}

	public CFGNode getControlParent() {
		return controlParent;
	}

	public void setControlParent(CFGNode controlParent) {
		this.controlParent = controlParent;
	}

}