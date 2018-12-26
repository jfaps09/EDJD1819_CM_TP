package game.ipca.trabalhopratico819;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.Random;

public class Star extends Sprite{




    public Star (Context context, Bitmap bitmap, int width, int height){
        super(context,bitmap,width,height);
        Random generator = new Random();
        speed = generator.nextInt(10);
        x = generator.nextInt(maxX);;
        y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed){
        x -= playerSpeed;
        x -= speed;
        if (x<0){
            x=maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(10);
        }
    }

    private float getStartWidth(){
        Random generator = new Random();
        float w = (float)(generator.nextFloat()*8.0 + 1.0);
        return w;
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(getStartWidth());
        canvas.drawPoint(x,y,paint);
    }
}
