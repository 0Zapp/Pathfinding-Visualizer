package model;

import java.util.ArrayList;

import controller.ClickOnItemController;
import controller.MousePrimerController;
import javafx.scene.paint.Paint;

public class Database {

	private static Database instance = null;
	private Item[][] items;
	private ArrayList<AbstractPathfinding> pathfindingAlgorithms;
	public final static String STANDARD_STROKE = "#4F6367";
	public final static String STANDARD_FILL = "#B8D8D8";
	public final static String WALL_FILL = "#000000";
	public final static String GOAL_FILL = "#EB2727";
	public final static String START_FILL = "#FDBD33";
	public final static String PROCESS_FILL = "#5C0185";
	public final static String CHECKED_FILL = "#118AB2";
	public final static String PATH_FILL = "#06d6a0";

	public Database() {
		items = new Item[30][40];
		pathfindingAlgorithms = new ArrayList<>();
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
		}

		return instance;

	}

	public ArrayList<AbstractPathfinding> getPathfindingAlgorithms() {
		return pathfindingAlgorithms;
	}

	public void setPathfindingAlgorithms(ArrayList<AbstractPathfinding> pathfindingAlgorithms) {
		this.pathfindingAlgorithms = pathfindingAlgorithms;
	}

	public Item[][] getItems() {
		return items;
	}

	public void setItems(Item[][] items) {
		this.items = items;
	}

	public static String getStandardStroke() {
		return STANDARD_STROKE;
	}

	public static String getStandardFill() {
		return STANDARD_FILL;
	}

	public static String getWallFill() {
		return WALL_FILL;
	}

	public static String getGoalFill() {
		return GOAL_FILL;
	}

	public static String getStartFill() {
		return START_FILL;
	}

	public static void setInstance(Database instance) {
		Database.instance = instance;
	}

	public Item[][] generateItems() {
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {

				Item r = new Item(i, j);
				r.setWidth(25);
				r.setHeight(25);

				r.setOnMouseEntered(new ClickOnItemController(r));
				r.setOnMouseClicked(new MousePrimerController(r));

				r.setStroke(Paint.valueOf(STANDARD_STROKE));
				r.setFill(Paint.valueOf(STANDARD_FILL));
				items[i][j] = r;
			}
		}
		return items;

	}

	public boolean isReady() {

		boolean startExists = false;
		boolean goalExists = false;
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {
				if (items[i][j].getState().equals(State.GOAL)) {
					goalExists = true;
				}
				if (items[i][j].getState().equals(State.START)) {
					startExists = true;
				}

			}
		}
		return (startExists && goalExists);
	}

}
