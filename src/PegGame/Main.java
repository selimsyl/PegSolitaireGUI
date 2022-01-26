package PegGame;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var pegGame = new PegSolitaire();
        pegGame.run();
    }
}
