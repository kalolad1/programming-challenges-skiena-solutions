import java.util.*;

public class Problem6 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		scan.nextLine();
		for (int i = 0; i < n; i++) {
			List<String> instructions = new ArrayList<String>();
			while (true) {
				String word = scan.nextLine();
				if (word.length() == 0) {
					break;
				}
				instructions.add(word);
			}	
			RAM ram = new RAM(instructions);
			int output = ram.processInstructions();
			System.out.println(output + "\n");
		}
	}
}


class RAM {
	private int[] registers;
	private List<String> instructions;
	
	public RAM(List<String> instructions) {
		registers = new int[10];
		this.instructions = instructions;
	}
	
	private void printRegisters() {
		for (int i = 0; i < registers.length; i++) {
			System.out.print(registers[i] + " ");
		}
		System.out.println();
		for (int i = 0; i < registers.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println();
	}

	public int processInstructions() {
		int instructionsExecuted = 0;
		int programCounter = 0;
		while (true) {
			String word = instructions.get(programCounter);
			instructionsExecuted++;
			programCounter = processWord(word, programCounter);
			if (programCounter == -1) {
				break;
			}	
		}	
		return instructionsExecuted;
	}
	
	private int getInt(char c) {
		return Character.getNumericValue(c);
	}

	private int processWord(String word, int programCounter) {
		char op = word.charAt(0);
		// First integer
		int f = getInt(word.charAt(1));
		// Second integer
		int s = getInt(word.charAt(2));
		if (op == '1') {
			return -1;
		} else if (op == '2') {
			setRegister(f, s);
		} else if (op == '3') {
			addToRegister(f, s);
		} else if (op == '4') {
			multiplyRegister(f, s);
		} else if (op == '5') {
			setRegister(f, getValAtRegister(s));
		} else if (op == '6') {
			addToRegister(f, getValAtRegister(s));
		} else if (op == '7') {
			multiplyRegister(f, getValAtRegister(s));
		} else if (op == '8') {
			setRegister(f, getValAtRegisterAddress(s));
		} else if (op == '9') {
			setRam(getValAtRegister(s), getValAtRegister(f));
		} else if (op == '0') {
			if (getValAtRegister(s) != 0) {
				programCounter = getValAtRegister(f);
				return programCounter;
			}
		}
		return ++programCounter;
	}

	private void setRam(int index, int val) {
		val %= 1000;
		instructions.set(index, String.format("%03d", val));
	}

	private int getValAtRegisterAddress(int a) {
		return Integer.parseInt(instructions.get(registers[a]));
	}

	private int getValAtRegister(int d) {
		return registers[d];
	}

	private void multiplyRegister(int d, int n) {
		registers[d] = (registers[d] * n) % 1000;
	}

	private void setRegister(int d, int n) {
		registers[d] = n % 1000;
	}

	private void addToRegister(int d, int n) {
		registers[d] = (registers[d] + n) % 1000;
	}
}

