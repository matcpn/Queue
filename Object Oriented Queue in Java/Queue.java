class Queue {
	private int size;
	private int numberOfElements;
	private QueueElement head;
	private QueueElement tail; //not necessary, but allows for a O(1) insertion time at the cost of a small amount of memory

	Queue(int desiredQueueSize) {
		this.size = desiredQueueSize;
		this.numberOfElements = 0;
		this.head = null;
		this.tail = null;
	}
	public void enqueue(int elementToAdd) throws Exception {
		if (isFull()) 
			throw new Exception("Cannot add element. Queue is full");
		if (getHead() == null) {
			QueueElement queueElementToAdd = new QueueElement(elementToAdd, null);
			setHead(queueElementToAdd);
			setTail(queueElementToAdd);
			addElement();
			return;
		}
		else {
			QueueElement queueElementToAdd = new QueueElement(elementToAdd, null);
			//set the tail node's next element to point to this one
			getTail().setNext(queueElementToAdd);
			//keep track of the tail in the queue data type
			setTail(queueElementToAdd);
			addElement();
			return;
		}
	}
	public int dequeue() throws Exception {
		if (this.isEmpty())
			throw new Exception("Cannot Dequeue. Queue is empty.");
		int elementData = getHead().getData();
		//get rid of the head node
		setHead(getHead().getNext());
		detractElement();
		return elementData;
	}

	public String toString() {
		return (getHead() == null) ? "Empty Queue." : getHead().toString();
	}
	public boolean Equals(Object object) {
		return ( (object instanceof Queue) && getSize() == ((Queue)object).getSize() && getHead().Equals( ((Queue)object).getHead() ));
	}
	public int hashCode() {
		return (getHead() == null) ? size + numberOfElements : size + numberOfElements + getHead().hashCode();
	}

	int getSize() {
		return this.size;
	}
	QueueElement getHead() {
		return this.head;
	}
	void setHead(QueueElement head) {
		this.head = head;
	}
	QueueElement getTail() {
		return this.tail;
	}
	void setTail(QueueElement tail) {
		this.tail = tail;
	}
	private void detractElement() {
		this.numberOfElements--;
	}
	private void addElement() {
		this.numberOfElements++;
	}
	private boolean isEmpty() {
		return this.numberOfElements == 0;
	}
	private boolean isFull() {
		return this.numberOfElements == this.size;
	}
}

class QueueElement {
	private QueueElement nextElementInQueue;
	private int data;

	QueueElement(int data, QueueElement nextElementInQueue) {
		this.data = data;
		this.nextElementInQueue = nextElementInQueue; 
	}

	int getData() {
		return this.data;
	}
	void setData(int data) {
		this.data = data;
	}
	QueueElement getNext() {
		return nextElementInQueue;
	}
	void setNext(QueueElement nextElementInQueue) {
		this.nextElementInQueue = nextElementInQueue;
	}

	public boolean Equals(Object object) {
		return ( (object instanceof QueueElement) && getData() == ((QueueElement)object).getData() && getNext().Equals( ((QueueElement)object).getNext() ));
	}
	public int hashCode() {
		return (getNext() == null) ? getData() : getData() + getNext().hashCode();
	}
	public String toString() {
		return (getNext() == null) ? data + "" : data + " " + getNext().toString();
	}
}

class ExamplesQueue {
	public static void main(String [] args) {
		Queue testQueue1 = new Queue(3);

		//for simplicity, just going to use print statements. Actual code would use JUnit
		try {
			System.out.println("Expected: \"Empty Queue.\", Actual: " + testQueue1.toString());
			testQueue1.enqueue(1);
			System.out.println("Expected: \"1\", Actual: " + testQueue1.toString());
			testQueue1.enqueue(2);
			System.out.println("Expected: \"1 2\", Actual: " + testQueue1.toString());
			testQueue1.enqueue(3);
			System.out.println("Expected: \"1 2 3\", Actual: " + testQueue1.toString());
			testQueue1.enqueue(4);
		}
		catch (Exception e) {
			System.out.println("Expected: \"Cannot add element. Queue is full\", Actual: " + e.toString());
		}
		try {
			System.out.println("Expected: \"1\", Actual: " + testQueue1.dequeue());
			System.out.println("Expected: \"2\", Actual: " + testQueue1.dequeue());
			System.out.println("Expected: \"3\", Actual: " + testQueue1.dequeue());
			testQueue1.dequeue();
		}
		catch (Exception e) {
			System.out.println("Expected: \"Cannot Dequeue. Queue is empty.\", Actual: " + e.toString());
		}

	}
}