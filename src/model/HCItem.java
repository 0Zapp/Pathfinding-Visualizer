package model;

import java.util.ArrayList;
import java.util.Collection;

import javafx.animation.Transition;

public class HCItem implements Comparable<HCItem> {

	Item item;
	int h;// distance from the goal

	public HCItem(Item item) {
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public int getH() {
		return h;
	}

	@Override
	public int compareTo(HCItem o) {
		if (this.getH() > o.getH()) {
			return 1;
		} else if (this.getH() < o.getH()) {
			return -1;
		}
		return 0;
	}

	public ArrayList<HCItem> getNeighbours(HCItem[][] hCItems, ArrayList<Transition> transitions, HCItem goalHCItem) {

		ArrayList<HCItem> list = new ArrayList<>();

		addIfValid(list, hCItems, this.item.getI() - 1, this.item.getJ(), transitions, goalHCItem);
		addIfValid(list, hCItems, this.item.getI(), this.item.getJ() + 1, transitions, goalHCItem);
		addIfValid(list, hCItems, this.item.getI() + 1, this.item.getJ(), transitions, goalHCItem);
		addIfValid(list, hCItems, this.item.getI(), this.item.getJ() - 1, transitions, goalHCItem);

		return list;

	}

	private void addIfValid(ArrayList<HCItem> list, HCItem[][] hCItems, int i, int j, ArrayList<Transition> transitions,
			HCItem goalHCItem) {
		if (i < 0 || i >= 30) {
			return;
		}
		if (j < 0 || j >= 40) {
			return;
		}
		if (!hCItems[i][j].getItem().isValid()) {
			return;
		}

		hCItems[i][j].getItem().setParentedItem(this.getItem());

		hCItems[i][j].setH(Math.abs(i - goalHCItem.getItem().getI()) + Math.abs(j - goalHCItem.getItem().getJ()));

		if (!hCItems[i][j].getItem().getState().equals(State.CHECKED)) {
			list.add(hCItems[i][j]);

			if (hCItems[i][j].getItem().getState() != State.START && hCItems[i][j].getItem().getState() != State.GOAL) {
				hCItems[i][j].getItem().setState(State.CHECKED);
				transitions.add(hCItems[i][j].getItem().colorItem(Database.PROCESS_FILL));
			}
		}

	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setH(int h) {
		this.h = h;
	}
}
