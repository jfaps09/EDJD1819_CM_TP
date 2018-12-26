package game.ipca.trabalhopratico819;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Boom extends Sprite{

    private boolean isBoom = false;

    public Boom (Context context,Bitmap bitmap, int width, int height){
        super(context,bitmap,width,height);
        x = -250;
        y = -250;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap,x,y,paint);
    }

    private int boomupdate = 0;

    public void update(int playerSpeed){
        super.update(playerSpeed);
        if (boomupdate>10) {
            x = -250;
            y = -250;
            boomupdate = 0;
        }
        boomupdate ++;

        if (isBoom){

        }
    }
}
