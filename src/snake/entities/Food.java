package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import snake.util.Global;

public class Food extends Point{
	
	private static final long serialVersionUID = -6824066307222022024L;

	/**
	 * 设置新的Food的位置
	 */
	public void newFood(Point p){
		
		this.setLocation(p);
	}
	
	/**
	 * 判断贪吃蛇是否吃掉了Food，就看蛇的头是否与当前Food的位置相同
	 */
	public boolean isSnakeEatFood(Snake snake){
		
		return this.equals(snake.getHead());
		
	}
	
	/**
	 * 绘制自身
	 */
	public void drawMe(Graphics g){
		
		g.fill3DRect(x * Global.CELL_SIZE, 
				y * Global.CELL_SIZE, 
				Global.CELL_SIZE, 
				Global.CELL_SIZE, 
				true);
		g.setColor(Color.RED);
	}
}
