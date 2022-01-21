package language.arith;

import language.BinaryOperator;
import language.Operand;
import language.Operator;

/**
 * The {@code SubOperator} is an operator that performs subtraction on two
 * integers.
 * 
 * @author jcollard, jddevaug
 *
 */
public class DivOperator extends BinaryOperator<Integer> {

	/**
	 * Performs this operation on values supplied via the
	 * {@link Operator#setOperand(int, Operand)} method and returns the resulting
	 * {@link Operand}.
	 * 
	 * @return the result of applying this {@link Operator} to its {@link Operand}s
	 * @throws IllegalStateException if the required {@link Operand}s were not set.
	 */
	@Override
	public Operand<Integer> performOperation() {
		Operand<Integer> op0 = this.getOp0();
		Operand<Integer> op1 = this.getOp1();
		if (op0 == null || op1 == null) {
			throw new IllegalStateException("Could not perform operation prior to operands being set.");
		}
		Integer result = op0.getValue() / op1.getValue();
		return new Operand<Integer>(result);
	}
	public void setOperand(int i, Operand<Integer> operand) {
		if(operand!= null && operand.getValue() == 0 && i == 1) {
			throw new IllegalStateException("Cannot set the denominator to 0");
		}
		//Call BinaryOperator's setOperand
		super.setOperand(i, operand);
	}
}