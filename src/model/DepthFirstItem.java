package model;

import java.util.ArrayList;

import javafx.animation.Transition;

public class DepthFirstItem {

	Item item;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public DepthFirstItem(Item item) {

		this.item = item;
	}

	public ArrayList<DepthFirstItem> getNeighbours(DepthFirstItem[][] depthFirstItems,
			ArrayList<Transition> transitions) {

		ArrayList<DepthFirstItem> list = new ArrayList<>();

		addIfValid(list, depthFirstItems, this.item.getI(), this.item.getJ() - 1, transitions);
		addIfValid(list, depthFirstItems, this.item.getI() + 1, this.item.getJ(), transitions);
		addIfValid(list, depthFirstItems, this.item.getI(), this.item.getJ() + 1, transitions);
		addIfValid(list, depthFirstItems, this.item.getI() - 1, this.item.getJ(), transitions);

		return list;
	}

	private void addIfValid(ArrayList<DepthFirstItem> list, DepthFirstItem[][] depthFirstItems, int i, int j,
			ArrayList<Transition> transitions) {
		if (i < 0 || i >= 30) {
			return;
		}
		if (j < 0 || j >= 40) {
			return;
		}
		if (!depthFirstItems[i][j].getItem().isValid()) {
			return;
		}

		depthFirstItems[i][j].getItem().setParentedItem(this.getItem());

		if (!depthFirstItems[i][j].getItem().getState().equals(State.CHECKED)) {
			list.add(depthFirstItems[i][j]);
		} else {
			list.remove(depthFirstItems[i][j]);
			list.add(depthFirstItems[i][j]);
		}

		if (depthFirstItems[i][j].getItem().getState() != State.START
				&& depthFirstItems[i][j].getItem().getState() != State.GOAL) {
			depthFirstItems[i][j].getItem().setState(State.CHECKED);
			transitions.add(depthFirstItems[i][j].getItem().colorItem(Database.PROCESS_FILL));
		}

	}

}
