import java.util.*;

//Implementation of circular buffer using start and end index pointers.
//This is already similar to the implementation of Java ArrayDeque,
//except that for the later it uses (tail - head) & (elements.length - 1)
//to compute the number of elements, so that it doesn't have count field.
public class MyCircularQueue<T> {

	private final Object[] arr; //Note that T[] won't compile!
	private int start = 0; //Point to the element to be dequeued.
	private int end = 0; //Point to the location to be enqueued.
	private int count = 0; //Current number of elements

	//Assuming n >= 0
	public MyCircularQueue(int n) {
		arr = new Object[n+1];
	}

	//Return false if already full, otherwise true;
	public boolean offer(T e) {
		if ((end + 1) % arr.length == start) {
			return false;
		}
		arr[end] = e;
		end = (end + 1) % arr.length;
		++count;
		return true;
	}

	//Return null if already empty, otherwise
	//return the dequeued element.
	public T poll() {
		if (isEmpty()) {
			return null;
		}
		T ret = (T)arr[start];
		start = (start + 1) % arr.length;
		--count;
		return ret;
	}

	public int capacity() {
		return arr.length - 1;
	}

	public boolean isEmpty() {
		return start == end;
	}

	public int size() {
		return count;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Content: ");
		int newEnd = (end < start) ? (end + arr.length) : end;
		for (int i = start; i < newEnd; ++i) {
			sb.append(arr[i % arr.length] + ", ");
		}
		sb.append("Start = " + start + ", ");
		sb.append("End = " + end + ", ");
		sb.append("Size = " + count);
		return sb.toString();
	}

	public static void main(String[] args) {
		MyCircularQueue<Integer> cq = new MyCircularQueue<>(4);
		cq.offer(1);
		cq.offer(2);
		cq.offer(3);
		cq.offer(4);
		System.out.println(cq.offer(5));
		System.out.println(cq); //Should be 1, 2, 3, 4
		while(!cq.isEmpty()) {
			System.out.println("Polled: " + cq.poll());
		}
		System.out.println(cq); //Should be empty
		for (int i = 0; i < 6; i++) {
			cq.offer(i);
		}
		System.out.println(cq); //Should be 0, 1, 2, 3
		cq.poll();
		cq.poll();
		cq.poll();
		cq.offer(6);
		cq.offer(7);
		System.out.println(cq); //Should be 3, 6, 7
	}
}