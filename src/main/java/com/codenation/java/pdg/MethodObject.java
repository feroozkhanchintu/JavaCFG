package com.codenation.java.pdg;

import com.codenation.java.pdg.decomposition.MethodBodyObject;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.List;
import java.util.ListIterator;

public class MethodObject implements AbstractMethodDeclaration {

    private TypeObject returnType;
    private boolean _abstract;
    private boolean _static;
    private boolean _synchronized;
    private boolean _native;
    private ConstructorObject constructorObject;
    private boolean testAnnotation;
    private volatile int hashCode = 0;

    public MethodObject(ConstructorObject co) {
        this.constructorObject = co;
        this._abstract = false;
        this._static = false;
        this._synchronized = false;
        this._native = false;
        this.testAnnotation = false;
    }

    public void setReturnType(TypeObject returnType) {
        this.returnType = returnType;
    }

    public TypeObject getReturnType() {
        return returnType;
    }

    public void setAbstract(boolean abstr) {
        this._abstract = abstr;
    }

    public boolean isAbstract() {
        return this._abstract;
    }

    public boolean isStatic() {
        return _static;
    }

    public void setStatic(boolean s) {
        _static = s;
    }

    public boolean isSynchronized() {
    	return this._synchronized;
    }

    public void setSynchronized(boolean s) {
    	this._synchronized = s;
    }

    public boolean isNative() {
    	return this._native;
    }

    public void setNative(boolean n) {
    	this._native = n;
    }

    public String getName() {
        return constructorObject.getName();
    }

    public boolean hasTestAnnotation() {
		return testAnnotation;
	}

	public void setTestAnnotation(boolean testAnnotation) {
		this.testAnnotation = testAnnotation;
	}

	public Access getAccess() {
        return constructorObject.getAccess();
    }

    public MethodDeclaration getMethodDeclaration() {
    	return constructorObject.getMethodDeclaration();
    }

    public MethodBodyObject getMethodBody() {
    	return constructorObject.getMethodBody();
    }

    public String getClassName() {
        return constructorObject.getClassName();
    }


    public ListIterator<ParameterObject> getParameterListIterator() {
        return constructorObject.getParameterListIterator();
    }

    public ParameterObject getParameter(int position) {
    	return constructorObject.getParameter(position);
    }


    public List<TypeObject> getParameterTypeList() {
    	return constructorObject.getParameterTypeList();
    }

    public List<String> getParameterList() {
    	return constructorObject.getParameterList();
    }


    private boolean equalParameterTypes(List<TypeObject> list1, List<TypeObject> list2) {
    	if(list1.size() != list2.size())
    		return false;
    	for(int i=0; i<list1.size(); i++) {
    		TypeObject type1 = list1.get(i);
    		TypeObject type2 = list2.get(i);
    		if(!type1.equalsClassType(type2))
    			return false;
    		//array dimension comparison is skipped if at least one of the class types is a type parameter name, such as E, K, N, T, V, S, U
    		if(type1.getArrayDimension() != type2.getArrayDimension() && type1.getClassType().length() != 1 && type2.getClassType().length() != 1)
    			return false;
    	}
    	return true;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }

        if (o instanceof MethodObject) {
            MethodObject methodObject = (MethodObject)o;

            return this.returnType.equals(methodObject.returnType) &&
                this.constructorObject.equals(methodObject.constructorObject);
        }
        return false;
    }

    public int hashCode() {
    	if(hashCode == 0) {
    		int result = 17;
    		result = 37*result + returnType.hashCode();
    		result = 37*result + constructorObject.hashCode();
    		hashCode = result;
    	}
    	return hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(!constructorObject.access.equals(Access.NONE))
            sb.append(constructorObject.access.toString()).append(" ");
        if(_abstract)
            sb.append("abstract").append(" ");
        if(_static)
            sb.append("static").append(" ");
        sb.append(returnType.toString()).append(" ");
        sb.append(constructorObject.name);
        sb.append("(");
        if(!constructorObject.parameterList.isEmpty()) {
            for(int i=0; i<constructorObject.parameterList.size()-1; i++)
                sb.append(constructorObject.parameterList.get(i).toString()).append(", ");
            sb.append(constructorObject.parameterList.get(constructorObject.parameterList.size()-1).toString());
        }
        sb.append(")");
        /*if(constructorObject.methodBody != null)
        	sb.append("\n").append(constructorObject.methodBody.toString());*/
        return sb.toString();
    }

    public String getSignature() {
    	return constructorObject.getSignature();
    }
}