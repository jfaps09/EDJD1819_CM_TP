package game.ipca.trabalhopratico819;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    MediaPlayer music, meowScream, meow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        music = MediaPlayer.create(this, R.raw.game_music);
        music.start();
        music.setLooping(true);

        meowScream= MediaPlayer.create(this, R.raw.scream_meow);
        meow= MediaPlayer.create(this, R.raw.meow);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        gameView = new GameView(this, size.x, size.y,this, meowScream, meow);
        setContentView(gameView);
    }

    public void gameOver(int score){
        Intent intent = new Intent(this, LeaderBoardActivity.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        music.stop();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
        music.start();
    }
}
