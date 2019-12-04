package view;

import controller.ResetBtnController;
import controller.StartBtnController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.AbstractPathfinding;
import model.Database;
import model.Item;

public class MainView extends Stage {

	private static MainView instance = null;
	private static Scene scene;
	private static GridPane gridpane;
	private static HBox controlHbox;
	private static VBox mainVbox;
	private static Label setOnClickLabel;
	private static ComboBox<String> setOnClickCmb;
	private static ComboBox<AbstractPathfinding> algorithmCmb;
	private static Button startBtn;
	private static Button resetBtn;
	private boolean mouseEnabled = true;
	private boolean mouseIsTurnedOn = false;
	private boolean mouseIsTurnedDelete = false;

	public MainView() {
		gridpane = new GridPane();
		populateGridPane();

		setOnClickLabel = new Label("Set on click:");
		setOnClickLabel.setTextFill(Color.web("#0076a3"));
		setOnClickCmb = new ComboBox<>();
		populateSetOnClickCmb();
		setOnClickCmb.getSelectionModel().selectFirst();
		algorithmCmb = new ComboBox<>();
		populateAlgorithmCmb();
		startBtn = new Button("Start");
		startBtn.setOnAction(new StartBtnController(this));
		resetBtn = new Button("Reset");
		resetBtn.setOnAction(new ResetBtnController(this));

		controlHbox = new HBox();
		controlHbox.setSpacing(10);
		controlHbox.setAlignment(Pos.CENTER);
		controlHbox.setPadding(new Insets(10, 10, 10, 10));
		controlHbox.getChildren().addAll(setOnClickLabel, setOnClickCmb, algorithmCmb, startBtn, resetBtn);

		mainVbox = new VBox();
		mainVbox.setSpacing(10);
		mainVbox.setAlignment(Pos.CENTER);
		mainVbox.setPadding(new Insets(10, 10, 10, 10));
		mainVbox.getChildren().addAll(gridpane, controlHbox);

		Pane root = new Pane();

		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(1000, 1000);

		holder.getChildren().addAll(canvas, mainVbox);
		holder.setAlignment(Pos.CENTER);

		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: #0F1516");

		scene = new Scene(root);
		setScene(scene);
		setTitle("Sorting Visualizer");
		show();

	}

	private void populateAlgorithmCmb() {
		algorithmCmb.getItems().clear();
		algorithmCmb.getItems().addAll(Database.getInstance().getPathfindingAlgorithms());
		algorithmCmb.getSelectionModel().selectFirst();

	}

	private void populateSetOnClickCmb() {
		setOnClickCmb.getItems().addAll("wall", "start", "goal");

	}

	public void populateGridPane() {
		gridpane.getChildren().clear();
		Item[][] items = Database.getInstance().generateItems();
		for (int i = 0; i < 30; i++) {
			for (int j = 0; j < 40; j++) {
				gridpane.add(items[i][j], j, i);
			}
		}

	}

	public static MainView getInstance() {

		if (instance == null) {
			instance = new MainView();
		}
		return instance;

	}

	public boolean isMouseEnabled() {
		return mouseEnabled;
	}

	public String getState() {

		return setOnClickCmb.getSelectionModel().getSelectedItem().toString();
	}

	public void TurnOffButtons() {
		startBtn.setDisable(true);
		resetBtn.setDisable(true);
		mouseEnabled = false;

	}

	public AbstractPathfinding getPathFindingAlgorithm() {

		return algorithmCmb.getSelectionModel().getSelectedItem();
	}

	public void TurnOnReset() {
		resetBtn.setDisable(false);

	}

	public void TurnOnStart() {
		startBtn.setDisable(false);
		mouseEnabled = true;
	}

	public boolean mouseIsTurnedOn() {

		return mouseIsTurnedOn;
	}

	public boolean mouseIsTurnedDelete() {

		return mouseIsTurnedDelete;
	}

	public void switchMouse() {
		if (mouseIsTurnedOn) {
			mouseIsTurnedOn = false;
		} else {
			mouseIsTurnedOn = true;
			mouseIsTurnedDelete = false;
		}

	}

	public void switchMouseDelete() {
		if (mouseIsTurnedDelete) {
			mouseIsTurnedDelete = false;
		} else {
			mouseIsTurnedDelete = true;
			mouseIsTurnedOn = false;
		}
	}

}
