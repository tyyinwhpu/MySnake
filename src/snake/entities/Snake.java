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
 * ̰���ߵ���ʵ��
 */
public class Snake {
	
	//��һ�εķ���
	private int oldDirection ;
	//�µķ���
	private int newDirection;
	private Point oldTail;
	//�ǡ��������ǡ�����
	private boolean life;
	//�����ͱ�ʾ����
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	//�߳Ե�����֮�󣬡���������ʱ�䣬1����
	private static final int SLEEP_TIME = 1000 ;
	
	//�ߵ����壬����Point��LinkedList��ʵ�ֵ�
	private LinkedList<Point> listPoint_body = new LinkedList<Point>();
	
	//��������
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	
	/*���췽��*/
	public Snake(){ 
		init();
	}
	
	/*��ʼ�� ��*/
	public void init(){ 
		
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		
		//Ĭ�Ϸ�������
		oldDirection = newDirection = RIGHT; 
		
		//��ʼ����Ϊ��
		life = true;  
		
		//���ض���Ԫ�ؼӵ�List��ĩβ
		for(int i= 0; i<3; i++){
			listPoint_body.addLast(new Point(x--,y));
		}
	}
	
	public void move(){
		
		//�����·������Ϸ���һ�����Ÿ���
		if(!(oldDirection + newDirection==0)){
			oldDirection = newDirection;
		}
		//TODO ���Ӽ��ٹ���
		
		//ȥ��̰���ߵ�β��
		oldTail = listPoint_body.removeLast();
		
		int x = listPoint_body.getFirst().x;
		int y = listPoint_body.getFirst().y;
		
		//�ж��ߵ��ƶ�����
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
		//2.��ͷ
		listPoint_body.addFirst(newHead);
	}
	
	public void eatFood(){
		System.out.println("Snake's eatFood");
		listPoint_body.addLast(oldTail);
			
		
	}
	
	/**
	 * ̰���߸ı䷽��
	 */
	public void changeDirection(int direction){
		System.out.println("Snake's changeDirection");
		
		//�������·���
		newDirection = direction;  
	}
	
	/**
	 * ���Ƿ�ٵ����Լ�
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
	 * ʹ������
	 */
	public void die(){
		life = false;
	}
	
	/**
	 * ��������
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
				
				move();   //�������ƶ���Ӧ����һ��ѭ����ѭ�����еļ�����
				for(SnakeListener l:listeners){
					l.snakeMoved(Snake.this);   //��������Ԫ�أ����ε���snakeMoved����
				}
				try {
					//�߳Ե�����֮��ģ���ߡ��������Ĺ��̣�ʹ��ֹͣ�ƶ�һ����
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ������ͷ��λ�ýڵ�
	 */
	public Point getHead(){
		return listPoint_body.getFirst();
	}
	
	public void start(){
		new Thread(new SnakeDriver()).start();
	}
	
	/**
	 * �������SnakeListener���뵽Set<SnakeListener>��
	 */
	public void addSnakeListener(SnakeListener listener){
		
		if(listener != null){
			this.listeners.add(listener);
		}
	}
}
