package com.codenation.java.pdg;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class ClassDeclarationObject {
	protected String name;
	protected List<MethodObject> methodList;
	protected List<FieldObject> fieldList;

	public ClassDeclarationObject() {
		this.methodList = new ArrayList<>();
		this.fieldList = new ArrayList<>();
	}

	public abstract CompilationUnit getCompilationUnit();
	public abstract ClassObject getClassObject();
	public abstract IFile getIFile();
	public abstract TypeObject getSuperclass();

	public boolean addMethod(MethodObject method) {
		return methodList.add(method);
	}

	public boolean addField(FieldObject f) {
		return fieldList.add(f);
	}

	public List<MethodObject> getMethodList() {
		return methodList;
	}

	public ListIterator<MethodObject> getMethodIterator() {
		return methodList.listIterator();
	}

	public ListIterator<FieldObject> getFieldIterator() {
		return fieldList.listIterator();
	}

	public int getNumberOfMethods() {
		return methodList.size();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
