package algorithm.leetcode.algorithm;
/*
 * hard
 * 212. Word Search II 
 *  Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells 
are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Return ["eat","oath"].

Note:
You may assume that all inputs are consist of lowercase letters a-z. 
 */
import java.util.*;
public class NO212_WordSearchII {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		char[][] board = new char[m][n];
		for(int i = 0 ; i < m ; i++){
			board[i] = in.next().toCharArray();
		}
		int k = in.nextInt();
		String[] words = new String[k];
		for(int i = 0 ; i < k ; i++){
			words[i] = in.next();
		}
		System.out.println(findWords(board, words));
	}
	//方法1：
	//通过建立字典树，得到所有单词的查找
	//然后遍历每一个位置，进行dfs查找存在的单词
	static class Trie{
        char c;
        boolean isWord;
        Trie[] children;
        Trie(char c,boolean isWord){
            this.c = c;
            this.isWord = isWord;
            children = new Trie[26];
        }
    }
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0){
            return result;
        }
        Trie root = new Trie('\0',false);
        Trie node;
        //建立字典树
        for(String word : words){
            node = root;
            for(int i = 0 ; i < word.length() ; i++){
                char c = word.charAt(i);
                if(node.children[c-'a'] == null){
                    node.children[c-'a'] = new Trie(c,false);
                }
                node = node.children[c-'a'];
                if(i == word.length()-1){
                    node.isWord = true;
                }
            }
        }
        int m = board.length,n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        StringBuilder builder = new StringBuilder();
        int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                dfs(result,board,dirs,builder,i,j,root,visited);
            }
        }
        return result;
    }
    private static void dfs(List<String> result,char[][] board,int[][] dirs,StringBuilder builder,int x,int y,Trie node,boolean[][] visited){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length 
            || visited[x][y] == true || node == null || node.children[board[x][y]-'a'] == null){
            return;
        }
        node = node.children[board[x][y]-'a'];
        builder.append(board[x][y]);
        if(node.isWord){
            String s = builder.toString();
            if(!result.contains(s)){
                result.add(s);
            }
        }
        visited[x][y] = true;
        for(int[] dir : dirs){
            dfs(result,board,dirs,builder,x+dir[0],y+dir[1],node,visited);
        }
        builder.deleteCharAt(builder.length()-1);
        visited[x][y] = false;
    }
    
    //方法2：
    //同方法1，代码更简单，而且不需要额外的空间存储遍历痕迹
    public List<String> findWords2(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {   // found one
            res.add(p.word);
            p.word = null;     // de-duplicate
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,p, res); 
        if (j > 0) dfs(board, i, j - 1, p, res);
        if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
        if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode p = root;
            for (char c : w.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) p.next[i] = new TrieNode();
                p = p.next[i];
           }
           p.word = w;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}
