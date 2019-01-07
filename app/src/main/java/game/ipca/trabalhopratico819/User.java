package game.ipca.trabalhopratico819;

import com.google.firebase.database.DataSnapshot;

public class User {
    String id;

    public User(String nome, int score) {
        this.nome = nome;
        this.score = score;
    }

    public User(DataSnapshot dataSnapshot) {
        this.id = dataSnapshot.getKey();
        this.nome = dataSnapshot.child("nome").toString();
        this.score  = Integer.parseInt(dataSnapshot.child("score").toString());
    }

    String nome;
    int score;
}
