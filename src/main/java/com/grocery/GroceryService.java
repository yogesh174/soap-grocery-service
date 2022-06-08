package com.grocery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GroceryService {
	
	public String addItem(String itemName, double itemPrice) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","");
			Statement st = con.createStatement();
		){
			String query = "INSERT INTO GROCERY.GROCERY(NAME,PRICE) values('" + itemName + "'," + itemPrice + ")";
			int count = st.executeUpdate(query);
			
			if(count!=0) {
				return "Record added";
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "Record could not be added";
	}
	
	public double getPrice(String itemName) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(
			Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/GROCERY","root","root");
			Statement st = con.createStatement();
		){
			String query = "SELECT NAME, PRICE FROM GROCERY.GROCERY WHERE NAME='" + itemName + "'";
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()) {
				return rs.getDouble(1);
			}
			
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public String updateItem(String itemName, double newPrice) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(
			Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/GROCERY","root","root");
			Statement st = con.createStatement();
		){
			
			String query = "UPDATE GROCERY.GROCERY SET PRICE=" + newPrice +" WHERE NAME='" + itemName + "'";
			int count = st.executeUpdate(query);

			if (count != 0) {
				return "Record updated";
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "Record could not be updated";
	}
	
	public String deleteItem(String itemName) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(
			Connection con = DriverManager.getConnection("jdbc:mysql://@localhost:3306/GROCERY","root","root");
			Statement st = con.createStatement();
		){
			String query = "DELETE FROM GROCERY.GROCERY WHERE NAME='" + itemName + "'";
			int count = st.executeUpdate(query);
			if (count != 0) {
				return "Record deleted";
			}
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "Record could not be deleted";
	}
}
