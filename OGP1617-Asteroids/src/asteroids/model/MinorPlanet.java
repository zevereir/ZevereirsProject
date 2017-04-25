package asteroids.model;

import asteroids.part2.CollisionListener;
/**
 * A class that describes minor planets (asteroids and planetoids). A minor planet has a position and
 * a velocity, both are described in a Cartesian xy-field. It also has an
 * orientation and a radius which defines its circular shape. The mass, density and maximum total velocity are 
 * the minor planets last properties.
 * 
 * @invar 	The position is a valid position.
 * 		  | isValidPosition(this.getEntityPositionX,this.getEntityPositionY)
 * @invar 	The velocity is a valid velocity.
 * 		  | isValidVelocity(this.getEntityVelocityX,this.getEntityVelocityY)
 * @invar 	The orientation is a valid orientation.
 * 		  | isValidOrientation(this.getEntityOrientation)
 * @invar 	The radius is a valid radius.
 * 		  | isValidRadius(this.getEntityRadius)
 * @invar 	The mass is a valid mass.
 * 		  | isValidMass(this.getEntityMass)
 * @invar 	The density is a valid density.
 * 		  | isValidDensity(this.getEntityDensity)
 * 
 * @version 8th of April
 * @authors Sieben Bocklandt and Ruben Broekx
 */
public abstract class MinorPlanet extends Entity{
	///CONSTRUCTOR///
	/**
	 * Initializes a new minor planet with given parameters.
	 * 
	 * @note	This constructor will never be used in facade, but we made it to increase the adaptability.
	 * 			In the future, it would be possible that each minor planet has a different maximum velocity and density.
	 * 			In this case, this constructor would come in handy.
	 * 
	 * @param 	positionX
	 *          The horizontal position of the minor planet in kilometers.
	 * @param 	positionY
	 *          The vertical position of the minor planet in kilometers.
	 * @param 	velocityX
	 *          The horizontal starting velocity of the minor planet in kilometers per second.
	 * @param 	velocityY
	 *          The vertical starting velocity of the minor planet in kilometers per second.
	 * @param 	radius
	 *          The radius that defines the circular shape of the minor planet in kilometers.
	 * @param 	orientation
	 *          The orientation of the minor planet in radians.
	 * @param 	mass
	 *          The mass of the minor planet in kilograms.
	 * @param 	maxVelocity
	 *          The maximum velocity of the minor planet in kilometers per second.
	 * @param 	density
	 *          The density of the minor planet in kilograms/km^3.         
	 * 
	 * @effect	This minor planet will be initialized as a new entity with a given position (x, y), velocity (velocityX, velocityY),
	 *			radius, density, mass and maximum velocity.
	 *		  | super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density)	 	
	 */
	protected MinorPlanet(double positionX, double positionY, double velocityX, double velocityY, double radius,
			double orientation, double mass, double maxVelocity, double density) {
		super(positionX, positionY, velocityX, velocityY, radius, orientation, mass, maxVelocity, density);
		
	}
	
	///DEFAULTS///
	/**
	 * The lower radius for a minor planet.
	 */
	protected final static double LOWER_MINOR_PLANET_RADIUS = 5;
	
	/**
	 * Returns the lower radius a minor planet can have.
	 * @return the radius.
	 * 			@see implementation
	 */
	protected static double getDefaultMinorPlanetRadius(){
		return LOWER_MINOR_PLANET_RADIUS;
	}
	
	///GETTERS///
	/**
	 * Returns the mass of the minor planet.
	 * @return the mass.
	 * 			@see implementation
	 */
	@Override
	public double getEntityMass() {
		return MassFormula(getEntityRadius(),getEntityDensity());
	}
	
	///SETTERS///

	/**
	 * Set the mass of the bullet to a given value.
	 * 
	 * @param 	mass
	 * 			The new mass of the bullet.
	 * 
	 * @post 	The new mass will be equal to the given mass.
	 * 			@see implementation
	 * @throws  IllegalArgumentException
	 * 			if the given mass isn't valid.
	 * 			@see implementation
	 */
	@Override
	protected void setEntityMass(double mass) {
		if (isValidMass(mass))
			this.mass = mass;
		else
			throw new IllegalArgumentException();
		
		
	}	
	
	///CHECKERS///
	/**
	 * Checks if a mass is valid for this minor planet.
	 * 
	 * @param 	mass
	 * 			The mass that has to be checked.
	 * 
	 * @return 	The boolean that checks the mass.
	 * 			@see implementation
	 */
	@Override
	protected boolean isValidMass(double mass) {
		return (mass != Double.NaN) ;
	}

	/**
	 * Checks if a radius is valid for this minor planet.
	 * 
	 * @param 	radius
	 * 			The radius that has to be checked.
	 * 
	 * @return 	The boolean that checks the radius.
	 * 			@see implementation
	 */
	@Override
	protected boolean isValidRadius(double radius) {
		return (radius >= LOWER_MINOR_PLANET_RADIUS);
		}


	
	///COLLISION///
	/**
	 * A method that resolves the collision between an minor planet and a boundary.
	 * @param collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param collisionListener
	 * 			A variable used to visualize the explosions.
	 * @post the collision will be resolved by checking if the minor planet collides with a 
	 * 			horizontal or vertical boundary and inverting the respective velocity.
	 * 			|if collideHorizontalBoundary(this,collisionPosition)
	 * 			|new.getEntityVelocityY == -this.getEntityVelocityY
	 * 			|else
	 * 			|new.getEntityVelocityX == -this.getEntityVelocityX
	 * @effect after the change of velocity, the entity_positionlist in world will be updated.
	 * 			|this.getEntityWorld().updatePositionListAfterCollision(this,defaultEvolvingTime)
	 */
	protected void entityAndBoundaryCollide(double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener) {
		double VelocityX = this.getEntityVelocityX();
		double VelocityY = this.getEntityVelocityY();

		if (collideHorizontalBoundary(this, collisionPosition))
			this.setEntityVelocity(VelocityX, -VelocityY);

		else
			this.setEntityVelocity(-VelocityX, VelocityY);
		World world = this.getEntityWorld();
		world.updatePositionListAfterCollision(this, defaultEvolvingTime);
	}
	
	/**
	 * A method that resolves the collision between two minor planets.
	 * @param minorPlanet
	 * 			The minorPlanet that will collide with the minor planet where the method is invoked on.
	 * @param collisionPosition
	 * 			An array that contains the x- and y-value of the position where the collision will happen.
	 * @param defaultEvolvingTime
	 * 			The time until the collision will happen.
	 * @param collisionListener
	 * 			A variable used to visualize the explosions.
	 * @effect Because the two entities are both minor planets, doubleShipOrMinorPlanetCollide will be used. 
	 * 			After this method, the positionlist in world will be updated.
	 * 			@see implementation
	 */
	protected void entityAndMinorPlanetCollide(MinorPlanet minorPlanet,double[] collisionPosition,double defaultEvolvingTime,CollisionListener collisionListener){
		this.doubleShipOrMinorPlanetCollide(minorPlanet);
		World world = this.getEntityWorld();
		world.updatePositionListAfterCollision(this,minorPlanet, defaultEvolvingTime);
	}

}
