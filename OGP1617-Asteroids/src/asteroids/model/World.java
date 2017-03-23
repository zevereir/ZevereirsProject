package asteroids.model;

import java.util.HashSet;
import java.util.Set;
import asteroids.util.ModelException;
import be.kuleuven.cs.som.annotate.*;
/**
 * a class that describes the world 
 * 
 * @version 21_Mar_19u
 * @authors Sieben Bocklandt and Ruben Broekx

 */
public class World {

	///CONSTRUCTOR///
	public World(double width, double height){
		setWorldWidth(width);
		setWorldHeight(height);
	}
	
	public World() {
		this(UPPER_WORLD_BOUND_WIDTH, UPPER_WORLD_BOUND_HEIGHT);
	}
	
	///BASIC PROPERTIES///
	private double width;
	private double height;
	
	
	///DEFAULTS///
	private final static double  UPPER_WORLD_BOUND_WIDTH = Double.MAX_VALUE;
	private final static double  UPPER_WORLD_BOUND_HEIGHT = Double.MAX_VALUE;
	private final static double OMEGA = 99/100;
	
	
	///GETTERS///
	
	
	public double getWorldWidth(){
		return this.width;
	}
	
	public double getWorldHeight(){
		return this.height;
	}
	
	
	///SETTERS///
	
	public void setWorldWidth(double width){
		if (width > UPPER_WORLD_BOUND_WIDTH || width < 0)
			width = UPPER_WORLD_BOUND_WIDTH;
		this.width = width;
	}
	
	public void setWorldHeight(double height){
		if (height > UPPER_WORLD_BOUND_HEIGHT|| height < 0)
			height = UPPER_WORLD_BOUND_HEIGHT;
		this.height = height;
	}
	
	///CONNECTIONS WITH OTHER CLASSES///
	 private final Set<Ship> ships = new HashSet<Ship>();
	 private final Set<Bullet> bullets = new HashSet<Bullet>();
	 
	///ADDERS///
	
	 public void addEntityToWorld(Entity entity) throws ModelException {
		 if (canHaveAsEntity(entity)){
		 	if (entity instanceof Ship)
			 	ships.add((Ship)entity);
		 	else 
		 		bullets.add((Bullet)entity);}
		 else{
			throw new ModelException("the entity cannot be added to this world");}
	 }
	 
	
	 
	 ///HAS///
	 public boolean hasAsEntity(Entity entity){
		 if (entity instanceof Ship)
			 return this.ships.contains(entity);
		 else
			 return this.bullets.contains(entity);
	 }        
	 
	
	 ///CHECKERS///
	 
	 //OVERLAP,TERMINATE,... NOG BEKIJKEN//
	 public boolean canHaveAsEntity(Entity entity){
		 return ((!this.hasAsEntity(entity)) &&(entity.getEntityWorld()==null) &&
				 (entity.entityFitsInWorld(entity,this)));
	
	 }
	
	 ///TERMINATION ETC///
	 private boolean isTerminated=false;
	 
	 public boolean isWorldTerminated(){
		 null
	 }
}
