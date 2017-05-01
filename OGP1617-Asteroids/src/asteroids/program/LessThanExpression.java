package asteroids.program;

class LessThanExpression extends BinaryArithmeticExpression {
	protected LessThanExpression(MyExpression leftExpression, MyExpression rightExpression)
			throws IllegalArgumentException {
		super(leftExpression, rightExpression);
	}

	@Override
	protected Object getExpressionResult() {
		return (double)getLeftOperand().getExpressionResult() < (double)getRightOperand().getExpressionResult();
	}

	@Override
	public boolean hasAsSubExpression(MyExpression expression) {
		// TODO Auto-generated method stub
		return false;
	}

}
