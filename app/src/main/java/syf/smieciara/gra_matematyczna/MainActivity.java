package syf.smieciara.gra_matematyczna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button dodajButton = findViewById(R.id.dodawButton);
        Button odejmButton = findViewById(R.id.odejmButton);
        Button mnozButton = findViewById(R.id.mnozButton);
        Button dzielButton = findViewById(R.id.dzielButton);
        TextView scoreCounter = findViewById(R.id.scoreCounter);
        scoreCounter.setText(String.format("Your score: %d", score));


        ActivityResultLauncher<Intent> getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                Intent intent = o.getData();
                if (intent == null){
                    return;
                }
                int points = intent.getIntExtra("points", 0);
                Toast.makeText(getBaseContext(), String.format("You earned: %d points", points), Toast.LENGTH_SHORT).show();
                score = score + points;
                scoreCounter.setText(String.format("Your score: %d", score));
            }
        });

        dodajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Activity2.class);
                intent.putExtra("gameType", "dod");
                getResult.launch(intent);
            }
        });

        odejmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Activity2.class);
                intent.putExtra("gameType", "min");
                getResult.launch(intent);
            }
        });

        mnozButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Activity2.class);
                intent.putExtra("gameType", "mnoz");
                getResult.launch(intent);
            }
        });

        dzielButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Activity2.class);
                intent.putExtra("gameType", "dziel");
                getResult.launch(intent);
            }
        });
    }
}