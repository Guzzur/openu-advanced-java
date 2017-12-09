import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Examinator {
    public static int defaultNumOfAnswers = 4;

    private String filepath;
    private int questCount;
    private int numOfAnswers;
    private int linesCount;
    private ArrayList<String> buffer = new ArrayList<>();
    private ArrayList<Question> questions = new ArrayList<>();
    private double grade;

    public Examinator() {
        this("..\\input\\exam.txt", defaultNumOfAnswers);
    }

    public Examinator(String filepath, int numOfAnswers) {
        this.filepath = filepath;
        this.questCount = 0;
        this.linesCount = 0;
        this.numOfAnswers = numOfAnswers;
        this.grade = -1.0; // unknown
    }

    public boolean buildExam() {
        try {
            Scanner input = new Scanner(new File(this.filepath));

            while (input.hasNextLine()) {
                this.buffer.add(new String(input.nextLine()));
                this.linesCount++;
            }

            input.close();

            this.questCount = this.linesCount / (this.numOfAnswers + 1);

            if (this.linesCount % (this.numOfAnswers +1) != 0) {
                System.out.println("*ERROR: Wrong input file");
                // TODO exit
                return false;
            }

            for (int i = 0; i <= this.linesCount-(this.numOfAnswers+1); i+=this.numOfAnswers+1) {
                String buff[] = new String[this.numOfAnswers+1];
                for (int j = 0; j < this.numOfAnswers+1; j++) {
                    buff[j] = new String(this.buffer.get(i + j));
                }
                this.questions.add(new Question(i+1, buff));
            }

            this.shuffleAnswers();

            return true;
        }
        catch (FileNotFoundException ex) {
            // TODO: handle exception
            System.out.println("Cannot find " + System.getProperty("user.dir") + "\\" + this.filepath);
            return false;
        }
    }

    private void shuffleAnswers() {
        for (Question q : this.questions) {
            q.shuffleAnswers();
        }
    }

    public int getQuestCount() {
        return this.questCount;
    }

    public int getNumOfAnswers() {
        return this.numOfAnswers;
    }

    public String getQuestion(int index) {
        if (index >= 0 && index < this.questions.size())
            return this.questions.get(index).getContent();
        return null;
    }

    public Answer[] getAnswers(int index) {
        if (index >= 0 && index < this.questions.size())
            return this.questions.get(index).getAnswers();
        return null;
    }

    public Question.answerStatus getStatus(int index) {
        if (index >= 0 && index < this.questions.size())
            return this.questions.get(index).getStatus();
        return Question.answerStatus.unknown;
    }

    public double calculateResult(int[] answers, JLabel questions[][]) {
        if (answers.length <= this.questions.size()) {
            int rightAnsCount = 0;
            for (int i = 0; i < this.questCount; i++) {
                if (answers[i] > 0 && answers[i] <= this.numOfAnswers && this.questions.get(i).getAnswers()[answers[i]-1].getIsRight()) {
                    rightAnsCount++;
                    this.questions.get(i).setStatus(Question.answerStatus.right);
                }
                else this.questions.get(i).setStatus(Question.answerStatus.wrong);
            }
            return ((double)rightAnsCount / (double)this.questCount) * 100;
        }
        return -1;
    }
}
