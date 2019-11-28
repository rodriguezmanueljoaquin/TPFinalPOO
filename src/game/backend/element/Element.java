package game.backend.element;

import game.backend.move.Direction;

public abstract class Element {
	
	public abstract boolean isMovable();
	
	public abstract String getKey();

	public boolean isSpecial(){ return false; }

	public String getLabel()
	{
		return "";
	}
	
	public String getFullKey() {
		return getKey();
	}

	public boolean isSolid() {
		return true;
	}
	
	public Direction[] explode() {
		return null;
	}
	
	public long getScore() {
		return 0;
	}
	
}
