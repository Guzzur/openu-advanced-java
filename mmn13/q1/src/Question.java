import java.util.ArrayList;
import java.util.Collections;

/**
 * Question
 */
public class Question {
    private int id;
    private String content;
    private ArrayList<Answer> answers = new ArrayList<>();

    // defines answer status enum
    public static enum answerStatus {
        right,
        wrong,
        unknown;
    }

    // holds question's status
    private answerStatus status;

    /**
     * C'tor
     * @param id in the exam
     * @param buffer to parse the answers from
     */
    public Question(int id, String[] buffer) {
        this.id = id;

        this.content = new String(buffer[0]);
        this.answers.add(new Answer(true, buffer[1]));
        this.status = answerStatus.unknown;

        for(int i = 2; i < buffer.length; i++)
            this.answers.add(new Answer(false, buffer[i]));
    }

    /**
     * @return the string of the question
     */
    public String getContent() {
        return this.content;
    }

    /**
     * @return the array of answers
     */
    public Answer[] getAnswers() {
        Answer ans[] = new Answer[this.answers.size()];

        for (int i = 0; i < this.answers.size(); i++)
            ans[i] = this.answers.get(i);

        return ans;
    }

    /**
     * Overrides Object's toString()
     * @return formatted string
     */
    @Override
    public String toString() {
        String retVal = "";

        for (Answer ans : getAnswers()) {
            retVal += "\n\t" + ans.toString();
        }

        return content + retVal;
    }

    /**
     * @return status of question
     */
    public answerStatus getStatus() {
        return this.status;
    }

    /**
     * @param status of question
     */
    public void setStatus(answerStatus status) {
        this.status = status;
    }

    /**
     * Shuffles the question's answers
     */
    public void shuffleAnswers() {
        Collections.shuffle(this.answers);
    }
}
