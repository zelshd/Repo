import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswer;

    public Question(String questionText, List<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz {
    private String name;
    private List<Question> questions;

    public Quiz(String name) {
        this.name = name;
        questions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}

public class OnlineQuizApplication {
    private List<Quiz> quizzes;
    private List<Double> userScores;
    private Scanner scanner;

    public OnlineQuizApplication() {
        quizzes = new ArrayList<>();
        userScores = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        OnlineQuizApplication app = new OnlineQuizApplication();
        app.run();
    }

    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    takeQuiz();
                    break;
                case 2:
                    viewScores();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\nOnline Quiz Application");
        System.out.println("1. Take Quiz");
        System.out.println("2. View Scores");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void takeQuiz() {
        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available. Please try again later.");
            return;
        }

        System.out.println("\nAvailable Quizzes:");
        for (int i = 0; i < quizzes.size(); i++) {
            System.out.println((i + 1) + ". " + quizzes.get(i).getName());
        }

        System.out.print("Enter the quiz number you want to take: ");
        int quizNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (quizNumber < 1 || quizNumber > quizzes.size()) {
            System.out.println("Invalid quiz number. Please try again.");
            return;
        }

        Quiz quiz = quizzes.get(quizNumber - 1);
        double score = conductQuiz(quiz);
        userScores.add(score);
        System.out.println("Quiz completed. Your score: " + score);
    }

    private double conductQuiz(Quiz quiz) {
        System.out.println("\nTaking Quiz: " + quiz.getName());

        List<Question> questions = quiz.getQuestions();
        int totalQuestions = questions.size();
        int correctAnswers = 0;

        for (int i = 0; i < totalQuestions; i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestionText());

            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            System.out.print("Enter your answer (1-" + options.size() + "): ");
            int userAnswer = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (userAnswer == question.getCorrectAnswer()) {
                correctAnswers++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect!");
            }
        }

        return (double) correctAnswers / totalQuestions * 100;
    }

    private void viewScores() {
        System.out.println("\nQuiz Scores:");
        if (userScores.isEmpty()) {
            System.out.println("No scores available.");
        } else {
            for (int i = 0; i < userScores.size(); i++) {
                System.out.println("Quiz " + (i + 1) + ": " + userScores.get(i));
            }
        }
    }

    // Add quizzes and questions
    // Call this method to initialize the quiz data
    private void initializeQuizzes() {
        Quiz quiz1 = new Quiz("General Knowledge");
        quiz1.addQuestion(new Question("Which planet is known as the Red Planet?", List.of("Mars", "Venus", "Jupiter", "Mercury"), 1));
        quiz1.addQuestion(new Question("Who painted the Mona Lisa?", List.of("Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Michelangelo"), 1));
        quiz1.addQuestion(new Question("What is the largest ocean in the world?", List.of("Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"), 1));
        quizzes.add(quiz1);

        Quiz quiz2 = new Quiz("Mathematics");
        quiz2.addQuestion(new Question("What is the value of pi (π)?", List.of("3.14", "2.71", "1.62", "1.41"), 1));
        quiz2.addQuestion(new Question("What is the square root of 16?", List.of("4", "2", "8", "6"), 1));
        quizzes.add(quiz2);

        // Add more quizzes and questions as needed
    }
}