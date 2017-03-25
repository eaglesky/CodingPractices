import java.util.*;

public class PrettyJson {
    
    private static StringBuilder getIndentsSb(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append('\t');
        }
        return sb;
    }
    
	public ArrayList<String> prettyJSON(String a) {
	    ArrayList<String> result = new ArrayList<>();
	    StringBuilder preSb = new StringBuilder();
	    int curIndentNum = 0;
	    for (int i = 0; i < a.length(); ++i) {
	        char c = a.charAt(i);
	        if (c == '"') {
	            preSb.append(c);
	            for (++i; i < a.length() && (c = a.charAt(i)) != '"'; ++i) {
	                preSb.append(c);
	            }
	            if (i >= a.length()) {
	                break;
	            }
	            preSb.append(c);
	        } else if (c == ' ') {
	            continue;
	        } else if (c != '{' && c != '}' && c != '[' && c != ']') {
	            preSb.append(c);
	            if (c == ',') {
		            result.add(getIndentsSb(curIndentNum).append(preSb).toString());
		            preSb = new StringBuilder();
	            }
	        } else {
	            if (preSb.length() > 0) {
	                result.add(getIndentsSb(curIndentNum).append(preSb).toString());
	                preSb = new StringBuilder();
	            }
	            if (c == '{' || c == '[') {
	                StringBuilder curSb = getIndentsSb(curIndentNum);
	                curSb.append(c);
	                result.add(curSb.toString());
	                curIndentNum++;
	            } else {
	                curIndentNum--;
	                preSb.append(c);
	            }
	        }
	    }
	    if (preSb.length() > 0) {
	    	result.add(getIndentsSb(curIndentNum).append(preSb).toString());
	    }
	    return result;
	}

	public static void main(String[] args) {
		String[] tests = {
			"{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}",
			"[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]",
			"{\"id\": \"0001\", \"type\": \"donut\", \"name\": \"Cake\", \"ppu\": 0.55, \"batters\":{\"batter\":[{ \"id\": \"1001\", \"type\": \"Regular\" },{ \"id\": \"1002\", \"type\": \"Chocolate\" }]},\"topping\":[{ \"id\": \"5001\", \"type\": \"None\" },{ \"id\": \"5002\", \"type\": \"Glazed\" }]}"
		};
		PrettyJson solution = new PrettyJson();
		for (String test : tests) {
			System.out.println(test);
			List<String> result = solution.prettyJSON(test);
			for (String str : result) {
				System.out.println(str);
			}
			System.out.println("");
		}
	}
}
