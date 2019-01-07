package game.ipca.trabalhopratico819;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Options extends AppCompatActivity {

    Button imageButtonPlay;
    Button imageButtonOpt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        imageButtonPlay = findViewById(R.id.buttonPlay);
        imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Options.this, GameActivity.class);
                startActivity(intent);
            }
        });

        imageButtonOpt = findViewById(R.id.buttonOptions);
        imageButtonOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Options.this, GameActivity.class);
                startActivity(intent);
            }
        });

    }
}
