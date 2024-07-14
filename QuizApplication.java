package org.example.Task4;


import java.util.*;

public class QuizApplication {
    private List<Question> questions;

    public QuizApplication(List<Question> questions) {
        this.questions = questions;
    }

    public void startQuiz() {
        int score = 0;
        for (Question question : questions) {
            score += askQuestion(question);
        }
        displayResult(score);
    }

    private int askQuestion(Question question) {
        System.out.println(question.getQuestionText());
        for (int i = 0; i < question.getOptions().length; i++) {
            System.out.println((i + 1) + ". " + question.getOptions()[i]);
        }

        int answerIndex = 0;
        if (question.getTimeLimit() > 0) {
            answerIndex = getUserInputWithTimer(question.getTimeLimit());
        } else {
            answerIndex = getUserInput();
        }

        if (answerIndex == question.getCorrectAnswerIndex()) {
            System.out.println("Correct!");
            return 1;
        } else {
            System.out.println("Incorrect. The correct answer is: " + question.getOptions()[question.getCorrectAnswerIndex()]);
            return 0;
        }
    }

    private int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt() - 1;
    }

    private int getUserInputWithTimer(int timeLimit) {
        Scanner scanner = new Scanner(System.in);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's Up!");
                timer.cancel();
            }
        };
        timer.schedule(task, timeLimit * 1000);

        try {
            return scanner.nextInt() - 1;
        } catch (Exception e) {
            System.out.println("Invalid input. Time's Up!");
        } finally {
            timer.cancel();
        }
        return -1;
    }

    private void displayResult(int score) {
        System.out.println("Your score is: " + score + "/" + questions.size());
    }

    public static void main(String[] args) {

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new String[]{"London", "Paris", "Berlin"}, 1, 10));
        questions.add(new Question("What is the largest planet in our solar system?", new String[]{"Jupiter", "Mars", "Earth"}, 0, 15));
        questions.add(new Question("What is the chemical symbol for water?", new String[]{"H2O", "CO2", "N2"}, 0, 20));

        QuizApplication quiz = new QuizApplication(questions);
        quiz.startQuiz();
    }
}



