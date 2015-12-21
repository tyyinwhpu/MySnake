package snake.game;

/**
 * �������
 */
import java.awt.BorderLayout;

import javax.swing.JFrame;

import snake.controller.Controller;
import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;
import snake.util.Global;
import snake.view.GamePanel;

public class Game {
	public static void main(String[] args){
		
		Snake snake = new Snake();
		Food food = new Food();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(snake, food, ground, gamePanel);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ܴ�С
		frame.setSize(Global.WIDTH * Global.CELL_SIZE + 15, Global.HEIGHT * Global.CELL_SIZE + 45);
		//��Ϸ����С
		gamePanel.setSize(Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE); 
		frame.add(gamePanel, BorderLayout.CENTER);
		
		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);
		
		//��԰���û��Ӧ�������������
		frame.addKeyListener(controller);
		frame.setVisible(true);
		controller.startGame();
	}
}
