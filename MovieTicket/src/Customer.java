import java.sql.*;
import java.util.*;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Customer {
	
	static String line;
	static String emailid;
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
	
	public static void verify() {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("UserName:");
		String uname = myObj.nextLine();
		System.out.println("Password:");
		String pass = myObj.nextLine();
		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Customers WHERE UserName = '"+uname+"' AND Password = '"+pass+"'");
			
			while (myRs.next()) {
				emailid = myRs.getString("EmailId");
				customerMenu();
			}
			MovieTicket.Menu();
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	public static void registration() {
		Scanner myObj = new Scanner(System.in);
		
		System.out.println("First Name:");
		String fn = myObj.nextLine();
		System.out.println("Last Name:");
		String ln = myObj.nextLine();
		System.out.println(" UserName:");
		String uname = myObj.nextLine();
		System.out.println("Password:");
		String pass = myObj.nextLine();
		System.out.println("email id:");
		String email = myObj.nextLine();

		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myStmt.executeUpdate("INSERT INTO `Movie_Ticket`.`Customers` (`UserName`, `Password`, `FirstName`, `LastName`, `EmailId`) VALUES ('"+uname+"', '"+pass+"', '"+fn+"', '"+ln+"', '"+email+"');");
			
			System.out.println("You successfuly registerd !!");

		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void seachMovie() {
		
		Scanner myOb = new Scanner(System.in);
		
		System.out.println("\ntittle\nLanguage\ndescription\ndurationInMins\nreleaseDate\ncountry\ngenre\n");
		
		String how,what;
		
		System.out.println("How you want to search :");
		how = myOb.nextLine();
		
		System.out.println("\nWhat you want to search :");
		what = myOb.nextLine();
		
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
		Scanner myOb = new Scanner(System.in);
		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Shows;");
			
			while (myRs.next()) {
				System.out.println("\n"+myRs.getString("id_city_theater_time") + ".\t " +myRs.getString("tittle") + ", " + myRs.getString("City") + ", " + myRs.getString("Theater") + ", " + myRs.getString("Time") + "\n" +myRs.getString("seats"));
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
		System.out.println("\nCode :");
		input = myOb.nextLine();
		
		System.out.println("\nNumber of seats :");
		int nticket = myOb.nextInt();
		
		List lseat = new ArrayList();
		
		for(int i = 0; i < nticket; i++) {
			Scanner myO = new Scanner(System.in);
			
			System.out.println("Seat :");
			seat = myO.nextLine();
			lseat.add(seat);
		}
		
		for(int i = 0; i < nticket; i++) {
			try {
				myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
				
				myStmt = myConn.createStatement();
				
				myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Shows WHERE id_city_theater_time = '"+input+"'");
				
				while (myRs.next()) {
					line = myRs.getString("seats");
				}

				String Str = new String(line); 
				System.out.println(Str.replaceFirst((String) lseat.get(i), "XX"));
				
				Statement myStmtt = myConn.createStatement();
				
				myStmtt.executeUpdate("UPDATE `Movie_Ticket`.`Shows` SET `seats` = '"+Str.replaceFirst((String) lseat.get(i), "XX")+"' WHERE (`id_city_theater_time` = '"+input+"');");
				
				sendMail(seat,Str.replaceFirst(seat, "XX"));
			}
			catch (Exception exc) {
				exc.printStackTrace();
			}
			
		}
		

		
		try {
			myConn = DriverManager.getConnection(MovieTicket.jdbc, MovieTicket.username, MovieTicket.password);
			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery("SELECT * FROM Movie_Ticket.Shows WHERE id_city_theater_time = '"+input+"'");
			
			while (myRs.next()) {
				line = myRs.getString("seats");
			}

			String Str = new String(line); 
			System.out.println(Str.replaceFirst(seat, "XX"));
			
			
			Statement myStmtt = myConn.createStatement();
			
			myStmtt.executeUpdate("UPDATE `Movie_Ticket`.`Shows` SET `seats` = '"+Str.replaceFirst(seat, "XX")+"' WHERE (`id_city_theater_time` = '"+input+"');");
			sendMail(seat,Str.replaceFirst(seat, "XX"));
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void sendMail(String st, String sitting) {
		Properties props = new Properties();
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		
		props.put("mail.smtp.connectiontimeout", "t1");
	    props.put("mail.smtp.timeout", "t2");
		
		String myid = "svraj157@gmail.com";
		String pass = "Usa@1234";
	    
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myid,pass);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myid));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailid));
			message.setSubject("your seat : "+st);
			message.setText(sitting);
			
			Transport.send(message);
		}catch(Exception e) {
			System.out.println("exception");
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
