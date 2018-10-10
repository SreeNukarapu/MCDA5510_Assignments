import java.sql.Connection;
import java.util.Collection;

public class Assignment2 {

	public static void main(String[] args) {
        MySQLAccess dao = new MySQLAccess();
        try {
			Connection connection = dao.setupConnection();
			Collection<Transaction> trxns = dao.getAllTransactions(connection);
			
			for (Transaction trxn:trxns ){
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
