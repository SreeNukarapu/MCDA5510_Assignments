
/**
 * Source code sample from 
 * http://www.vogella.com/tutorials/MySQLJava/article.html
 * 
**/


import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class MySQLAccess {

	private Connection connect = null;
	private Statement statement = null;
//	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public static void main(String[] args) {
        MySQLAccess dao = new MySQLAccess();
        try {
			dao.readDataBase();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	
	public void readDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB

			
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/transactoins?"   //DTP I spelled transactoins wrong oops 
			             + "user=root&password=db123456"   //Creds
			             +"&useSSL=false"                  // b/c localhost
					     +"&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");  // timezone

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			// Result set get the result of the SQL query
			resultSet = statement.executeQuery("select * from transactoins.transaction");
			writeResultSet(resultSet);

			/**
			
			// PreparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
			// "myuser, webpage, datum, summary, COMMENTS from
			// feedback.comments");
			// Parameters start with 1
			preparedStatement.setString(1, "Test");
			preparedStatement.setString(2, "TestEmail");
			preparedStatement.setString(3, "TestWebpage");
			preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
			preparedStatement.setString(5, "TestSummary");
			preparedStatement.setString(6, "TestComment");
			preparedStatement.executeUpdate();

			preparedStatement = connect
					.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
			preparedStatement = connect.prepareStatement("delete from feedback.comments where myuser= ? ; ");
			preparedStatement.setString(1, "Test");
			preparedStatement.executeUpdate();

			resultSet = statement.executeQuery("select * from feedback.comments");
			writeMetaData(resultSet);
**/
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}

	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String ID = resultSet.getString("ID");
			String NameOnCard = resultSet.getString("NameOnCard");
			String CardNumber = resultSet.getString("CardNumber");
			String ExpDate = resultSet.getString("CardNumber");
			String UnitPrice = resultSet.getString("UnitPrice");
			Date qty = resultSet.getDate("Quantity");
			String totalPrice = resultSet.getString("TotalPrice");
			String createdOn = resultSet.getString("CreatedOn");
			String createdBy = resultSet.getString("CreatedBy");
			System.out.println("ID: " + ID);
			System.out.println("NameOnCard: " + NameOnCard);
			System.out.println("CardNumber: " + CardNumber);
			System.out.println("ExpDate: " + ExpDate);
			System.out.println("UnitPrice: " + UnitPrice);
			System.out.println("Qty: " + qty);
			System.out.println("TotalPrice: " + totalPrice);
			System.out.println("CreatedOn: " + createdOn);
			System.out.println("CreatedBy: " + createdBy);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}



}
