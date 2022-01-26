package PegGame;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Board namespace includes board base type and board types
 */
public class Board {

    /**
     * Abstract board base type for common board operations
     */
    public abstract static class BoardBase implements Serializable {

        /**
         * @param rowSize rowSize
         * @param columnSize columnSize
         */
        public BoardBase(int rowSize, int columnSize) {
            this.rowSize =rowSize;
            this.columnSize = columnSize;
            for (int i =0; i < rowSize*columnSize; ++i) {
                var btn = new PegCell(i,PegCell.peg);
                btn.setBackground(Color.white);
                PegCellList.add(btn);
            }
        }

        /**
         * Initializes board each JButton cell
         */
        public void initializeBoard() {
            for (var btnIdx:unUsedPegCellIndexList) {
                PegCellList.get(btnIdx).setBackground(Color.lightGray);
                PegCellList.get(btnIdx).setUnused();
            }
        }

        /**
         * @param idx JButton cell index
         * @return Jbutton is included in board or not
         */
        public boolean isCellValid(Integer idx) {
            for (var index:unUsedPegCellIndexList) {
                if (idx.equals(index))
                    return false;
            }
            return true;
        }

        /**
         * @param no JButton cell index diff
         * @return True if clicked Jbutton beging moved to valid position
         * on board.
         */
        public boolean isValidMove(int no) {
            return no==rowSize+columnSize ||
                    no==-(rowSize+columnSize) ||
                    no==2                 ||
                    no==-2;
        }

        /**
         * @param idx Index of JButton Cell
         * @return Reference  to JButton Cell
         */
        public PegCell getCell(int idx) {
            if (idx < 0 || idx > rowSize*columnSize)
                throw new IndexOutOfBoundsException();
            return PegCellList.get(idx);
        }

        /**
         * @return Reference to JButton cell list
         */
        public ArrayList<PegCell> getPegCellList() {
            return PegCellList;
        }

        /**
         * @param cellIdx cellIdx
         * @param diff  diff
         * @return True if cell is located either end or start of row
         */
        boolean checkCornerCases(int cellIdx,int diff) {
            if (diff==1&&cellIdx!=1&&cellIdx!=columnSize+1)
                return (cellIdx)%(columnSize) == columnSize-1;

            if (diff==-1)
                return (cellIdx)%(columnSize) == 0;
            return false;
        }

        /**
         * @return True if there is no more cell to be moved
         */
        boolean isGameFinished() {
            for (int i = 0; i < PegCellList.size(); ++i) {
                if (PegCellList.get(i).isPeg()) {
                    for (var diff: new int[]{-1, 1, rowSize, -rowSize}) {
                        try {
                            if (getCell(i + diff).isPeg() && getCell(i + 2 * diff).isNoPeg()) {
                                if (checkCornerCases(i,diff))
                                    continue;
                                return false;
                            }
                        }catch (IndexOutOfBoundsException e) {

                        }
                    }
                }
            }
            return true;
        }

        /**
         * @param startIdx startIdx
         * @param rowCnt rowCnt
         * @param columnCnt columnCnt
         * @param increase increase
         */
        protected void calcUnusedCells(int startIdx,int rowCnt,int columnCnt,int increase) {
            for (int i=0;i<rowCnt;++i) {
                for(int k=0;k<columnCnt;++k){
                    unUsedPegCellIndexList.add(startIdx + (i*increase) + k);
                }
            }
        }

        /**
         * JButton Cell list
         */
        protected ArrayList<PegCell> PegCellList = new ArrayList<>();
        /**
         * Unused JButton Cell indexes to seperate them
         */
        protected ArrayList<Integer> unUsedPegCellIndexList = new ArrayList<>();
        /**
         * Board row size
         */
        protected int rowSize;
        /**
         * Board column size
         */
        protected int columnSize;
    }

