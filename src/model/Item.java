package model;

import javafx.animation.FillTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Item extends Rectangle {

	private boolean valid;
	private State state;
	private Item parentedItem;
	private int i;
	private int j;

	public Item(int i, int j) {
		valid = true;
		this.state = State.STANDARD;
		parentedItem = null;
		this.i = i;
		this.j = j;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Item getParentedItem() {
		return parentedItem;
	}

	public void setParentedItem(Item parentedItem) {
		this.parentedItem = parentedItem;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public Transition colorItem(String color) {
		FillTransition ft = new FillTransition();
		ft.setShape(this);
		ft.setToValue(Color.web(color));
		ft.setDuration(Duration.millis(30));
		return ft;
	}

}
