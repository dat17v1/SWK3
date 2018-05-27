package threads.concurrency;

import java.sql.*;


public class BankKontoEksempel {
	static int numberOfThreads=10;

	public static void main(String[] args) {

		try (Connection connection = getDatabaseConnection()){
			Statement statement= connection.createStatement();
			statement.executeUpdate("update bank set beloeb=1000 where kontonummer=1;");
		}catch (Exception e){
			e.printStackTrace();
		}

		int counter=0;
		while(counter<numberOfThreads){
			Worker worker = new Worker();
			worker.start();
			counter++;
		}
	}

	// Læg mærke til, at i dette eksempel simulerer vi at flere brugere kontakter databasen samtidig.
	// Derfor bruger jeg ikke Singleton pattern her.
	static Connection getDatabaseConnection(){
		Connection connection=null;
		String dataBase="db";
		String userName="root";  // udskift med din lokale MySQL Server brugernavn
		String passWord=""; // udskift med din lokale MySQL Server password
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///"+dataBase, userName, passWord);
		} catch (SQLException e) {
			System.out.println("error connecting to DB Server " + e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}

	static class Worker extends Thread {

		public void run(){
			System.out.println("hej thread number "+this.getId());

			try (Connection connection = getDatabaseConnection()){
				updateDatabase(connection);
			}catch (Exception e){
				e.printStackTrace();
			}
		}

		private void updateDatabase(Connection connection) throws SQLException {
			Statement statement= connection.createStatement();
			statement.execute("begin;");
			statement.execute("select beloeb from bank where kontonummer=1 for update"); // korrekt opførsel. Resultat = 1100
			//statement.execute("select beloeb from bank where kontonummer=1"); // vi mister opdateringer. Resultat < 1100
			ResultSet resultset=statement.getResultSet();
			resultset.next();
			int beloeb=resultset.getInt(1); // her simulerer vi, at der skal foretages nogle
			// komplekse beregninger i Java koden.
			beloeb = beloeb + 10;
			statement.execute("update bank set beloeb="+beloeb+" where kontonummer=1;");
		    statement.execute("commit;");
			System.out.println("amount is: "+beloeb);
		}
	}

}
