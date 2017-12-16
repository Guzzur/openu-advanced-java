import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicUI {
    // defines block (row, col, square) size
    private final static int numOfModuleValues = 9;
    // defines num of blocks
    private final static int numOfRowColBlocks = 3;

    // stores windows name
    private String windowName;
    // window's frame
    private JFrame frame;
    // main panel
    private JPanel pnlMain;
    // panel that holds the game
    private JPanel pnlGame;
    // panel that holds buttons
    private JPanel pnlButtons;
    // array of panel for each square (block)
    private JPanel[][] pnlsSquares;
    // array of text fields (the board)
    private JTextField[][] textFields;
    // button "Set"
    private JButton btnSetValues;
    // button "Clear"
    private JButton btnClearWindow;
    // array of rows
    private SudokuIndepModule[] sudokuIndepCols;
    // array of columns
    private SudokuIndepModule[] sudokuIndepRows;
    // array of squares (blocks)
    private SudokuIndepModule[] sudokuIndepSquares;

    /**
     * C'tor
     * @param windowName name to be shown
     */
    public GraphicUI(String windowName) {
        // init frame and panels
        this.frame = new JFrame();
        this.pnlMain = new JPanel();
        this.pnlGame = new JPanel();
        this.pnlButtons = new JPanel();

        // store window name
        this.windowName = windowName;

        // define text boxes and fields
        this.pnlsSquares = new JPanel[numOfRowColBlocks][numOfRowColBlocks];
        this.textFields = new JTextField[numOfModuleValues][numOfModuleValues];

        // set layout to the main grid
        //this.pnlMain.setLayout(new GridLayout(2,1));
        this.pnlMain.setLayout(new BorderLayout());

        // set 3*3 layout to the game grid
        this.pnlGame.setLayout(new GridLayout(numOfRowColBlocks,numOfRowColBlocks));

        // set layout to the buttons grid
        this.pnlButtons.setLayout(new GridLayout(1,2));

        // default operation for closing window
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // defines window size
        this.frame.setSize(500, 520);
        // defines window name
        this.frame.setTitle(this.windowName);

        // init buttons
        this.btnSetValues = new JButton("Set");
        this.btnClearWindow = new JButton("Clear");

        // init game modules
        this.sudokuIndepCols = new SudokuIndepModule[numOfModuleValues];
        this.sudokuIndepRows = new SudokuIndepModule[numOfModuleValues];
        this.sudokuIndepSquares = new SudokuIndepModule[numOfModuleValues];
    }

    /**
     * Presents the board
     */
    public void present() {
        // defines font for cells
        Font font = new Font("SansSerif", Font.BOLD, 30);

        // creates panel for each square
        for(int row = 0; row < numOfRowColBlocks; row++) {
            for(int col = 0; col < numOfRowColBlocks; col++) {
                this.pnlsSquares[row][col] = new JPanel();
                this.pnlsSquares[row][col].setLayout(new GridLayout(numOfRowColBlocks,numOfRowColBlocks));
            }
        }

        for(int row = 0; row < numOfModuleValues; row++) {
            // initializes game modules
            this.sudokuIndepCols[row] = new SudokuIndepModule();
            this.sudokuIndepRows[row] = new SudokuIndepModule();
            this.sudokuIndepSquares[row] = new SudokuIndepModule();

            for(int col = 0; col < numOfModuleValues; col++) {
                // creates fields (board)
                this.textFields[row][col] = new JTextField();

                // adds listener for "enter pressed" checking
                this.textFields[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        // checks validity of entry
                        checkValidity(true);
                    }
                });

                // sets font parameters
                this.textFields[row][col].setFont(font);
                this.textFields[row][col].setForeground(Color.BLUE);
                this.textFields[row][col].setHorizontalAlignment(SwingConstants.CENTER);

                // places the field to proper panel
                this.pnlsSquares[row / numOfRowColBlocks][col / numOfRowColBlocks].add(this.textFields[row][col]);

                // change background color as shown in the exercise
                // _V_|___|_V_
                // ___|_V_|___
                //  V |   | V
                if (((row < numOfRowColBlocks || row >= numOfRowColBlocks*2) && (col < numOfRowColBlocks || col >= numOfRowColBlocks*2)) ||
                        (row >= numOfRowColBlocks && row < numOfRowColBlocks*2 && (col >= numOfRowColBlocks && col < numOfRowColBlocks*2))) {
                    this.textFields[row][col].setBackground(Color.YELLOW);
                }
            }
        }

        // add all square panels to the game panel
        for(int row = 0; row < numOfRowColBlocks; row++) {
            for(int col = 0; col < numOfRowColBlocks; col++) {
                this.pnlGame.add(this.pnlsSquares[row][col]);
            }
        }

        // add listener to "Set" button
        this.btnSetValues.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(checkValidity(true)) {
                    // disable the button
                    btnSetValues.setEnabled(false);
                    for(int row = 0; row < numOfModuleValues; row++) {
                        for (int col = 0; col < numOfModuleValues; col++) {
                            if(!textFields[row][col].getText().isEmpty()) {
                                // disable permanent fields
                                textFields[row][col].setEnabled(false);
                            }
                        }
                    }
                }
            }
        });

        // adds listener to "Clear" button
        this.btnClearWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // enables "Set" button
                btnSetValues.setEnabled(true);
                for(int row = 0; row < numOfModuleValues; row++) {
                    for(int col = 0; col < numOfModuleValues; col++) {
                        // clears all fields
                        textFields[row][col].setText("");
                        // enables all fields
                        textFields[row][col].setEnabled(true);
                    }
                }
            }
        });

        // adds buttons to buttons panel
        this.pnlButtons.add(this.btnSetValues);
        this.pnlButtons.add(this.btnClearWindow);
        // add game panel
        this.pnlMain.add(this.pnlGame, BorderLayout.CENTER);
        // add button panel
        this.pnlMain.add(this.pnlButtons, BorderLayout.SOUTH);
        // add main panel
        this.frame.add(this.pnlMain);
        // place in the center of the screen
        frame.setLocationRelativeTo(null);
        // make the window be visible
        this.frame.setVisible(true);
    }

    /**
     * Check validity for each module (row, column, square)
     * after making a change in the game board
     * @param isClearField true if wrong field value should be removed
     * @return true if every module's entry is valid
     */
    private boolean checkValidity(boolean isClearField) {
        // checks for row modules
        for (int row = 0; row < numOfModuleValues; row++) {
            // reset module
            this.sudokuIndepRows[row] = new SudokuIndepModule();
            for(int col = 0; col < numOfModuleValues; col++) {
                // check if the field is not empty
                if(!this.textFields[row][col].getText().isEmpty()) {
                    // get value into integer
                    int value = Integer.parseInt(this.textFields[row][col].getText());
                    // try to update the value from board in the module
                    if (!this.sudokuIndepRows[row].updateValue(col, value)) {
                        // cannot update this field, take an action
                        if (isClearField) {
                            this.textFields[row][col].setText("");
                        }
                        else this.textFields[row][col].setForeground(Color.RED);
                        // pop-up message
                        showMessage(value,row,col,"row");
                        return false;
                    }
                    // change text color to blue (valid)
                    else this.textFields[row][col].setForeground(Color.BLUE);
                }
                // is empty, remove from module
                else this.sudokuIndepRows[row].removeValue(col);
            }
        }
        // checks for column modules
        for (int col = 0; col < numOfModuleValues; col++) {
            // reset module
            this.sudokuIndepCols[col] = new SudokuIndepModule();
            for(int row = 0; row < numOfModuleValues; row++) {
                // check if the field is not empty
                if(!this.textFields[row][col].getText().isEmpty()) {
                    // get value into integer
                    int value = Integer.parseInt(this.textFields[row][col].getText());
                    // try to update the value from board in the module
                    if (!this.sudokuIndepCols[col].updateValue(row, value)) {
                        // cannot update this field, take an action
                        if (isClearField) {
                            this.textFields[row][col].setText("");
                        }
                        else this.textFields[row][col].setForeground(Color.RED);
                        // pop-up message
                        showMessage(value,row,col,"column");
                        return false;
                    }
                    // change text color to blue (valid)
                    else this.textFields[row][col].setForeground(Color.BLUE);
                }
                // is empty, remove from module
                else this.sudokuIndepCols[col].removeValue(row);
            }
        }
        // defines index in the square (block)
        int squareIndex = 0;
        // checks for row modules
        for(int blockRow = 0; blockRow < numOfRowColBlocks; blockRow++) {
            for (int blockCol = 0; blockCol < numOfRowColBlocks; blockCol++) {
                // defines indices in square
                int flatSquareIndex = 0;
                // reset module
                this.sudokuIndepSquares[squareIndex] = new SudokuIndepModule();
                // run in current
                for(int row = blockRow*3; row < blockRow*3+3; row++) {
                    for (int col = blockCol*3; col < blockCol*3+3; col++) {
                        // check if the field is not empty
                        if(!this.textFields[row][col].getText().isEmpty()) {
                            // get value into integer
                            int value = Integer.parseInt(this.textFields[row][col].getText());
                            // try to update the value from board in the module
                            if (!this.sudokuIndepSquares[squareIndex].updateValue(flatSquareIndex, value)) {
                                // cannot update this field, take an action
                                if (isClearField) {
                                    this.textFields[row][col].setText("");
                                }
                                else this.textFields[row][col].setForeground(Color.RED);
                                // pop-up message
                                showMessage(value,row,col,"block");
                                return false;
                            }
                            // change text color to blue (valid)
                            else this.textFields[row][col].setForeground(Color.BLUE);
                        }
                        // is empty, remove from module
                        else this.sudokuIndepSquares[squareIndex].removeValue(flatSquareIndex);
                        flatSquareIndex++;
                    }
                }
                squareIndex++;
            }
        }
        return true;
    }

    /**
     * Pop-up error message for user
     * @param value which value was invalid
     * @param row in which row
     * @param col in which column
     * @param cause what module caused it (row, column, square (block))
     */
    private void showMessage(int value, int row, int col, String cause) {
        JOptionPane.showMessageDialog(frame,
                "Cannot set [row: " + (row+1) + ", column: " + (col+1) + "] field\n" +
                "Value " + value + " is already exists in this " + cause,
                "Validation error",
                JOptionPane.ERROR_MESSAGE);
    }
}
