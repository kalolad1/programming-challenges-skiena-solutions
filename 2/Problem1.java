import java.util.*;
import java.lang.*;


public class Problem1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			Scanner lineScanner = new Scanner(line);
			ArrayList<Integer> jollyJumper = new ArrayList<Integer>();
			int n = lineScanner.nextInt();
			for (int i = 0; i < n; i++) {
				jollyJumper.add(lineScanner.nextInt());
			}
			if (isJollyJumper(jollyJumper)) {
				System.out.println("Jolly");
			} else {
				System.out.println("Not jolly");
			}
		}	
	}

	private static boolean isJollyJumper(ArrayList<Integer> jollyJumper) {	
		if (jollyJumper.size() == 1) {
			return true;
		} 
		boolean range[] = new boolean[jollyJumper.size()];
		for (int i = 0; i < jollyJumper.size() - 1; i++) {
			int val = Math.abs(jollyJumper.get(i) - jollyJumper.get(i + 1));
			if (range[val]) {
				return false;
			} else {
				range[val] = true;
			}
		}
		return true;
	}
}
