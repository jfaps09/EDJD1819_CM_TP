package game.ipca.trabalhopratico819;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public abstract class Sprite {

    int x;
    int y;
    int speed = 0;
    protected int maxY;
    protected int minY;
    protected int maxX;
    protected int minX;

    Bitmap bitmap;

    Rect detectCollision;

    Paint paint = new Paint();

    public Sprite (Context context, Bitmap bitmap, int width, int height){
        this.bitmap = bitmap;
        if (bitmap!=null){
            maxY = height - bitmap.getHeight();
            minY = 0;
            maxX = width - bitmap.getWidth();
            minX = 0;
            detectCollision = new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
        }else{
            maxY = height;
            minY = 0;
            maxX = width;
            minX = 0;
        }
    }

    public void update(int playerSpeed){
        if (bitmap!=null) {
            detectCollision.left = x;
            detectCollision.top = y;
            detectCollision.right = x + bitmap.getWidth();
            detectCollision.bottom = y + bitmap.getHeight();
        }
    }

    public abstract void draw(Canvas canvas);


}
