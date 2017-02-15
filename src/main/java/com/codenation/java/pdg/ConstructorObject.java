package com.codenation.java.pdg;

import com.codenation.java.pdg.decomposition.MethodBodyObject;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.*;

public class ConstructorObject implements AbstractMethodDeclaration {

    protected String name;
	protected List<ParameterObject> parameterList;
    protected Access access;
    protected String className;
    protected MethodBodyObject methodBody;
    protected Set<String> exceptionsInJavaDocThrows;
    //protected MethodDeclaration methodDeclaration;
    protected ASTInformation methodDeclaration;
    private volatile int hashCode = 0;

    public ConstructorObject() {
		this.parameterList = new ArrayList<>();
		this.exceptionsInJavaDocThrows = new LinkedHashSet<>();
        this.access = Access.NONE;
    }

    public void setMethodDeclaration(MethodDeclaration methodDeclaration) {
    	//this.methodDeclaration = methodDeclaration;
    	this.methodDeclaration = ASTInformationGenerator.generateASTInformation(methodDeclaration);
    }

    public MethodDeclaration getMethodDeclaration() {
    	//return this.methodDeclaration;
    	return (MethodDeclaration)this.methodDeclaration.recoverASTNode();
    }

    public void setMethodBody(MethodBodyObject methodBody) {
    	this.methodBody = methodBody;
    }

    public MethodBodyObject getMethodBody() {
    	return this.methodBody;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public Access getAccess() {
        return access;
    }

    public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }

	public boolean addParameter(ParameterObject parameter) {
		return parameterList.add(parameter);
	}

    public ListIterator<ParameterObject> getParameterListIterator() {
		return parameterList.listIterator();
	}

    public ParameterObject getParameter(int position) {
    	if(position >= 0 && position < parameterList.size())
    		return parameterList.get(position);
    	else if(position >= parameterList.size()) {
    		ParameterObject param = parameterList.get(parameterList.size()-1);
    		if(param.isVarargs())
    			return param;
    		else
    			return null;
    	}
    	else
    		return null;
    }


    public List<TypeObject> getParameterTypeList() {
    	List<TypeObject> list = new ArrayList<>();
    	for(ParameterObject parameterObject : parameterList)
    		list.add(parameterObject.getType());
    	return list;
    }

    public List<String> getParameterList() {
    	List<String> list = new ArrayList<>();
    	for(ParameterObject parameterObject : parameterList)
    		list.add(parameterObject.getType().toString());
    	return list;
    }

    public boolean equals(ClassInstanceCreationObject creationObject) {
    	return this.className.equals(creationObject.getType().getClassType()) &&
    			equalParameterTypes(this.getParameterTypeList(), creationObject.getParameterTypeList());
    }

    private boolean equalParameterTypes(List<TypeObject> list1, List<TypeObject> list2) {
    	if(list1.size() != list2.size())
    		return false;
    	for(int i=0; i<list1.size(); i++) {
    		TypeObject type1 = list1.get(i);
    		TypeObject type2 = list2.get(i);
    		if(!type1.equalsClassType(type2))
    			return false;
    		if(type1.getArrayDimension() != type2.getArrayDimension())
    			return false;
    	}
    	return true;
    }

    public boolean equals(Object o) {
        if(this == o) {
			return true;
		}

		if (o instanceof ConstructorObject) {
			ConstructorObject constructorObject = (ConstructorObject)o;

			return this.className.equals(constructorObject.className) && this.name.equals(constructorObject.name) &&
				this.parameterList.equals(constructorObject.parameterList);
		}
		return false;
    }

    public int hashCode() {
    	if(hashCode == 0) {
    		int result = 17;
    		result = 37*result + className.hashCode();
    		result = 37*result + name.hashCode();
    		result = 37*result + parameterList.hashCode();
    		hashCode = result;
    	}
    	return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(!access.equals(Access.NONE))
            sb.append(access.toString()).append(" ");
        sb.append(name);
        sb.append("(");
        if(!parameterList.isEmpty()) {
            for(int i=0; i<parameterList.size()-1; i++)
                sb.append(parameterList.get(i).toString()).append(", ");
            sb.append(parameterList.get(parameterList.size()-1).toString());
        }
        sb.append(")");
        /*if(methodBody != null)
        	sb.append("\n").append(methodBody.toString());*/
        return sb.toString();
    }

	public String getSignature() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		sb.append("(");
		if (!this.parameterList.isEmpty()) {
			for (int i = 0; i < this.parameterList.size() - 1; i++)
				sb.append(this.parameterList.get(i).getType()).append(", ");
			sb.append(this.parameterList.get(this.parameterList.size() - 1).getType());
		}
		sb.append(")");
		return sb.toString();
	}

	public boolean isAbstract() {
		return false;
	}
}