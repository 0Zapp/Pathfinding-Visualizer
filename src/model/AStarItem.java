package model;

import java.util.ArrayList;

import javafx.animation.Transition;

public class AStarItem implements Comparable<AStarItem> {

	Item item;
	int f;// total score
	int h;// distance from the goal
	int g;// distance from the start

	public AStarItem(Item item, int h, int g) {
		this.item = item;
		this.h = h;
		this.g = g;
		f = h + g;
	}

	public AStarItem(Item item) {
		this.item = item;
		this.h = 0;
		this.g = 0;
		this.f = 0;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public ArrayList<AStarItem> getNeighbours(AStarItem[][] aStarItems, ArrayList<Transition> transitions,
			AStarItem startAStarItem, AStarItem goalAStarItem) {

		ArrayList<AStarItem> list = new ArrayList<>();

		addIfValid(list, aStarItems, this.item.getI() - 1, this.item.getJ(), transitions, startAStarItem,
				goalAStarItem);
		addIfValid(list, aStarItems, this.item.getI(), this.item.getJ() + 1, transitions, startAStarItem,
				goalAStarItem);
		addIfValid(list, aStarItems, this.item.getI() + 1, this.item.getJ(), transitions, startAStarItem,
				goalAStarItem);
		addIfValid(list, aStarItems, this.item.getI(), this.item.getJ() - 1, transitions, startAStarItem,
				goalAStarItem);

		return list;
	}

	private void addIfValid(ArrayList<AStarItem> list, AStarItem[][] aStarItems, int i, int j,
			ArrayList<Transition> transitions, AStarItem startAStarItem, AStarItem goalAStarItem) {
		if (i < 0 || i >= 30) {
			return;
		}
		if (j < 0 || j >= 40) {
			return;
		}
		if (!aStarItems[i][j].getItem().isValid()) {
			return;
		}

		if (!aStarItems[i][j].getItem().getState().equals(State.CHECKED)) {
			aStarItems[i][j].setG(Math.abs(this.getG()) + 1);
			aStarItems[i][j].getItem().setParentedItem(this.getItem());
		}
		if ((this.getG() + 1) < aStarItems[i][j].getG()) {
			aStarItems[i][j].setG(Math.abs(this.getG()) + 1);// check all four neightbours for the shortest path
			aStarItems[i][j].getItem().setParentedItem(this.getItem());
		}

		aStarItems[i][j]
				.setH(Math.abs(i - goalAStarItem.getItem().getI()) + Math.abs(j - goalAStarItem.getItem().getJ()));
		aStarItems[i][j].setF(aStarItems[i][j].getG() + aStarItems[i][j].getH());
		if (!aStarItems[i][j].getItem().getState().equals(State.CHECKED)) {
			list.add(aStarItems[i][j]);

			if (aStarItems[i][j].getItem().getState() != State.START
					&& aStarItems[i][j].getItem().getState() != State.GOAL) {
				aStarItems[i][j].getItem().setState(State.CHECKED);
				transitions.add(aStarItems[i][j].getItem().colorItem(Database.PROCESS_FILL));
			}
		}
	}

	@Override
	public int compareTo(AStarItem o) {
		if (this.getF() > o.getF()) {
			return 1;
		} else if (this.getF() < o.getF()) {
			return -1;
		} else if (this.getH() > o.getH()) {
			return 1;
		} else if (this.getH() < o.getH()) {
			return -1;
		}
		return 0;
	}

}
