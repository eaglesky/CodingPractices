import java.util.*;

//Implementation of circular buffer using start pointer and count.
public class MyCircularQueue2<T> {

	private final Object[] arr; //Note that T[] won't compile!
	private int start = 0; //Point to the element to be dequeued.
	private int count = 0; //Current number of elements

	//Assuming n >= 0
	public MyCircularQueue2(int n) {
		arr = new Object[n];
	}

	//Return false if already full, otherwise true;
	public boolean offer(T e) {
		if (count == arr.length) {
			return false;
		}
		int nextId = start + count;
		if (nextId >= arr.length) {
			nextId %= arr.length;
		}
		arr[nextId] = e;
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
		return arr.length;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	public int size() {
		return count;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Content: ");
		for (int i = 0; i < count; ++i) {
			sb.append(arr[(start+i) % arr.length] + ", ");
		}
		sb.append("Start = " + start + ", ");
		sb.append("Size = " + count);
		return sb.toString();
	}

	public static void main(String[] args) {
		MyCircularQueue2<Integer> cq = new MyCircularQueue2<>(4);
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