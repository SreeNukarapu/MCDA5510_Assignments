package com.dpenny.mcda5510;
import java.sql.Connection;
import java.util.Collection;

import com.dpenny.mcda5510.con.ConnectionFactory;
import com.dpenny.mcda5510.con.MySQLJDBCConnection;
import com.dpenny.mcda5510.dao.MySQLAccess;
import com.dpenny.mcda5510.entity.Transaction;

public class Assignment2 {

	public static Connection single_instance;

	public static Connection getInstance() {
		if (single_instance == null) {
			MySQLJDBCConnection dbConnection = new MySQLJDBCConnection();
			single_instance = dbConnection.setupConnection();
		}

		return single_instance;
	}

	public static void main(String[] args) {
		MySQLAccess dao = new MySQLAccess();
		try {
			// Connection connection = getInstance();

			ConnectionFactory factory = new ConnectionFactory();
			Connection connection = factory.getConnection("mySQLJDBC");

			Collection<Transaction> trxns = dao.getAllTransactions(connection);

			for (Transaction trxn : trxns) {
				System.out.println(trxn.toString());
			}

			if (connection != null) {
				connection.close();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
