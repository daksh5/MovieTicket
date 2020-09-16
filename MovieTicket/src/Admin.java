import java.util.*;
import java.sql.*;


public class Admin {
	
	static Connection myConn;
	static Statement myStmt;
	static ResultSet myRs;
	private static int count = 0;
	private static String seats = "1A 2A 3A 4A 5A \n1B 2B 3B 4B 5B \n1C 2C 3C 4C 5C"
			+ "\n1D 2D 3D 4D 5D \n1E 2E 3E 4E 5E\n ----------\n   Screen  \n ---------- ";
	
public static void AdminMenu() {
		
		Scanner myObj = new Scanner(System.in);  
	    System.out.println("----What you want to do----");
	    System.out.println(" 1. Add movies and shows");
	    System.out.println(" 2. Cancel whole Movies");
	    System.out.println(" 3. Cancel particular shows");
	    System.out.println(" 4. Delete the customer");
	    System.out.println(" 5. Main Menu");
	    System.out.println(" 6. Exit\n");
	    int input;
	    while (true) {
	        try {
	            input = myObj.nextInt();
	            break;
	        }
	        catch (Exception e) {
	            System.out.print("Invalid input. Please try again: ");
	            myObj.nextLine();
	        }
	    }

	    
	    
	    switch (input) {
	      case 1:
	    	  System.out.println("Add movies and shows\n");
	    	  addMovies();
	    	  System.out.println("\n");
	    	  AdminMenu();
	    	  break;
	      case 2:
	    	  System.out.println("Cancel Movies and shows\n");
	    	  Guest.movieList();
	    	  System.out.println("\n");
	    	  deleteMovie();
	    	  System.out.println("\n");
	    	  MovieTicket.Menu();
	    	  break;
	      case 3:
	    	  System.out.println("Cancel shows\n");
	    	  deleteShows();
	    	  AdminMenu();
	    	  break;
	      case 4:
	    	  blockCustomer();
	    	  MovieTicket.Menu();
	    	  break;
	      case 5:
	    	  MovieTicket.Menu();
	    	  break;
	      case 6:
	    	  System.exit(0);
	      default:
	    	  System.out.print("Invalid input. Please try again: \n\n");
	    	  AdminMenu();
	    }
		
	}

	public static void blockCustomer() {
		System.out.println();
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Customers;");
			
			while (myRs.next()) {
				System.out.println(myRs.getString("UserName"));
			}
			
			Scanner myObj = new Scanner(System.in);
			System.out.println("\nWhich customer you want to block?");
			String uname = myObj.nextLine();
			
			myStmt.executeUpdate("DELETE FROM `Movie_Ticket`.`Customers` WHERE (`UserName` = '"+uname+"');");
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
	}
	
	public static void addMovies() {
		
		try {

			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username , MovieTicket.password);
			
			myStmt = myConn.createStatement();
	
			myRs = myStmt.executeQuery("Select *from Movie_Ticket.Movies ORDER BY id DESC LIMIT 1;");
			
			while (myRs.next()) {
				count = Integer.parseInt(myRs.getString("id"));
			}
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
		Scanner myObj = new Scanner(System.in); 
		System.out.println("movie name:");
		String name = myObj.nextLine();
		System.out.println("movie description:");
		String description = myObj.nextLine();
		System.out.println("movie durationInMins:");
		int durationInMins = Integer.parseInt(myObj.nextLine());
		System.out.println("movie language:");
		String language = myObj.nextLine();
		System.out.println("date:");
		String date = myObj.nextLine();
		System.out.println("movie country:");
		String country = myObj.nextLine();
		System.out.println("movie genre:");
		String genre = myObj.nextLine();
		
		try {

			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username , MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myStmt.executeUpdate("INSERT INTO `Movie_Ticket`.`Movies` (`id`, `tittle`, `Language`, `description`, `durationInMins`, `releaseDate`, `country`, `genre`) VALUES "
					+ "('"+(count+1)+"', '"+name+"', '"+language+"', '"+description+"', "
							+ "'"+durationInMins+"', '"+date+"', '"+country+"', '"+genre+"');");
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		System.out.println("How many shows:");
		int nshows = myObj.nextInt();
		
		
		
		List<Shows> list = new ArrayList<Shows>();
		Shows s = new Shows();
		
		for(int i=0;i<nshows;i++) {
			Scanner myOb = new Scanner(System.in); 
			
			System.out.println("city:");
			String city = myOb.nextLine();
			System.out.println("theater:");
			String theater = myOb.nextLine();
			System.out.println("show time:");
			String time = myOb.nextLine();
			
//			s.setCity(city);
//			s.setId((count+1));
//			s.setId_city((count+1)+city);
//			s.setSeats(seats);
//			s.setTheater(theater);
//			s.setTimes(time);
//			s.setTittle(name);
//			s.setId_city_theater_time((count+1)+city+theater+time);
			
			try {

				myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username , MovieTicket.password);
				
				myStmt = myConn.createStatement();
				
				myStmt.executeUpdate("INSERT INTO `Movie_Ticket`.`Shows` (`id`, `City`, `Theater`, `id_city`, `Time`, `tittle`, `seats`, `id_city_theater_time`) "
						+ "VALUES ('"+(count+1)+"', '"+city+"', '"+theater+"', '"+(count+1)+city+"', '"+time+"', '"+name+"', '"+seats+"', '"+((count+1)+"_"+city+"_"+theater+"_"+time)+"');");	
				
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}
			
		}
		
	}

	
public static void deleteMovie() {
		
		Scanner myObj = new Scanner(System.in);  
		System.out.println("Which Movie you want to delete: ");
		
		int n = myObj.nextInt();
		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username , MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myStmt.executeUpdate("DELETE FROM `Movie_Ticket`.`Movies` WHERE (`id` = '"+ n +"');");
			myStmt.executeUpdate("DELETE FROM `Movie_Ticket`.`Shows` WHERE (`id` = '"+ n +"');");
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public static void deleteShows() {
		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Shows;");
			
			while (myRs.next()) {
				System.out.println("\n"+myRs.getString("id_city_theater_time") + ".\t " +myRs.getString("tittle") + ", " + myRs.getString("City") + ", " + myRs.getString("Theater") + ", " + myRs.getString("Time") + "\n");
			}
			
			Scanner myObj = new Scanner(System.in);  
			System.out.println("Which show you want to delete: ");
			String dlt = myObj.nextLine();
			
			myStmt.executeUpdate("DELETE FROM `Movie_Ticket`.`Shows` WHERE (`id_city_theater_time` = '"+ dlt +"');");
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		
		
		
	}
	

}
