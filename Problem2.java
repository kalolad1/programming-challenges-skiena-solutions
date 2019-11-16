import java.util.*;

public class Problem2 {
	private static int[] rowOffsets = {-1, -1, -1, 0, 0, 1, 1, 1};
	private static int[] colOffsets = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	private static boolean is_valid(int[][] field, int row, int col) {
		if (row < 0 || row >= field.length) {
			return false;
		}

		if (col < 0 || col >= field[0].length) {
			return false;
		}

		if (field[row][col] == -1) {
			return false;
		}
		return true;
	}
	
	private static void increment_adj(int[][] field, int row, int col) {
		// First set curr as -1
		field[row][col] = -1;

		// Increment to 8 boxes around if they are valid
		for (int i = 0; i < rowOffsets.length; i++) {
			if (is_valid(field, row + rowOffsets[i], col + colOffsets[i])) {
				field[row + rowOffsets[i]][col + colOffsets[i]]++;
			}
		}
	}
		
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input_index = 1;
		while (true) {
			int n = scan.nextInt();
			int m = scan.nextInt();
			if (n == 0 && m == 0) {
				break;
			}
			char field[][] = new char[n][m];
			for (int i = 0; i < n; i++) {
				String row = scan.next();
				for (int j = 0; j < row.length(); j++) {
					field[i][j] = row.charAt(j);
				}
			}
			int output_field[][] = new int[n][m];
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (field[i][j] == '*') {
						increment_adj(output_field, i, j);
					}
				}
			}
			System.out.println("Field #" + input_index + ":");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (output_field[i][j] == -1) {
						System.out.print("*");
					} else {
						System.out.print(output_field[i][j]);
					}
				}
				System.out.println();
			}
			System.out.println();
		}			
	}
}
