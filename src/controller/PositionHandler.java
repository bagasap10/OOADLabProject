package controller;

import java.util.*;

import model.Employee;
import model.Product;
import model.Position;

public class PositionHandler {
	public static String errorMsg;

	public PositionHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static Vector<Position> getAllPositions(){
		Position pos = new Position();
		return pos.getAllPositions();
	}
	
	public static String getPosition(int positionID) {
		Position pos = new Position();
		return pos.getPosition();
	}
}
