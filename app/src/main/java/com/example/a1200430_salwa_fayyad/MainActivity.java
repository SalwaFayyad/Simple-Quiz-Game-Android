package com.example.a1200430_salwa_fayyad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Salwa Fayyad 1200430
public class MainActivity extends AppCompatActivity {
    private TextView countdownTextView;
    private CountDownTimer countDownTimer;
    private TextView scoreTextView;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private List<Question> questions = new ArrayList<>(Arrays.asList(
            new Question("Q1. Who painted the Mona Lisa?", "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Michelangelo", "Leonardo da Vinci"),
            new Question("Q2. Who wrote the play Romeo and Juliet", "Ben Jonson", "Thomas Kyd", "Christopher Marlowe", "William Shakespeare", "William Shakespeare"),
            new Question("Q3. What is the tallest mountain in the world", "Mount Kilimanjaro", "Mount Elbrus", "Mount Everest", "Denali", "Mount Everest"),
            new Question("Q4. Who wrote the Harry Potter book series?", "J.R.R. Tolkien", "J.K. Rowling", "Stephen King", "George R.R. Martin", "J.K. Rowling"),
            new Question("Q5. What is the chemical symbol for gold?", "Au", "Ag", "Cu", "Fe", "Au")
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countdownTextView = findViewById(R.id.second_left_id);
        scoreTextView = findViewById(R.id.current_score_id); // Initialize scoreTextView here

        start_game();
    }

    private void start_game() {
        score = 0; // Reset the score
        scoreTextView.setText("0");
        currentQuestionIndex = 0; // Reset the question index
        displayQuestion(currentQuestionIndex);
        startCountdownTimer();
    }

    private void displayQuestion(int questionIndex) {
        Question currentQuestion = questions.get(questionIndex);
        TextView questionTextView = findViewById(R.id.question_text_id);
        questionTextView.setText(currentQuestion.getQuestion());

        Button button1 = findViewById(R.id.button1_id);
        button1.setText(currentQuestion.getAnswer1());

        Button button2 = findViewById(R.id.button2_id);
        button2.setText(currentQuestion.getAnswer2());

        Button button3 = findViewById(R.id.button3_id);
        button3.setText(currentQuestion.getAnswer3());

        Button button4 = findViewById(R.id.button4_id);
        button4.setText(currentQuestion.getAnswer4());

        // Set onClickListeners for the answer buttons to check the answer
        button1.setOnClickListener(view -> checkAnswer(button1.getText().toString(), currentQuestion.getCorrectAnswer()));
        button2.setOnClickListener(view -> checkAnswer(button2.getText().toString(), currentQuestion.getCorrectAnswer()));
        button3.setOnClickListener(view -> checkAnswer(button3.getText().toString(), currentQuestion.getCorrectAnswer()));
        button4.setOnClickListener(view -> checkAnswer(button4.getText().toString(), currentQuestion.getCorrectAnswer()));
    }

    private void checkAnswer(String selectedAnswer, String correctAnswer) {
        // Check if the selected answer is not empty and is correct
        if (!selectedAnswer.isEmpty() && selectedAnswer.equals(correctAnswer)) {
            int score = Integer.parseInt(scoreTextView.getText().toString());
            score++;
            scoreTextView.setText(String.valueOf(score));
        }
        updateQuestionIndex();

    }
    private void updateQuestionIndex() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            displayQuestion(currentQuestionIndex);
            startCountdownTimer(); // Start the countdown timer for the next question
        } else if (currentQuestionIndex==questions.size()) { // If reach the question number 5
            gotoResultActivity();
        }
    }

    private void startCountdownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Cancel the previous timer
        }

        // To start from 10 to 0
        countDownTimer = new CountDownTimer(11000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                countdownTextView.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                countdownTextView.setText("0");
                // Automatically move to the next question
                checkAnswer("", "");
            }
        }.start();
    }

    private void gotoResultActivity() {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        score = Integer.parseInt(scoreTextView.getText().toString());
        String result;
        if (score > 3) {
            result = "You Win";
        } else {
            result = "You Lose";
        }
        // Send the data (score and result) to result activity
        intent.putExtra("score", score);
        intent.putExtra("result", result);
        startActivity(intent);
        finish();
    }

}
