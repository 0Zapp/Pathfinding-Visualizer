package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

import javafx.animation.Transition;

public class HCPathfinding extends AbstractPathfinding {

	private String name;

	public HCPathfinding(String name) {
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

	private ArrayList<Transition> findPath(HCItem startHCItem, HCItem[][] hCItems, HCItem goalHCItem) {
		ArrayList<Transition> transitions = new ArrayList<>();

		ArrayList<HCItem> list = new ArrayList<>();
		list.add(startHCItem);

		while (!list.isEmpty()) {

			Collections.sort(list);

			HCItem current = list.get(0);
			current.getItem().setValid(false);
			list.remove(0);

			if (current.getItem().getState() != State.START && current.getItem().getState() != State.GOAL) {
				transitions.add(current.getItem().colorItem(Database.CHECKED_FILL));
			}

			while (!list.isEmpty()) {
				Collections.sort(list);
				HCItem current1 = list.get(0);
				current1.getItem().setState(State.STANDARD);
				transitions.add(current1.getItem().colorItem(Database.STANDARD_FILL));
				list.remove(current1);
			}

			list.addAll(current.getNeighbours(hCItems, transitions, goalHCItem));
			if (current.getItem().getState() == State.GOAL) {
				while (!list.isEmpty()) {
					Collections.sort(list);
					HCItem current1 = list.get(0);
					current1.getItem().setState(State.STANDARD);
					transitions.add(current1.getItem().colorItem(Database.STANDARD_FILL));
					list.remove(current1);
				}
				showPath(startHCItem, hCItems, goalHCItem, transitions);
				break;
			}

		}

		return transitions;
	}

	private void showPath(HCItem startHCItem, HCItem[][] hCItems, HCItem goalHCItem,
			ArrayList<Transition> transitions) {

		Item current = goalHCItem.getItem();

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
		HCItem[][] HCItems = new HCItem[30][40];
		HCItem startHCItem = null;
		HCItem goalHCItem = null;

		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {
				HCItems[i][j] = new HCItem(AllItems[i][j]);
				if (HCItems[i][j].getItem().getState().equals(State.START)) {
					startHCItem = HCItems[i][j];
				}
				if (HCItems[i][j].getItem().getState().equals(State.GOAL)) {
					goalHCItem = HCItems[i][j];
				}

			}
		}
		transitions.addAll(findPath(startHCItem, HCItems, goalHCItem));

		return transitions;
	}

}
