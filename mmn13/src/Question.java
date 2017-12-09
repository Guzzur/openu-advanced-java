import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Question {
    private int id;
    private String content;
    private ArrayList<Answer> answers = new ArrayList<>();

    public static enum answerStatus {
        right,
        wrong,
        unknown;
    }

    private answerStatus status;

    public Question(int id, String[] buffer) {
        this.id = id;

        this.content = new String(buffer[0]);
        this.answers.add(new Answer(true, buffer[1]));
        this.status = answerStatus.unknown;

        for(int i = 2; i < buffer.length; i++)
            this.answers.add(new Answer(false, buffer[i]));
    }

    public String getContent() {
        return this.content;
    }

    public Answer[] getAnswers() {
        Answer ans[] = new Answer[this.answers.size()];

        for (int i = 0; i < this.answers.size(); i++)
            ans[i] = this.answers.get(i);

        return ans;
    }

    @Override
    public String toString() {
        String retVal = "";

        for (Answer ans : getAnswers()) {
            retVal += "\n\t" + ans.toString();
        }

        return content + retVal;
    }

    public answerStatus getStatus() {
        return this.status;
    }

    public void setStatus(answerStatus status) {
        this.status = status;
    }

    public void shuffleAnswers() {
        Collections.shuffle(this.answers);
    }
}
