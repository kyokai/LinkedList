import java.util.Scanner;
public class PA1 {
// passing System.in to scanner in order to read and iterate through file of Distance Event data
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String firstLine = scanner.nextLine();
        String[] noAthletes = firstLine.split(", ");
        String EventName = noAthletes[0];
        int data = Integer.parseInt(noAthletes[1]);
        MyDoubleEndedLinkedList<DistanceEvent> DistanceEventList = new MyDoubleEndedLinkedList<DistanceEvent>();
        
        
        
 // For loop to insert each new DistantEvent into the doubly linked list       
        for (int i = 0; i < data; i++){
        	String NameCountry = scanner.nextLine();
        	String[] AthInfo = new String[2];
        	AthInfo =  NameCountry.split(",");
        	DistanceEvent Athlete = new DistanceEvent(AthInfo[0], AthInfo[1], (i +1));
            DistanceEventList.insertInLastPosition(Athlete);
            }
        

//Getting and assigning score to the respective individual DistanceEvent objects       
        int index = 0;
        DistanceEventList.resetCurrent();
        DistanceEventList.nextCurrent();
        
        	while (scanner.hasNextLine()){
        		if (DistanceEventList.endList() && scanner.hasNextFloat()){
        			Float NextScore = scanner.nextFloat();
        			DistanceEventList.getCurrent().distance[index] = NextScore;
        	    	DistanceEventList.nextCurrent();}
        	
        	    else if (!DistanceEventList.endList()){
        	    	DistanceEventList.resetCurrent();
        	    	DistanceEventList.nextCurrent();
        	    	index ++;
        	    	continue; }
        	
        
        	    else if (scanner.nextLine().equals("list")){
  
        	    	while (DistanceEventList.iterator().hasNext()){
        	    			System.out.println("Yes");;
        					DistanceEventList.iterator().next();}
        	    	continue;}
        		}
        

        
 //sorting scores from best to worst for comparison between DistanceEvents

        DistanceEventList.resetCurrent();
        DistanceEventList.nextCurrent();
        while (DistanceEventList.endList()){
    	   DistanceEventList.getCurrent().sortScores();
    	   DistanceEventList.nextCurrent(); 
    	   
       }
       

//Create sortedLinked list inherited from MyDoubleEndedLinkedList and insert DistanceEvent objects in order
       
       MyDoubleEndedLinkedList<DistanceEvent> sortedList = new MyDoubleEndedLinkedList<DistanceEvent>();
       DistanceEventList.resetCurrent();
       DistanceEventList.nextCurrent();
       while (DistanceEventList.endList()){
    	   sortedList.insert( DistanceEventList.getCurrent());
    	   DistanceEventList.nextCurrent(); 
       }
       sortedList.resetCurrent();
       sortedList.nextCurrent();
       
       
       
       
       
//Printing DistanceEvent objects of sortedLikedList in order
       int i = 0;
       System.out.println(EventName);
       while (sortedList.endList()){
    	   sortedList.getCurrent().display();
    	   switch(i){
    	   case 0: System.out.println(" GOLD");
    	   			break;
    	   case 1: System.out.println(" SILVER");
  			break;
    	   case 2: System.out.println(" BRONZE");
  			break;
  			default:System.out.println("");
   			break;
    	   } 
    	 
    	   i ++;
    	   
    	   sortedList.nextCurrent();
       } 

       /*for (DistanceEvent distanceEvent: DistanceEventList){
    	   System.out.println(distanceEvent.getname());*/
       
    scanner.close();

		
}


    }
        
