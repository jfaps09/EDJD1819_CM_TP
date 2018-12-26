package game.ipca.trabalhopratico819;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player extends Sprite {

    boolean boosting = false;
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    public void setX(int position) {
        x = position;
        if(x > maxX) x = maxX;
        if(x < minX) x = minX;
    }


    public Player (Context context, Bitmap bitmap, int width, int height){
        super(context,bitmap,width,height);

        maxX = width - bitmap.getWidth();
        minX = 0;

        y = height - bitmap.getHeight();
        speed = 1;
    }

    public void update(int playerSpeed){
        super.update(playerSpeed);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,x,y,paint);
    }
}
