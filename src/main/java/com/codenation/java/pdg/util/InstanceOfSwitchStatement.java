package com.codenation.java.pdg.util;

import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchStatement;

public class InstanceOfSwitchStatement implements StatementInstanceChecker {

	public boolean instanceOf(Statement statement) {
		if(statement instanceof SwitchStatement)
			return true;
		else
			return false;
	}

}