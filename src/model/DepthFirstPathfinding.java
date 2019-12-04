package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javafx.animation.Transition;

public class DepthFirstPathfinding extends AbstractPathfinding {

	private String name;

	public DepthFirstPathfinding(String name) {
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
	
	private void showPath(DepthFirstItem startDepthFirstItem, DepthFirstItem[][] depthFirstItems,
			DepthFirstItem goalDepthFirstItem, ArrayList<Transition> transitions) {

		Item current = goalDepthFirstItem.getItem();

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
	
	

	private ArrayList<Transition> findPath(DepthFirstItem startDepthFirstItem, DepthFirstItem[][] depthFirstItems,
			DepthFirstItem goalDepthFirstItem) {

		ArrayList<Transition> transitions = new ArrayList<>();
		Stack stack = new Stack();
		stack.push(startDepthFirstItem);

		while (!stack.isEmpty()) {

			DepthFirstItem current = (DepthFirstItem) stack.pop();
			current.getItem().setValid(false);

			if (current.getItem().getState() != State.START && current.getItem().getState() != State.GOAL) {
				transitions.add(current.getItem().colorItem(Database.CHECKED_FILL));
			}
			
			
			stack.addAll(current.getNeighbours(depthFirstItems, transitions));
			
			if (current.getItem().getState() == State.GOAL) {
				showPath(startDepthFirstItem, depthFirstItems, goalDepthFirstItem, transitions);
				break;
			}
		}

		return transitions;
	}

	@Override
	public ArrayList<Transition> start(Item[][] items) {
		ArrayList<Transition> transitions = new ArrayList<>();

		Item[][] AllItems = Database.getInstance().getItems();
		DepthFirstItem[][] depthFirstItems = new DepthFirstItem[30][40];
		DepthFirstItem startDepthFirstItem = null;
		DepthFirstItem goalDepthFirstItem = null;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {
				depthFirstItems[i][j] = new DepthFirstItem(AllItems[i][j]);
				if (depthFirstItems[i][j].getItem().getState().equals(State.START)) {
					startDepthFirstItem = depthFirstItems[i][j];
				}
				if (depthFirstItems[i][j].getItem().getState().equals(State.GOAL)) {
					goalDepthFirstItem = depthFirstItems[i][j];
				}

			}
		}
		transitions.addAll(findPath(startDepthFirstItem, depthFirstItems, goalDepthFirstItem));

		return transitions;
	}

}
