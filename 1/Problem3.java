import java.util.*;
import java.lang.*;

public class Problem3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			int n = scan.nextInt();
			if (n == 0) {
				break;
			}			

			int[] moneySpent = new int[n];
			int totalSpent = 0;
			for (int i = 0; i < n; i++) {
				moneySpent[i] = (int)(scan.nextFloat() * 100);
				totalSpent += moneySpent[i];
			}
			int average = (int) Math.ceil((float) totalSpent / n);
			
			int moneyTransfered = 0;
			for (int i = 0; i < n; i++) {
				if (moneySpent[i] > average) {
					moneyTransfered += moneySpent[i] - average;
				}
			}
			String centsOutput = Integer.toString(moneyTransfered % 100);
			if (centsOutput.length() == 1) {
				centsOutput += "0";
			}
			System.out.println("$" + moneyTransfered / 100 + "." + centsOutput);

			
		}
	}
}
