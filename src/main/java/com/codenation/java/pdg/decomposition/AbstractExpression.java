package com.codenation.java.pdg.decomposition;

import com.codenation.java.pdg.ASTInformation;
import com.codenation.java.pdg.ASTInformationGenerator;
import org.eclipse.jdt.core.dom.Expression;

public class AbstractExpression extends AbstractMethodFragment {

	private ASTInformation expression;
	
	public AbstractExpression(Expression expression) {
		super(null);
		this.expression = ASTInformationGenerator.generateASTInformation(expression);
	}

	public AbstractExpression(Expression expression, AbstractMethodFragment parent) {
		super(parent);
		this.expression = ASTInformationGenerator.generateASTInformation(expression);
	}

	public Expression getExpression() {
		return (Expression)this.expression.recoverASTNode();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractExpression other = (AbstractExpression) obj;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		return true;
	}

	public String toString() {
		return getExpression().toString();
	}
}
