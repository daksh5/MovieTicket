import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class MovieTicket {
	
	public static String username = "root";
	public static String password = "Qwerty@1234";
	public static String jdbc = "jdbc:mysql://localhost:3306/Movie_Ticket?serverTimezone=UTC";
	
	public static void Menu() {
		Scanner myObj = new Scanner(System.in);  
	    System.out.println("\n----Who are you?----");
	    System.out.println(" 1. Admin");
	    System.out.println(" 2. Front Desk Officer");
	    System.out.println(" 3. Customer");
	    System.out.println(" 4. Guest");
	    System.out.println(" 5. Register as a Customer");
	    System.out.println(" 6. Exit");

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
	    	  System.out.println("Admin\n");
	    	  Admin.AdminMenu();
	    	  System.out.println("\n");
	    	  Menu();
	    	  break;
	      case 2:
	    	  System.out.println("Front Desk Officer\n");
	    	  Customer.seatsBooking();
	    	  Menu();
	    	  break;
	      case 3:
	    	  System.out.println("Customer\n");
	    	  Customer.verify();
//	    	  Customer.customerMenu();
//	    	  System.out.println("\n");
//	    	  Menu();
	    	  break;
	      case 4:
	    	  System.out.println("Guest\n");
	    	  System.out.println("movies List");
	    	  Guest.movieList();
	    	  System.out.println("\n");
	    	  Menu();
	    	  break;
	      case 5:
	    	  System.out.println(" 5. Register as a Customer");
	    	  System.out.println("\n");
	    	  Customer.registration();
	    	  break;
	      case 6:
	    	  System.exit(0);
	      default:
	    	  System.out.print("Invalid input. Please try again: \n\n");
	    	  Menu();
	    }
	    
	}

	public static void main(String[] args) {
		
		Menu();
	}

}
