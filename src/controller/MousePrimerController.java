package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import model.Database;
import model.Item;
import model.State;
import view.MainView;

public class MousePrimerController implements EventHandler<MouseEvent> {

	private Item r;

	public MousePrimerController(Item r) {
		this.r = r;
	}

	@Override
	public void handle(MouseEvent event) {

		if (MainView.getInstance().isMouseEnabled()) {
			if (event.getButton() == MouseButton.PRIMARY) {
				MainView.getInstance().switchMouse();

			}
			if (event.getButton() == MouseButton.SECONDARY) {
				MainView.getInstance().switchMouseDelete();

			}

		}
	}

}