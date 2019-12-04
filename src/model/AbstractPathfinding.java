package model;

import java.util.ArrayList;

import javafx.animation.Transition;

public abstract class AbstractPathfinding {

	public abstract ArrayList<Transition> start(Item[][] items);

}
