package syf.smieciara.gra_matematyczna;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intentdeez = getIntent();
        String gameType = intentdeez.getStringExtra("gameType");
        if (gameType == null || gameType.isEmpty()){
            Intent intent_back = new Intent();
            setResult(Activity.RESULT_CANCELED, intent_back);
            finish();
            return;
        }
        Random random = new Random();
        ArrayList<TextView> textViews = new ArrayList<>();
        ArrayList<EditText> editTexts = new ArrayList<>();
        ArrayList<Integer> wyniki = new ArrayList<>();
        textViews.add(findViewById(R.id.rown_1));
        textViews.add(findViewById(R.id.drugie_rown));
        textViews.add(findViewById(R.id.trzecie_rown));
        textViews.add(findViewById(R.id.czwarte_rown));
        textViews.add(findViewById(R.id.piate_rown));
        editTexts.add(findViewById(R.id.pierwszy_wynik));
        editTexts.add(findViewById(R.id.drugi_wynik));
        editTexts.add(findViewById(R.id.trzeci_wynik));
        editTexts.add(findViewById(R.id.czwarty_wynik));
        editTexts.add(findViewById(R.id.piaty_wynik));
        for(int i=0; i<textViews.size(); i++){
            int pierwsza_liczba = random.nextInt(200);
            int druga_liczba = random.nextInt(100);
            if (gameType.equalsIgnoreCase("mnoz")){
                wyniki.add(pierwsza_liczba*druga_liczba);
                textViews.get(i).setText(String.format("%d*%d", pierwsza_liczba, druga_liczba));
            } else if (gameType.equalsIgnoreCase("dod")){
                wyniki.add(pierwsza_liczba+druga_liczba);
                textViews.get(i).setText(String.format("%d+%d", pierwsza_liczba, druga_liczba));
            } else if (gameType.equalsIgnoreCase("min")){
                wyniki.add(pierwsza_liczba-druga_liczba);
                textViews.get(i).setText(String.format("%d-%d", pierwsza_liczba, druga_liczba));
            } else if (gameType.equalsIgnoreCase("dziel")){
                wyniki.add(pierwsza_liczba/druga_liczba);
                textViews.get(i).setText(String.format("%d/%d", pierwsza_liczba, druga_liczba));
            }
        }
        Button verifyButton = findViewById(R.id.verifyButton);
        verifyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int points = 0;
                for(int i=0; i<textViews.size(); i++){
                    if(!editTexts.get(i).getText().toString().isEmpty()){
                        int wynik_user = Integer.parseInt(editTexts.get(i).getText().toString());
                        int wynik_tab = wyniki.get(i);
                        if (wynik_user == wynik_tab){
                            points = points+1;
                        }
                    }
                }
                Intent back = new Intent();
                back.putExtra("points", points);
                setResult(Activity.RESULT_OK, back);
                finish();
            }
        });
    }
}