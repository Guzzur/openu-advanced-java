import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicUI{
    public final int even = 0;
    public final int odd = 1;
    private JFrame frame;
    private JPanel panel;
    private String frameName;
    private Examinator exam;
    private JLabel grade;
    private JRadioButton radio[][];
    private ButtonGroup buttonGroups[][];
    private Box box[];
    private JButton btnCheckAns;
    private JButton btnReplay;
    private JLabel questions[][];

    public GraphicUI(String frameName, Examinator exam) {
        this.frameName = frameName;
        this.exam = exam;
    }

    public void init() {
        this.frame = new JFrame();
        this.panel = new JPanel();

        // default operation for closing window
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // defines window size
        this.frame.setSize(1000, 650);

        this.frame.setTitle(this.frameName);

        //This will center the JFrame in the middle of the screen
        this.frame.setLocationRelativeTo(null);

        this.panel.setLayout(new GridLayout(0,2));

        this.radio = new JRadioButton[exam.getQuestCount()][exam.getNumOfAnswers()];

        this.buttonGroups = new ButtonGroup[2][exam.getQuestCount()];

        this.questions = new JLabel[2][exam.getQuestCount()];

        this.btnCheckAns = new JButton("Check answers");
        this.btnCheckAns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int[] ansArray = new int[exam.getQuestCount()];

                for (int i = 0; i < exam.getQuestCount() - 1; i += 2) {
                    for (int j = 0; j < exam.getNumOfAnswers(); j++) {
                        if (radio[i][j].isSelected())
                            ansArray[i] = j + 1;
                        if (radio[i + 1][j].isSelected())
                            ansArray[i + 1] = j + 1;
                    }
                }

                btnCheckAns.setEnabled(false);
                grade.setText("Your grade is: " + exam.calculateResult(ansArray, questions));

                for (int i = 0; i < exam.getQuestCount()-1; i+=2) {
                    questions[even][i].setText((i + 1) + ") " + exam.getQuestion(i) +
                            " [" + exam.getStatus(i).toString() + "]");
                    questions[odd][i].setText((i + 2) + ") " + exam.getQuestion(i + 1) +
                            " [" + exam.getStatus(i + 1).toString() + "]");
                }
            }
        });

        this.btnReplay = new JButton("Clean and start again");
        //this.btnCheckAns.addActionListener(this);
    }

    public void present() {
        for (int i = 0; i < exam.getQuestCount()-1; i+=2) {
            this.questions[even][i] = new JLabel((i+1) + ") " + exam.getQuestion(i) +
                    " [" + exam.getStatus(i).toString() + "]", SwingConstants.LEFT);
            this.questions[odd][i] = new JLabel((i+2) + ") " + exam.getQuestion(i+1) +
                    " [" + exam.getStatus(i+1).toString() + "]", SwingConstants.LEFT);

            this.panel.add(this.questions[even][i]);
            this.panel.add(this.questions[odd][i]);

            this.radio[i] = new JRadioButton[exam.getNumOfAnswers()];
            this.radio[i+1] = new JRadioButton[exam.getNumOfAnswers()];

            this.buttonGroups[even][i] = new ButtonGroup();
            this.buttonGroups[odd][i] = new ButtonGroup();

            this.box = new Box[2];
            this.box[even] = Box.createHorizontalBox();
            this.box[odd] = Box.createHorizontalBox();

            for (int j=0; j < exam.getNumOfAnswers(); j++) {
                this.radio[i][j] = new JRadioButton(exam.getAnswers(i)[j].toString());
                this.radio[i+1][j] = new JRadioButton(exam.getAnswers(i+1)[j].toString());

                buttonGroups[even][i].add(this.radio[i][j]);
                buttonGroups[odd][i].add(this.radio[i+1][j]);

                this.box[even].add(this.radio[i][j]);
                this.box[odd].add(this.radio[i+1][j]);
            }

            this.panel.add(this.box[even]);
            this.panel.add(this.box[odd]);
        }

        this.grade = new JLabel("Your grade is: unknown");

        this.panel.add(this.grade);
        this.panel.add(new Label());
        this.panel.add(btnCheckAns);
        this.panel.add(btnReplay);

        // add drawing panel to the window
        this.frame.add(this.panel);
        // make the window be visible
        this.frame.setVisible(true);
    }
    /*
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.btnCheckAns) {
            int[] ansArray = new int[this.exam.getQuestCount()];

            for (int i = 0; i < this.exam.getQuestCount() - 1; i += 2) {
                for (int j = 0; j < exam.getNumOfAnswers(); j++) {
                    if (this.radio[i][j].isSelected())
                        ansArray[i] = j + 1;
                    if (this.radio[i + 1][j].isSelected())
                        ansArray[i + 1] = j + 1;
                }
            }
        }

        else { }

        frame.invalidate();
        frame.validate();
        frame.repaint();
        // this.grade.setText("Your grade is: " + this.exam.calculateResult(ansArray, this.questions));
    }
    */
}
