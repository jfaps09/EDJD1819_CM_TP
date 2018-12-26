package game.ipca.trabalhopratico819;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SoundBarView extends View {
    public SoundBarView(Context context) {
        super(context);
    }

    public SoundBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SoundBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint= new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        canvas.drawRect(0,0,progress,getWidth()/4,paint);
    }

    float progress =0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                progress = event.getX();
                invalidate();
                break;
        }
        return true;
    }
}
