package database;

import java.sql.*;

public class DatabasePractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Problem in loading the driver");
			ex.printStackTrace();
		}

		try {
			String dbName = "Employee.accdb";
			String dbURL = "jdbc:ucanaccess://db/" + dbName;

			connection = DriverManager.getConnection(dbURL);
			statement = connection.createStatement();

			String newEmployee = "Chau";
			double newSalary = 90000;
			String query = "INSERT INTO Employee (EmployeeName, Salary) VALUES ('" + newEmployee + "'," + newSalary + ")";
			// statement.executeUpdate(query);
			
			query = "UPDATE Employee SET Salary = 120000 WHERE EmployeeName = 'Long'";
			statement.executeUpdate(query);
			
			query = "DELETE FROM Employee WHERE EmployeeName = 'Kim'";
			statement.executeUpdate(query);

			resultSet = statement.executeQuery("SELECT * FROM Employee");

			int id;
			String employeeName;
			double salary;
			while (resultSet.next()) {
				id = resultSet.getInt(1);
				employeeName = resultSet.getString(2);
				salary = resultSet.getDouble(3);

				System.out.println(id + " " + employeeName + " " + salary);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					resultSet.close();
					statement.close();
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
}
