import java.util.*;

public class Problem4 {
	private static HashMap<Character, List<Integer>> map;
		
	private static void clearDisplay(char[][] display) {
		for (int i = 0; i < display.length; i++) {
			for (int j = 0; j < display[0].length; j++) {
				display[i][j] = ' ';
			}
		}
	}

	private static void fillSection(int section, char[][] display) {
		int s = (display.length - 3) / 2;
		switch (section) {
			case 0:
				for (int i = 1; i <= s; i++) {
					display[0][i] = '-';
				}
				break;
			case 1:
				for (int i = 1; i <= s; i++) {
					display[i][display[0].length - 1] = '|';
				}
				break;
			case 2:
				for (int i = 1; i <= s; i++) {
					display[s + 1 + i][display[0].length - 1] = '|';
				}
				break;
			case 3:
				for (int i = 1; i <= s; i++) {
					display[display.length - 1][i] = '-';
				}
				break;
			case 4:
				for (int i = 1; i <= s; i++) {
					display[s + 1 + i][0] = '|';
				}
				break;
			case 5:
				for (int i = 1; i <= s; i++) {
					display[s + 1][i] = '-';
				}
				break;
			case 6:
				for (int i = 1; i <= s; i++) {
					display[i][0] = '|';
				}
				break;
		}
	}

	private static void drawDigit(char digit, char[][] display)  {
		clearDisplay(display);

		List<Integer> filledSections = map.get(digit);
		for (int i = 0; i < filledSections.size(); i++) {
			fillSection(filledSections.get(i), display);
		}	
	}

	private static void printArray(char[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				System.out.print(array[i][j]);
			}
			System.out.println();
		}
	}

	private static void printNumber(int num, int size) {
		int row = size * 2 + 3;
		int col = size + 2;
		String numAsString= Integer.toString(num);

		List<char[][]> digitDisplays = new ArrayList<char[][]>();
		for (int i = 0; i < numAsString.length(); i++) {
			char digitDisplay[][] = new char[row][col];
			drawDigit(numAsString.charAt(i), digitDisplay);
			digitDisplays.add(digitDisplay);
		}	
		
		char fullDisplay[][] = new char[row][(col + 1) * numAsString.length()];
		for (int i = 0; i < digitDisplays.size(); i++) {
			char digitDisplay[][] = digitDisplays.get(i);
			for (int k = 0; k < row; k++) {
				for (int j = 0; j < col; j++) {
					fullDisplay[k][i * col + i + j] = digitDisplay[k][j];
				}	
			}
		}
		printArray(fullDisplay);
	}

	private static void init_map() {
		map = new HashMap<Character, List<Integer>>();
		map.put('0', Arrays.asList(0, 1, 2, 3, 4, 6));
		map.put('1', Arrays.asList(1, 2));
		map.put('2', Arrays.asList(0, 1, 5, 4, 3));
		map.put('3', Arrays.asList(0, 1, 3, 2, 5));
		map.put('4', Arrays.asList(6, 5, 1, 2));
		map.put('5', Arrays.asList(0, 6, 5, 2, 3));
		map.put('6', Arrays.asList(0, 6, 4, 5, 2, 3));
		map.put('7', Arrays.asList(0, 1, 2));
		map.put('8', Arrays.asList(0, 1, 2, 3, 4, 5, 6));
		map.put('9', Arrays.asList(0, 6, 5, 1, 2, 3));
	}

	public static void main(String[] args) {
		init_map();
		Scanner scan = new Scanner(System.in);
		int s = 0;
		int n = 0;
		while (true) {
			s = scan.nextInt();
			n = scan.nextInt();
			if (s == 0 && n == 0) {
				break;
			}
			printNumber(n, s);
		}		
	}
}
