package model;

import java.util.ArrayList;

import javafx.animation.Transition;

public class BreadthFirstItem implements Comparable<BreadthFirstItem> {

	Item item;
	int g;// distance from the start

	public BreadthFirstItem(Item item) {

		this.item = item;
		g = 0;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setG(int g) {
		this.g = g;
	}

	public Item getItem() {
		return item;
	}

	public int getG() {
		return g;
	}

	public ArrayList<BreadthFirstItem> getNeighbours(BreadthFirstItem[][] breadthFirstItems,
			ArrayList<Transition> transitions) {

		ArrayList<BreadthFirstItem> list = new ArrayList<>();

		addIfValid(list, breadthFirstItems, this.item.getI() - 1, this.item.getJ(), transitions);
		addIfValid(list, breadthFirstItems, this.item.getI(), this.item.getJ() + 1, transitions);
		addIfValid(list, breadthFirstItems, this.item.getI() + 1, this.item.getJ(), transitions);
		addIfValid(list, breadthFirstItems, this.item.getI(), this.item.getJ() - 1, transitions);

		return list;
	}

	private void addIfValid(ArrayList<BreadthFirstItem> list, BreadthFirstItem[][] breadthFirstItems, int i, int j,
			ArrayList<Transition> transitions) {
		if (i < 0 || i >= 30) {
			return;
		}
		if (j < 0 || j >= 40) {
			return;
		}
		if (!breadthFirstItems[i][j].getItem().isValid()) {
			return;
		}

		if (!breadthFirstItems[i][j].getItem().getState().equals(State.CHECKED)) {
			breadthFirstItems[i][j].setG(Math.abs(this.getG()) + 1);
			breadthFirstItems[i][j].getItem().setParentedItem(this.getItem());
		}

		if (!breadthFirstItems[i][j].getItem().getState().equals(State.CHECKED)) {
			list.add(breadthFirstItems[i][j]);

			if (breadthFirstItems[i][j].getItem().getState() != State.START
					&& breadthFirstItems[i][j].getItem().getState() != State.GOAL) {
				breadthFirstItems[i][j].getItem().setState(State.CHECKED);
				transitions.add(breadthFirstItems[i][j].getItem().colorItem(Database.PROCESS_FILL));
			}
		}
	}

	@Override
	public int compareTo(BreadthFirstItem o) {
		if (this.getG() > o.getG()) {
			return 1;
		} else if (this.getG() < o.getG()) {
			return -1;
		}
		return 0;
	}

}
