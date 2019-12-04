package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javafx.animation.Transition;

public class BreadthFirstPathfinding extends AbstractPathfinding {

	private String name;

	public BreadthFirstPathfinding(String name) {
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

	private void showPath(BreadthFirstItem startBreadthFirstItem, BreadthFirstItem[][] breadthFirstItems,
			BreadthFirstItem goalBreadthFirstItem, ArrayList<Transition> transitions) {

		Item current = goalBreadthFirstItem.getItem();

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

	private ArrayList<Transition> findPath(BreadthFirstItem startBreadthFirstItem,
			BreadthFirstItem[][] breadthFirstItems, BreadthFirstItem goalBreadthFirstItem) {

		ArrayList<Transition> transitions = new ArrayList<>();
		ArrayList<BreadthFirstItem> list = new ArrayList<>();
		list.add(startBreadthFirstItem);

		while (!list.isEmpty()) {

			Collections.sort(list);

			BreadthFirstItem current = list.get(0);
			current.getItem().setValid(false);
			list.remove(0);

			if (current.getItem().getState() != State.START && current.getItem().getState() != State.GOAL) {
				transitions.add(current.getItem().colorItem(Database.CHECKED_FILL));
			}
			list.addAll(current.getNeighbours(breadthFirstItems, transitions));
			if (current.getItem().getState() == State.GOAL) {
				showPath(startBreadthFirstItem, breadthFirstItems, goalBreadthFirstItem, transitions);
				break;
			}
		}

		return transitions;
	}

	@Override
	public ArrayList<Transition> start(Item[][] items) {
		ArrayList<Transition> transitions = new ArrayList<>();

		Item[][] AllItems = Database.getInstance().getItems();
		BreadthFirstItem[][] breadthFirstItems = new BreadthFirstItem[30][40];
		BreadthFirstItem startBreadthFirstItem = null;
		BreadthFirstItem goalBreadthFirstItem = null;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {
				breadthFirstItems[i][j] = new BreadthFirstItem(AllItems[i][j]);
				if (breadthFirstItems[i][j].getItem().getState().equals(State.START)) {
					startBreadthFirstItem = breadthFirstItems[i][j];
				}
				if (breadthFirstItems[i][j].getItem().getState().equals(State.GOAL)) {
					goalBreadthFirstItem = breadthFirstItems[i][j];
				}

			}
		}
		transitions.addAll(findPath(startBreadthFirstItem, breadthFirstItems, goalBreadthFirstItem));

		return transitions;
	}

}
