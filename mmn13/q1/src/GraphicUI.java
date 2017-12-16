import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for exam / quiz
 */
public class GraphicUI{
    // even numbered questions will be placed on the
    // left side of panel and odd - on the right
    public final int even = 0;
    public final int odd = 1;

    // frame and panel for drawing all components
    private JFrame frame;
    private JPanel panel;

    // holds the window's name
    private String frameName;

    // the exam itself
    private Examinator exam;

    // array of questions where:
    // [even/odd][question number]
    private JLabel questions[][];

    // array of radio buttons where:
    // [question number][answer number]
    private JRadioButton radio[][];

    // array of radio buttons groups where:
    // [question number][answer number]
    private ButtonGroup buttonGroups[][];

    // array of boxes that holds left and right sides
    private Box box[];

    // label for showing the grade
    private JLabel grade;

    // button for exam finishing and grade calculation
    private JButton btnCheckAns;

    // button for re-taking the exam
    private JButton btnReplay;


    /**
     * C'tor
     * @param frameName will be shown as window name
     * @param exam initialized exam
     */
    public GraphicUI(String frameName, Examinator exam) {
        this.frameName = frameName;
        this.exam = exam;
    }

    /**
     * Initializes all units and draws them
     */
    public void init() {
        // init frame and panel
        this.frame = new JFrame();
        this.panel = new JPanel();

        // default operation for closing window
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // defines window size
        this.frame.setSize(1000, 650);
        // defines window name
        this.frame.setTitle(this.frameName);

        // this will center the JFrame in the middle of the screen
        this.frame.setLocationRelativeTo(null);
        // sets window layout to be in 2 columns
        this.panel.setLayout(new GridLayout(0,2));

        // inits radio buttons
        this.radio = new JRadioButton[exam.getQuestCount()][exam.getNumOfAnswers()];
        // inits group of radio buttons
        this.buttonGroups = new ButtonGroup[2][exam.getQuestCount()];
        // inits question labels
        this.questions = new JLabel[2][exam.getQuestCount()];

        // inits exam check button
        this.btnCheckAns = new JButton("Finish and check answers");
        // implements Action Listener for click
        this.btnCheckAns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                // holds all answers
                int[] ansArray = new int[exam.getQuestCount()];

                // fills answer array
                for (int i = 0; i < exam.getQuestCount() - 1; i += 2) {
                    for (int j = 0; j < exam.getNumOfAnswers(); j++) {
                        if (radio[i][j].isSelected())
                            ansArray[i] = j + 1;
                        if (radio[i + 1][j].isSelected())
                            ansArray[i + 1] = j + 1;
                    }
                }

                // disables the button, exam is done
                btnCheckAns.setEnabled(false);
                // updates the grade
                grade.setText("Your grade is: " + exam.calculateResult(ansArray));

                // marking each question if it is right or wrong
                for (int i = 0; i < exam.getQuestCount()-1; i+=2) {
                    questions[even][i].setText((i + 1) + ") " + exam.getQuestion(i) +
                            " [" + exam.getStatus(i).toString() + "]");
                    questions[odd][i].setText((i + 2) + ") " + exam.getQuestion(i + 1) +
                            " [" + exam.getStatus(i + 1).toString() + "]");
                }
            }
        });

        // inits restart button
        this.btnReplay = new JButton("Clean and start again");
        // implements Action Listener for click
        this.btnReplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i < exam.getQuestCount()-1; i+=2) {
                    // resets questions status
                    questions[even][i].setText((i + 1) + ") " + exam.getQuestion(i) +
                            " [unknown]");
                    questions[odd][i].setText((i + 2) + ") " + exam.getQuestion(i + 1) +
                            " [unknown]");
                    // clears answers
                    buttonGroups[even][i].clearSelection();
                    buttonGroups[odd][i].clearSelection();
                    }
                // enables finish button for re-taking the quiz
                btnCheckAns.setEnabled(true);
            }
        });
    }

    /**
     * Main logic and placing of GUI objects
     */
    public void present() {
        for (int i = 0; i < exam.getQuestCount()-1; i+=2) {
            // fills questions labels and statuses
            this.questions[even][i] = new JLabel((i+1) + ") " + exam.getQuestion(i) +
                    " [" + exam.getStatus(i).toString() + "]", SwingConstants.LEFT);
            this.questions[odd][i] = new JLabel((i+2) + ") " + exam.getQuestion(i+1) +
                    " [" + exam.getStatus(i+1).toString() + "]", SwingConstants.LEFT);

            // adds them to the panel
            this.panel.add(this.questions[even][i]);
            this.panel.add(this.questions[odd][i]);

            // inits radio buttons for current questions
            this.radio[i] = new JRadioButton[exam.getNumOfAnswers()];
            this.radio[i+1] = new JRadioButton[exam.getNumOfAnswers()];

            // same with groups
            this.buttonGroups[even][i] = new ButtonGroup();
            this.buttonGroups[odd][i] = new ButtonGroup();

            // creates independent boxes for answers
            this.box = new Box[2];
            this.box[even] = Box.createHorizontalBox();
            this.box[odd] = Box.createHorizontalBox();

            for (int j=0; j < exam.getNumOfAnswers(); j++) {
                // fills radio buttons for answers
                this.radio[i][j] = new JRadioButton(exam.getAnswers(i)[j].toString());
                this.radio[i+1][j] = new JRadioButton(exam.getAnswers(i+1)[j].toString());

                // unite with groups
                buttonGroups[even][i].add(this.radio[i][j]);
                buttonGroups[odd][i].add(this.radio[i+1][j]);

                // add radios to boxes
                this.box[even].add(this.radio[i][j]);
                this.box[odd].add(this.radio[i+1][j]);
            }

            // add boxes to the panel
            this.panel.add(this.box[even]);
            this.panel.add(this.box[odd]);
        }

        // init grade label
        this.grade = new JLabel("Your grade is: unknown");

        // add grade label to panel
        this.panel.add(this.grade);
        // adds empty label to right side
        this.panel.add(new Label());
        // add finish button
        this.panel.add(btnCheckAns);
        // add replay button
        this.panel.add(btnReplay);

        // add drawing panel to the window
        this.frame.add(this.panel);
        // make the window be visible
        this.frame.setVisible(true);
    }
}
