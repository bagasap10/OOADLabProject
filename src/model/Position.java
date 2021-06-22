package model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import connect.Connect;

public class Position {

	private static Connect con = new Connect();

	private int positionID;
	private String name;
	
	
	
	
	public Position(int positionID, String name) {
		super();
		this.positionID = positionID;
		this.name = name;
	}

	public Position() {
		// TODO Auto-generated constructor stub
	}

	public Vector<Position> getAllPositions() {
		Vector<Position> listPosition = new Vector<>();

		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM position");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int posID = rs.getInt("positionID");
				String name = rs.getString("name");

				Position position = new Position(posID, name);
				listPosition.add(position);
			}
			return listPosition;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPosition() {
		String name = null;
		try {
			PreparedStatement ps = Connect.getInstance().prepareStatement("SELECT * FROM position WHERE positionID = ?");
			ps.setInt(1, positionID);
			ResultSet rs = ps.executeQuery();
			name = rs.getString(name);
			
			return name;
		}

		catch(Exception e) {
			return null;
		}
	}

	/**
	 * @return the positionID
	 */
	public int getPositionID() {
		return positionID;
	}

	/**
	 * @param positionID the positionID to set
	 */
	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



}
