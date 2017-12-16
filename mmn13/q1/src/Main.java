public class Main {
    public static void main(String[] args) {
        // creates quiz
        Examinator exam = new Examinator("input\\exam.txt", Examinator.defaultNumOfAnswers);
        // builds data into it
        if(exam.buildExam()) {
            // creates GUI
            GraphicUI gui = new GraphicUI("Exam", exam);
            // inits it's members
            gui.init();
            // presents it to the user
            gui.present();
        }
        else System.out.println("*ERROR: Cannot build the exam from input file");
    }
}
