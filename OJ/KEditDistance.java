/*
Given a set of strings which just has lower case letters and a target string, 
output all the strings for each the edit distance with the target no greater than k.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example
Given words = ["abc", "abd", "abcd", "adc"] and target = "ac", k = 1
Return ["abc", "adc"]
*/

//Solution from Nine Chapter
public class KEditDistance {

    private static class TrieNode {
        public TrieNode[] children;
        public boolean hasWord;
        public String str;
        
        public TrieNode() {
            children = new TrieNode[26];
            for (int i = 0; i < 26; ++i)
                children[i] = null;
            hasWord = false;
        }
    
        // Adds a word into the data structure.
        static public void addWord(TrieNode root, String word) {
            TrieNode now = root;
            for(int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                if (now.children[c - 'a'] == null) {
                    now.children[c - 'a'] = new TrieNode();
                }
                now = now.children[c - 'a'];
            }
            now.str = word;
            now.hasWord = true;
        }
    }

    //Total time is O(n*word_average_len + l_target*n_trie_node)
    //Compared to the brute force way O(l_target*n*word_average_len), this way
    //should be faster when may words share the same prefix.
    public List<String> kDistance(String[] words, String target, int k) {
        
        //Prefer building the trie first and then do the dp computing.
        //If you do the dp computing while building the trie, it won't save
        //any time, and you can't use trie libs to customize addWord behaviour.
        //Another benefit is that this way is more efficient when we need compute
        //k distance for multiple targets of same input.
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++)
            TrieNode.addWord(root, words[i]);

        List<String> result = new ArrayList<String>();

        int n = target.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            dp[i] = i;

        find(root, result, k, target, dp);
        return result;
    }

    public void find(TrieNode node, List<String> result, int k, String target, int[] dp) {
        int n = target.length();
        //dp[i] is the edit distance between the current prefix(including the current node)
        //and target(0...i-1)
        if (node.hasWord && dp[n] <= k) {
            result.add(node.str);
        }
        int[] next = new int[n + 1];
        for (int i = 0; i <= n; ++i)
            next[i] = 0;

        for (int i = 0; i < 26; ++i)
            if (node.children[i] != null) {
                next[0] = dp[0] + 1;
                for (int j = 1; j <= n; j++) {
                    if (target.charAt(j - 1) - 'a' == i) {
                        next[j] = Math.min(dp[j - 1], Math.min(next[j - 1] + 1, dp[j] + 1));
                    } else {
                        next[j] = Math.min(dp[j - 1] + 1, Math.min(next[j - 1] + 1, dp[j] + 1));
                    }
                }
                find(node.children[i], result, k, target, next);
            }
    }
}