import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class MovieTicket {
	
	public static void AdminMenu() {
		
		Scanner myObj = new Scanner(System.in);  
	    System.out.println("----What you want to do----");
	    System.out.println(" 1. Add movies and shows");
	    System.out.println(" 2. Cancel Movies and shows");
	    System.out.println(" 3. Blocking/Unblocking customers");

	    String opt = myObj.nextLine();
	    
	    switch (Integer.parseInt(opt)) {
	      case 1:
	        System.out.println("Add movies and shows\n");
	        addMovies();
	        System.out.println("\n");
	        Menu();
	        break;
	      case 2:
	        System.out.println("Cancel Movies and shows\n");
	        movieList();
	        System.out.println("\n");
	        deleteMovie();
	        System.out.println("\n");
	        Menu();
	        break;
	      case 3:
	        System.out.println("Blocking/Unblocking customers\n");
	        break;
	      default:
	    	System.out.println("try again");  
	    }
		
	}
	
	public static void deleteMovie() {
		
		Scanner myObj = new Scanner(System.in);  
		System.out.println("WHich Movie you want to delete: ");
		
		int n = Integer.parseInt(myObj.nextLine())-1;
		
		File inputFile = new File("movies.txt");
		File tempFile = new File("myTempFile.txt");
		File inputFile1 = new File("Shows.txt");
		File tempFile1 = new File("myTempFile1.txt");

		
		
		
	      try{
	        String line = Files.readAllLines(Paths.get("movies.txt")).get(n);
	        System.out.println("deleted..");
	        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String lineToRemove = line;
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    String trimmedLine = currentLine.trim();
			    if(trimmedLine.equals(lineToRemove)) continue;
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close(); 
			reader.close(); 
			boolean successful = tempFile.renameTo(inputFile);
	      } 
	      catch(IOException e){
	        System.out.println(e);
	      }
	      
	      try{
	    	  	String line = Files.readAllLines(Paths.get("Shows.txt")).get(n);
		        BufferedReader reader = new BufferedReader(new FileReader(inputFile1));
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile1));

				String lineToRemove = line;
				String currentLine;

				while((currentLine = reader.readLine()) != null) {
				    String trimmedLine = currentLine.trim();
				    if(trimmedLine.equals(lineToRemove)) continue;
				    writer.write(currentLine + System.getProperty("line.separator"));
				}
				writer.close(); 
				reader.close(); 
				boolean successful = tempFile1.renameTo(inputFile1);
		      } 
		      catch(IOException e){
		        System.out.println(e);
		      }
	}
	
	
	public static void addMovies() {
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
		
		
		
		Movies movie1 = new Movies();
		movie1.setTitle(name);
	    movie1.setCountry(country);
	    movie1.setDate(date);
	    movie1.setDescription(description);
	    movie1.setDurationInMins(durationInMins);
	    movie1.setGenre(genre);
	    movie1.setLanguage(language);
	    
	    List<Movies> movielist = new ArrayList<Movies>();
	    movielist.add(movie1);
	    

	    System.out.println("theater:");
		String theater = myObj.nextLine();
		System.out.println("shows:");
		String shows = myObj.nextLine();
		System.out.println("show time:");
		String showTime = myObj.nextLine();
		
		Shows sh = new Shows();
		sh.setName(name);
		sh.setTheater(theater);
		sh.setShows(Integer.parseInt(shows));
		sh.setShowTimes(showTime);
		
		
		List<Shows> shw = new ArrayList<Shows>();
		shw.add(sh);
		
	    
	    try {  
	    	File f1 = new File("movies.txt");
		    if(!f1.exists()) {
	            f1.createNewFile();
	         }
		    FileWriter fileWritter = new FileWriter(f1.getName(),true);
		    BufferedWriter myWriter = new BufferedWriter(fileWritter);
		      for (Movies mov : movielist) {
		    	  myWriter.write(mov.getTitle());
		    	  myWriter.write(" | ");
		    	  myWriter.write(mov.getCountry());
		    	  myWriter.write(" | ");
		    	  myWriter.write(mov.getDate());
		    	  myWriter.write(" | ");
		    	  myWriter.write(mov.getDescription());
		    	  myWriter.write(" | ");
		    	  myWriter.write(Integer.toString(mov.getDurationInMins()));
		    	  myWriter.write(" | ");
		    	  myWriter.write(mov.getGenre());
		    	  myWriter.write(" | ");
		    	  myWriter.write(mov.getLanguage()+"\n");
			}
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } 
	    
	    try {  
	    	File f2 = new File("shows.txt");
		    if(!f2.exists()) {
	            f2.createNewFile();
	         }
		    FileWriter fileWritter = new FileWriter(f2.getName(),true);
		    BufferedWriter myWriter = new BufferedWriter(fileWritter);
		      for (Shows sw : shw) {
		    	  myWriter.write(sw.getName());
		    	  myWriter.write(" | ");
		    	  myWriter.write(sw.getTheater());
		    	  myWriter.write(" | ");
		    	  myWriter.write(Integer.toString(sw.getShows()));
		    	  myWriter.write(" | ");
		    	  myWriter.write(sw.getShowTimes()+"\n");
			}
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    } 
	    
	  
	}
	
	public static void showList() {
		
		System.out.println("\nName | Country | Date | Description | minutes | genre | Language\n");
		
		try {
		      FileReader file = new FileReader("Shows.txt");
		      Scanner myReader = new Scanner(file);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}
	
	
	public static void movieList() {
		
		System.out.println("\nName | City | Shows | Timing\n");
		
		try {
		      FileReader file = new FileReader("movies.txt");
		      Scanner myReader = new Scanner(file);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        System.out.println(data);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static void Menu() {
		Scanner myObj = new Scanner(System.in);  
	    System.out.println("----Who are you?----");
	    System.out.println(" 1. Admin");
	    System.out.println(" 2. Front Desk Officer");
	    System.out.println(" 3. Customer");
	    System.out.println(" 4. Guest");

	    String user = myObj.nextLine();  
		
		
		switch (Integer.parseInt(user)) {
	      case 1:
	        System.out.println("Admin\n");
	        AdminMenu();
	        System.out.println("\n");
	        Menu();
	        break;
	      case 2:
	        System.out.println("Front Desk Officer\n");
	        break;
	      case 3:
	        System.out.println("Customer\n");
	        movieList();
	        System.out.println("\n");
	        showList();
	        break;
	      case 4:
	        System.out.println("Guest\n");
	        System.out.println("movies List");
	        movieList();
	        System.out.println("\n");
	        Menu();
	        break;
	      default:
	    	  System.out.println("Try again!");
	    }
	    
	}

	public static void main(String[] args) {
		
		Menu();
		
	     
	    
	
	}

}
