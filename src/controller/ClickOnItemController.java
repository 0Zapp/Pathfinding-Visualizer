package controller;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.Database;
import model.Item;
import model.State;
import view.MainView;

public class ClickOnItemController implements EventHandler<MouseEvent> {

	private Item r;

	public ClickOnItemController(Item r) {
		this.r = r;
	}

	@Override
	public void handle(MouseEvent event) {

		if (MainView.getInstance().isMouseEnabled()) {
			if (MainView.getInstance().mouseIsTurnedOn()) {
//			if (event.isAltDown()) {
//			if (event.isPrimaryButtonDown()) {
//			if (event.getButton() == MouseButton.PRIMARY) {
				if (MainView.getInstance().getState().equals("wall")) {
					r.setFill(Paint.valueOf(Database.WALL_FILL));
					r.setValid(false);
					r.setState(State.WALL);
				} else if (MainView.getInstance().getState().equals("start")) {
					for (int i = 0; i < 30; i++) {
						for (int j = 0; j < 40; j++) {
							if (Database.getInstance().getItems()[i][j].getState().equals(State.START)) {
								Database.getInstance().getItems()[i][j].setFill(Paint.valueOf(Database.STANDARD_FILL));
								Database.getInstance().getItems()[i][j].setState(State.STANDARD);
								Database.getInstance().getItems()[i][j].setValid(true);
							}
						}
					}
					r.setFill(Paint.valueOf(Database.START_FILL));
					r.setState(State.START);
					r.setValid(true);
				} else if (MainView.getInstance().getState().equals("goal")) {
					for (int i = 0; i < 30; i++) {
						for (int j = 0; j < 40; j++) {
							if (Database.getInstance().getItems()[i][j].getState().equals(State.GOAL)) {
								Database.getInstance().getItems()[i][j].setFill(Paint.valueOf(Database.STANDARD_FILL));
								Database.getInstance().getItems()[i][j].setState(State.STANDARD);
								Database.getInstance().getItems()[i][j].setValid(true);
							}
						}
					}
					r.setFill(Paint.valueOf(Database.GOAL_FILL));
					r.setState(State.GOAL);
					r.setValid(true);
				}
			} else if (MainView.getInstance().mouseIsTurnedDelete()) {
//			} else if (event.isControlDown()) {
				r.setFill(Paint.valueOf(Database.STANDARD_FILL));
				r.setValid(true);
				r.setState(State.STANDARD);
			}
		}
	}

}