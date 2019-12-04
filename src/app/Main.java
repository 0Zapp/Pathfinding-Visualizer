package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.AStarPathfinding;
import model.BestFirstPathfinding;
import model.BreadthFirstPathfinding;
import model.Database;
import model.DepthFirstPathfinding;
import model.HCPathfinding;
import view.MainView;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		Database.getInstance().getPathfindingAlgorithms().add(new AStarPathfinding("A*"));
		Database.getInstance().getPathfindingAlgorithms().add(new HCPathfinding("Hill-Climbing"));
		Database.getInstance().getPathfindingAlgorithms().add(new BestFirstPathfinding("Best-First"));
		Database.getInstance().getPathfindingAlgorithms().add(new DepthFirstPathfinding("Depth-First"));
		Database.getInstance().getPathfindingAlgorithms().add(new BreadthFirstPathfinding("Breadth-First"));
		MainView.getInstance();
	}

}