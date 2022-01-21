package evaluator.arith;

import evaluator.IllegalPostFixExpressionException;
import evaluator.PostFixEvaluator;
import language.Operand;
import language.Operator;
import parser.arith.ArithPostFixParser;
import stack.LinkedStack;
import stack.StackInterface;


/**
 * An {@link ArithPostFixEvaluator} is a post fix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostFixEvaluator implements PostFixEvaluator<Integer> {

  private final StackInterface<Operand<Integer>> stack;

  /**
   * Constructs an {@link ArithPostFixEvaluator}.
   */
  public ArithPostFixEvaluator() {
	  this.stack = new LinkedStack<Operand<Integer>>(); ; //TODO Initialize to your LinkedStack
  }

  /**
   * Evaluates a postfix expression.
   * @return the result 
   */
  @Override
  public Integer evaluate(String expr) {
    // TODO Use all of the things they've built so far to 
    // create the algorithm to do post fix evaluation

    ArithPostFixParser parser = new ArithPostFixParser(expr);
    while (parser.hasNext()) {
      switch (parser.nextType()) { 
        case OPERAND:
          //TODO What do we do when we see an operand?
        	stack.push(parser.nextOperand());
          break;
        case OPERATOR:
          //TODO What do we do when we see an operator?
        	//Grab the operator from the parser then based on the number of arguments we pop the appropriate amount from the stack set the operands perform the operation and push the result onto the stack
        	Operator<Integer> operator = parser.nextOperator();
        	for(int i = operator.getNumberOfArguments()-1; i >= 0; i--) {
        		operator.setOperand(i, stack.pop());
        	}
        	stack.push(operator.performOperation());
          break;
        default:
          //TODO If we get here, something went terribly wrong
        	throw new IllegalPostFixExpressionException();
      
      }
    }

    //TODO What do we return?
    if(stack.size()>1) {
    	throw new IllegalPostFixExpressionException();
    }
    return stack.pop().getValue();
  }

}
