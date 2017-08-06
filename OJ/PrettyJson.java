/*
https://www.interviewbit.com/problems/pretty-json/#_=_
https://discuss.leetcode.com/topic/249/print-json-format-string
*/

import java.util.*;

//Best logic is as follows, which can deal with the case when the required result is either
//a string or a list of strings. It is also flexible.
//
//Check each character in the given string(assuming it is valid json):
//	c is double quote: loop to the matching quote and add the whole string to StringBuilder(SB);
//	c is white space(' ', '\t', '\n'...): skip it and continue;
//	c is common character other than braces and brackets: 
//	Add it to SB, if it is comma, add newline and indents on the next line to SB.
//  c is '{' or '[':
//	If the last character in SB is whitespace('\t'), then it indicates that
//	the previous letter in the original string is '{' or ',', just add c, newline and
//  increased indents to SB.
//	Otherwise we need to add newline, indents on the currentline first, and then do the above.
//  c is '}' or ']':
//	Just add newline to SB and decreased indents on the next line, and c.
//	Note that if previous character is '{' or '[', the output will have a blank line between
//  the two braces or brackets. I'm not sure if this is acceptable.

// A few things need to consider:
//	1. How to add indents? Especially those before '{' or '['
//  2. The case when ',' is right after '}' or ']'.

public class PrettyJson {
    
    //First try
    private static StringBuilder getIndentsSb(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            sb.append('\t');
        }
        return sb;
    }
    
	public ArrayList<String> prettyJSON0(String a) {
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
	        } else if (Character.isWhitespace(c)) {
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


	//Create string first and then create array from it.
	private static void addIndentsToSb(StringBuilder sb, int n) {
        for (int i = 0; i < n; ++i) {
            sb.append('\t');
        }
    }

    public ArrayList<String> prettyJSON1(String a) {
	    ArrayList<String> result = new ArrayList<>();
	    StringBuilder sb = new StringBuilder();
	    int curIndentNum = 0;
	    for (int i = 0; i < a.length(); ++i) {
	        char c = a.charAt(i);
	        if (c == '"') {
	            sb.append(c);
	            for (++i; i < a.length() && (c = a.charAt(i)) != '"'; ++i) {
	                sb.append(c);
	            }
	            if (i >= a.length()) {
	                break;
	            }
	            sb.append(c);
	        } else if (Character.isWhitespace(c)) {
	            continue;
	        } else if (c != '{' && c != '}' && c != '[' && c != ']') {
	            sb.append(c);
	            if (c == ',') {
		            sb.append('\n');
		            addIndentsToSb(sb, curIndentNum);
	            }
	        } else if (c == '{' || c == '[') {
            	if (sb.length() > 0 && !Character.isWhitespace(sb.charAt(sb.length()-1))) {
            		sb.append('\n');
            		addIndentsToSb(sb, curIndentNum);
            	}
                sb.append(c).append('\n');
                addIndentsToSb(sb, ++curIndentNum);
	        } else {
            	sb.append('\n');
                addIndentsToSb(sb, --curIndentNum);
                sb.append(c);
	        }
	    }
	    StringBuilder curSb = new StringBuilder();
	    for (int i = 0; i < sb.length(); ++i) {
	    	char c = sb.charAt(i);
	    	if (c == '\n') {
	    		result.add(curSb.toString());
	    		curSb = new StringBuilder();
	    	} else {
	    		curSb.append(c);
	    	}
	    }
	    if (curSb.length() > 0) {
	    	result.add(curSb.toString());
	    }
	    return result;
	}

	//Similar to above logic, but create array list directly like the first try
	public ArrayList<String> prettyJSON2(String a) {
	    ArrayList<String> result = new ArrayList<>();
	    StringBuilder sb = new StringBuilder();
	    int curIndentNum = 0;
	    for (int i = 0; i < a.length(); ++i) {
	        char c = a.charAt(i);
	        if (c == '"') {
	            sb.append(c);
	            for (++i; i < a.length() && (c = a.charAt(i)) != '"'; ++i) {
	                sb.append(c);
	            }
	            if (i >= a.length()) {
	                break;
	            }
	            sb.append(c);
	        } else if (Character.isWhitespace(c)) {
	            continue;
	        } else if (c != '{' && c != '}' && c != '[' && c != ']') {
	            sb.append(c);
	            if (c == ',') {
		            result.add(sb.toString());
		            sb = getIndentsSb(curIndentNum);
	            }
	        } else if (c == '{' || c == '[') {
            	if (sb.length() > 0 && !Character.isWhitespace(sb.charAt(sb.length()-1))) {
            		result.add(sb.toString());
            		sb = getIndentsSb(curIndentNum);
            	}
                result.add(sb.append(c).toString());
                sb = getIndentsSb(++curIndentNum);
	        } else {
            	result.add(sb.toString());
                sb = getIndentsSb(--curIndentNum).append(c);
	        }
	    }
	    if (sb.length() > 0) {
	    	result.add(sb.toString());
	    }
	    return result;
	}

	//Better implementation logic
	//Returning arraylist makes more sense than returning a string,
	//since the latter way could overflow but the former does not.
	public ArrayList<String> prettyJSON(String a) {
	    ArrayList<String> result = new ArrayList<>();
	    if (a == null || a.isEmpty()) {
	        return result;
	    }
	    StringBuilder shift = new StringBuilder();
	    Set<Character> stopChars = new HashSet<>(
	        Arrays.asList(',', '{', '}', '[', ']'));
	    for (int i = 0; i < a.length(); ++i) {
	        char c = a.charAt(i);
	        if (Character.isWhitespace(c)) {
    	        continue;
	        }
            if (c == '{' || c == '[') {
                StringBuilder sb = new StringBuilder()
                                        .append(shift)
                                        .append(c);
                result.add(sb.toString());
                shift.append('\t');
            } else if (c == '}' || c == ']') {
                shift.setLength(shift.length() - 1);
                StringBuilder sb = new StringBuilder()
                                        .append(shift)
                                        .append(c);
                result.add(sb.toString());
            } else if (c == ',') {
                if (!result.isEmpty()) {
                    String lastStr = result.get(result.size() - 1);
                    result.set(result.size()-1, lastStr + c);
                }
            } else {
                StringBuilder sb = new StringBuilder().append(shift);
                for (; i < a.length() && !stopChars.contains(a.charAt(i)); ++i) {
                    sb.append(a.charAt(i));
                }
                --i;
                result.add(sb.toString());
            }
	    }
	    return result;
	}

	public static void main(String[] args) {
		String[] tests = {
			"{A:\"B\",C:{D:\"E\",F:{G:\"H\",I:\"J\"}}}",
			"[\"foo\", {\"bar\":[\"baz\",null,1.0,2]}]",
			"{\"id\": \"0001\", \"type\": \"donut\", \"name\": \"Cake\", \"ppu\": 0.55, \"batters\":{\"batter\":[{ \"id\": \"1001\", \"type\": \"Regular\" },{ \"id\": \"1002\", \"type\": \"Chocolate\" }]},\"topping\":[{ \"id\": \"5001\", \"type\": \"None\" },{ \"id\": \"5002\", \"type\": \"Glazed\" }]}",
			"[\"foo\", {\"bar\":[]}]"
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
