package asteroids.program;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

class IfElseStatement extends MyStatement {

	/// CONSTRUCTOR ///
	
	public IfElseStatement(MyExpression condition, MyStatement ifBody, MyStatement elseBody) {
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	
	/// BASIC PROPERTIES ///

	private MyExpression condition;
	private MyStatement ifBody;
	private MyStatement elseBody;
	
	
	/// GETTERS ///

	protected MyExpression getCondition() {
		return this.condition;
	}
	
	
	/// SETTERS ///

	private void setCondition(MyExpression condition) {
		this.condition = condition;
	}

	private void setElseBody(MyStatement elseBody) {
		this.elseBody = elseBody;
	}

	private void setIfBody(MyStatement ifBody) {
		this.ifBody = ifBody;
	}
	
	
	/// EVALUATION ///

	@Override
	public void evaluate(Program program, List<MyExpression> actualArgs) {
		setStatementProgram(program);

		if (canHaveAsCondition(condition, actualArgs, null)) {
			if ((boolean) condition.getExpressionResult(program, actualArgs, null)) {
				ifBody.evaluate(program, actualArgs);
			} else if (elseBody != null) {
				elseBody.evaluate(program, actualArgs);
			}
		} else
			throw new IllegalArgumentException();
	}

	public Object evaluateInFunction(Program program, List<MyExpression> actualArgs, MyFunction function) {
		setStatementProgram(program);

		if ((boolean) getCondition().getExpressionResult(program, actualArgs, function)) {
			if (ifBody instanceof AssignmentStatement)
				((AssignmentStatement) ifBody).assignLocalVariable(getStatementProgram(), actualArgs, function);
			else
				return ifBody.evaluateInFunction(getStatementProgram(), actualArgs, null);
		} else if (elseBody != null) {
			if (elseBody instanceof AssignmentStatement)
				((AssignmentStatement) elseBody).assignLocalVariable(getStatementProgram(), actualArgs, function);
			else
				return elseBody.evaluateInFunction(getStatementProgram(), actualArgs, null);
		}
		return null;
	}

	@Override
	public void skipEvaluationUntilLocation(Program program, List<MyExpression> actualArgs, SourceLocation location) {
		if ((boolean) condition.getExpressionResult(program, actualArgs, null))
			ifBody.skipEvaluationUntilLocation(program, actualArgs, location);
		else if (elseBody != null)
			elseBody.skipEvaluationUntilLocation(program, actualArgs, location);
	}

}
