package com.zackoji.mysudoku;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by Zackoji on 31/5/2558.
 */
public class PuzzleView extends View{
    private final PuzzleActivity game;

    private float width;
    private float height;
    private int selX;
    private int selY;
    private final Rect selRect = new Rect();

    public PuzzleView(Context context){
        super(context);
        this.game = (PuzzleActivity) context;
        setFocusable(true);//?
        setFocusableInTouchMode(true);//?
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        width = w/9f;
        height = h/9f;
        getReact(selX, selY, selRect);
    }

    private void getReact(int x, int y, Rect rect) {
        rect.set((int) (x * width), (int) (y * height), (int) (x * width + width), (int) (y * height + height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Draw Background
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.puzzle_background));
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        //Draw Grid
            //Set Color
        Paint dark = new Paint();
        dark.setColor(getResources().getColor(R.color.puzzle_dark));
        Paint highlight = new Paint();
        highlight.setColor(getResources().getColor(R.color.puzzle_highlight));
        Paint light = new Paint();
        light.setColor(getResources().getColor(R.color.puzzle_light));
            //Draw Sub Grid
        for(int i = 0; i < 9; i++){
            canvas.drawLine(0, i * height, getWidth(), i * height, light);// ----
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, highlight);
            canvas.drawLine(i * width, 0 , i * width, getHeight(), light);
            canvas.drawLine(i * width + 1, 0 , i * width + 1, getHeight(), highlight);
        }
            //Draw Primary Grid
        for(int i = 0; i < 9; i++){
            if(i % 3 != 0) continue;
                canvas.drawLine(0, i * height, getWidth(), i * height, dark);
                canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, dark);
                canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
                canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), dark);
        }

            //Draw Number
        Paint fg = new Paint(Paint.ANTI_ALIAS_FLAG);
        fg.setColor(getResources().getColor(R.color.puzzle_foreground));
        fg.setStyle(Paint.Style.FILL);
        fg.setTextSize(height * 0.75f);
        fg.setTextScaleX(width / height);
        fg.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fm = fg.getFontMetrics();
        float x = width / 2;
        float y = height / 2 - (fm.ascent + fm.descent) / 2;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                canvas.drawText(this.game.getTileString(i, j), i * width + x, j * height + y, fg);
            }
        }

        Paint selected = new Paint();
        selected.setColor(getResources().getColor(R.color.puzzle_selected));
        canvas.drawRect(selRect, selected);
    }

    public boolean onKeyDown() {
        //Implement
        return true;
    }






}

