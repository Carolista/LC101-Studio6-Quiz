package quizzes;

import java.util.*;

public class QuizRunner {

    public static void main(String[] args) {

        List<Question> questions = new ArrayList<>();

        // 1 - CREATE QUESTIONS AND PUT IN LIST OF ALL QUESTIONS

        questions.add(
                new MultipleChoice("Which one of these actors did not appear in the film 'Gosford Park'?",
                        new String[]{"Michael Gambon", "Clive Owen", "Kiera Knightley", "Dame Maggie Smith"},
                        new String[]{"Kiera Knightley"})
        );
        questions.add(
                new MultipleChoice("What is the answer to the meaning of life, the universe, and everything?",
                        new String[]{"51", "42", "1", "1001"},
                        new String[]{"42"}));
        questions.add(
                new MultipleChoice("From where did Arthur's knights originate in the film 'King Arthur'?",
                        new String[]{"Sarmatia", "Roma", "Brittania"},
                        new String[]{"Sarmatia"})
        );
        questions.add(
                new MultipleChoice("In the 'Lord of the Rings' trilogy, what was Gandalf's name before he became the White Wizard?",
                        new String[]{"Gandalf the Grey", "Gandalf of Old", "Gandalf the Wise"},
                        new String[]{"Gandalf the Grey"})
        );
        questions.add(
                new Checkbox("Which of these are Marvel characters?",
                        new String[]{"Black Widow", "QuickSilver", "The Flash", "Green Arrow"},
                        new String[]{"Black Widow", "QuickSilver"})
        );
        questions.add(
                new Checkbox("Which of these magical beasts are from the wizarding world of Harry Potter?",
                        new String[]{"Acromantula", "Basilisk", "Varactyl", "Thestral", "Fathier"},
                        new String[]{"Acromantula", "Basilisk", "Thestral"})
        );
        questions.add(
                new Checkbox("In which years were the first three Star Wars films released?",
                        new String[]{"1976", "1977", "1979", "1980", "1983", "1984"},
                        new String[]{"1977", "1980", "1983"})
        );
        questions.add(
                new Checkbox("Which of the following characters from the 2003 film 'The Italian Job' had their nicknames from the beginning of the film?",
                        new String[]{"Handsome Rob", "Left Ear", "Napster", "Skinny Pete", "Wrench"},
                        new String[]{"Handsome Rob", "Left Ear", "Skinny Pete", "Wrench"})
        );
        questions.add(
                new TrueFalse("True or False: The historical fiction piece 'Girl with a Pearl Earring' centers around a maid named Griet and her relationship with Dutch master painter Rembrandt.",
                        new String[]{"True", "False"},
                        new String[]{"False"})
        );

        questions.add(
                new TrueFalse("True or False: In the epic battle that takes place in Tolkien's 'The Two Towers,' the fellowship is aided by immortal elves from Rivendell.",
                        new String[]{"True", "False"},
                        new String[]{"False"})
        );

        questions.add(
                new TrueFalse("True or False: In the film 'Star Trek: Into Darkness,' the true identity of the villain played by Benedict Cumberbatch is revealed to be the superhuman Khan.",
                        new String[]{"True", "False"},
                        new String[]{"True"})
        );
        questions.add(
                new TrueFalse("True or False: In the film 'Gosford Park', Sir William McCordle died because of poison in his whiskey.",
                        new String[]{"True", "False"},
                        new String[]{"True"})
        );

        // 2 - BUILD QUIZ

        // Put questions in random-ish order
        Collections.shuffle(questions);

        // Create Quiz object with shuffled questions and base score of zero
        Quiz quiz = new Quiz(questions, 0);

        // 3 - RUN QUIZ
        Scanner input = new Scanner(System.in);

        int q;
        String ask;
        String[] choiceList;
        List<String> correct;
        int numCorrect; // how many possible correct answers per question
        double increment = 0; // partial points if multiple correct answers
        double questionScore = 0; // full or partial points per question based on response(s)
        int response = 0;
        double totalAsked = 0;

        // Start quiz
        System.out.println("\n\n******************** POP QUIZ! *********************\n\n(TODAY'S TOPIC: Useless Movie Trivia)\n\n");

        // Present questions to user one at a time

        for (q = 0; q < questions.size(); q++) {

            ask = questions.get(q).getInquiry();
            choiceList = questions.get(q).getChoices();
            correct = Arrays.asList(questions.get(q).getCorrectAnswer()); // this only checks first element
            numCorrect = questions.get(q).getCorrectAnswer().length;
            increment = 1.0 / numCorrect;

            // Assign numbers to each choice (method will randomize the order)
            Map<Integer, String> choiceMap = quiz.buildChoices(choiceList);

            // Ask user the question
            System.out.println("QUESTION " + (q+1));
            System.out.println(ask);

            // Display each choice on a new line
            for (Map.Entry<Integer, String> choice : choiceMap.entrySet()) {
                System.out.println(choice.getKey() + " - " + choice.getValue());
            }
            System.out.println(); // extra line

            // Instructions for "checkbox" questions only
            if (numCorrect > 1) {
                System.out.println("* NOTE: This question has more than one correct answer. Enter each number on a separate line.\n");
            }

            // Accept response(s) from user
            for (int n = 0; n < numCorrect; n++) {

                // Validate input
                while (response == 0 || response > choiceList.length) {
                    String userInput = input.nextLine();
                    if (userInput.equals("")) {
                        System.out.println("Please enter one of the numbers listed above.");
                    } else if (userInput.matches("[0-9]?")) {
                        response = Integer.parseInt(userInput);
                        if (response == 0 || response > choiceList.length) {
                            System.out.println("Your answer must be one of the numbers listed above.");
                        }
                    } else {
                        System.out.println("Oops! You must enter a number. Try again.");
                    }
                }

                if (correct.contains(choiceMap.get(response))) {
                    questionScore += increment;
                }

                response = 0; // reset for next loop

            }

            // update quiz score
            quiz.setScore(quiz.getScore() + questionScore);

            System.out.println(); // extra line

            // update number of questions answered
            totalAsked = q + 1.0;

            // return result of user's input for this question
            if (questionScore == 1) {
                System.out.println("Great job! You get a full point added to your score.");
            } else if (questionScore == 0) {
                System.out.println("Sorry, no points for you!");
                System.out.println("Here is the correct answer: " + correct);
            } else {
                System.out.println("Not quite right, but you can have partial credit!");
                System.out.println("Here is the correct answer: " + correct);
            }
            System.out.println("\n* * * Your score is now " + (Math.round(10.0 * quiz.getScore()) / 10.0) + " out of " + totalAsked + " * * *\n\n");

            // reset score for next question
            questionScore = 0;

        }

        input.close();


        // 4 - REPORT
        // At end, report with final grade

        double grade = Math.round(10.0 * 100 * quiz.getScore() / totalAsked) / 10.0;

        System.out.println("************************************************************\n" +
                "        Congrats! You made it to the end of the quiz. \n" +
                "                Your final grade is " + grade + "%.\n" +
                "************************************************************\n\n");

        System.out.println("This quiz brought to you by the Quizzical Quandary Committee. Thanks for playing!\n");



    }
}
