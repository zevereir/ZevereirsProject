package asteroids.program;

import java.util.List;

class AsteroidEntity extends EntityExpression {

	protected AsteroidEntity() throws IllegalArgumentException {
	
	}

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Asteroid"));
	}
	
}
