package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import snake.util.Global;

public class Ground {
	
	private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];
	
	public Ground(){
		for(int x= 0; x < Global.WIDTH; x++){
				rocks[x][0] = 1;
				rocks[x][Global.HEIGHT-1 ] = 1;
			
		}
		
	}
	
	public boolean isSnakeEatRock(Snake snake){
		
		for(int x=0; x < Global.WIDTH; x++){
			for(int y=0; y < Global.HEIGHT; y++){
				if(rocks[x][y] == 1 && x == snake.getHead().x && y == snake.getHead().y){
					return true;
				}
			}
		}
		return false;
	}
	
	public Point getPoint(){
		
		int x,y;
		Random random = new Random();
		do{
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
		}while(rocks[x][y] == 1 );
		return new Point(x,y);  //封装的随机点
		
	}
	
	/**
	 * 绘制自身
	 */
	public void drawMe(Graphics g){
		
		g.setColor(Color.DARK_GRAY);
		
		for(int i=0; i< Global.WIDTH; i++){
			for(int j =0; j < Global.HEIGHT; j++){
				if(rocks[i][j] == 1){
					//Paints a 3-D highlighted rectangle filled with the current color
					g.fill3DRect(i * Global.CELL_SIZE, 
							j * Global.CELL_SIZE, 
							Global.CELL_SIZE, 
							Global.CELL_SIZE, 
							true);
				
				}
			}	
		
		}
	}
}
