package quizzes;

public abstract class Question {

    private int id;
    private int nextId = 1;

    private String inquiry;
    private String[] choices;
    private String[] correctAnswer;

    // unique ID number

    public Question() {
        id = nextId;
        nextId++;
    }

    public Question(String inquiry, String[] choices, String[] correctAnswer) {
        this();
        this.inquiry = inquiry;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    // GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public String getInquiry() {
        return inquiry;
    }

    public String[] getChoices() {
        return choices;
    }

    public String[] getCorrectAnswer() {
        return correctAnswer;
    }


    // METHODS



}
