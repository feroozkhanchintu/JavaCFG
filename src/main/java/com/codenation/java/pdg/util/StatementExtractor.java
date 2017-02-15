package com.codenation.java.pdg.util;

import org.eclipse.jdt.core.dom.*;

import java.util.ArrayList;
import java.util.List;

public class StatementExtractor {
	private InstanceType instanceChecker;

	public List<Statement> getVariableDeclarationStatements(ASTNode statement) {
		instanceChecker = InstanceType.VARIABLE_DECLARATION_STATEMENT;
		return getStatements(statement);
	}

	public List<Statement> getTryStatements(ASTNode statement) {
		instanceChecker = InstanceType.TRY_STATEMENT;
		return getStatements(statement);
	}
	
	public List<Statement> getSwitchStatements(ASTNode statement) {
		instanceChecker = InstanceType.SWITCH_STATEMENT;
		return getStatements(statement);
	}
	
	public List<Statement> getIfStatements(ASTNode statement) {
		instanceChecker = InstanceType.IF_STATEMENT;
		return getStatements(statement);
	}
	
	public List<Statement> getReturnStatements(ASTNode statement) {
		instanceChecker = InstanceType.RETURN_STATEMENT;
		return getStatements(statement);
	}

	public List<Statement> getBreakStatements(ASTNode statement) {
		instanceChecker = InstanceType.BREAK_STATEMENT;
		return getStatements(statement);
	}

	public List<Statement> getContinueStatements(ASTNode statement) {
		instanceChecker = InstanceType.CONTINUE_STATEMENT;
		return getStatements(statement);
	}
	
	public List<Statement> getEnhancedForStatements(ASTNode statement) {
		instanceChecker = InstanceType.ENHANCED_FOR_STATEMENT;
		return getStatements(statement);
	}

	public List<Statement> getForStatements(ASTNode statement) {
		instanceChecker = InstanceType.FOR_STATEMENT;
		return getStatements(statement);
	}

	public List<Statement> getWhileStatements(ASTNode statement) {
		instanceChecker = InstanceType.WHILE_STATEMENT;
		return getStatements(statement);
	}

	public List<Statement> getDoStatements(ASTNode statement) {
		instanceChecker = InstanceType.DO_STATEMENT;
		return getStatements(statement);
	}

    public List<Statement> getThrowsStatement(ASTNode statement) {
        instanceChecker = InstanceType.THROWS_STATEMENT;
        return getStatements(statement);
    }

	public List<Statement> getTypeDeclarationStatements(ASTNode statement) {
		instanceChecker = InstanceType.TYPE_DECLARATION_STATEMENT;
		return getStatements(statement);
	}
	
