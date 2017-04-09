/*
Given an array of contacts with phone numbers/emails you should detect and union identical contacts. 

For example, given the following contacts array: 

[ [ "John", "john@gmail.com", "john@fb.com"], 
[ "Dan", "dan@gmail.com", "+1234567"], 
[ "john123", "+5412312", "john123@skype.com"], 
[ "john1985", "+5412312", "john@fb.com"] ] 

We can see that john1985, John and john123 are the same person by their contact information. 
We should output [[0, 2, 3], [1]]

Assuming that the infos in each contact are different.
*/
import java.util.*;

public class UnionContacts {

	//DFS solution. Better visualize this before writing the code.
	//O(n) time and O(n) space, n is the total number of strings.
	//Each node in the graph represents a string in the contacts, and a box wrapping several
	//nodes represents a contact that include those strings. 
	//If we want to return a list of merged contacts, we can treat the string as node in DFS,
	//and use a hashset to store the strings that belong to the same contact.
	private static void dfs(int contactId, List<List<String>> contacts, Map<String, List<Integer>> map,
		int[] groups, int groupId) {
		if (groups[contactId] > 0) {
			return;
		}
		groups[contactId] = groupId;
		List<String> contact = contacts.get(contactId);
		for (String str : contact) {
			List<Integer> adjContactIds = map.get(str);
			for (int adjContactId : adjContactIds) {
				dfs(adjContactId, contacts, map, groups, groupId);
			}
		}
	}

	public static List<List<Integer>> unionContacts(List<List<String>> contacts) {
		Map<String, List<Integer>> str2ContactIds = new HashMap<>();
		int numContacts = contacts.size();
		int[] groups = new int[numContacts];
		for (int i = 0; i < numContacts; ++i) {
			for (String str : contacts.get(i)) {
				List<Integer> contactIds = str2ContactIds.get(str);
				if (contactIds == null) {
					contactIds = new ArrayList<>();
					str2ContactIds.put(str, contactIds);
				}
				contactIds.add(i);
			}
		}
		int groupId = 1;
		for (int i = 0; i < numContacts; ++i) {
			if (groups[i] == 0) {
				dfs(i, contacts, str2ContactIds, groups, groupId++);
			}
		}
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < groups.length; ++i) {
			int id = groups[i] - 1;
			if (result.size() <= id) {
				result.add(new ArrayList<>());
			}
			result.get(id).add(i);
		}
		return result;
	}

	public static void main(String[] args) {
		List<List<String>> contacts = new ArrayList<>();
		contacts.add((Arrays.asList("John", "john@gmail.com", "john@fb.com")));
		contacts.add((Arrays.asList("Dan", "dan@gmail.com", "+1234567")));
		contacts.add((Arrays.asList("john123", "+5412312", "john123@skype.com")));
		contacts.add((Arrays.asList("john1985", "+5412312", "john@fb.com")));
		contacts.add((Arrays.asList("Dan", "dan@gmail.com", "+1234567")));
		contacts.add((Arrays.asList("dan@gmail.com", "dan@hotmail.com")));
		contacts.add((Arrays.asList("john@gmail.com")));

		List<List<Integer>> result = unionContacts(contacts);
		System.out.println(result);
		//Expected: [[0, 2, 3, 6], [1, 4, 5]]
	}

}