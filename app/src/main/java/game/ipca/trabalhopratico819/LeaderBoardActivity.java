package game.ipca.trabalhopratico819;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {

    Button buttonBack;
    MediaPlayer lbMusic;
    int newScore = -1;
    String nome;

    List<User> userList = new ArrayList<>();

    TextView textView1, textView2, textView3, textView4, textView5,
             textView6, textView7, textView8, textView9, textView10;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        lbMusic = MediaPlayer.create(this, R.raw.win_song);
        lbMusic.start();
        lbMusic.setLooping(true);

        textView1 = findViewById(R.id.player1Text);
        textView2 = findViewById(R.id.player2Text);
        textView3 = findViewById(R.id.player3Text);
        textView4 = findViewById(R.id.player4Text);
        textView5 = findViewById(R.id.player5Text);
        textView6 = findViewById(R.id.player6Text);
        textView7 = findViewById(R.id.player7Text);
        textView8 = findViewById(R.id.player8Text);
        textView9 = findViewById(R.id.player9Text);
        textView10 = findViewById(R.id.player10Text);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("score").child(currentUser.getUid());

        if(getIntent().hasExtra("score"))
            newScore = getIntent().getIntExtra("score", 0);

        if(getIntent().hasExtra("nome"))
            nome = getIntent().getStringExtra("nome");
        else
            nome = "N/A";

        if(newScore > 0) {
            User user = new User(nome, newScore);
            myRef.setValue(user);

        }

        myRef = database.getReference("score");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    User newUser = new User(d);
                    userList.add(newUser);
                }

                if (userList.size() > 1) {
                    Collections.sort(userList, new Comparator<User>() {
                        @Override
                        public int compare(User u1, User u2) {
                            return Integer.compare(u2.score, u1.score);
                        }
                    });
                }

                if (userList.size() > 0) textView1.setText(userList.get(0).nome);
                if (userList.size() > 1) textView2.setText(userList.get(1).nome);
                if (userList.size() > 2) textView3.setText(userList.get(2).nome);
                if (userList.size() > 3) textView4.setText(userList.get(3).nome);
                if (userList.size() > 4) textView5.setText(userList.get(4).nome);
                if (userList.size() > 5) textView6.setText(userList.get(5).nome);
                if (userList.size() > 6) textView7.setText(userList.get(6).nome);
                if (userList.size() > 7) textView8.setText(userList.get(7).nome);
                if (userList.size() > 8) textView9.setText(userList.get(8).nome);
                if (userList.size() > 9) textView10.setText(userList.get(9).nome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });

        buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LeaderBoardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN: {
                if (userList.size() > 0) textView1.setText(String.valueOf(userList.get(0).score));
                if (userList.size() > 1) textView2.setText(String.valueOf(userList.get(1).score));
                if (userList.size() > 2) textView3.setText(String.valueOf(userList.get(2).score));
                if (userList.size() > 3) textView4.setText(String.valueOf(userList.get(3).score));
                if (userList.size() > 4) textView5.setText(String.valueOf(userList.get(4).score));
                if (userList.size() > 5) textView6.setText(String.valueOf(userList.get(5).score));
                if (userList.size() > 6) textView7.setText(String.valueOf(userList.get(6).score));
                if (userList.size() > 7) textView8.setText(String.valueOf(userList.get(7).score));
                if (userList.size() > 8) textView9.setText(String.valueOf(userList.get(8).score));
                if (userList.size() > 9) textView10.setText(String.valueOf(userList.get(9).score));
            }
            break;
            case MotionEvent.ACTION_UP: {
                if (userList.size() > 0) textView1.setText(userList.get(0).nome);
                if (userList.size() > 1) textView2.setText(userList.get(1).nome);
                if (userList.size() > 2) textView3.setText(userList.get(2).nome);
                if (userList.size() > 3) textView4.setText(userList.get(3).nome);
                if (userList.size() > 4) textView5.setText(userList.get(4).nome);
                if (userList.size() > 5) textView6.setText(userList.get(5).nome);
                if (userList.size() > 6) textView7.setText(userList.get(6).nome);
                if (userList.size() > 7) textView8.setText(userList.get(7).nome);
                if (userList.size() > 8) textView9.setText(userList.get(8).nome);
                if (userList.size() > 9) textView10.setText(userList.get(9).nome);
            }
            break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        lbMusic.stop();
    }
}