	private List<Statement> getStatements(ASTNode statement) {
		List<Statement> statementList = new ArrayList<>();
		if(statement instanceof Block) {
			Block block = (Block)statement;
			List<Statement> blockStatements = block.statements();
			for(Statement blockStatement : blockStatements)
				statementList.addAll(getStatements(blockStatement));
		}
		else if(statement instanceof IfStatement) {
			IfStatement ifStatement = (IfStatement)statement;
			statementList.addAll(getStatements(ifStatement.getThenStatement()));
			if(ifStatement.getElseStatement() != null) {
				statementList.addAll(getStatements(ifStatement.getElseStatement()));
			}
			if(instanceChecker.instanceOf(ifStatement))
				statementList.add(ifStatement);
		}
		else if(statement instanceof ForStatement) {
			ForStatement forStatement = (ForStatement)statement;
			statementList.addAll(getStatements(forStatement.getBody()));
			if(instanceChecker.instanceOf(forStatement))
				statementList.add(forStatement);
		}
		else if(statement instanceof EnhancedForStatement) {
			EnhancedForStatement enhancedForStatement = (EnhancedForStatement)statement;
			statementList.addAll(getStatements(enhancedForStatement.getBody()));
			if(instanceChecker.instanceOf(enhancedForStatement))
				statementList.add(enhancedForStatement);
		}
		else if(statement instanceof WhileStatement) {
			WhileStatement whileStatement = (WhileStatement)statement;
			statementList.addAll(getStatements(whileStatement.getBody()));
			if(instanceChecker.instanceOf(whileStatement))
				statementList.add(whileStatement);
		}
		else if(statement instanceof DoStatement) {
			DoStatement doStatement = (DoStatement)statement;
			statementList.addAll(getStatements(doStatement.getBody()));
			if(instanceChecker.instanceOf(doStatement))
				statementList.add(doStatement);
		}
		else if(statement instanceof ExpressionStatement) {
			ExpressionStatement expressionStatement = (ExpressionStatement)statement;
		}
		else if(statement instanceof SwitchStatement) {
			SwitchStatement switchStatement = (SwitchStatement)statement;
			List<Statement> statements = switchStatement.statements();
			for(Statement statement2 : statements)
				statementList.addAll(getStatements(statement2));
			if(instanceChecker.instanceOf(switchStatement))
				statementList.add(switchStatement);
		}
		else if(statement instanceof SwitchCase) {
			SwitchCase switchCase = (SwitchCase)statement;
		}
		else if(statement instanceof AssertStatement) {
			AssertStatement assertStatement = (AssertStatement)statement;
		}
		else if(statement instanceof LabeledStatement) {
			LabeledStatement labeledStatement = (LabeledStatement)statement;
			statementList.addAll(getStatements(labeledStatement.getBody()));
		}
		else if(statement instanceof ReturnStatement) {
			ReturnStatement returnStatement = (ReturnStatement)statement;
			if(instanceChecker.instanceOf(returnStatement))
				statementList.add(returnStatement);
		}
		else if(statement instanceof SynchronizedStatement) {
			SynchronizedStatement synchronizedStatement = (SynchronizedStatement)statement;
			statementList.addAll(getStatements(synchronizedStatement.getBody()));
		}
		else if(statement instanceof ThrowStatement) {
			ThrowStatement throwStatement = (ThrowStatement)statement;
            if(instanceChecker.instanceOf(throwStatement))
                statementList.add(throwStatement);
		}
		else if(statement instanceof TryStatement) {
			TryStatement tryStatement = (TryStatement)statement;
			statementList.addAll(getStatements(tryStatement.getBody()));
			List<CatchClause> catchClauses = tryStatement.catchClauses();
			for(CatchClause catchClause : catchClauses) {
				statementList.addAll(getStatements(catchClause.getBody()));
			}
			Block finallyBlock = tryStatement.getFinally();
			if(finallyBlock != null)
				statementList.addAll(getStatements(finallyBlock));
			if(instanceChecker.instanceOf(tryStatement))
				statementList.add(tryStatement);
		}
		else if(statement instanceof VariableDeclarationStatement) {
			VariableDeclarationStatement variableDeclarationStatement = (VariableDeclarationStatement)statement;
			if(instanceChecker.instanceOf(variableDeclarationStatement))
				statementList.add(variableDeclarationStatement);
		}
		else if(statement instanceof ConstructorInvocation) {
			ConstructorInvocation constructorInvocation = (ConstructorInvocation)statement;
			if(instanceChecker.instanceOf(constructorInvocation))
				statementList.add(constructorInvocation);
		}
		else if(statement instanceof SuperConstructorInvocation) {
			SuperConstructorInvocation superConstructorInvocation = (SuperConstructorInvocation)statement;
			if(instanceChecker.instanceOf(superConstructorInvocation))
				statementList.add(superConstructorInvocation);
		}
		else if(statement instanceof BreakStatement) {
			BreakStatement breakStatement = (BreakStatement)statement;
			if(instanceChecker.instanceOf(breakStatement))
				statementList.add(breakStatement);
		}
		else if(statement instanceof ContinueStatement) {
			ContinueStatement continueStatement = (ContinueStatement)statement;
			if(instanceChecker.instanceOf(continueStatement))
				statementList.add(continueStatement);
		}
		else if(statement instanceof TypeDeclarationStatement) {
			TypeDeclarationStatement typeDeclarationStatement = (TypeDeclarationStatement)statement;
			if(instanceChecker.instanceOf(typeDeclarationStatement))
				statementList.add(typeDeclarationStatement);
		}
		return statementList;
	}
	
