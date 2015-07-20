package iCal;

import java.util.Comparator;

/**
 * implements a Doubly-linked list
 * @author Scott Leung
 *
 * @param <E>
 */
public class EventLinkedList<E> {
  private DLinkedNode<E> head = new DLinkedNode<E>();
  private DLinkedNode<E> tail = new DLinkedNode<E>();
  private int size;

  /**
   * implements a doubly-linked node
   * @author scott leung
   *
   * @param <E>
   */
  private static class DLinkedNode<E> {
    E data;
    DLinkedNode<E> next = null;
    DLinkedNode<E> prev = null;
    
    /**
     * default constructor for a doubly-linked node
     */
    private DLinkedNode() {
      this.data = null;
      this.next = null;
      this.prev = null;
    }
    
    /**
     * constructor for a doubly-linked node that takes in an element
     * @param E element
     */
    private DLinkedNode(E element) {
      this.data = element;
    }
  }
  
  /**
   * Constructor for a linked list using doubly-linked nodes
   */
  public EventLinkedList() {
    this.head.next = tail;
    this.tail.prev = head;
    this.size = 0;
  }
  
  /**
   * adds an element to the end of a linked list
   * @param E e element to be added
   * @return true if element was added successfully
   */
  public boolean add(E e) {
    DLinkedNode<E> node = new DLinkedNode<E>(e);
    node.prev = tail.prev;
    tail.prev.next = node;
    node.next = tail;
    tail.prev = node;
    size++;
    return true;
  }
  
  /**
   * returns the size of the list
   * @return int size of the list
   */
  public int size() {
    return size;
  }
  
  
  /**
   * returns a string representation of the list
   * @author Data Structures by Koffman and Wolfgang
   * @return String the list
   */
  public String toString() {
    DLinkedNode<E> nodeRef = head.next;
    StringBuilder result = new StringBuilder();
    while (!nodeRef.equals(tail)) {
      result.append(nodeRef.data);
      if (!nodeRef.next.equals(tail)) {
        result.append(" \n");
      }
      nodeRef = nodeRef.next;
    }
    return result.toString();
  }
  
  /**
   * implements the insertion sort on the list
   * @param Comparator compare
   */
  public void insertionSort(Comparator<? super E> compare) {
    DLinkedNode<E> node = head.next.next;
    while (!node.equals(tail)) {
      E nextVal = node.data;
      DLinkedNode<E> compNode = node;
      while (!compNode.equals(head.next) && compare.compare(nextVal, compNode.prev.data) < 0) {
        compNode.data = compNode.prev.data;
        compNode = compNode.prev;
      }
      compNode.data = nextVal;
      node = node.next;
    }
  }

  public Event getNode(int index) {
	  DLinkedNode<Event> node = (DLinkedNode<Event>) head.next;
	  for (int i = 0; i < index && node != null; i++) {
		  node = node.next;
	  }
	  return node.data;
  }
  
  /**
   * calculates the great circle distance between two events 
   */
  public void calcGCD() {
    DLinkedNode<Event> node = (DLinkedNode<Event>) head.next;
    if (!(node.next == null)) {
    	while(!node.next.equals(tail)) {
	    	if(node.data.getDateStart().equals(node.next.data.getDateStart())) {
		        Double lat1 = (double) node.data.getLatitude();
		        Double long1 = (double) node.data.getLongitude();
		        Double lat2 = (double) node.next.data.getLatitude();
		        Double long2 = (double) node.next.data.getLongitude();
		        // NEED TO CONVERT DEGREES TO RADIANS. MISSING STEP 
		        if(lat1 != null && long1 != null && lat2 != null && long2 != null) {
		          float miles, km;
		        // ORIGINAL GCD CODE NOT CALCULATING CORRECTLY
		          miles = (float) (3963 * Math.acos(Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1 - long2) + Math.sin(lat1) * Math.sin(lat2)));
		          km = (float) (miles * 1.60934);
		          
		      //============================ TWO MORE GCD TESTS ================================  
		  /*
		        // FIRST METHOD: METHOD OF COSINES
		        // converting from degrees to radians
		          lat1 = Math.toRadians(lat1);
		          long1 = Math.toRadians(long1);
		          lat2 = Math.toRadians(lat2);
		          long2 = Math.toRadians(long2);
		        // GCD in radians TEST
		          double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
	                      + Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1 - long2));
		        // convert GCD back to degrees
		          angle = Math.toDegrees(angle);
		        // Each degree on a great circle of Earth is 69.1105 miles
		          double distance = 69.1105 * angle;
		        // Print out to screen
		          System.out.println(distance + " miles");
		          
		          
		        // SECOND METHOD: HAVERSINE FORMULA
		          public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
		        	    double earthRadius = 3958.75; // miles (or 6371.0 kilometers)
		        	    double dLat = Math.toRadians(lat2-lat1);
		        	    double dLng = Math.toRadians(lng2-lng1);
		        	    double sindLat = Math.sin(dLat / 2);
		        	    double sindLng = Math.sin(dLng / 2);
		        	    double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
		        	            * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
		        	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		        	    double dist = earthRadius * c;

		        	    return dist;
		        	    }
		       */   
		          
		          node.data.setComment("The great circle distance to your next event is " + miles + " miles(or " + km + "km).");    
		        }
    		}
	    	node = node.next;
    	}
    }
  }
  
}
