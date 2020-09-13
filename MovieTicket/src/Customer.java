import java.sql.*;
import java.util.Scanner;

public class Customer {
	
	static String line;
	static String input,seat;
	static Connection myConn;
	static Statement myStmt;
	static ResultSet myRs;
	
	public static void customerMenu() {
		Scanner myObj = new Scanner(System.in);  
	    System.out.println("\n----What you want to do----");
	    System.out.println(" 1. Movie List");
	    System.out.println(" 2. Search a movie");
	    System.out.println(" 3. Movie Timing");
	    System.out.println(" 4. Book a seat");
	    System.out.println(" 5. Main Menu");
	    System.out.println(" 6. Exit");
	 
	    int i;
	    
	    while (true) {
	        try {
	            i = myObj.nextInt();
	            break;
	        }
	        catch (Exception e) {
	            System.out.print("Invalid input. Please try again: ");
	            myObj.nextLine();
	        }
	    }
	    
	    switch (i) {
	      case 1:
	    	  System.out.println("Movie List\n");
	    	  Guest.movieList();
	    	  System.out.println("\n");
	    	  customerMenu();
	    	  break;
	      case 2:
	    	  System.out.println("Search a movie\n");
	    	  seachMovie();
	    	  System.out.println("\n");
	    	  customerMenu();
	    	  break;
	      case 3:
	    	  System.out.println("Movie Timing\n");
	    	  showList();
	    	  customerMenu();
	    	  break;
	      case 4:
	    	  System.out.println("Book a seat\n");
	    	  seatsBooking();
	    	  break;
	      case 5:
	    	  MovieTicket.Menu();
	    	  break;
	      case 6:
	    	  System.exit(0);
	      default:
	    	  System.out.print("Invalid input. Please try again: \n\n");
	    }
	}
	
	public static void seachMovie() {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("\ntittle\nLanguage\ndescription\ndurationInMins\nreleaseDate\ncountry\ngenre\n");
		
		String how,what;
		
		System.out.println("How you want to search :");
		how = myObj.nextLine();
		
		System.out.println("\nWhat you want to search :");
		what = myObj.nextLine();
		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Movies WHERE "+how+ " = '"+what+"'");
			
			while (myRs.next()) {
				System.out.println("\n"+myRs.getString("id") + ". " + myRs.getString("tittle") + ", " + myRs.getString("Language") + ", " + myRs.getString("description")
				 + ", " + myRs.getString("durationInMins") + ", " + myRs.getString("releaseDate") + ", " + myRs.getString("country") + ", " + myRs.getString("genre"));
			}
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
	
	public static void seatsBooking() {
		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Shows;");
			
			while (myRs.next()) {
				System.out.println("\n"+myRs.getString("id_city") + ".\t " +myRs.getString("tittle") + ", " + myRs.getString("City") + ", " + myRs.getString("Theater") + ", " + myRs.getString("Time") + "\n" +myRs.getString("seats"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("\nCode :");
		input = myObj.nextLine();
		
		
		System.out.println("Seat :");
		seat = myObj.nextLine();
		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Shows WHERE id_city = '"+input+"'");
			
			while (myRs.next()) {
				line = myRs.getString("seats");
			}
			
			String Str = new String(line); 
			System.out.println(Str.replaceFirst(seat, "XX"));
			
			
			Statement myStmtt = myConn.createStatement();
			
			myStmtt.executeUpdate("UPDATE `Movie_Ticket`.`Shows` SET `seats` = '"+Str.replaceFirst(seat, "XX")+"' WHERE (`id_city` = '"+input+"');");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void showList() {
		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Shows;");
			
			while (myRs.next()) {
				System.out.println("\n"+myRs.getString("tittle") + ", " + myRs.getString("City") + ", " + myRs.getString("Theater") + ", " + myRs.getString("Time"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
