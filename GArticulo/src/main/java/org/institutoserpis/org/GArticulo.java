package org.institutoserpis.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GArticulo {
	public static void main(String[] args) throws SQLException{
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/dbprueba", 
				"root", "sistemas");

	}
}
