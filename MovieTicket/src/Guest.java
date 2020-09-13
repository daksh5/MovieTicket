import java.sql.*;

public class Guest {
	
	public static void movieList() {
			
		try {
			Connection myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			Statement myStmt = myConn.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Movies;");
			
			while (myRs.next()) {
				System.out.println(myRs.getString("id") + ". " + myRs.getString("tittle") + ", " + myRs.getString("Language") + ", " + myRs.getString("description")
				 + ", " + myRs.getString("durationInMins") + ", " + myRs.getString("releaseDate") + ", " + myRs.getString("country") + ", " + myRs.getString("genre"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

}
