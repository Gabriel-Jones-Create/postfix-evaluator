package evaluator.arith;

import evaluator.IllegalPostFixExpressionException;
import evaluator.PostFixEvaluator;
import language.Operand;
import language.Operator;
import language.arith.DivOperator;
import language.arith.ExponentOperator;
import language.arith.MultOperator;
import language.arith.PlusOperator;
import language.arith.SubOperator;
import parser.arith.ArithPostFixParser;
import stack.LinkedStack;
import stack.StackInterface;


/**
 * An {@link ArithPostFixEvaluator} is a post fix evaluator over simple arithmetic expressions.
 *
 */
public class ArithInFixEvaluator implements PostFixEvaluator<Integer> {

  private final StackInterface<Operand<Integer>> stackOfNumbers;
  private final StackInterface<Operator<Integer>>stackOfOperators;
  /**
   * Constructs an {@link ArithPostFixEvaluator}.
   */
  public ArithInFixEvaluator() {
	  this.stackOfNumbers = new LinkedStack<Operand<Integer>>();
	  this.stackOfOperators =  new LinkedStack<Operator<Integer>>();
	  //TODO Initialize to your LinkedStack
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
        	stackOfNumbers.push(parser.nextOperand());
          break;
        case OPERATOR:
          //TODO What do we do when we see an operator?
        	//Grab the operator from the parser then based on the number of arguments we pop the appropriate amount from the stack set the operands perform the operation and push the result onto the stack
        	Operator<Integer> operator = parser.nextOperator();
         	stackOfOperators.push(operator);
        	Operator<Integer> previousOperator = stackOfOperators.top();
        	while(checkPriority(operator) <= checkPriority(previousOperator)) {
        		for(int i = previousOperator.getNumberOfArguments()-1; i >= 0; i--) {
            		operator.setOperand(i, stackOfNumbers.pop());
            	}
        		
        	}
        	stackOfOperators.push(operator);
        	/*for(int i = operator.getNumberOfArguments()-1; i >= 0; i--) {
        		operator.setOperand(i, stack.pop());
        	}
        	stack.push(operator.performOperation());
          break;
        default:
          //TODO If we get here, something went terribly wrong
        	throw new IllegalPostFixExpressionException();*/
      
      }
    }

    //TODO What do we return?
    if(stackOfNumbers.size()>1) {
    	throw new IllegalPostFixExpressionException();
    }
    return stackOfNumbers.pop().getValue();
  }
  public int checkPriority(Operator<Integer> operator) {
	  if (operator instanceof ExponentOperator) {
		  return 2;
	  }
	  else if ((operator instanceof MultOperator) || (operator instanceof DivOperator)) {
		  return 1;
	  }
	  else if ((operator instanceof PlusOperator) || (operator instanceof SubOperator)) {
		  return 0;
	  }
	  return -1;
  }

}
