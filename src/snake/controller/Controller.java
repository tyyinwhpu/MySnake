package snake.controller;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import snake.Listener.SnakeListener;
import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;
import snake.util.Global;
import snake.view.GamePanel;

public class Controller extends KeyAdapter implements SnakeListener{
	
	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;
	
	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
			
		}
	}
	
	@Override
	public void snakeMoved(Snake snake) {
		
		gamePanel.display(snake, food, ground);
		
		if(food.isSnakeEatFood(snake))  //如果蛇吃到食物
		{
			snake.eatFood(); //蛇尾巴添
			food.newFood(ground.getPoint()); //添新食物
		}
		
		if(ground.isSnakeEatRock(snake)){  //蛇吃到石头
			snake.die();	
		}
		
		if(snake.isEatBody()){  //若蛇吃到身体
			snake.die();
		}
	}
	

	public void startGame(){
		snake.start();
		food.newFood(ground.getPoint());
	}
	
}
