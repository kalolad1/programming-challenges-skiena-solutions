#include <iostream>
#include <fstream>
#include <unordered_map>

using namespace std;

unordered_map<int, int> dict;

int calc_collatz(int i) {
	if (!(dict.find(i) == dict.end())) {
		return dict[i];
	}
	if (i == 1) {
		return 1;
	} else if (i % 2 == 0) {
		dict[i] = 1 + calc_collatz(i / 2);
		return dict[i];
	} else {
		dict[i] = 1 + calc_collatz(3 * i + 1);
		return dict[i];
	}
}

int calc_cycle_length(int i, int j) {
	int max_length = 0;
	int curr_collatz = 0;
	for (; i <= j; i++) {
		curr_collatz = calc_collatz(i);
		if (curr_collatz > max_length) {
			max_length = curr_collatz;
		}
	}
	return max_length;
}

int main() {
	ifstream inFile;
	inFile.open("1.txt");
	if (!inFile) {
		cout << "Failed to open file" << endl;
		return 0;	
	}
	
	int i, j;
	int cycle_length;
	while (inFile >> i) {
		inFile >> j;
		cycle_length = calc_cycle_length(i, j);
		cout << i << " " << j << " " << cycle_length << endl;
	}	
	inFile.close();
	return 0;
}
