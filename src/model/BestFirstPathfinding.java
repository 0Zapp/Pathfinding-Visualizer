package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javafx.animation.Transition;

public class BestFirstPathfinding extends AbstractPathfinding {

	private String name;

	public BestFirstPathfinding(String name) {

		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {

		return name;
	}

	private ArrayList<Transition> findPath(BestFirstItem startBestFirstItem, BestFirstItem[][] bestFirstItems,
			BestFirstItem goalBestFirstItem) {
		ArrayList<Transition> transitions = new ArrayList<>();

		ArrayList<BestFirstItem> list = new ArrayList<>();
		list.add(startBestFirstItem);

		while (!list.isEmpty()) {

			Collections.sort(list);

			BestFirstItem current = list.get(0);
			current.getItem().setValid(false);
			list.remove(0);

			if (current.getItem().getState() != State.START && current.getItem().getState() != State.GOAL) {
				transitions.add(current.getItem().colorItem(Database.CHECKED_FILL));
			}
			list.addAll(current.getNeighbours(bestFirstItems, transitions, startBestFirstItem, goalBestFirstItem));
			if (current.getItem().getState() == State.GOAL) {
				showPath(startBestFirstItem, bestFirstItems, goalBestFirstItem, transitions);
				break;
			}

		}

		return transitions;
	}

	private void showPath(BestFirstItem startBestFirstItem, BestFirstItem[][] bestFirstItems,
			BestFirstItem goalBestFirstItem, ArrayList<Transition> transitions) {

		Item current = goalBestFirstItem.getItem();

		Stack stack = new Stack();

		while (current != null) {
			stack.push(current);
			current = current.getParentedItem();

		}

		while (!stack.empty()) {
			current = (Item) stack.pop();
			if (current.getState() != State.GOAL && current.getState() != State.START) {
				transitions.add(current.colorItem(Database.PATH_FILL));
			}
		}

	}

	@Override
	public ArrayList<Transition> start(Item[][] items) {
		ArrayList<Transition> transitions = new ArrayList<>();

		Item[][] AllItems = Database.getInstance().getItems();
		BestFirstItem[][] bestFirstItems = new BestFirstItem[30][40];
		BestFirstItem startBestFirstItem = null;
		BestFirstItem goalBestFirstItem = null;

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {
				bestFirstItems[i][j] = new BestFirstItem(AllItems[i][j]);
				if (bestFirstItems[i][j].getItem().getState().equals(State.START)) {
					startBestFirstItem = bestFirstItems[i][j];
				}
				if (bestFirstItems[i][j].getItem().getState().equals(State.GOAL)) {
					goalBestFirstItem = bestFirstItems[i][j];
				}

			}
		}

		transitions.addAll(findPath(startBestFirstItem, bestFirstItems, goalBestFirstItem));

		return transitions;
	}

}
