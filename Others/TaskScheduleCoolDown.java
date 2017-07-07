/* 
Given an array of task and k wait time for which a repeated task needs to wait k time to execute again. 
Return overall unit time it will take to complete all the task. 
Example: 
1. A B C D and k = 3 
ans: 4 (execute order A B C D) 
2. A B A D and k = 3 
ans: 6 (execute order A B . . A D) 
3. A A A A and k =3 
ans: 13 (A . . . A . . . A . . . A) 
4. A B C A C B D A and k = 4 
ans: 11 (A B C . . A .C B D A )
*/
import java.util.*;

public class TaskScheduleCoolDown {

	//O(n) time and O(n) space
	public static int totalTime0(String tasks, int k) {
		int elaspsedTime = 0;
		Map<Character, Integer> taskToTime = new HashMap<>();
		for (int i = 0; i < tasks.length(); ++i) {
			char task = tasks.charAt(i);
			Integer prevTime = taskToTime.get(task);
			if (prevTime != null) {
				elaspsedTime = Math.max(elaspsedTime, prevTime + k + 1);
			}
			taskToTime.put(task, elaspsedTime++);
		}
		return elaspsedTime;
	}

	//Another implementation
	public static int totalTime(String tasks, int k) {
		int elaspsedTime = 0;
		Map<Character, Integer> taskToTime = new HashMap<>();
		for (int i = 0; i < tasks.length(); ++i) {
			char task = tasks.charAt(i);
			Integer prevTime = taskToTime.get(task);
			if (prevTime != null) {
				elaspsedTime = Math.max(elaspsedTime + 1, prevTime + k + 2);
			} else {
				elaspsedTime++;
			}
			taskToTime.put(task, elaspsedTime - 1);
		}
		return elaspsedTime;
	}

	public static void main(String[] args) {
		String[] tests = new String[] {
			"ABCD", 
			"ABAD",
			"AAAA",
			"ABCACBDA",
			"12323",
			"1242353"
		};
		int[] limits = new int[] {
			3, 3, 3, 4, 3, 4
		};
		int[] answers = new int[] {
			4, 6, 13, 11, 7, 13
		};
		for (int i = 0; i < tests.length; ++i) {
			String test = tests[i];
			int limit = limits[i];
			System.out.println(test + ", " + limit);
			int result = totalTime(test, limit);
			System.out.println(result + ", expected = " + answers[i]);
		}
	}
}