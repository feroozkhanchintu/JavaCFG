package com.codenation.java.pdg;

import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ClassObject extends ClassDeclarationObject {

	private List<ConstructorObject> constructorList;
	private List<EnumConstantDeclarationObject> enumConstantDeclarationList;
	private TypeObject superclass;
	private List<TypeObject> interfaceList;
	private boolean _abstract;
    private boolean _interface;
    private boolean _static;
    private boolean _enum;
    private Access access;
    //private TypeDeclaration typeDeclaration;
    private ASTInformation typeDeclaration;
    private IFile iFile;

    public ClassObject() {
		this.constructorList = new ArrayList<>();
		this.interfaceList = new ArrayList<>();
		this.enumConstantDeclarationList = new ArrayList<>();
		this._abstract = false;
        this._interface = false;
        this._static = false;
        this._enum = false;
        this.access = Access.NONE;
    }

    public void setAbstractTypeDeclaration(AbstractTypeDeclaration typeDeclaration) {
    	//this.typeDeclaration = typeDeclaration;
    	this.typeDeclaration = ASTInformationGenerator.generateASTInformation(typeDeclaration);
    }

    public AbstractTypeDeclaration getAbstractTypeDeclaration() {
    	//return this.typeDeclaration;
    	if(_enum)
    		return (EnumDeclaration)this.typeDeclaration.recoverASTNode();
    	else
    		return (TypeDeclaration)this.typeDeclaration.recoverASTNode();
    }

    public ClassObject getClassObject() {
    	return this;
    }

    public CompilationUnit getCompilationUnit() {
        return typeDeclaration.getCompilationUnit();
    }

    public IFile getIFile() {
		return iFile;
	}

	public void setIFile(IFile file) {
		iFile = file;
	}


    public void setAccess(Access access) {
        this.access = access;
    }

    public Access getAccess() {
        return access;
    }

    public void setSuperclass(TypeObject superclass) {
		this.superclass = superclass;
	}

	public boolean addInterface(TypeObject i) {
		return interfaceList.add(i);
	}
	
	public boolean addConstructor(ConstructorObject c) {
		return constructorList.add(c);
	}
	
	public boolean addEnumConstantDeclaration(EnumConstantDeclarationObject f) {
		return enumConstantDeclarationList.add(f);
	}
	
	public ListIterator<ConstructorObject> getConstructorIterator() {
		return constructorList.listIterator();
	}
	
	public ListIterator<TypeObject> getInterfaceIterator() {
		return interfaceList.listIterator();
	}

    public ListIterator<TypeObject> getSuperclassIterator() {
		List<TypeObject> superclassList = new ArrayList<>(interfaceList);
		superclassList.add(superclass);
		return superclassList.listIterator();
	}

	public ListIterator<EnumConstantDeclarationObject> getEnumConstantDeclarationIterator() {
		return enumConstantDeclarationList.listIterator();
	}

	public TypeObject getSuperclass() {
		return superclass;
	}
	
	public void setAbstract(boolean abstr) {
		this._abstract = abstr;
	}
	
	public boolean isAbstract() {
		return this._abstract;
	}

    public void setInterface(boolean i) {
        this._interface = i;
    }

    public boolean isInterface() {
        return this._interface;
    }

    public boolean isStatic() {
        return _static;
    }

    public void setStatic(boolean s) {
        _static = s;
    }

    public boolean isEnum() {
		return _enum;
	}

	public void setEnum(boolean _enum) {
		this._enum = _enum;
	}

	public ConstructorObject getConstructor(ClassInstanceCreationObject cico) {
        ListIterator<ConstructorObject> ci = getConstructorIterator();
        while(ci.hasNext()) {
        	ConstructorObject co = ci.next();
            if(co.equals(cico))
                return co;
        }
        return null;
    }


	public String toString() {
        StringBuilder sb = new StringBuilder();
        if(!access.equals(Access.NONE))
            sb.append(access.toString()).append(" ");
        if(_static)
            sb.append("static").append(" ");
        if(_interface)
            sb.append("interface").append(" ");
        else if(_abstract)
            sb.append("abstract class").append(" ");
        else
            sb.append("class").append(" ");
        sb.append(name).append(" ");
        sb.append("extends ").append(superclass);
        if(!interfaceList.isEmpty()) {
            sb.append(" ").append("implements ");
            for(int i=0; i<interfaceList.size()-1; i++)
                sb.append(interfaceList.get(i)).append(", ");
            sb.append(interfaceList.get(interfaceList.size()-1));
        }
        sb.append("\n\n").append("Fields:");
        for(FieldObject field : fieldList)
            sb.append("\n").append(field.toString());

        sb.append("\n\n").append("Constructors:");
        for(ConstructorObject constructor : constructorList)
            sb.append("\n").append(constructor.toString());

        sb.append("\n\n").append("Methods:");
        for(MethodObject method : methodList)
            sb.append("\n").append(method.toString());

        return sb.toString();
    }
}