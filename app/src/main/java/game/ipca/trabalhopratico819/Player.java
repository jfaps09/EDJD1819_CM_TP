package game.ipca.trabalhopratico819;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Player extends Sprite {
    int hp;

    public void setX(int position) {
        x = position;
        if(x > maxX) x = maxX;
        if(x < minX) x = minX;
    }


    public Player (Context context, Bitmap bitmap, int width, int height){
        super(context,bitmap,width,height);

        hp = 3;

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
