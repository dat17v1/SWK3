package threads.concurrency;

import java.sql.*;


public class W_bank {
	static int numberOfThreads=10;
	public static void main(String[] args) {
		Connection connection = getDatabaseConnection();
		try {
			Statement statement= connection.createStatement();
			statement.executeUpdate("update bank set beloeb=1000 where kontonummer=1;");
			closeDatabaseConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int counter=0;
		while(counter<numberOfThreads){
			Worker worker = new Worker();
			worker.start();
			counter++;
		}
	}

	static void closeDatabaseConnection(Connection conn) {
		try
		{
			if (conn != null)
			{
				conn.close();
			}
		}
		catch (SQLException e)
		{

		}
	}

	static Connection getDatabaseConnection(){
		Connection connection=null;
		String dataBase="db";
		String userName="root";
		String passWord="";
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
			Connection connection = getDatabaseConnection();
			try {
				updateDatabase(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private void updateDatabase(Connection connection) throws SQLException {
			
			Statement statement= connection.createStatement();
			statement.execute("begin;");
			//6statement.execute("select beloeb from bank where kontonummer=1 for update");
			statement.execute("select beloeb from bank where kontonummer=1");
			ResultSet resultset=statement.getResultSet();
			resultset.next();
			double beloeb=resultset.getInt(1);
			beloeb=beloeb + 10;
			statement.execute("update bank set beloeb="+beloeb+" where kontonummer=1;");
		    statement.execute("commit;");
			System.out.println("amount is: "+beloeb);
//			String navn="(select navn from logind where id=1)";
//			String password="(select password from logind where id=1)";
////			String sql="select id from logind where navn='"+navn+"' and password='"+password+"'";
//			String sql="select id from logind where navn=? and password=?";
//			System.out.println(sql);
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, "jon");
//			preparedStatement.setInt(2, 123);
//			preparedStatement.executeQuery();
//			resultset=preparedStatement.getResultSet();
//			if(resultset.next()){
//			String navn2=resultset.getString(1);
//			//System.out.println("id er :"+navn2);
//			}
			closeDatabaseConnection(connection);
		}
	}

}
