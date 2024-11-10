import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class OnlineExamSystem {

    // User class for login and registration
    static class User {
        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getPassword() {
            return password;
        }
    }

    // UserManager class to handle users
    static class UserManager {
        private HashMap<String, User> users = new HashMap<>();

        public boolean register(String username, String password) {
            if (!users.containsKey(username)) {
                users.put(username, new User(username, password));
                return true;
            }
            return false;
        }

        public boolean login(String username, String password) {
            User user = users.get(username);
            return user != null && user.getPassword().equals(password);
        }
    }

    // Question class to store questions and answers
    static class Question {
        private String questionText;
        private String[] options;
        private int correctAnswer;

        public Question(String questionText, String[] options, int correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String[] getOptions() {
            return options;
        }

        public boolean isCorrect(int choice) {
            return choice == correctAnswer;
        }
    }

    // QuestionBank class to store multiple questions
    static class QuestionBank {
        private ArrayList<Question> questions = new ArrayList<>();

        public void addQuestion(Question question) {
            questions.add(question);
        }

        public Question getQuestion(int index) {
            return questions.get(index);
        }

        public int getTotalQuestions() {
            return questions.size();
        }
    }

    // Exam class to conduct the exam
    static class Exam {
        private QuestionBank questionBank;
        private int score = 0;

        public Exam(QuestionBank questionBank) {
            this.questionBank = questionBank;
        }

        public void start() {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < questionBank.getTotalQuestions(); i++) {
                Question question = questionBank.getQuestion(i);
                System.out.println("Q" + (i + 1) + ": " + question.getQuestionText());

                for (int j = 0; j < question.getOptions().length; j++) {
                    System.out.println((j + 1) + ". " + question.getOptions()[j]);
                }

                System.out.print("Your answer: ");
                int choice = scanner.nextInt() - 1;
                if (question.isCorrect(choice)) {
                    score++;
                }
            }
            scanner.close();
        }

        public int getScore() {
            return score;
        }
    }

    // Result class to display results
    static class Result {
        private int totalQuestions;
        private int score;

        public Result(int totalQuestions, int score) {
            this.totalQuestions = totalQuestions;
            this.score = score;
        }

        public void display() {
            System.out.println("Your Score: " + score + "/" + totalQuestions);
            System.out.println("Percentage: " + (score * 100 / totalQuestions) + "%");
        }
    }

    // Main function to run the online exam system
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.register("ajay2529", "ajay6314");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userManager.login(username, password)) {
            System.out.println("Login successful! Starting the exam...\n");

            QuestionBank questionBank = new QuestionBank();
            questionBank.addQuestion(new Question("What is 2 + 2?", new String[]{"3", "4", "5"}, 1));
            questionBank.addQuestion(new Question("Java is a _ language.", new String[]{"Object-oriented", "Procedural", "Functional"}, 0));
            questionBank.addQuestion(new Question("Which company created Java?", new String[]{"Microsoft", "Apple", "Sun Microsystems"}, 2));
            questionBank.addQuestion(new Question("What is encapsulation?", new String[]{"Data hiding", "Wrapping up of data", "Both A & B"}, 2));
            questionBank.addQuestion(new Question("Which keyword is used to inherit a class in Java?", new String[]{"implement", "extend", "inherit"}, 1));

            Exam exam = new Exam(questionBank);
            exam.start();

            Result result = new Result(questionBank.getTotalQuestions(), exam.getScore());
            result.display();
        } else {
            System.out.println("Login failed! Please check your credentials.");
        }
        scanner.close();
    }
}
