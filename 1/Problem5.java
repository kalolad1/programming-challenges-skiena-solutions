import java.util.*;

public class Problem5 {

	private static char image[][];
	private static final int[] xOffsets = {-1, 0, 1, 0};
	private static final int[] yOffsets = {0, 1, 0, -1};

	private static void initImage(int N, int M) {
		image = new char[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				image[i][j] = 'O';
			}
		}
	}

	private static void changePixelColor(int x, int y, char color) {
		image[x][y] = color;
	}

	private static void drawVerticalSegment(int x, int y1, int y2, char color) {
		for (int i = y1; i <= y2; i++) {
			image[i][x] = color;
		}
	}

	private static void drawHorizontalSegment(int x1, int x2, int y, char color) {
		for (int i = x1; i <= x2; i++) {
			image[y][i] = color;
		}
	}

	private static void drawRectangleSegment(int x1, int y1, int x2, int y2, char color) {
		for (int i = y1; i <= y2; i++) {
			for (int j = x1; j <= x2; j++) {
				image[i][j] = color;
			}
		}
	}

	private static void fillRegion(int x, int y, char color) {
		char oldColor = image[x][y];
		if (color == oldColor) {
			return;
		}
		
		fillRegionHelper(x, y, color, oldColor);
	}

	private static boolean inBounds(int x, int y) {
		if (x < 0 || x >= image.length) {
			return false;
		}
		if (y < 0 || y >= image[0].length) {
			return false;
		}
		return true;
	}

	private static void fillRegionHelper(int x, int y, char newColor, char oldColor) {
		if (image[x][y] == oldColor) {
			image[x][y] = newColor;

			for (int i = 0; i < xOffsets.length; i++) {
				if (inBounds(x + xOffsets[i], y + yOffsets[i])) {
					fillRegionHelper(x + xOffsets[i], y + yOffsets[i], newColor, oldColor);
				}
			}
		}
	}

	private static void printImageWithFileName(String fileName) {
		System.out.println(fileName);
		printImage();
	}

	private static void printImage() {
		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image[0].length; j++) {
				System.out.print(image[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			String operation = scan.next();
			if (operation.equals("X")) {
				break;
			} else if (operation.equals("I")) {
				int M = scan.nextInt();
				int N = scan.nextInt();
				initImage(N, M);
			} else if (operation.equals("L")) {
				int y = scan.nextInt();
				int x = scan.nextInt();
				String color = scan.next();
				changePixelColor(x - 1, y - 1, color.charAt(0));	
			} else if (operation.equals("V")) {
				int x = scan.nextInt();
				int y1 = scan.nextInt();
				int y2 = scan.nextInt();
				String color = scan.next();
				drawVerticalSegment(x - 1, y1 - 1, y2 - 1, color.charAt(0));
			} else if (operation.equals("H")) {
				int x1 = scan.nextInt();
				int x2 = scan.nextInt();
				int y = scan.nextInt();
				String color = scan.next();
				drawHorizontalSegment(x1 - 1, x2 - 1, y - 1, color.charAt(0));
			} else if (operation.equals("K")) {
				int x1 = scan.nextInt();
				int y1 = scan.nextInt();
				int x2 = scan.nextInt();
				int y2 = scan.nextInt();
				String color = scan.next();
				drawRectangleSegment(x1 - 1, y1 - 1, x2 - 1, y2 - 1, color.charAt(0));
			} else if (operation.equals("F")) {
				int y = scan.nextInt();
				int x = scan.nextInt();
				String color = scan.next();
				fillRegion(x - 1, y - 1, color.charAt(0));
			} else if (operation.equals("S")) {
				String fileName = scan.next();
				printImageWithFileName(fileName);
			} else {
				scan.nextLine();
			}
			
		}
		
	
	}
}
