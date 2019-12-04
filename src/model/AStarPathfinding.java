package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

import javafx.animation.Transition;

public class AStarPathfinding extends AbstractPathfinding {

	private String name;

	public AStarPathfinding(String name) {
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

	private ArrayList<Transition> findPath(AStarItem startAStarItem, AStarItem[][] aStarItems,
			AStarItem goalAStarItem) {

		ArrayList<Transition> transitions = new ArrayList<>();
		ArrayList<AStarItem> list = new ArrayList<>();
		list.add(startAStarItem);

		while (!list.isEmpty()) {

			Collections.sort(list);

			AStarItem current = list.get(0);
			current.getItem().setValid(false);
			list.remove(0);

			if (current.getItem().getState() != State.START && current.getItem().getState() != State.GOAL) {
				transitions.add(current.getItem().colorItem(Database.CHECKED_FILL));
			}
			list.addAll(current.getNeighbours(aStarItems, transitions, startAStarItem, goalAStarItem));
			if (current.getItem().getState() == State.GOAL) {
				showPath(startAStarItem, aStarItems, goalAStarItem, transitions);
				break;
			}
		}

		return transitions;
	}

	private void showPath(AStarItem startAStarItem, AStarItem[][] aStarItems, AStarItem goalAStarItem,
			ArrayList<Transition> transitions) {

		Item current = goalAStarItem.getItem();

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
		AStarItem[][] AStarItems = new AStarItem[30][40];
		AStarItem startAStarItem = null;
		AStarItem goalAStarItem = null;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {
				AStarItems[i][j] = new AStarItem(AllItems[i][j]);
				if (AStarItems[i][j].getItem().getState().equals(State.START)) {
					startAStarItem = AStarItems[i][j];
				}
				if (AStarItems[i][j].getItem().getState().equals(State.GOAL)) {
					goalAStarItem = AStarItems[i][j];
				}

			}
		}
		transitions.addAll(findPath(startAStarItem, AStarItems, goalAStarItem));

		return transitions;
	}

}
