package game.ipca.trabalhopratico819;

import com.google.firebase.database.DataSnapshot;

public class User {

    String nome;
    int score;

    public User(String nome, int score) {
        this.nome = nome;
        this.score = score;
    }

    public User(DataSnapshot dataSnapshot) {
        this.nome = (String)dataSnapshot.child("nome").getValue();
        this.score  =  dataSnapshot.child("score").getValue(Integer.class);
    }
}
