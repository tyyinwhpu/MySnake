package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import snake.util.Global;

public class Food extends Point{
	
	private static final long serialVersionUID = -6824066307222022024L;

	/**
	 * �����µ�Food��λ��
	 */
	public void newFood(Point p){
		
		this.setLocation(p);
	}
	
	/**
	 * �ж�̰�����Ƿ�Ե���Food���Ϳ��ߵ�ͷ�Ƿ��뵱ǰFood��λ����ͬ
	 */
	public boolean isSnakeEatFood(Snake snake){
		
		return this.equals(snake.getHead());
		
	}
	
	/**
	 * ��������
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
