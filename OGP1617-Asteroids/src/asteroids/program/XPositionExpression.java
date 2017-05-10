package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

class XPositionExpression extends OnEntityExpression {
	
	/// CONSTRUCTOR ///
	
	protected XPositionExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	
	/// GETTERS ///
	
	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setExpressionProgram(program);

		return ((Entity) getOperand().getExpressionResult(program, null, null)).getEntityPositionX();
	}

}
