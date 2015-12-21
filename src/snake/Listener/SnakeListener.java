package snake.Listener;

import snake.entities.Snake;

public interface SnakeListener {
	
	/* 让蛇每移动一次，就触发这个事件*/
	void snakeMoved(Snake snake);
}
