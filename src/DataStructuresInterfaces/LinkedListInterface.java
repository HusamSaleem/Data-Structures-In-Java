package DataStructuresInterfaces;


public interface LinkedListInterface {
	public <E> boolean insertAtEnd(E data);
	
	public <E> boolean insertAtHead(E data);
	
	public <E> E peek();
	
	public <E> E removeEnd();
	
	public <E> E removeHead();
	
	public boolean isEmpty();
}
