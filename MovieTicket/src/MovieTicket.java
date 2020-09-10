import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class MovieTicket {
	
	public void movieList() {
		
		Movies movie1 = new Movies();
	    
	    movie1.setTitle("Bad Boys");
	    movie1.setCountry("USA");
	    movie1.setDate("1995");
	    movie1.setDescription("Will Smith Movie about cops.");
	    movie1.setDurationInMins(128);
	    movie1.setGenre("Action");
	    movie1.setLanguage("English");
	    
	    Movies movie2 = new Movies();
	    
	    movie2.setTitle("Bad Boys 2");
	    movie2.setCountry("USA");
	    movie2.setDate("1995");
	    movie2.setDescription("Will Smith Movie about cops.");
	    movie2.setDurationInMins(128);
	    movie2.setGenre("Action");
	    movie2.setLanguage("English");
	    
	    List<Movies> movielist = new ArrayList<Movies>();
	    
	    movielist.add(movie1);
	    movielist.add(movie2);
	    
	    movielist.forEach((n) -> System.out.println(n.getTitle()));
		
	}

	public static void main(String[] args) {
		
		Scanner myObj = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("----Who are you?----");
	    System.out.println(" 1. Admin");
	    System.out.println(" 2. Front Desk Officer");
	    System.out.println(" 3. Customer");
	    System.out.println(" 4. Guest");

	    String user = myObj.nextLine();  // Read user input
	    System.out.println("Username is: " + user); 
		
		
		switch (Integer.parseInt(user)) {
	      case 1:
	        System.out.println("Monday");
	        break;
	      case 2:
	        System.out.println("Tuesday");
	        break;
	      case 3:
	        System.out.println("Wednesday");
	        break;
	      case 4:
	        System.out.println("Thursday");
	        break;
	      case 5:
	        System.out.println("Friday");
	        break;
	      case 6:
	        System.out.println("Saturday");
	        break;
	      case 7:
	        System.out.println("Sunday");
	        break;
	    }
	    
		
		
	     
	    
	
	}

}
