package com.codenation.java.pdg.decomposition;

public abstract class AbstractMethodFragment {
	private AbstractMethodFragment parent;

	protected AbstractMethodFragment(AbstractMethodFragment parent) {
		this.parent = parent;
	}

	public AbstractMethodFragment getParent() {
    	return this.parent;
    }

}
