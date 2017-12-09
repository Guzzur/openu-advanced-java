public class Main {
    public static void main(String[] args) {
        Examinator exam = new Examinator("input\\exam.txt", Examinator.defaultNumOfAnswers);
        exam.buildExam();

        GraphicUI gui = new GraphicUI("Exam", exam);
        gui.init();
        gui.present();
    }
}
