import javax.swing.*;
import java.awt.*;

public class GraphicUI {
    private final static int numOfModuleValues = 9;
    private final static int numOfRowColBlocks = 3;

    private String windowName;
    private JFrame frame;
    private JPanel pnlGame;
    private JPanel pnlButtons;
    private JPanel pnlsSquares[][];
    private JTextField[][] textFields;
    private JButton btnSetValues;
    private JButton btnClearWindow;
    private SudokuIndepCol[] sudokuIndepCols;
    private SudokuIndepRow[] sudokuIndepRows;
    private SudokuIndepSquare[] sudokuIndepSquares;

    public GraphicUI(String windowName) {
        // init frame and panels
        this.frame = new JFrame();
        this.pnlGame = new JPanel();
        this.pnlButtons = new JPanel();

        // store window name
        this.windowName = windowName;

        // define text boxes and fields
        // "magic" numbers are constant for every Sudoku
        this.pnlsSquares = new JPanel[numOfRowColBlocks][numOfRowColBlocks];
        this.textFields = new JTextField[numOfModuleValues][numOfModuleValues];

        // init buttons
        this.btnSetValues = new JButton("Set");
        this.btnClearWindow = new JButton("Clear");
    }

    public void present() {
        // default operation for closing window
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // defines window size
        this.frame.setSize(600, 600);
        // defines window name
        this.frame.setTitle(this.windowName);

        // set 3*3 layout to the game grid
        this.pnlGame.setLayout(new GridLayout(numOfRowColBlocks,numOfRowColBlocks));

        for(int row = 0; row < numOfRowColBlocks; row++) {
            for(int col = 0; col < numOfRowColBlocks; col++) {
                this.pnlsSquares[row][col] = new JPanel();
                this.pnlsSquares[row][col].setLayout(new GridLayout(numOfRowColBlocks,numOfRowColBlocks));
            }
        }

        for(int row = 0; row < numOfModuleValues; row++) {
            for(int col = 0; col < numOfModuleValues; col++) {
                this.textFields[row][col] = new JTextField();
                this.pnlsSquares[row / numOfRowColBlocks][col / numOfRowColBlocks].add(this.textFields[row][col]);
            }
        }

        for(int row = 0; row < numOfRowColBlocks; row++) {
            for(int col = 0; col < numOfRowColBlocks; col++) {
                this.pnlGame.add(this.pnlsSquares[row][col]);
            }
        }

        this.pnlButtons.setLayout(new GridLayout(1,2));
        this.pnlButtons.add(this.btnSetValues);
        this.pnlButtons.add(this.btnClearWindow);

        // add game panel
        this.frame.add(this.pnlGame);
        // add button panel
        this.frame.add(this.pnlButtons);
        // make the window be visible
        this.frame.setVisible(true);
    }
}
