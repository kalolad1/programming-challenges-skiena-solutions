import java.util.*;

public class Problem7 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		ChessBoard chessBoard = new ChessBoard(8, 8);
		int rowIndex = 0;
		int gameIndex = 0;
		while (scan.hasNextLine()) {
			String row = scan.nextLine();
			if (row.isEmpty()) {
				System.out.println("Game #" + ++gameIndex + ": " + chessBoard.getCheckStatus());
				rowIndex = 0;
				chessBoard.reset();
				continue;
			}
			for (int i = 0; i < row.length(); i++) {
				Piece piece;
				char p = row.charAt(i);
				if (p == '.') {
					continue;
				}
				Color color = Character.isUpperCase(p) ? Color.WHITE : Color.BLACK;
				p = Character.toLowerCase(p);
				if (p == 'p') {
					piece = new Pawn(rowIndex, i, color);
				} else if (p == 'k') {
					piece = new King(rowIndex, i, color);
				} else if (p == 'r') {
					piece = new Rook(rowIndex, i, color);
				} else if (p == 'b') {
					piece = new Bishop(rowIndex, i, color);
				} else if (p == 'q') {
					piece = new Queen(rowIndex, i, color);
				} 
				else {
					continue;
				} 
				chessBoard.addPiece(piece);
			}
			rowIndex++;
		}
	}
}

enum Color {
	WHITE, BLACK;
}

class Position {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
	public String toString() {
		return String.format("(%d, %d)", x, y);	
	}
}

abstract class Piece {
	private Color color;
	private Position position;
	
	public Piece(int x, int y, Color color) {
		this.position = new Position(x, y);
		this.color = color;
	}
	
	public boolean canCheck(ChessBoard chessBoard) {
		// System.out.println(String.format("Checking %s at (%d, %d)", this.toString(), getPosition().getX(), getPosition().getY()));
		ArrayList<Position> positions = getCheckablePositions(chessBoard);
		// System.out.println("Positions: ");
		// for (int i = 0; i < positions.size(); i++) {
		//	System.out.println(positions.get(i));
		//}
		for (int i = 0; i < positions.size(); i++) {
			if (chessBoard.isCheckablePosition(positions.get(i), color)) {
				return true;
			}
		}
		return false;
	}

	abstract ArrayList<Position> getCheckablePositions(ChessBoard chessBoard);

	public Position getPosition() {
		return this.position;
	}

	public Color getColor() {
		return this.color;
	}
}

class Pawn extends Piece {
	public Pawn(int x, int y, Color color) {
		super(x, y, color);
	}

	public ArrayList<Position> getCheckablePositions(ChessBoard chessBoard) {
		int rowOffset = 0;
		if (getColor() == Color.WHITE) {
			rowOffset = -1;
		} else if (getColor() == Color.BLACK) {
			rowOffset = 1;
		}
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(getPosition().getX() + rowOffset, getPosition().getY() - 1));	
		positions.add(new Position(getPosition().getX() + rowOffset, getPosition().getY() + 1));
		return positions;
	}
}

class King extends Piece {
	public King(int x, int y, Color color) {
		super(x, y, color);
	}

	public ArrayList<Position> getCheckablePositions(ChessBoard chessBoard) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int rowOffsets[] = {-1, -1, -1, 0, 0, 1, 1, 1};
		int colOffsets[] = {-1, 0, 1, -1, 1, -1, 0, 1};
		for (int i = 0; i < rowOffsets.length; i++) {
			positions.add(
				new Position(
					getPosition().getX() + rowOffsets[i],
					getPosition().getY() + colOffsets[i]));
		}
		return positions;
	}		
}

class Rook extends Piece {
	public Rook(int x, int y, Color color) {
		super(x, y, color);
	}

	public ArrayList<Position> getCheckablePositions(ChessBoard chessBoard) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int[] rowIncrements = {-1, 0, 1, 0};
		int[] colIncrements = {0, 1, 0, -1};
		for (int i = 0; i < rowIncrements.length; i++) {
			int currX = getPosition().getX();
			int currY = getPosition().getY();
			while (true) {
				currX += rowIncrements[i];
				currY += colIncrements[i];
				Position position = new Position(currX, currY);
				if (chessBoard.isPositionEmptyOrValid(position)) {
					positions.add(position);
				} else {
					break;
				}
			}
		}
		return positions;
	}
}

