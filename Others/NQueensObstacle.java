import java.util.*;

public class NQueensObstacle {

	private static boolean checkOneSide(int[][] board, int r, int c, int rInc, int cInc) {
		for (int newR = r + rInc, newC = c + cInc; newR >= 0 && newR < board.length
			&& newC >= 0 && newC < board[0].length; newR += rInc, newC += cInc) {
			if (board[newR][newC] == 2) {
				return false;
			} else if (board[newR][newC] == 1) {
				return true;
			}
		}
		return true;
	}

	private static boolean isValid(int[][] board, int r, int c) {
		if (r < 0 || r >= board.length || c < 0 || c >= board[0].length
			|| board[r][c] != 0) {
			return false;
		}
		int[][] incs = new int[][] {
			{-1, 0}, {1, 0}, {0, 1}, {0, -1}, 
			{1, 1}, {1, -1}, {-1, 1}, {-1, -1}
		};
		for (int[] inc : incs) {
			if (!checkOneSide(board, r, c, inc[0], inc[1])) {
				return false;
			}
		}
		return true;
	}

	private static void dfs(int[][] board, int id, int numQueensLeft,
		List<int[][]> result) {
		int len = board.length * board[0].length;
		if (numQueensLeft == 0 || id >= len) {
			if (numQueensLeft == 0) {
				int[][] curSolution = new int[board.length][];
				for (int i = 0; i < board.length; ++i) {
					curSolution[i] = board[i].clone();
				}
				result.add(curSolution);
			}
			return;
		}
		
		for (int i = id; i < len; ++i) {
			int r = i / board[0].length;
			int c = i % board[0].length;
			if (isValid(board, r, c)) {
				board[r][c] = 2;
				dfs(board, i + 1, numQueensLeft - 1, result);
				board[r][c] = 0;
			}
		}
	}

	public List<int[][]> solve(int[][] board, int m) {
		List<int[][]> result = new ArrayList<>();
		dfs(board, 0, m, result);
		return result;
	}

	private static void test(NQueensObstacle nqo, int[][] board, int m) {
		List<int[][]> result = nqo.solve(board, m);
		System.out.println(" m = " + m);
		for (int[][] solution : result) {
			for (int r = 0; r < solution.length; ++r) {
				System.out.println(Arrays.toString(solution[r]));
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		NQueensObstacle nqo = new NQueensObstacle();
		int n = 4;
		int m = 3;
		int[][] board = new int[][]{
			{0, 1, 0, 0},
			{0, 0, 1, 0},
			{1, 0, 1, 0},
			{0, 0, 0, 1}
		};
		test(nqo, board, 3);
		board = new int[][] {
			{0, 1, 0},
			{1, 1, 0}
		};
		test(nqo, board, 1);
		test(nqo, board, 2);
		test(nqo, board, 3);
	}
}