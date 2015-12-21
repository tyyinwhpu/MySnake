package snake.game;

/**
 * 程序入口
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
		//框架大小
		frame.setSize(Global.WIDTH * Global.CELL_SIZE + 15, Global.HEIGHT * Global.CELL_SIZE + 45);
		//游戏面板大小
		gamePanel.setSize(Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE); 
		frame.add(gamePanel, BorderLayout.CENTER);
		
		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);
		
		//针对按键没反应，添加面板监听器
		frame.addKeyListener(controller);
		frame.setVisible(true);
		controller.startGame();
	}
}
