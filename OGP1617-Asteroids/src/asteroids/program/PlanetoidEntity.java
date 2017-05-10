package asteroids.program;

import java.util.List;

class PlanetoidEntity extends EntityExpression {

	protected PlanetoidEntity() throws IllegalArgumentException {
		//
	}

	protected Object getExpressionResult(Program program, List<MyExpression> actualArgs,MyFunction function) {
		setExpressionProgram(program);
		return getClosestEntity(getExpressionShip().getEntityWorld().getWorldSpecificEntities("Planetoid"));
	}
	
}
