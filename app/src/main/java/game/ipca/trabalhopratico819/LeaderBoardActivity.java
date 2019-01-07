package game.ipca.trabalhopratico819;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

    int newScore = -1;

    List<User> userList = new ArrayList<>();

    TextView textView1, textView2, textView3, textView4, textView5,
             textView6, textView7, textView8, textView9, textView10;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

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

        if(getIntent().hasExtra("score")) {
            newScore = getIntent().getIntExtra("score", 0);
        }

        if(newScore > 0) {
            User user = new User("fg", newScore);
            myRef.setValue(user);

        }

        myRef = database.getReference("score");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d:dataSnapshot.getChildren()) {
                    User newUser = new User(d);
                    userList.add(newUser);
                }

                /*
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    userList.sort(new Comparator<User>() {
                        @Override
                        public int compare(User u1, User u2) {
                            if(u1.score > u2.score)
                                return 0;
                            else
                                return 1;
                        }
                    });
                }
*/
                textView1.setText(userList.get(0).nome);
                /*
                textView2.setText(userList.get(1).nome);
                textView3.setText(userList.get(2).nome);
                textView4.setText(userList.get(3).nome);
                textView5.setText(userList.get(4).nome);
                textView6.setText(userList.get(5).nome);
                textView7.setText(userList.get(6).nome);
                textView8.setText(userList.get(7).nome);
                textView9.setText(userList.get(8).nome);
                textView10.setText(userList.get(9).nome);*/
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
}
