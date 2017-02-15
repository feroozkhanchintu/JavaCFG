package com.codenation.java.pdg.decomposition.cfg;

import com.codenation.java.pdg.decomposition.AbstractStatement;
import org.eclipse.jdt.core.dom.SwitchCase;

public class CFGSwitchCaseNode extends CFGNode {
	private boolean isDefault;
	
	public CFGSwitchCaseNode(AbstractStatement statement) {
		super(statement);
		SwitchCase switchCase = (SwitchCase)statement.getStatement();
		if(switchCase.isDefault())
			isDefault = true;
		else
			isDefault = false;
	}

	public boolean isDefault() {
		return isDefault;
	}
}
