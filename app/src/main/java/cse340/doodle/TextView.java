package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import cse340.doodle.DimHelp;

public class TextView extends DrawView {

    /** The text to show */
    private String mText;

    public String getText() {
        return mText;
    }

    /**
     * Constructor of the TextView
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param x The left side of the text (in dp)
     * @param y the y position of the text baseline (in dp)
     * @param fontSize the font size to use for drawing the text in dp
     * @param text the text to draw
     * @param mBrush The Paint object used to style the text
     */
    public TextView(Context context, float x, float y, int fontSize, String text, Paint mBrush) {
        super(context, mBrush);
        setContentDescription(text);

        /*
         * TODO Find the bounding box for your string
         * 1) Setup your TextPaint object with the proper text size
         *    (note that to properly handle text size you will need to convert
         *     from the dp size passed in to px)
         * 1) You can use the provided x for top left
         * 2) Assume the y provided is the baseline. Calculate the bounding box from there using fontMetrics
         * 3) Use your paint object to measureText and find out the width of the text
         * 4) Use fontMetrics to calculate the total height of the text
         * 5) Just like any other DrawView, set up the position, width and height using
         *    initFromParentCoordsPX().
         * You should provide a link to the android documentation for any class, advice
         * or method that you use to figure this out for completeness
         */
    }


    /**
     * Draw the text on the Canvas
     * @param canvas the canvas that is drawn upon
     */
    protected void onDraw(Canvas canvas) {
        // TODO draw your text
        //  1) calculate the position of the baseline in y
        //  2) call drawText with the correct x and y (baseline)
    }
}
