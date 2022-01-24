package PegGame;

public class BoardFact {
    public static Board.BoardBase makeBoard(int type) {
        Board.BoardBase board;
        switch (type) {
            case 0: board = new Board.Board1(); break;
            case 1: board = new Board.Board2(); break;
            case 2: board = new Board.Board3(); break;
            case 3: board = new Board.Board4(); break;
            case 4: board = new Board.Board5(); break;
            case 5: board = new Board.Board6(); break;
            default: return null;
        }
        board.initializeBoard();
        return board;
    }
}
