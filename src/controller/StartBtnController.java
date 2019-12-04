package controller;

import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.AbstractPathfinding;
import model.Database;
import view.MainView;

public class StartBtnController implements EventHandler<ActionEvent> {

	private MainView view;

	public StartBtnController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		if (Database.getInstance().isReady()) {
			view.TurnOffButtons();
			AbstractPathfinding pathFinding = view.getPathFindingAlgorithm();
			SequentialTransition sq = new SequentialTransition();
			sq.getChildren().addAll(pathFinding.start(Database.getInstance().getItems()));
			sq.setOnFinished(e -> {
				view.TurnOnReset();
			});
			sq.play();
		}
	}

}
