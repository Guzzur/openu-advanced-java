public class Answer {
    private boolean isRight;
    private String content;

    /**
     * C'tor
     * @param isRight status of this answer
     * @param content the string
     */
    public Answer(boolean isRight, String content) {
        this.isRight = isRight;
        this.content = new String(content);
    }

    /**
     * @return true if it's right
     */
    public boolean getIsRight() {
        return this.isRight;
    }

    /**
     * Overrides Object's toString()
     * @return formatted string
     */
    @Override
    public String toString() {
        return this.content;
    }
}
