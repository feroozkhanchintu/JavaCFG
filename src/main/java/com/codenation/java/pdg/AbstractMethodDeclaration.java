package com.codenation.java.pdg;

import com.codenation.java.pdg.decomposition.MethodBodyObject;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.List;
import java.util.ListIterator;

public interface AbstractMethodDeclaration {

	public String getName();

	public Access getAccess();

	public MethodDeclaration getMethodDeclaration();

	public MethodBodyObject getMethodBody();

	public String getClassName();

	public ListIterator<ParameterObject> getParameterListIterator();

	public ParameterObject getParameter(int position);

	public List<TypeObject> getParameterTypeList();

	public List<String> getParameterList();

	public String getSignature();

	public boolean isAbstract();
}
