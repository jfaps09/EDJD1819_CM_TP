package game.ipca.trabalhopratico819;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Button buttonPlay;
    Button buttonOpt;
    Button buttonLb;

    MediaPlayer menuMusic;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuMusic = MediaPlayer.create(this, R.raw.menu_music);
        menuMusic.start();
        menuMusic.setLooping(true);

        mAuth = FirebaseAuth.getInstance();

        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddItemDialog(MainActivity.this);
            }
        });

        buttonLb = findViewById(R.id.buttonLeaderboard);
        buttonLb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LeaderBoardActivity.class);
                startActivity(intent);
            }
        });

        buttonOpt = findViewById(R.id.buttonOptions);
        buttonOpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAuth.signInAnonymously();
    }

    @Override
    protected void onPause() {
        super.onPause();
        menuMusic.stop();
    }

    private void showAddItemDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Type in your username")
                .setView(taskEditText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nome = String.valueOf(taskEditText.getText());
                        Intent intent=new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("nome", nome);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}
