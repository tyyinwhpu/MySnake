package snake.view;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import snake.entities.Food;
import snake.entities.Ground;
import snake.entities.Snake;
import snake.util.Global;

/**
 * 游戏的主体窗口
 */

public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 3452534179478005853L;
	
	private Snake snake;
	private Food food;
	private Ground ground;
	
	public void display(Snake snake, Food food, Ground ground){
		System.out.println("GamePanel's display");
		
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		//最终会调用paintComponet()重新显示 重绘
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE , Global.HEIGHT * Global.CELL_SIZE );
		
		if(ground != null && snake != null && food != null){
			this.ground.drawMe(g);
			this.snake.drawMe(g);
			this.food.drawMe(g);
		}
		
	}
	
	
}
