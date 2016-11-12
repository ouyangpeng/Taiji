package oyp.com.taiji;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class TaiJi extends View {
    private Paint whitePaint;   //白色画笔
    private Paint blackPaing;   //黑色画笔

    public TaiJi(Context context) {
        this(context, null);
    }

    public TaiJi(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaiJi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaints();
    }

    //初始化画笔函数
    private void initPaints() {
        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);

        blackPaing = new Paint(whitePaint);
        blackPaing.setColor(Color.BLACK);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int width = canvas.getWidth();          //画布宽度
        int height = canvas.getHeight();        //画布高度

        canvas.translate(width/2, height/2);    // 移动坐标原点到画布中心

        canvas.drawColor(Color.GRAY);           //绘制背景色
        canvas.rotate(degrees);                 //旋转画布

        //绘制两个半圆
        int radius = Math.min(width, height) / 2 - 100;        		//太极半径
        RectF rect = new RectF(-radius, -radius, radius, radius);   //绘制区域
        canvas.drawArc(rect, 90, 180, true, blackPaing);            //绘制黑色半圆
        canvas.drawArc(rect, -90, 180, true, whitePaint);           //绘制白色半圆

        //绘制两个小圆
        int smallRadius = radius / 2;	                            //小圆半径为大圆的一半
        canvas.drawCircle(0, -smallRadius, smallRadius, blackPaing);
        canvas.drawCircle(0, smallRadius, smallRadius, whitePaint);

        //绘制鱼眼（两个更小的圆）
        canvas.drawCircle(0, -smallRadius, smallRadius / 4, whitePaint);
        canvas.drawCircle(0, smallRadius, smallRadius / 4, blackPaing);

    }

    private float degrees = 0;                  //旋转角度
    public void setRotate(float degrees) {
        this.degrees = degrees;
        invalidate();                           //重绘界面
    }
}