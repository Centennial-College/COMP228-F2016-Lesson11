package lecture;

import java.sql.*;

public class connectURL {
	public static void main(String[] args) {
		// Create a variable for the connection string.
		String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "databaseName=AdventureWorks2014;" + "user=kevin;"
				+ "password=kevin";

		// Declare the JDBC objects.
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			// tells the program what driver we will be using
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Establish the connection.
			conn = DriverManager.getConnection(connectionUrl);

			// Create and execute an SQL statement that returns some data.
			String SQL = "select top 10 * from person.person";
			stmt = conn.createStatement();
			stmt.executeQuery(SQL);
			rs = stmt.getResultSet();

			/*
			 * Moves the cursor forward one row from its current position. A
			 * ResultSet cursor is initially positioned before the first row;
			 * the first call to the method next makes the first row the current
			 * row; the second call makes the second row the current row, and so
			 * on.
			 */
			// Iterate through the data in the result set and display it.
			while (rs.next()) {
				// NOTE: pk is ignored for the column index, so the 'first'
				System.out.println(rs.getString(5) + " " + rs.getString(7));
			}
		}
		// Handle any errors that may have occurred.
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {

				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
				}
			}
		}
	}
}
