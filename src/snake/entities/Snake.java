package snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import snake.Listener.SnakeListener;
import snake.util.Global;

/**
 * 贪吃蛇的蛇实体
 */
public class Snake {
	
	//上一次的方向
	private int oldDirection ;
	//新的方向
	private int newDirection;
	private Point oldTail;
	//是“生”还是“死”
	private boolean life;
	//用整型表示方向
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	//蛇吃掉东西之后，“消化”的时间，1秒钟
	private static final int SLEEP_TIME = 1000 ;
	
	//蛇的身体，是用Point的LinkedList来实现的
	private LinkedList<Point> listPoint_body = new LinkedList<Point>();
	
	//监听器组
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	
	/*构造方法*/
	public Snake(){ 
		init();
	}
	
	/*初始化 蛇*/
	public void init(){ 
		
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		
		//默认方向向右
		oldDirection = newDirection = RIGHT; 
		
		//初始生命为真
		life = true;  
		
		//将特定的元素加到List的末尾
		for(int i= 0; i<3; i++){
			listPoint_body.addLast(new Point(x--,y));
		}
	}
	
	public void move(){
		
		//若最新方向与老方向不一样，才更新
		if(!(oldDirection + newDirection==0)){
			oldDirection = newDirection;
		}
		//TODO 增加加速功能
		
		//去掉贪吃蛇的尾巴
		oldTail = listPoint_body.removeLast();
		
		int x = listPoint_body.getFirst().x;
		int y = listPoint_body.getFirst().y;
		
		//判断蛇的移动方向
		switch(oldDirection){
		case UP:
			y--;
			if(y < 0)
				y = Global.HEIGHT - 1;
			break;
		case DOWN:
			y++;
			if(y >= Global.HEIGHT)
				y = 0;
			break;
		case LEFT:
			x--;
			if(x < 0)
				x = Global.WIDTH -1;
			break;
		case RIGHT:
			x++;
			if(x >= Global.WIDTH)
				x = 0;
			break;
			
		}
		
		Point newHead = new Point(x,y);
		//2.加头
		listPoint_body.addFirst(newHead);
	}
	
	public void eatFood(){
		System.out.println("Snake's eatFood");
		listPoint_body.addLast(oldTail);
			
		
	}
	
	/**
	 * 贪吃蛇改变方向
	 */
	public void changeDirection(int direction){
		System.out.println("Snake's changeDirection");
		
		//传递最新方向
		newDirection = direction;  
	}
	
	/**
	 * 蛇是否迟到了自己
	 * @return
	 */
	public boolean isEatBody(){
		System.out.println("Snake's eatBody");
		
		for(int i = 1; i < listPoint_body.size(); i++){
			if(listPoint_body.get(i).equals(this.getHead()) ){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 使蛇死掉
	 */
	public void die(){
		life = false;
	}
	
	/**
	 * 绘制自身
	 */
	public void drawMe(Graphics g){
		
		g.setColor(Color.BLUE);
		for(Point p :listPoint_body){
			g.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
			
		}
	}
	
	private class SnakeDriver implements Runnable{

	@Override
		public void run() {
		
			while(life){
				
				move();   //蛇在这移动，应触发一个循环，循环所有的监听器
				for(SnakeListener l:listeners){
					l.snakeMoved(Snake.this);   //遍历所有元素，依次调用snakeMoved方法
				}
				try {
					//蛇吃掉东西之后，模拟蛇“消化”的过程，使蛇停止移动一秒钟
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 返回蛇头的位置节点
	 */
	public Point getHead(){
		return listPoint_body.getFirst();
	}
	
	public void start(){
		new Thread(new SnakeDriver()).start();
	}
	
	/**
	 * 将传入的SnakeListener加入到Set<SnakeListener>中
	 */
	public void addSnakeListener(SnakeListener listener){
		
		if(listener != null){
			this.listeners.add(listener);
		}
	}
}