	public int getTotalNumberOfStatements(Statement statement) {
		int statementCounter = 0;
		if(statement instanceof Block) {
			Block block = (Block)statement;
			List<Statement> blockStatements = block.statements();
			for(Statement blockStatement : blockStatements)
				statementCounter += getTotalNumberOfStatements(blockStatement);
		}
		else if(statement instanceof IfStatement) {
			IfStatement ifStatement = (IfStatement)statement;
			statementCounter += 1;
			statementCounter += getTotalNumberOfStatements(ifStatement.getThenStatement());
			if(ifStatement.getElseStatement() != null) {
				statementCounter += getTotalNumberOfStatements(ifStatement.getElseStatement());
			}
		}
		else if(statement instanceof ForStatement) {
			ForStatement forStatement = (ForStatement)statement;
			statementCounter += 1;
			statementCounter += getTotalNumberOfStatements(forStatement.getBody());
		}
		else if(statement instanceof EnhancedForStatement) {
			EnhancedForStatement enhancedForStatement = (EnhancedForStatement)statement;
			statementCounter += 1;
			statementCounter += getTotalNumberOfStatements(enhancedForStatement.getBody());
		}
		else if(statement instanceof WhileStatement) {
			WhileStatement whileStatement = (WhileStatement)statement;
			statementCounter += 1;
			statementCounter += getTotalNumberOfStatements(whileStatement.getBody());
		}
		else if(statement instanceof DoStatement) {
			DoStatement doStatement = (DoStatement)statement;
			statementCounter += 1;
			statementCounter += getTotalNumberOfStatements(doStatement.getBody());
		}
		else if(statement instanceof ExpressionStatement) {
			statementCounter += 1;
		}
		else if(statement instanceof SwitchStatement) {
			SwitchStatement switchStatement = (SwitchStatement)statement;
			statementCounter += 1;
			List<Statement> statements = switchStatement.statements();
			for(Statement statement2 : statements)
				statementCounter += getTotalNumberOfStatements(statement2);
		}
		else if(statement instanceof SwitchCase) {
		}
		else if(statement instanceof AssertStatement) {
			statementCounter += 1;
		}
		else if(statement instanceof LabeledStatement) {
			LabeledStatement labeledStatement = (LabeledStatement)statement;
			statementCounter += 1;
			statementCounter += getTotalNumberOfStatements(labeledStatement.getBody());
		}
		else if(statement instanceof ReturnStatement) {
			statementCounter += 1;
		}
		else if(statement instanceof SynchronizedStatement) {
			SynchronizedStatement synchronizedStatement = (SynchronizedStatement)statement;
			statementCounter += 1;
			statementCounter += getTotalNumberOfStatements(synchronizedStatement.getBody());
		}
		else if(statement instanceof ThrowStatement) {
			statementCounter += 1;
		}
		else if(statement instanceof TryStatement) {
			TryStatement tryStatement = (TryStatement)statement;
			statementCounter += 1;
			statementCounter += getTotalNumberOfStatements(tryStatement.getBody());
			List<CatchClause> catchClauses = tryStatement.catchClauses();
			for(CatchClause catchClause : catchClauses) {
				statementCounter += getTotalNumberOfStatements(catchClause.getBody());
			}
			Block finallyBlock = tryStatement.getFinally();
			if(finallyBlock != null)
				statementCounter += getTotalNumberOfStatements(finallyBlock);
		}
		else if(statement instanceof VariableDeclarationStatement) {
			statementCounter += 1;
		}
		else if(statement instanceof ConstructorInvocation) {
			statementCounter += 1;
		}
		else if(statement instanceof SuperConstructorInvocation) {
			statementCounter += 1;
		}
		else if(statement instanceof BreakStatement) {
			statementCounter += 1;
		}
		else if(statement instanceof ContinueStatement) {
			statementCounter += 1;
		}
		else if(statement instanceof TypeDeclarationStatement) {
			statementCounter += 1;
		}
		return statementCounter;
	}
}
