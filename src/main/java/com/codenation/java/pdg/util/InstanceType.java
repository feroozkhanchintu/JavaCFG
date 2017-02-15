package com.codenation.java.pdg.util;

import org.eclipse.jdt.core.dom.*;

/**
 * Created by Ferooz on 15/02/17.
 */
public enum InstanceType {
    BREAK_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof BreakStatement;
        }
    },
    CONTINUE_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof ContinueStatement;
        }
    },
    DO_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof DoStatement;
        }
    },
    ENHANCED_FOR_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof EnhancedForStatement;
        }
    },
    FOR_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof ForStatement;
        }
    },
    IF_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof IfStatement;
        }
    },
    RETURN_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof ReturnStatement;
        }
    },
    SWITCH_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof SwitchStatement;
        }
    },
    THROWS_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof ThrowStatement;
        }
    },
    TRY_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof TryStatement;
        }
    },
    TYPE_DECLARATION_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof TypeDeclarationStatement;
        }
    },
    VARIABLE_DECLARATION_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof VariableDeclarationStatement;
        }
    },
    WHILE_STATEMENT {
        public boolean instanceOf(ASTNode statement) {
            return statement instanceof WhileStatement;
        }
    };

    public abstract boolean instanceOf(ASTNode statement);

}
