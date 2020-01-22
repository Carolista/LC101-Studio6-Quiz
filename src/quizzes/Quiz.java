package quizzes;

import java.util.*;


public class Quiz {

    private List<Question> questions; // QuizRunner will create a limited set of questions from pool
    private double score = 0; // QuizRunner will update with each correct answer

    public Quiz(List<Question> questions, double score) {
        this.questions = questions;
        this.score = score;
    }

    // METHODS

    // Create helper method to shuffle choices before assigning numbers
    public String[] shuffleList(String[] choices) {
        List<String> tempList = Arrays.asList(choices);
        Collections.shuffle(tempList);
        choices = tempList.toArray(new String[tempList.size()]);
        return choices;
    }

    // Assign integer keys for each choice for user
    public Map<Integer, String> buildChoices(String[] choices) {
        Map<Integer, String> choiceMap = new HashMap<>();
        int l = choices.length;
        String[] shuffledChoices = shuffleList(choices);
        for (int i = 0; i < l; i++) {
            choiceMap.put(i+1, shuffledChoices[i]);
        }
        return choiceMap;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
