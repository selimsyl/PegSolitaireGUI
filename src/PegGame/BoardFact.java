package PegGame;

public class BoardFact {
    public enum BoardTypes {Tpype1,Tpype2,Tpype3,Tpype4,Tpype5,Tpype6};
    public static Board.BoardBase makeBoard(BoardTypes type) {
        Board.BoardBase board;
        switch (type) {
            case Tpype1: board = new Board.Board1(); break;
            case Tpype2: board = new Board.Board2(); break;
            case Tpype3: board = new Board.Board3(); break;
            case Tpype4: board = new Board.Board4(); break;
            case Tpype5: board = new Board.Board5(); break;
            case Tpype6: board = new Board.Board6(); break;
            default: return null;
        }
        board.initializeBoard();
        return board;
    }
}
