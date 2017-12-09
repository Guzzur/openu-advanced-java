public class Answer {
    private boolean isRight;
    private String content;

    public Answer(boolean isRight, String content) {
        this.isRight = isRight;
        this.content = new String(content);
    }

    public boolean getIsRight() {
        return this.isRight;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
