package _06_Snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {
	public static final Color SNAKE_COLOR = Color.BLUE;
	public static final int BODY_SIZE = 50;

	private SnakeSegment head;
	private ArrayList<SnakeSegment> snake;

	private Direction currentDirection;

	private boolean canMove = true;

	public Snake(Location location) {
		snake = new ArrayList<SnakeSegment>();
		head = new SnakeSegment(location, BODY_SIZE);
		snake.add(head);

		currentDirection = Direction.RIGHT;
	}

	public void feed() {
		//1. add a new SnakeSegment object to the snake
		snake.add(new SnakeSegment(snake.get(0).getLocation(), BODY_SIZE));
	}

	public Location getHeadLocation() {
		//2. return the location of the snake's head
		return head.getLocation();
	}

	public void update() {
		//1. use a switch statement to check on the currentDirection
		//   of the snake and calculate its next x and y position.
		Location currentLoc = head.getLocation();
		Location newLoc=null;
		switch (currentDirection) {
		case RIGHT:{
			newLoc= new Location(currentLoc.x+1,currentLoc.y) ;
			break;
		}
		case LEFT:{
		newLoc=new Location(currentLoc.x-1,currentLoc.y);
			break;
		}
		case UP:{
		newLoc= new Location(currentLoc.x,currentLoc.y-1);
			break;
		}
		case DOWN:{
			newLoc= new Location(currentLoc.x,currentLoc.y+1);
			break;
		}
		default:System.out.println("ur update done goofed");
		}

		//2. Iterate through the SnakeSegments in reverse order
		//2a. Update each snake segment to the location of the segment 
		//    in front of it.
		for(int i=snake.size()-1;i>0;i--) {
			Location a=snake.get(i).getLocation();
			Location b=snake.get(i-1).getLocation();
			a=b;
		}
		
		//3. set the location of the head to the new location calculated in step 1
		head.setLocation(newLoc);

		//4. set canMove to true
		canMove=true;
	}

	public void setDirection(Direction d) {
		//1. set the current direction equal to the passed in Direction only if canMove is true.
		//   set canMove equal to false.
		//   make sure the snake cannot completely reverse directions.
		if(canMove) {
		currentDirection=d;
		}
	}

	public void reset(Location loc) {
		//1. clear the snake
		snake.clear();
		//2. set the location of the head
		head.setLocation(loc);
		//3. add the head to the snake
		snake.add(head);
	}

	public boolean isOutOfBounds() {
		//1. complete the method so it returns true if the head of the snake is outsize of the window
		//   and false otherwise
		if(head.getLocation().x>_00_SnakeGame.WIDTH||head.getLocation().x<0||head.getLocation().y<0||head.getLocation().y>_00_SnakeGame.HEIGHT
				) {
			System.out.println("out");
			return true;
		}
		return false;
	}
	
	public boolean isHeadCollidingWithBody() {
		//1. complete the method so it returns true if the head is located
		//   in the same location as any other body segment
		Location bodyLoc;
		for(int i =1;i<snake.size();i++) {
			 bodyLoc=snake.get(i).getLocation();
			 if(head.getLocation().equals(bodyLoc)) {
				 System.out.println("body");
					return true;
				}
		}
		return false;
	}

	public boolean isLocationOnSnake(Location loc) {
		//1. complete the method so it returns true if the passed in
		//   location is located on the snake
		for(int i =1;i<snake.size();i++) {
			Location bodyLoc=snake.get(i).getLocation();
			if(bodyLoc.equals(loc)) {
				return true;
			}
		}
		return false;
	}

	public void draw(Graphics g) {
		for (SnakeSegment s : snake) {
			s.draw(g);
		}
	}
}
