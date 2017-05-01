package asteroids.program;

import java.util.Set;

import asteroids.model.Entity;

class AnyEntity extends EntityExpression {

	protected AnyEntity() throws IllegalArgumentException {
		Object operand = null;
		do {Set<? extends Object> entities = getExpressionShip().getEntityWorld().getWorldEntities();
		operand = entities.stream().skip((int)(entities.size() * Math.random())).findFirst();
		setOperand((Entity)operand);
		}		
		while (getOperand() == getExpressionShip());

	}
}
