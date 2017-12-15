import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicUI {
    private final static int numOfModuleValues = 9;
    private final static int numOfRowColBlocks = 3;
    private final static int textFieldSize = 30;

    private String windowName;
    private JFrame frame;
    private JPanel pnlMain;
    private JPanel pnlGame;
    private JPanel pnlButtons;
    private JPanel[][] pnlsSquares;
    private JTextField[][] textFields;
    private JButton btnSetValues;
    private JButton btnClearWindow;
    private SudokuIndepModule[] sudokuIndepCols;
    private SudokuIndepModule[] sudokuIndepRows;
    private SudokuIndepModule[] sudokuIndepSquares;

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

        this.sudokuIndepCols = new SudokuIndepModule[numOfModuleValues];
        this.sudokuIndepRows = new SudokuIndepModule[numOfModuleValues];
        this.sudokuIndepSquares = new SudokuIndepModule[numOfModuleValues];
    }

    public void present() {
        for(int row = 0; row < numOfRowColBlocks; row++) {
            for(int col = 0; col < numOfRowColBlocks; col++) {
                this.pnlsSquares[row][col] = new JPanel();
                this.pnlsSquares[row][col].setLayout(new GridLayout(numOfRowColBlocks,numOfRowColBlocks));
            }
        }

        Font font = new Font("SansSerif", Font.BOLD, 30);
        for(int row = 0; row < numOfModuleValues; row++) {
            this.sudokuIndepCols[row] = new SudokuIndepModule();
            this.sudokuIndepRows[row] = new SudokuIndepModule();
            this.sudokuIndepSquares[row] = new SudokuIndepModule();
            for(int col = 0; col < numOfModuleValues; col++) {
                this.textFields[row][col] = new JTextField();

                this.textFields[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        checkValidity(true);
                    }
                });

                this.textFields[row][col].setFont(font);
                this.textFields[row][col].setForeground(Color.BLUE);
                this.textFields[row][col].setHorizontalAlignment(SwingConstants.CENTER);
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

        for(int row = 0; row < numOfRowColBlocks; row++) {
            for(int col = 0; col < numOfRowColBlocks; col++) {
                this.pnlGame.add(this.pnlsSquares[row][col]);
            }
        }

        this.btnSetValues.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(checkValidity(true)) {
                    btnSetValues.setEnabled(false);
                    for(int row = 0; row < numOfModuleValues; row++) {
                        for (int col = 0; col < numOfModuleValues; col++) {
                            if(!textFields[row][col].getText().isEmpty()) {
                                textFields[row][col].setEnabled(false);
                            }
                        }
                    }
                }
            }
        });

        this.btnClearWindow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                btnSetValues.setEnabled(true);
                for(int row = 0; row < numOfModuleValues; row++) {
                    for(int col = 0; col < numOfModuleValues; col++) {
                        textFields[row][col].setText("");
                        textFields[row][col].setEnabled(true);
                    }
                }
            }
        });

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

    private boolean checkValidity(boolean isClearField) {
        for (int row = 0; row < numOfModuleValues; row++) {
            this.sudokuIndepRows[row] = new SudokuIndepModule();
            for(int col = 0; col < numOfModuleValues; col++) {
                if(!this.textFields[row][col].getText().isEmpty()) {
                    int value = Integer.parseInt(this.textFields[row][col].getText());
                    if (!this.sudokuIndepRows[row].updateValue(col, value)) {
                        if (isClearField) {
                            this.textFields[row][col].setText("");
                        }
                        else this.textFields[row][col].setForeground(Color.RED);
                        showMessage(value,row,col,"row");
                        return false;
                    }
                    else this.textFields[row][col].setForeground(Color.BLUE);
                }
                else this.sudokuIndepRows[row].updateValue(col, 0);
            }
        }
        for (int col = 0; col < numOfModuleValues; col++) {
            this.sudokuIndepCols[col] = new SudokuIndepModule();
            for(int row = 0; row < numOfModuleValues; row++) {
                if(!this.textFields[row][col].getText().isEmpty()) {
                    int value = Integer.parseInt(this.textFields[row][col].getText());
                    if (!this.sudokuIndepCols[col].updateValue(row, value)) {
                        if (isClearField) {
                            this.textFields[row][col].setText("");
                        }
                        else this.textFields[row][col].setForeground(Color.RED);
                        showMessage(value,row,col,"column");
                        return false;
                    }
                    else this.textFields[row][col].setForeground(Color.BLUE);
                }
                else this.sudokuIndepCols[col].updateValue(row, 0);
            }
        }
        int squareIndex = 0;
        for(int blockRow = 0; blockRow < numOfRowColBlocks; blockRow++) {
            for (int blockCol = 0; blockCol < numOfRowColBlocks; blockCol++) {
                int flatSquareIndex = 0;
                this.sudokuIndepSquares[squareIndex] = new SudokuIndepModule();
                for(int row = blockRow*3; row < blockRow*3+3; row++) {
                    for (int col = blockCol*3; col < blockCol*3+3; col++) {
                        if(!this.textFields[row][col].getText().isEmpty()) {
                            int value = Integer.parseInt(this.textFields[row][col].getText());
                            if (!this.sudokuIndepSquares[squareIndex].updateValue(flatSquareIndex, value)) {
                                if (isClearField) {
                                    this.textFields[row][col].setText("");
                                }
                                else this.textFields[row][col].setForeground(Color.RED);
                                showMessage(value,row,col,"square");
                                return false;
                            }
                        }
                        else this.sudokuIndepSquares[squareIndex].updateValue(flatSquareIndex, 0);
                        flatSquareIndex++;
                    }
                }
                squareIndex++;
            }
        }
        return true;
    }

    private void showMessage(int value, int row, int col, String cause) {
        JOptionPane.showMessageDialog(frame,
                "Cannot set [row: " + (row+1) + ", column: " + (col+1) + "] field\n" +
                "Value " + value + " is already exists in this " + cause,
                "Validation error",
                JOptionPane.ERROR_MESSAGE);
    }
}
