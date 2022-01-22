package PegGame;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var pegGame = new PegSolitaire(BoardFact.makeBoard(BoardFact.BoardTypes.Tpype5));
//        {var pegGame = new PegSolitaire(BoardFact.makeBoard(BoardFact.BoardTypes.Tpype4));}
//        {var pegGame = new PegSolitaire(BoardFact.makeBoard(BoardFact.BoardTypes.Tpype3));}
//        {var pegGame = new PegSolitaire(BoardFact.makeBoard(BoardFact.BoardTypes.Tpype2));}
//        {var pegGame = new PegSolitaire(BoardFact.makeBoard(BoardFact.BoardTypes.Tpype1));}
        pegGame.run();

    }
}
