import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private static final Scanner scanner = new Scanner(System.in);
    private final Question[] questions;
    private int score;
    private int currentQuestionIndex;
    private String userAnswer;
    private boolean timerExpired;
    private final int timeLimit = 10; // seconds

    public Quiz() {
        questions = new Question[]{
            new Question("Which of the following is not a Java feature?", new String[]{"A. Object-oriented", "B. Use of pointers", "C. Portable", "D. Dynamic and Extensible"}, "B"),
            new Question("What is the size of int in Java?", new String[]{"A. 8-bit", "B. 16-bit", "C. 32-bit", "D. 64-bit"}, "C"),
            new Question("Which method must be implemented by all threads?", new String[]{"A. wait()", "B. start()", "C. run()", "D. stop()"}, "C"),
        };
        score = 0;
        currentQuestionIndex = 0;
    }

    public void startQuiz() {
        for (Question question : questions) {
            askQuestion(question);
        }
        showResults();
    }

    private void askQuestion(Question question) {
        System.out.println(question.getQuestion());
        for (String option : question.getOptions()) {
            System.out.println(option);
        }
        System.out.println("You have " + timeLimit + " seconds to answer.");

        startTimer();

        userAnswer = null;
        while (!timerExpired && userAnswer == null) {
            String input = scanner.nextLine().toUpperCase();
            if (input.equals("A") || input.equals("B") || input.equals("C") || input.equals("D")) {
                userAnswer = input;
            } else {
                System.out.println("Invalid input. Please enter A, B, C, or D.");
            }
        }

        if (timerExpired) {
            System.out.println("Time's up!");
        }

        if (userAnswer != null && userAnswer.equals(question.getAnswer())) {
            score++;
            question.setResult("Correct");
        } else {
            question.setResult("Incorrect");
        }

        currentQuestionIndex++;
    }

    private void startTimer() {
        timerExpired = false;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timerExpired = true;
            }
        }, timeLimit * 1000);
    }

    private void showResults() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your final score is " + score + "/" + questions.length + "\n");

        for (Question question : questions) {
            System.out.println("Question: " + question.getQuestion());
            System.out.println("Your answer: " + userAnswer + " - " + question.getResult());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.startQuiz();
    }
}

class Question {
    private final String question;
    private final String[] options;
    private final String answer;
    private String result;

    public Question(String question, String[] options, String answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
        this.result = "";
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
