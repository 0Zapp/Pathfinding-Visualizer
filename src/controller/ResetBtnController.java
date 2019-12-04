package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;

public class ResetBtnController implements EventHandler<ActionEvent> {

	private MainView view;

	public ResetBtnController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		view.populateGridPane();
		view.TurnOnStart();
	}

}
