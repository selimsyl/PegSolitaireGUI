package PegGame;

import java.util.ArrayList;

public class Board {

    public abstract static class BoardBase {

    }

    public static class Board1 extends GameBoard {
        Board1() {
            super(7,7);
            unUsedPegCellListIndex.add(0);
            unUsedPegCellListIndex.add(1);
            unUsedPegCellListIndex.add(5);
            unUsedPegCellListIndex.add(6);
            unUsedPegCellListIndex.add(7);
            unUsedPegCellListIndex.add(13);
            unUsedPegCellListIndex.add(35);
            unUsedPegCellListIndex.add(41);
            unUsedPegCellListIndex.add(42);
            unUsedPegCellListIndex.add(43);
            unUsedPegCellListIndex.add(47);
            unUsedPegCellListIndex.add(48);
            for (var btnIdx:unUsedPegCellListIndex)
                PegCellList.get(btnIdx).unsetPeg();
            initUnusedPegs();
            PegCellList.get(17).unsetPeg();
        }
    }

    public static class Board2 extends GameBoard {
        Board2()
        {
            super(9,9);
            calcUnusedCells(0,3,3,9);
            calcUnusedCells(6,3,3,9);
            calcUnusedCells(54,3,3,9);
            calcUnusedCells(60,3,3,9);

            for (var btnIdx:unUsedPegCellListIndex)
                PegCellList.get(btnIdx).unsetPeg();
            initUnusedPegs();
            PegCellList.get(40).unsetPeg();
        }
    }
    public static class Board3 extends GameBoard {
        Board3()
        {
            super(8,8);
            calcUnusedCells(0,3,2,8);
            calcUnusedCells(5,3,3,8);
            calcUnusedCells(48,2,2,8);
            calcUnusedCells(53,2,3,8);

            for (var btnIdx:unUsedPegCellListIndex)
                PegCellList.get(btnIdx).unsetPeg();
            initUnusedPegs();
            PegCellList.get(35).unsetPeg();
        }
    }
    public static class Board4 extends GameBoard {
        Board4()
        {
            super(7,7);
            calcUnusedCells(0,2,2,7);
            calcUnusedCells(5,2,2,7);
            calcUnusedCells(35,2,2,7);
            calcUnusedCells(40,2,2,7);

            for (var btnIdx:unUsedPegCellListIndex)
                PegCellList.get(btnIdx).unsetPeg();
            initUnusedPegs();
            PegCellList.get(24).unsetPeg();
        }
    }

    public static class Board5 extends GameBoard {
        Board5()
        {
            super(9,9);
            unUsedPegCellListIndex.add(0);
            unUsedPegCellListIndex.add(1);
            unUsedPegCellListIndex.add(2);
            unUsedPegCellListIndex.add(3);
            unUsedPegCellListIndex.add(9);
            unUsedPegCellListIndex.add(10);
            unUsedPegCellListIndex.add(11);
            unUsedPegCellListIndex.add(18);
            unUsedPegCellListIndex.add(19);
            unUsedPegCellListIndex.add(27);

            unUsedPegCellListIndex.add(5);
            unUsedPegCellListIndex.add(6);
            unUsedPegCellListIndex.add(7);
            unUsedPegCellListIndex.add(8);
            unUsedPegCellListIndex.add(15);
            unUsedPegCellListIndex.add(16);
            unUsedPegCellListIndex.add(17);
            unUsedPegCellListIndex.add(25);
            unUsedPegCellListIndex.add(26);
            unUsedPegCellListIndex.add(35);

            unUsedPegCellListIndex.add(45);
            unUsedPegCellListIndex.add(54);
            unUsedPegCellListIndex.add(55);
            unUsedPegCellListIndex.add(63);
            unUsedPegCellListIndex.add(64);
            unUsedPegCellListIndex.add(65);
            unUsedPegCellListIndex.add(72);
            unUsedPegCellListIndex.add(73);
            unUsedPegCellListIndex.add(74);
            unUsedPegCellListIndex.add(75);

            unUsedPegCellListIndex.add(53);
            unUsedPegCellListIndex.add(62);
            unUsedPegCellListIndex.add(61);
            unUsedPegCellListIndex.add(71);
            unUsedPegCellListIndex.add(70);
            unUsedPegCellListIndex.add(69);
            unUsedPegCellListIndex.add(80);
            unUsedPegCellListIndex.add(79);
            unUsedPegCellListIndex.add(78);
            unUsedPegCellListIndex.add(77);

            for (var btnIdx:unUsedPegCellListIndex)
                PegCellList.get(btnIdx).unsetPeg();
            initUnusedPegs();

            PegCellList.get(40).unsetPeg();
        }

    }

    public static class Board6 extends GameBoard {
        Board6()
        { super(5,5); }
    }
}
