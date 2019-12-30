import java.util.*;
import java.lang.*;

public class Problem8 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numTestCase = scan.nextInt();
		for (int i = 0; i < numTestCase; i++) {
			Ballot ballot = createBallotFromInput(scan);
			ArrayList<Candidate> candidates = ballot.getWinningCandidates();
			for (int j = 0; j < candidates.size(); j++) {
				System.out.println(candidates.get(j));
			}
			System.out.println();
		}
		scan.close();
	}

	static public Ballot createBallotFromInput(Scanner scan) {	
		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		ArrayList<Vote> votes = new ArrayList<Vote>();
		
		int numCandidates = scan.nextInt();
		scan.nextLine();
		for (int j = 0; j < numCandidates; j++) {
			candidates.add(new Candidate(scan.nextLine(), j + 1));
		}
	
		while (scan.hasNextLine()) {
			String votesString = scan.nextLine();
			if (votesString.isEmpty()) {
				break;
			}
			Scanner voteScan = new Scanner(votesString);
			int[] voteInstance = new int[numCandidates];
			for (int k = 0; k < numCandidates; k++) {
				voteInstance[k] = voteScan.nextInt();
			}
			votes.add(new Vote(voteInstance));
		}
		Ballot ballot = new Ballot(candidates, votes);
		return ballot;
	}
}

class Ballot {
	private HashMap<Integer, Candidate> candidates;
	private ArrayList<Vote> votes;

	private static double THRESHOLD = 0.5;	

	public Ballot(ArrayList<Candidate> candidates, ArrayList<Vote> votes) {
		this.candidates = new HashMap<Integer, Candidate>();
		for (int i = 0; i < candidates.size(); i++) {
			this.candidates.put(candidates.get(i).getID(), candidates.get(i));
		}
		this.votes = votes;	
	}

	public void printDetails() {
		System.out.println("Candidates: ");
		for (Map.Entry<Integer, Candidate> entry : candidates.entrySet()) {
			System.out.println(entry.getKey());
		}

		System.out.println("Votes: ");
		for (int i = 0; i < votes.size(); i++) {
			System.out.println(votes.get(i));
		}
	}

	public ArrayList<Candidate> getWinningCandidates() {
		ArrayList<Candidate> winningCandidates = new ArrayList<Candidate>();
		HashMap<Integer, Candidate> remainingCandidates = new HashMap<Integer, Candidate>(candidates);
		do {
			winningCandidates = runRankingRound(remainingCandidates);
		} while (winningCandidates.size() < 1);	
		return winningCandidates;
	}

	private ArrayList<Candidate> runRankingRound(HashMap<Integer, Candidate> remainingCandidates) {
		ArrayList<Candidate> topCandidates = new ArrayList<Candidate>();
		HashMap<Candidate, Integer> candidateVotes = new HashMap<Candidate, Integer>();
		for (int i = 0; i < votes.size(); i++) {
			Candidate candidate = remainingCandidates.get(votes.get(i).getFirstCandidate());
			if (candidateVotes.containsKey(candidate)) {
				candidateVotes.put(candidate, candidateVotes.get(candidate) + 1);
			} else {
				candidateVotes.put(candidate, 1);
			}
		}
	
		int minVotes = Integer.MAX_VALUE;		
		for (Map.Entry<Candidate, Integer> entry : candidateVotes.entrySet()) {
			if ((float) entry.getValue() / votes.size() > THRESHOLD) {
				topCandidates.add(entry.getKey());
				return topCandidates;
			}
			if (entry.getValue() < minVotes) {
				minVotes = entry.getValue();
			}	
		}

		ArrayList<Integer> candidatesToRemove = new ArrayList<Integer>();
		for (Map.Entry<Candidate, Integer> entry : candidateVotes.entrySet()) {
			if (entry.getValue() == minVotes) {
				candidatesToRemove.add(entry.getKey().getID());
			}	
		}
		
		// If all min are tied.
		if (candidatesToRemove.size() == remainingCandidates.entrySet().size()) {
			for (Map.Entry<Integer, Candidate> entry : remainingCandidates.entrySet()) {
				topCandidates.add(entry.getValue());
			}
			return topCandidates;
		} else {
			for (int i = 0; i < candidatesToRemove.size(); i++) {
				remainingCandidates.remove(candidatesToRemove.get(i));
			}
		}

		// Update vote preferences.
		removeCandidatesFromVotes(candidatesToRemove);	
		return topCandidates;	
	}

	private void removeCandidatesFromVotes(ArrayList<Integer> candidatesToRemove) {
		for (int i = 0; i < candidatesToRemove.size(); i++) {
			int c = candidatesToRemove.get(i);
			for (int j = 0; j < votes.size(); j++) {
				if (votes.get(j).getFirstCandidate() == c) {
					votes.get(j).popFirstCandidate();
				}
			}
		}
	}
}

class Candidate {
	private String name;
	private int ID;
	
	public Candidate(String name, int ID) {
		this.name = name;
		this.ID = ID;
	}

	public String toString() {
		return name;
	}

	public int getID() {
		return ID;
	}
}

class Vote {
	Stack<Integer> votes;
	public Vote(int[] votes) {
		this.votes = new Stack<Integer>();
		for (int i = votes.length - 1; i >= 0; i--) {
			this.votes.push(votes[i]);
		}
	}

	public String toString() {
		return Arrays.toString(votes.toArray());
	}

	public Integer getFirstCandidate() {
		return votes.peek();
	}
	
	public Integer popFirstCandidate() {
		return votes.pop();
	}

}