    /**
     * Board Type One
     */
    public static class Board1 extends BoardBase {
        /**
         * To set specific board shape unused sells
         * is determined in no param ctor
         */
        Board1() {
            super(7,7);
            unUsedPegCellIndexList.add(0);
            unUsedPegCellIndexList.add(1);
            unUsedPegCellIndexList.add(5);
            unUsedPegCellIndexList.add(6);
            unUsedPegCellIndexList.add(7);
            unUsedPegCellIndexList.add(13);
            unUsedPegCellIndexList.add(35);
            unUsedPegCellIndexList.add(41);
            unUsedPegCellIndexList.add(42);
            unUsedPegCellIndexList.add(43);
            unUsedPegCellIndexList.add(47);
            unUsedPegCellIndexList.add(48);

            PegCellList.get(17).unsetPeg();
        }
    }

    /**
     * Board Type Two
     */
    public static class Board2 extends BoardBase {
        /**
         * To set specific board shape unused sells
         * is determined in no param ctor
         */
        Board2()
        {
            super(9,9);
            calcUnusedCells(0,3,3,9);
            calcUnusedCells(6,3,3,9);
            calcUnusedCells(54,3,3,9);
            calcUnusedCells(60,3,3,9);

            PegCellList.get(40).unsetPeg();
        }
    }

    /**
     * Board Type Three
     */
    public static class Board3 extends BoardBase {
        /**
         * To set specific board shape unused sells
         * is determined in no param ctor
         */
        Board3()
        {
            super(8,8);
            calcUnusedCells(0,3,2,8);
            calcUnusedCells(5,3,3,8);
            calcUnusedCells(48,2,2,8);
            calcUnusedCells(53,2,3,8);

            PegCellList.get(35).unsetPeg();
        }
    }

    /**
     * Board Type Four
     */
    public static class Board4 extends BoardBase {
        /**
         * To set specific board shape unused sells
         * is determined in no param ctor
         */
        Board4()
        {
            super(7,7);
            calcUnusedCells(0,2,2,7);
            calcUnusedCells(5,2,2,7);
            calcUnusedCells(35,2,2,7);
            calcUnusedCells(40,2,2,7);

            PegCellList.get(24).unsetPeg();
        }
    }

    /**
     * Board Type Five
     */
    public static class Board5 extends BoardBase {
        /**
         * To set specific board shape unused sells
         * is determined in no param ctor
         */
        Board5()
        {
            super(9,9);
            unUsedPegCellIndexList.add(0);
            unUsedPegCellIndexList.add(1);
            unUsedPegCellIndexList.add(2);
            unUsedPegCellIndexList.add(3);
            unUsedPegCellIndexList.add(9);
            unUsedPegCellIndexList.add(10);
            unUsedPegCellIndexList.add(11);
            unUsedPegCellIndexList.add(18);
            unUsedPegCellIndexList.add(19);
            unUsedPegCellIndexList.add(27);

            unUsedPegCellIndexList.add(5);
            unUsedPegCellIndexList.add(6);
            unUsedPegCellIndexList.add(7);
            unUsedPegCellIndexList.add(8);
            unUsedPegCellIndexList.add(15);
            unUsedPegCellIndexList.add(16);
            unUsedPegCellIndexList.add(17);
            unUsedPegCellIndexList.add(25);
            unUsedPegCellIndexList.add(26);
            unUsedPegCellIndexList.add(35);

            unUsedPegCellIndexList.add(45);
            unUsedPegCellIndexList.add(54);
            unUsedPegCellIndexList.add(55);
            unUsedPegCellIndexList.add(63);
            unUsedPegCellIndexList.add(64);
            unUsedPegCellIndexList.add(65);
            unUsedPegCellIndexList.add(72);
            unUsedPegCellIndexList.add(73);
            unUsedPegCellIndexList.add(74);
            unUsedPegCellIndexList.add(75);

            unUsedPegCellIndexList.add(53);
            unUsedPegCellIndexList.add(62);
            unUsedPegCellIndexList.add(61);
            unUsedPegCellIndexList.add(71);
            unUsedPegCellIndexList.add(70);
            unUsedPegCellIndexList.add(69);
            unUsedPegCellIndexList.add(80);
            unUsedPegCellIndexList.add(79);
            unUsedPegCellIndexList.add(78);
            unUsedPegCellIndexList.add(77);

            PegCellList.get(40).unsetPeg();
        }

    }

    /**
     * Board Type Six
     */
    public static class Board6 extends BoardBase {
        /**
         * No Implemented
         */
        Board6()
        { super(5,5); }
    }
}
