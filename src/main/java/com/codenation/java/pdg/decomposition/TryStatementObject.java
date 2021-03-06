package com.codenation.java.pdg.decomposition;

import org.eclipse.jdt.core.dom.Statement;

import java.util.ArrayList;
import java.util.List;

public class TryStatementObject extends CompositeStatementObject {
	private List<CatchClauseObject> catchClauses;
	private CompositeStatementObject finallyClause;
	
	public TryStatementObject(Statement statement, AbstractMethodFragment parent) {
		super(statement, StatementType.TRY, parent);
		this.catchClauses = new ArrayList<>();
	}

	public List<AbstractStatement> getStatementsInsideTryBlock() {
		CompositeStatementObject tryBlock = (CompositeStatementObject)getStatements().get(0);
		return tryBlock.getStatements();
	}

	public boolean hasResources() {
		return !super.getExpressions().isEmpty();
	}

	public void addCatchClause(CatchClauseObject catchClause) {
		catchClauses.add(catchClause);
		catchClause.setParent(this);
	}

	public List<CatchClauseObject> getCatchClauses() {
		return catchClauses;
	}

	public void setFinallyClause(CompositeStatementObject finallyClause) {
		this.finallyClause = finallyClause;
	}

	public CompositeStatementObject getFinallyClause() {
		return finallyClause;
	}

	public List<AbstractExpression> getExpressions() {
		List<AbstractExpression> expressions = new ArrayList<>();
		expressions.addAll(super.getExpressions());
		for(CatchClauseObject catchClause : catchClauses) {
			expressions.addAll(catchClause.getExpressions());
		}
		return expressions;
	}

	public List<String> stringRepresentation() {
		List<String> stringRepresentation = new ArrayList<>();
		stringRepresentation.addAll(super.stringRepresentation());
		for(CatchClauseObject catchClause : catchClauses) {
			stringRepresentation.addAll(catchClause.stringRepresentation());
		}
		if(finallyClause != null) {
			stringRepresentation.addAll(finallyClause.stringRepresentation());
		}
		return stringRepresentation;
	}

	public List<CompositeStatementObject> getIfStatements() {
		List<CompositeStatementObject> ifStatements = new ArrayList<>();
		ifStatements.addAll(super.getIfStatements());
		for(CatchClauseObject catchClause : catchClauses) {
			ifStatements.addAll(catchClause.getIfStatements());
		}
		if(finallyClause != null) {
			ifStatements.addAll(finallyClause.getIfStatements());
		}
		return ifStatements;
	}

	public List<CompositeStatementObject> getSwitchStatements() {
		List<CompositeStatementObject> switchStatements = new ArrayList<>();
		switchStatements.addAll(super.getSwitchStatements());
		for(CatchClauseObject catchClause : catchClauses) {
			switchStatements.addAll(catchClause.getSwitchStatements());
		}
		if(finallyClause != null) {
			switchStatements.addAll(finallyClause.getSwitchStatements());
		}
		return switchStatements;
	}

	public List<TryStatementObject> getTryStatements() {
		List<TryStatementObject> tryStatements = new ArrayList<>();
		tryStatements.addAll(super.getTryStatements());
		for(CatchClauseObject catchClause : catchClauses) {
			tryStatements.addAll(catchClause.getTryStatements());
		}
		if(finallyClause != null) {
			tryStatements.addAll(finallyClause.getTryStatements());
		}
		return tryStatements;
	}

	public boolean hasCatchClause() {
		return !catchClauses.isEmpty();
	}
}