class Bishop extends Piece {
	public Bishop(int x, int y, Color color) {
		super(x, y, color);
	}
	
	public ArrayList<Position> getCheckablePositions(ChessBoard chessBoard) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int[] rowIncrements = {-1, 1, 1, -1};
		int[] colIncrements = {1, 1, -1, -1};
		for (int i = 0; i < rowIncrements.length; i++) {
			int currX = getPosition().getX();
			int currY = getPosition().getY();
			while (true) {
				currX += rowIncrements[i];
				currY += colIncrements[i];
				Position position = new Position(currX, currY);
				if (chessBoard.isPositionEmptyOrValid(position)) {
					positions.add(position);
				} else {
					break;
				}
			}
		}
		return positions;
	}	
}

class Queen extends Piece {
	public Queen(int x, int y, Color color) {
		super(x, y, color);
	}

	public ArrayList<Position> getCheckablePositions(ChessBoard chessBoard) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int[] rowIncrements = {-1, 1, 1, -1, -1, 0, 1, 0};
		int[] colIncrements = {1, 1, -1, -1, 0, 1, 0, -1};
		for (int i = 0; i < rowIncrements.length; i++) {
			int currX = getPosition().getX();
			int currY = getPosition().getY();
			while (true) {
				currX += rowIncrements[i];
				currY += colIncrements[i];
				Position position = new Position(currX, currY);
				if (chessBoard.isPositionEmptyOrValid(position)) {
					positions.add(position);
				} else {
					break;
				}
			}
		}
		return positions;
	}	
} 
 
class Knight extends Piece {
	public Knight(int x, int y, Color color) {
		super(x, y, color);
	}

	public ArrayList<Position> getCheckablePositions(ChessBoard chessBoard) {
		ArrayList<Position> positions = new ArrayList<Position>();
		int[] rowIncrements = {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] colIncrements = {1, 2, 2, 1, -1, -2, -2, -1};
		for (int i = 0; i < rowIncrements.length; i++) {
			Position position = new Position(
						getPosition().getX() + rowIncrements[i],
						getPosition().getY() + colIncrements[i]);
			if (chessBoard.isPositionEmptyOrValid(position)) {
				positions.add(position);
			}
		}
		return positions;
	}	
} 

class ChessBoard {
	private Piece[][] board;

	public ChessBoard(int rows, int col) {
		board = new Piece[rows][col];
	}
	
	public int getLength() {
		return board.length;
	}

	public boolean isPositionEmptyOrValid(Position position) {
		if (!isInBounds(position.getX(), position.getY())) {
			return false;	
		}
		
		if (board[position.getX()][position.getY()] == null) {
			return true;
		}
		if (board[position.getX()][position.getY()] instanceof King) {
			return true;
		}
		return false;	
	}

	public boolean isCheckablePosition(Position position, Color color) {
		return isInBounds(position.getX(), position.getY())
				&& board[position.getX()][position.getY()] != null
				&& board[position.getX()][position.getY()] instanceof King
				&& board[position.getX()][position.getY()].getColor() != color;
	}
	
	private boolean isInBounds(int x, int y) {
		if (x < 0 || x >= board.length) {
			return false;
		} 
		if (y < 0 || y >= board[0].length) {
			return false;
		}
		return true;
	}
	
	public void addPiece(Piece piece) {
		board[piece.getPosition().getX()][piece.getPosition().getY()] = piece;
	}

	private Piece getCheckingPiece() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != null && board[i][j].canCheck(this)) {
					return board[i][j];
				}	
			}
		}
		return null;
	}

	public String getCheckStatus() {
		Piece checker = getCheckingPiece();
		if (checker == null) {
			return "no king is in check";
		} else if (checker.getColor() == Color.WHITE) {
			return "black king is in check";
		} else if (checker.getColor() == Color.BLACK) {
			return "white king is in check";
		}
		return "error";
	}

	public void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void reset() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = null;
			}
		}
	}	
}
