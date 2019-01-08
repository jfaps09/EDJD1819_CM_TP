package game.ipca.trabalhopratico819;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.hardware.camera2.params.BlackLevelPattern;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread =  null;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private int width, height;

    MediaPlayer meowScream, meow;

    private Player player;
    private Boom boom;

    private Paint scorePaint = new Paint();
    private int score;
    private Bitmap life;
    private Bitmap background, starUI;

    private List<Sprite> sprites = new ArrayList<>();

    GameActivity activity;

    public GameView(Context context, int width, int height, GameActivity activity, MediaPlayer meowScream, MediaPlayer meow) {
        super(context);
        this.activity=activity;
        surfaceHolder = getHolder();
        player = new Player(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.paw), width,  height);
        sprites.add(player);
        for (int i = 0 ; i<100;i++){
            sprites.add(new Star(context, null, width,height));
        }

        this.meowScream = meowScream;
        this.meow = meow;

        score = 0;
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(50);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        background = BitmapFactory.decodeResource(getResources(), R.drawable.background_game_simple_small);
        starUI = BitmapFactory.decodeResource(getResources(), R.drawable.score_star);

        life = BitmapFactory.decodeResource(getResources(), R.drawable.heart1);

        sprites.add(new Fish(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.blue),width,height, "blue"));
        sprites.add(new Fish(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.golden),width,height, "golden"));
        sprites.add(new Fish(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.green),width,height, "green"));

        for (int i = 0 ; i<2;i++){
            sprites.add(new Enemy(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.rock),width,height));
        }
        boom  = new Boom(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.ouch),width,height);
        sprites.add(boom);
    }

    @Override
    public void run() {
        while (playing){
            update();
            draw();
            control();
        }
    }

    private void update() {
        if(player.hp == 0) {
            activity.gameOver(score);
        }

        for(Sprite s: sprites){
            s.update(player.speed);

            if (s instanceof Enemy)
             if (Rect.intersects(player.detectCollision, s.detectCollision)){
                boom.x = s.x;
                boom.y = s.y;
                s.x=-200;
                meowScream.start();
                player.hp -= 1;
             }

            if (s instanceof Fish)
                if (Rect.intersects(player.detectCollision, s.detectCollision)){
                    if(((Fish) s).color.equals("golden")) meow.start();
                    s.x = -200;
                    score += ((Fish) s).fishPoints;
                }
        }
    }

    private void draw() {
        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            canvas.drawBitmap(background, 0, 0, null);
            canvas.drawBitmap(starUI, 10, 25, null);

            canvas.drawText("" + score, 110, 85, scorePaint);

            for (int i = 0 ; i<player.hp; i++)
                canvas.drawBitmap(life, 682+80*i, 25, null);

            for(Sprite s: sprites)
                s.draw(canvas);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void resume(){
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause(){
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE: {
                if (Math.abs((player.x + player.bitmap.getWidth() / 2) - (int) event.getX()) <= 200)
                    player.setX((int) event.getX() - player.bitmap.getWidth() / 2);
            }
                break;
        }
        return true;
    }
}
