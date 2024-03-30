package com.example.a1200430_salwa_fayyad;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;


//Salwa Fayyad 1200430
public class ResultActivity extends AppCompatActivity {

    private TextView resultscoreTextView;
    private  TextView resultTextView;
    private Button button_reset;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultscoreTextView = findViewById(R.id.score_result_id);
        resultTextView = findViewById(R.id.result_id);
        button_reset = findViewById(R.id.button_reset_id);
        image = findViewById(R.id.imageView_id);

        // Set background color of the reset button
        button_reset.setBackgroundColor(Color.parseColor("#FF0000")); // Red color

        // Set text color of the reset button
        button_reset.setTextColor(Color.parseColor("#FFFFFF")); // White color

        // Get the score and result from the intent

        int score = getIntent().getIntExtra("score", 0);
        String result = getIntent().getStringExtra("result");

        if (score > 3) {
            image.setImageResource(R.drawable.winning_image);
        } else {
            image.setImageResource(R.drawable.losing_image);
        }

        // Display the score and result
        resultscoreTextView.setText(String.valueOf(score)+"/5");
        resultTextView.setText(result);

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Reset the game and switch back to MainActivity
                Intent resetIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(resetIntent);
                finish();
            }
        });
    }

}
