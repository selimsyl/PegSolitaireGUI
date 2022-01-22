package PegGame;


import java.io.Serializable;

public class  BoardFact implements Serializable {
    public enum BoardTypes {Tpype1,Tpype2,Tpype3,Tpype4,Tpype5,Tpype6};

    static GameBoard makeBoard(BoardTypes type) {
        switch (type) {
            case Tpype1 -> {
                return new Board.Board1();
            }
            case Tpype2 -> {
                return new Board.Board2();
            }
            case Tpype3 -> {
                return new Board.Board3();
            }
            case Tpype4 -> {
                return new Board.Board4();
            }
            case Tpype5 -> {
                return new Board.Board5();
            }
            case Tpype6 -> {
                return new Board.Board6();
            }
        }
        return null;
    }
}
