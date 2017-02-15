package com.codenation.java.pdg.decomposition;

import org.eclipse.jdt.core.dom.*;

import java.util.List;

public class MethodBodyObject {
	
	private CompositeStatementObject compositeStatement;
	
	public MethodBodyObject(Block methodBody) {
		this.compositeStatement = new CompositeStatementObject(methodBody, StatementType.BLOCK, null);
        List<Statement> statements = methodBody.statements();
		for(Statement statement : statements) {
			processStatement(compositeStatement, statement);
		}
	}

	public CompositeStatementObject getCompositeStatement() {
		return compositeStatement;
	}

	private void processStatement(CompositeStatementObject parent, Statement statement) {
		if(statement instanceof Block) {
			Block block = (Block)statement;
			List<Statement> blockStatements = block.statements();
			CompositeStatementObject child = new CompositeStatementObject(block, StatementType.BLOCK, parent);
			parent.addStatement(child);
			for(Statement blockStatement : blockStatements) {
				processStatement(child, blockStatement);
			}
		}
		else if(statement instanceof IfStatement) {
			IfStatement ifStatement = (IfStatement)statement;
			CompositeStatementObject child = new CompositeStatementObject(ifStatement, StatementType.IF, parent);
			AbstractExpression abstractExpression = new AbstractExpression(ifStatement.getExpression(), child);
			child.addExpression(abstractExpression);
			//processExpression(child, ifStatement.getExpression());
			parent.addStatement(child);
			processStatement(child, ifStatement.getThenStatement());
			if(ifStatement.getElseStatement() != null) {
				processStatement(child, ifStatement.getElseStatement());
			}
		}
		else if(statement instanceof ForStatement) {
			ForStatement forStatement = (ForStatement)statement;
			CompositeStatementObject child = new CompositeStatementObject(forStatement, StatementType.FOR, parent);
			List<Expression> initializers = forStatement.initializers();
			for(Expression initializer : initializers) {
				AbstractExpression abstractExpression = new AbstractExpression(initializer, child);
				child.addExpression(abstractExpression);
				//processExpression(child, initializer);
			}
			Expression expression = forStatement.getExpression();
			if(expression != null) {
				AbstractExpression abstractExpression = new AbstractExpression(expression, child);
				child.addExpression(abstractExpression);
				//processExpression(child, expression);
			}
			List<Expression> updaters = forStatement.updaters();
			for(Expression updater : updaters) {
				AbstractExpression abstractExpression = new AbstractExpression(updater, child);
				child.addExpression(abstractExpression);
				//processExpression(child, updater);
			}
			parent.addStatement(child);
			processStatement(child, forStatement.getBody());
		}
		else if(statement instanceof EnhancedForStatement) {
			EnhancedForStatement enhancedForStatement = (EnhancedForStatement)statement;
			CompositeStatementObject child = new CompositeStatementObject(enhancedForStatement, StatementType.ENHANCED_FOR, parent);
			SingleVariableDeclaration variableDeclaration = enhancedForStatement.getParameter();
			AbstractExpression variableDeclarationName = new AbstractExpression(variableDeclaration.getName(), child);
			child.addExpression(variableDeclarationName);
			//processExpression(child, variableDeclaration.getName());
			if(variableDeclaration.getInitializer() != null) {
				AbstractExpression variableDeclarationInitializer = new AbstractExpression(variableDeclaration.getInitializer(), child);
				child.addExpression(variableDeclarationInitializer);
				//processExpression(child, variableDeclaration.getInitializer());
			}
			AbstractExpression abstractExpression = new AbstractExpression(enhancedForStatement.getExpression(), child);
			child.addExpression(abstractExpression);
			//processExpression(child, enhancedForStatement.getExpression());
			parent.addStatement(child);
			processStatement(child, enhancedForStatement.getBody());
		}
		else if(statement instanceof WhileStatement) {
			WhileStatement whileStatement = (WhileStatement)statement;
			CompositeStatementObject child = new CompositeStatementObject(whileStatement, StatementType.WHILE, parent);
			AbstractExpression abstractExpression = new AbstractExpression(whileStatement.getExpression(), child);
			child.addExpression(abstractExpression);
			//processExpression(child, whileStatement.getExpression());
			parent.addStatement(child);
			processStatement(child, whileStatement.getBody());
		}
		else if(statement instanceof DoStatement) {
			DoStatement doStatement = (DoStatement)statement;
			CompositeStatementObject child = new CompositeStatementObject(doStatement, StatementType.DO, parent);
			AbstractExpression abstractExpression = new AbstractExpression(doStatement.getExpression(), child);
			child.addExpression(abstractExpression);
			//processExpression(child, doStatement.getExpression());
			parent.addStatement(child);
			processStatement(child, doStatement.getBody());
		}
		else if(statement instanceof ExpressionStatement) {
			ExpressionStatement expressionStatement = (ExpressionStatement)statement;
			StatementObject child = new StatementObject(expressionStatement, StatementType.EXPRESSION, parent);
			//processExpression(child, expressionStatement.getExpression());
			parent.addStatement(child);
		}
		else if(statement instanceof SwitchStatement) {
			SwitchStatement switchStatement = (SwitchStatement)statement;
			CompositeStatementObject child = new CompositeStatementObject(switchStatement, StatementType.SWITCH, parent);
			AbstractExpression abstractExpression = new AbstractExpression(switchStatement.getExpression(), child);
			child.addExpression(abstractExpression);
			//processExpression(child, switchStatement.getExpression());
			parent.addStatement(child);
			List<Statement> switchStatements = switchStatement.statements();
			for(Statement switchStatement2 : switchStatements)
				processStatement(child, switchStatement2);
		}
		else if(statement instanceof SwitchCase) {
			SwitchCase switchCase = (SwitchCase)statement;
			StatementObject child = new StatementObject(switchCase, StatementType.SWITCH_CASE, parent);
			/*if(switchCase.getExpression() != null)
				processExpression(child, switchCase.getExpression());*/
			parent.addStatement(child);
		}
		else if(statement instanceof AssertStatement) {
			AssertStatement assertStatement = (AssertStatement)statement;
			StatementObject child = new StatementObject(assertStatement, StatementType.ASSERT, parent);
			/*processExpression(child, assertStatement.getExpression());
			Expression message = assertStatement.getMessage();
			if(message != null)
				processExpression(child, message);*/
			parent.addStatement(child);
		}
		else if(statement instanceof LabeledStatement) {
			LabeledStatement labeledStatement = (LabeledStatement)statement;
			CompositeStatementObject child = new CompositeStatementObject(labeledStatement, StatementType.LABELED, parent);
			/*if(labeledStatement.getLabel() != null)
				processExpression(child, labeledStatement.getLabel());*/
			parent.addStatement(child);
			processStatement(child, labeledStatement.getBody());
		}
		else if(statement instanceof ReturnStatement) {
			ReturnStatement returnStatement = (ReturnStatement)statement;
			StatementObject child = new StatementObject(returnStatement, StatementType.RETURN, parent);
			//processExpression(child, returnStatement.getExpression());
			parent.addStatement(child);	
		}
		else if(statement instanceof SynchronizedStatement) {
			SynchronizedStatement synchronizedStatement = (SynchronizedStatement)statement;
			SynchronizedStatementObject child = new SynchronizedStatementObject(synchronizedStatement, parent);
			//processExpression(child, synchronizedStatement.getExpression());
			parent.addStatement(child);
			processStatement(child, synchronizedStatement.getBody());
		}
		else if(statement instanceof ThrowStatement) {
			ThrowStatement throwStatement = (ThrowStatement)statement;
			StatementObject child = new StatementObject(throwStatement, StatementType.THROW, parent);
			//processExpression(child, throwStatement.getExpression());
			parent.addStatement(child);
		}
		else if(statement instanceof TryStatement) {
			TryStatement tryStatement = (TryStatement)statement;
			TryStatementObject child = new TryStatementObject(tryStatement, parent);
			List<VariableDeclarationExpression> resources = tryStatement.resources();
			for(VariableDeclarationExpression expression : resources) {
				AbstractExpression variableDeclarationExpression = new AbstractExpression(expression, child);
				child.addExpression(variableDeclarationExpression);
				//processExpression(child, expression);
			}
			parent.addStatement(child);
			processStatement(child, tryStatement.getBody());
			List<CatchClause> catchClauses = tryStatement.catchClauses();
			for(CatchClause catchClause : catchClauses) {
				CatchClauseObject catchClauseObject = new CatchClauseObject(catchClause, child);
				Block catchClauseBody = catchClause.getBody();
				CompositeStatementObject catchClauseStatementObject = new CompositeStatementObject(catchClauseBody, StatementType.BLOCK, null);
				SingleVariableDeclaration variableDeclaration = catchClause.getException();
				AbstractExpression variableDeclarationName = new AbstractExpression(variableDeclaration.getName(), child);
				catchClauseObject.addExpression(variableDeclarationName);
				if(variableDeclaration.getInitializer() != null) {
					AbstractExpression variableDeclarationInitializer = new AbstractExpression(variableDeclaration.getInitializer(), child);
					catchClauseObject.addExpression(variableDeclarationInitializer);
				}
				List<Statement> blockStatements = catchClauseBody.statements();
				for(Statement blockStatement : blockStatements) {
					processStatement(catchClauseStatementObject, blockStatement);
				}
				catchClauseObject.setBody(catchClauseStatementObject);
				catchClauseObject.setCatchClause(catchClause);
				child.addCatchClause(catchClauseObject);
			}
			Block finallyBlock = tryStatement.getFinally();
			if(finallyBlock != null) {
				CompositeStatementObject finallyClauseStatementObject = new CompositeStatementObject(finallyBlock, StatementType.BLOCK, null);
				List<Statement> blockStatements = finallyBlock.statements();
				for(Statement blockStatement : blockStatements) {
					processStatement(finallyClauseStatementObject, blockStatement);
				}
				child.setFinallyClause(finallyClauseStatementObject);
			}
		}
		else if(statement instanceof VariableDeclarationStatement) {
			VariableDeclarationStatement variableDeclarationStatement = (VariableDeclarationStatement)statement;
			StatementObject child = new StatementObject(variableDeclarationStatement, StatementType.VARIABLE_DECLARATION, parent);
			/*List<VariableDeclarationFragment> fragments = variableDeclarationStatement.fragments();
			for(VariableDeclarationFragment fragment : fragments) {
				processExpression(child, fragment.getName());
				processExpression(child, fragment.getInitializer());
			}*/
			parent.addStatement(child);
		}
		else if(statement instanceof ConstructorInvocation) {
			ConstructorInvocation constructorInvocation = (ConstructorInvocation)statement;
			StatementObject child = new StatementObject(constructorInvocation, StatementType.CONSTRUCTOR_INVOCATION, parent);
			/*List<Expression> arguments = constructorInvocation.arguments();
			for(Expression argument : arguments)
				processExpression(child, argument);*/
			parent.addStatement(child);
		}
		else if(statement instanceof SuperConstructorInvocation) {
			SuperConstructorInvocation superConstructorInvocation = (SuperConstructorInvocation)statement;
			StatementObject child = new StatementObject(superConstructorInvocation, StatementType.SUPER_CONSTRUCTOR_INVOCATION, parent);
			/*if(superConstructorInvocation.getExpression() != null)
				processExpression(child, superConstructorInvocation.getExpression());
			List<Expression> arguments = superConstructorInvocation.arguments();
			for(Expression argument : arguments)
				processExpression(child, argument);*/
			parent.addStatement(child);
		}
		else if(statement instanceof BreakStatement) {
			BreakStatement breakStatement = (BreakStatement)statement;
			StatementObject child = new StatementObject(breakStatement, StatementType.BREAK, parent);
			/*if(breakStatement.getLabel() != null)
				processExpression(child, breakStatement.getLabel());*/
			parent.addStatement(child);
		}
		else if(statement instanceof ContinueStatement) {
			ContinueStatement continueStatement = (ContinueStatement)statement;
			StatementObject child = new StatementObject(continueStatement, StatementType.CONTINUE, parent);
			/*if(continueStatement.getLabel() != null)
				processExpression(child, continueStatement.getLabel());*/
			parent.addStatement(child);
		}
		else if(statement instanceof EmptyStatement) {
			EmptyStatement emptyStatement = (EmptyStatement)statement;
			StatementObject child = new StatementObject(emptyStatement, StatementType.EMPTY, parent);
			parent.addStatement(child);
		}
	}

	public List<TryStatementObject> getTryStatements() {
		return compositeStatement.getTryStatements();
	}

	public List<String> stringRepresentation() {
		return compositeStatement.stringRepresentation();
	}
}
