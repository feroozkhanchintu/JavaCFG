package com.codenation.java.pdg.decomposition;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CatchClause;

import java.util.ArrayList;
import java.util.List;

public class CatchClauseObject extends CompositeStatementObject{

	private CatchClause catchClause;
	private CompositeStatementObject body;
	private List<AbstractExpression> expressionList;
	private TryStatementObject parent;
	
	public CatchClauseObject(ASTNode catchNode, AbstractMethodFragment parent) {
		super(catchNode, StatementType.CATCH, parent);
		this.expressionList = new ArrayList<>();
		this.parent = null;
	}

	public void setCatchClause(CatchClause catchClause){
	    this.catchClause = catchClause;
    }

    public CatchClause getCatchClause(){
	    return this.catchClause;
    }

    public void setParent(TryStatementObject parent) {
    	this.parent = parent;
    }

	public TryStatementObject getParent() {
		return parent;
	}

	public void setBody(CompositeStatementObject body) {
		this.body = body;
	}

	public CompositeStatementObject getBody() {
		return body;
	}

	public void addExpression(AbstractExpression expression) {
		expressionList.add(expression);
	}

	public List<AbstractExpression> getExpressions() {
		return expressionList;
	}

	public List<String> stringRepresentation() {
		List<String> stringRepresentation = new ArrayList<>();
		stringRepresentation.add(this.toString());
		stringRepresentation.addAll(body.stringRepresentation());
		return stringRepresentation;
	}

	public List<CompositeStatementObject> getIfStatements() {
		List<CompositeStatementObject> ifStatements = new ArrayList<>();
		ifStatements.addAll(body.getIfStatements());
		return ifStatements;
	}

	public List<CompositeStatementObject> getSwitchStatements() {
		List<CompositeStatementObject> switchStatements = new ArrayList<>();
		switchStatements.addAll(body.getSwitchStatements());
		return switchStatements;
	}

	public List<TryStatementObject> getTryStatements() {
		List<TryStatementObject> tryStatements = new ArrayList<>();
		tryStatements.addAll(body.getTryStatements());
		return tryStatements;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("catch");
		if(expressionList.size() > 0) {
			sb.append("(");
			for(AbstractExpression expression : expressionList)
				sb.append(expression.toString());
			sb.append(")");
		}
		return sb.toString();
	}
}
