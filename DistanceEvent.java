//Each Athletes performance will be stored as an object of type DistanceEvent
import java.util.Arrays;
import java.util.Comparator;


public class DistanceEvent implements Comparable<DistanceEvent>{

	   private String name;
	   private String country;
	   private Integer turn;
	   //-1 was used to represent scores/ attempts yet to be made
	   Object[] distance = new Object[]	{ 'U', 'U', 'U', 'U', 'U', 'U' };
	    Object [] sortedDistance;
	   public DistanceEvent(String name, String country, int turn){
	        this.name = name;
	        this.country = country;
	        this.turn = turn;
	  }

	   
	public String getname(){
		return name;
	}
	    
//Method to sort scores from further distance to shortest distance (i.e best to worst)   
	   
	   public void sortScores(){
	    	sortedDistance = distance.clone();
	        Arrays.sort(sortedDistance, new DistanceComparator());
	        }
	    
//Display data in the format of required output	    
	    public void display(){
	    	//System.out.println(Arrays.toString(sortedDistance));
	    	System.out.printf("%-15.15s", name);
	    	System.out.printf("%4.3s", country);
	    	for (int i = 0; i < 6; i ++){
	    	if (distance[i] instanceof Float){
	    		System.out.printf("%7.2f", distance[i]);}
	    	else{System.out.printf("%7s", distance[i] + "   ");}
	    	
	 
	    }}
	    
	    public static class DistanceComparator implements Comparator<Object> {

	        @Override
	        public int compare(Object o1, Object o2) {
	        	
	            if (o1 instanceof Float) {
	                if (o2 instanceof Float) {
	                    if ((Float) o1 > (Float) o2){
	                    	return -1;}
	                    else{
	                    	return 1;}
	                    } // Compare by int
	                 else {
	                    return -1; // int < String
	                }
	            } else {
	                if (o2 instanceof String) {
	                    return ((String) o1).compareTo((String) o2); // Compare by string
	                } else {
	                    return 1; // String > int
	                }
	            }
	        }
	    }	    
	    
	    
//Compare to method to compare distant event objects based on their performance
	    
	    @Override public int compareTo(DistanceEvent other) {
	    	for (int i =0; i < 6; i++){
	    		if (other.sortedDistance[i] != sortedDistance[i]) {
	    			if (sortedDistance[i] instanceof Float){
	    				if(other.sortedDistance[i] instanceof Float){
	    					if  ((Float) other.sortedDistance[i] < ((Float)sortedDistance[i])){
	    	    				return - 1;}
	    					else {return 1;}
	    					
	    					}
	    				else {return -1;}
	    			}
	    			else{
	    				if(other.sortedDistance[i] instanceof String){
	    					return ((String) sortedDistance[i]).compareTo((String) (other.sortedDistance[i]));}
	    				else{
	    					return 1;}
	    				}
	    		}
	    	}
	    	for (int i =0; i < 6; i++){
	    		 Integer x = Arrays.asList(distance).indexOf(sortedDistance[i]);
	    		 Integer y = Arrays.asList(other.distance).indexOf(other.sortedDistance[i]);

	    		 if (x != y) {
	    			 if (x < y){
	    				 return -1;
	    			 }
	    			 else{
	    				 return 1;}	
		    		}
	    	
	    	}
	    	
	    	if (turn == other.turn){
	    		return 0;
	    	}
	    	else if (turn < other.turn){
	    		return -1;
	    	}
	    	else{
	    		return 1;
	    	}
	    	
			
	    	
	  }
}

