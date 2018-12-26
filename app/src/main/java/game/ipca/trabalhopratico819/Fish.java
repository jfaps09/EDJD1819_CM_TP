package game.ipca.trabalhopratico819;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Fish extends Sprite{

    String color;
    //int fishPoints;

    public Fish(Context context, Bitmap bitmap, int width, int height, String color){
        super(context,bitmap,width,height);
        this.bitmap = bitmap;
        this.color = color;
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = generator.nextInt(maxX);
        y = height;

    }
    public void update(int playerSpeed){
        y += playerSpeed;
        y += speed;

        if (y>maxY + bitmap.getHeight()){
            y=minY;
            Random generator = new Random();
            x = (maxX / 4) * generator.nextInt(4) + maxX / 8;
            speed = generator.nextInt(6)+10;
        }
        super.update(playerSpeed);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,x,y,paint);
    }


}
