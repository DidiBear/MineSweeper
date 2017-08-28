package disopy.MineSweeper.game;


import disopy.MineSweeper.engine.utils.dev.ExceptionUtils;

public class Cell {
	
	public static enum Event {NONE, HOVER, PRESSED}
	public static enum State {HIDE, OPEN, FLAGGED}
	public static enum Type {NUMBER, MINE}
	
	private Type type;
	private int value;
	private State state;
	private Event event;
	
	public Cell(){
		setValue(0);
		setType(Type.NUMBER);
		setState(State.HIDE);
		setEvent(Event.NONE);
		
	}
	
	
	public boolean isMine(){
		return Type.MINE.equals(type);
	}
	public boolean isNumber(){
		return Type.NUMBER.equals(type);
	}
	public boolean isHide(){
		return State.HIDE.equals(state);
	}
	public boolean isOpen(){
		return State.OPEN.equals(state);
	}
	public boolean isFlagged(){
		return State.FLAGGED.equals(state);
	}
	public boolean isHover(){
		return Event.HOVER.equals(event);
	}
	public boolean isPressed(){
		return Event.PRESSED.equals(event);
	}
	
	public void open(){
		setState(Cell.State.OPEN);
	}
	public void flag(){
		if(isHide())
			setState(Cell.State.FLAGGED);
		else if (isFlagged())
			hide();
		
	}
	public void hide(){
		setState(Cell.State.HIDE);
	}
	
	public void setHoverEvent(){
		setEvent(Cell.Event.HOVER);
	}
	public void setPressedEvent(){
		setEvent(Cell.Event.PRESSED);
	}
	public void setNoneEvent(){
		setEvent(Cell.Event.HOVER);
	}
	
	public int getValue(){
		return value;
	}
	public void setValue(int value){
		this.value = value;
	}
	public State getState(){
		return state;
	}
	public void setState(State state){
		ExceptionUtils.verifyNullParameters(state);
		this.state = state;
	}
	public Type getType(){
		return type;
	}
	public void setType(Type type){
		ExceptionUtils.verifyNullParameters(type);
		this.type = type;
	}
	public Event getEvent(){
		return event;
	}
	public void setEvent(Event event){
		this.event = event;
	}
}
