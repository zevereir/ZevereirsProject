package asteroids.program;

import java.util.List;

import asteroids.model.Entity;

class XVelocityExpression extends OnEntityExpression {
	protected XVelocityExpression(MyExpression operand) throws IllegalArgumentException {
		super(operand);
	}

	@Override
	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		
		return ((Entity)getOperand().getExpressionResult(program, null,null)).getEntityVelocityX();
	}

}
