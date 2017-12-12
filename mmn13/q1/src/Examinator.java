import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The quiz
 */
public class Examinator {
    // never checked with another ammount but seems to be OK with that :)
    public static int defaultNumOfAnswers = 4;

    // path to exam's data file
    private String filepath;
    // questions counter
    private int questCount;
    // number of answers
    private int numOfAnswers;
    // lines in file
    private int linesCount;
    // used for file reading
    private ArrayList<String> buffer = new ArrayList<>();
    // holds list of questions
    private ArrayList<Question> questions = new ArrayList<>();
    // holds current grade, -1 is the unknown state
    private double grade;

    /**
     * Default C'tor redirects to next one
     */
    public Examinator() {
        this("..\\input\\exam.txt", defaultNumOfAnswers);
    }

    /**
     * C'tor
     * @param filepath path to data file
     * @param numOfAnswers number of answers to read after each question
     */
    public Examinator(String filepath, int numOfAnswers) {
        this.filepath = filepath;
        this.questCount = 0;
        this.linesCount = 0;
        this.numOfAnswers = numOfAnswers;
        this.grade = -1.0; // unknown
    }

    /**
     * Builds the exam
     * @return true if everything is done
     */
    public boolean buildExam() {
        try {
            Scanner input = new Scanner(new File(this.filepath));

            // reads the file into buffer and counts lines
            while (input.hasNextLine()) {
                this.buffer.add(new String(input.nextLine()));
                this.linesCount++;
            }

            input.close();

            // calculates number of questions
            this.questCount = this.linesCount / (this.numOfAnswers + 1);

            // checks if given data is valid by validating lines number
            if (this.linesCount % (this.numOfAnswers +1) != 0) {
                System.out.println("*ERROR: Wrong input file");
                return false;
            }

            // fill questions and answers lists
            for (int i = 0; i <= this.linesCount-(this.numOfAnswers+1); i+=this.numOfAnswers+1) {
                String buff[] = new String[this.numOfAnswers+1];
                for (int j = 0; j < this.numOfAnswers+1; j++) {
                    buff[j] = new String(this.buffer.get(i + j));
                }
                this.questions.add(new Question(i+1, buff));
            }

            // randomly shuffle answers
            this.shuffleAnswers();

            return true;
        }
        catch (FileNotFoundException ex) {
            // specific exception handling is not needed
            System.out.println("Cannot find " + System.getProperty("user.dir") + "\\" + this.filepath);
            return false;
        }
    }

    /**
     * Shuffling answers
     */
    private void shuffleAnswers() {
        for (Question q : this.questions) {
            q.shuffleAnswers();
        }
    }

    /**
     * @return number of questions in exam
     */
    public int getQuestCount() {
        return this.questCount;
    }

    /**
     * @return number of answers in each question
     */
    public int getNumOfAnswers() {
        return this.numOfAnswers;
    }

    /**
     * Returns question string by index
     * @param index of question in list
     * @return string of question
     */
    public String getQuestion(int index) {
        if (index >= 0 && index < this.questions.size())
            return this.questions.get(index).getContent();
        return null;
    }

    /**
     * Returs array of answer for question by index
     * @param index of question in list
     * @return array of answers
     */
    public Answer[] getAnswers(int index) {
        if (index >= 0 && index < this.questions.size())
            return this.questions.get(index).getAnswers();
        return null;
    }

    /**
     * Returns answer status by index
     * @param index of question
     * @return answer status
     */
    public Question.answerStatus getStatus(int index) {
        if (index >= 0 && index < this.questions.size())
            return this.questions.get(index).getStatus();
        return Question.answerStatus.unknown;
    }

    /**
     * Calculates the result of an exam
     * @param answers given de facto
     * @return the grade in double
     */
    public double calculateResult(int[] answers) {
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
