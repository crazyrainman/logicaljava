package carman.designs;

import java.util.StringTokenizer;

abstract class Expression {
    public abstract boolean interpret(String str);
}
class TerminalExpression extends Expression {
    private String literal = null;
    public TerminalExpression(String str) {
        literal = str;
    }
    public boolean interpret(String str) {
        StringTokenizer st = new StringTokenizer(str);
        while(st.hasMoreTokens()) {
            String test = st.nextToken();
            if (test.equals(literal)) {
                return true;
            }
        }
        return false;
    }
}
class AndExpression extends Expression {
    private Expression expression1 = null;
    private Expression expression2 = null;
    public AndExpression(Expression expression1, Expression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
    }
    public boolean interpret(String str) {
        return expression1.interpret(str) && expression2.interpret(str);
    }
}
class OrExpression extends Expression {
    private Expression exp1 = null;
    private Expression exp2 = null;
    public OrExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
    public boolean interpret(String str) {
        return exp1.interpret(str) || exp2.interpret(str);
    }
}
public class InterpreterT {
    public static Expression buildInterpreterTree() {
        Expression terminal1 = new TerminalExpression("A");
        Expression terminal2 = new TerminalExpression("B");
        Expression terminal3 = new TerminalExpression("C");
        Expression terminal4 = new TerminalExpression("D");
        // B || C
        Expression alternation1 = new OrExpression(terminal2, terminal3);
        // A || (B || C)
        Expression alternation2 = new OrExpression(terminal1, alternation1);
        // D && ( A || (B || C))
        return new AndExpression(terminal4, alternation2);
    }
    public static void main(String[] args) {
        Expression define = buildInterpreterTree();
        String context1 = "D C";
        String context2 = "A C";
        System.out.println(define.interpret(context1));
        System.out.println(define.interpret(context2));
    }
}
