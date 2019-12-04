package model;

import java.util.ArrayList;
import java.util.Collection;

import javafx.animation.Transition;

public class BestFirstItem implements Comparable<BestFirstItem> {

	Item item;
	int h;// distance from the goal

	public BestFirstItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getH() {
		return h;
	}

	@Override
	public int compareTo(BestFirstItem o) {
		if (this.getH() > o.getH()) {
			return 1;
		} else if (this.getH() < o.getH()) {
			return -1;
		}
		return 0;
	}

	public ArrayList<BestFirstItem> getNeighbours(BestFirstItem[][] bestFirstItems, ArrayList<Transition> transitions,
			BestFirstItem startBestFirstItem, BestFirstItem goalBestFirstItem) {

		ArrayList<BestFirstItem> list = new ArrayList<>();

		addIfValid(list, bestFirstItems, this.item.getI() - 1, this.item.getJ(), transitions, goalBestFirstItem);
		addIfValid(list, bestFirstItems, this.item.getI(), this.item.getJ() + 1, transitions, goalBestFirstItem);
		addIfValid(list, bestFirstItems, this.item.getI() + 1, this.item.getJ(), transitions, goalBestFirstItem);
		addIfValid(list, bestFirstItems, this.item.getI(), this.item.getJ() - 1, transitions, goalBestFirstItem);

		return list;
	}

	private void addIfValid(ArrayList<BestFirstItem> list, BestFirstItem[][] bestFirstItems, int i, int j,
			ArrayList<Transition> transitions, BestFirstItem goalBestFirstItem) {
		if (i < 0 || i >= 30) {
			return;
		}
		if (j < 0 || j >= 40) {
			return;
		}
		if (!bestFirstItems[i][j].getItem().isValid()) {
			return;
		}
		if (!bestFirstItems[i][j].getItem().getState().equals(State.CHECKED)) {
			bestFirstItems[i][j].getItem().setParentedItem(this.getItem());

			bestFirstItems[i][j].setH(Math.abs(i - goalBestFirstItem.getItem().getI())
					+ Math.abs(j - goalBestFirstItem.getItem().getJ()));
		}
		if (!bestFirstItems[i][j].getItem().getState().equals(State.CHECKED)) {
			list.add(bestFirstItems[i][j]);

			if (bestFirstItems[i][j].getItem().getState() != State.START
					&& bestFirstItems[i][j].getItem().getState() != State.GOAL) {
				bestFirstItems[i][j].getItem().setState(State.CHECKED);
				transitions.add(bestFirstItems[i][j].getItem().colorItem(Database.PROCESS_FILL));
			}
		}

	}
}
