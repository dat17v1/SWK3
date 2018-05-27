package threads.concurrency;

import java.sql.*;

/*
Dette eksempel viser hvordan man kan håndtere flere samtidige database brugere, uden at lave fejl.
Dette område kaldes for "Transaction Management", og den skal bl.a. opfylde de 4 kriterier "ACID" som er beskrevet
her https://en.wikipedia.org/wiki/ACID (Atomicity, Consistency, Isolation, Durability)
"Isolation" betyder, at selvom to brugere samtidigt opdaterer en given række, så skal det endelige resultat
være det samme, som hvis det var foregået efter hinanden (sekventielt)

MySQL har et værktøj til at håndtere dette. Det kan ses i metoden updateDatabase() i klassen Worker herunder.
Det næste kald til statement.execute(...) findes i to udgaver. En er kommenteret ud. Den anden er aktiv.

Det ene kald til statement.execute("select beloeb from bank where kontonummer=1 for update")
indeholder to keywords til sidst: "for update", som sikrer, at ingen anden bruger kan læse, opdatere
eller slette den valgte række imens denne proces er i gang.

Det andet kald til statement.execute("select beloeb from bank where kontonummer=1") indeholder ikke "for update"
til sidst. Hvis du aktiverer denne linie, og udkommenterer den anden, vil du se, at programmet ikke giver korrekt resultat.


Der findes også en anden, mere fleksibel lås, som hedder "lock in share mode". Denne tillader andre brugere
at læse rækken, men ikke at opdatere eller slette, sålænge som den første proces er i gang.

Praktisk info: i denne package ligger filen sqlDump.sql. Den kan du evt anvende for at oprette tabellen 'bank'
 */

public class BankKontoEksempel {
	private int numberOfThreads=10;

	public static void main(String[] args) {
		new BankKontoEksempel();
	}

	public BankKontoEksempel(){
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

	class Worker extends Thread {

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
			//statement.execute("select beloeb from bank where kontonummer=1 for update"); // korrekt opførsel. Resultat = 1100
			statement.execute("select beloeb from bank where kontonummer=1"); // vi mister opdateringer. Resultat < 1100
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
