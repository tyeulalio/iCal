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
        result.append(" <==> ");
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
  
}
