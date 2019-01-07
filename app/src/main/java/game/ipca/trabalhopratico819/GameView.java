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

    private Player player;
    private Boom boom;

    private Paint scorePaint = new Paint();
    private int score;
    private Bitmap life[] =  new Bitmap[2];

    private List<Sprite> sprites = new ArrayList<>();

    public GameView(Context context, int width, int height) {
        super(context);
        surfaceHolder = getHolder();
        player = new Player(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.paw), width,  height);
        sprites.add(player);
        for (int i = 0 ; i<100;i++){
            sprites.add(new Star(context, null, width,height));
        }

        score = 0;
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart1);

        sprites.add(new Fish(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.blue),width,height, "blue"));
        sprites.add(new Fish(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.golden),width,height, "golden"));
        sprites.add(new Fish(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.green),width,height, "green"));

        for (int i = 0 ; i<2;i++){
            sprites.add(new Enemy(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.rock),width,height));
        }
        boom  = new Boom(context,BitmapFactory.decodeResource(context.getResources(), R.drawable.boom),width,height);
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
            pause();
            Intent intent = new Intent(getContext(), LeaderBoardActivity.class);
            intent.putExtra("score", score);
            getContext().startActivity(intent);
        }

        for(Sprite s: sprites){
            s.update(player.speed);

            if (s instanceof Enemy)
             if (Rect.intersects(player.detectCollision, s.detectCollision)){
                boom.x = s.x;
                boom.y = s.y;
                s.x=-200;

                player.hp -= 1;
             }

            if (s instanceof Fish)
                if (Rect.intersects(player.detectCollision, s.detectCollision)){
                    s.x = -200;
                    score += ((Fish) s).fishPoints;
                }
        }
    }

    private void draw() {
        Paint paint= new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);

        if(surfaceHolder.getSurface().isValid()){
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            canvas.drawText("Score : " + score, 20, 60, scorePaint);

            for (int i = 0 ; i<player.hp; i++)
                canvas.drawBitmap(life[0], 600+60*i, 30, null);

            for(Sprite s: sprites){
                s.draw(canvas);
                if(s.detectCollision != null)
                    canvas.drawRect(s.detectCollision, paint);
            }
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
            case MotionEvent.ACTION_MOVE:
                player.setX((int)event.getX() - player.bitmap.getWidth() / 2);
                break;
        }
        return true;
    }
}
