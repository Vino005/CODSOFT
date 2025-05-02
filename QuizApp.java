import java.util.*;
public class QuizApp{
    static class Question{
        String question;
        String[] options;
        String correctAnswer;
        
        public Question(String question, String[] options, String correctAnswer){
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
        public String getQuestion(){
            return question;
        }
        public String[] getOptions(){
            return options;
        }
        public String getCorrectAnswer(){
            return correctAnswer;
        }
    }
    public static void main(String[] args){
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "Rome"}, "Paris"));
        questions.add(new Question("Wthat is 2+2?", new String[]{"3", "4","5","6"}, "4"));
        
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        int totalQuestions = questions.size();
        
        int timeLimit = 10;
        for(int i=0;i<totalQuestions;i++){
            Question currentQuestion = questions.get(i);
            
            long startTime = System.currentTimeMillis();
            System.out.println("Question " + (i+1) + ": " + currentQuestion.getQuestion());
            
            String[] options = currentQuestion.getOptions();
            for(int j=0; j< options.length; j++){
                System.out.println((j+1) + ". " + options[j]);
            }
            
            String userAnswer = getAnswerWithTimer(scanner, timeLimit, startTime);
            
            if(userAnswer != null && userAnswer.equals(currentQuestion.getCorrectAnswer())){
                score++;
            }
        }
        showResult(totalQuestions, score);
    }
    public static String getAnswerWithTimer(Scanner scanner, int timeLimit, long startTime){
        System.out.println("You have " + timeLimit + " seconds to answer...");
        String userAnswer = null;
        while(System.currentTimeMillis() - startTime < timeLimit * 1000){
            if(scanner.hasNextLine()){
                userAnswer = scanner.nextLine();
                break;
            }
        }
        if(userAnswer == null){
            System.out.println("\nTime's up! No answer provides.");
            return null;
        }
        return userAnswer;
    }
    public static void showResult(int totalQuestions, int score){
        System.out.println("\nYour final score is: " + score + "/" + totalQuestions);
        System.out.println(score == totalQuestions ? "Congratulations, you got all answers correct!" : "Better luck next time.");
    }
}