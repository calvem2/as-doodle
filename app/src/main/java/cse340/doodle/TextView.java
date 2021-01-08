package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;

import cse340.doodle.DimHelp;

// Documentation used
// View: https://developer.android.com/reference/android/view/View
// Canvas: https://developer.android.com/reference/android/graphics/Canvas
// Paint: https://developer.android.com/reference/android/graphics/Paint
// FontMetrics: https://developer.android.com/reference/android/graphics/Paint.FontMetrics

public class TextView extends DrawView {

    /** The text to show */
    private String mText;
    private float baseline;

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
        mText = text;
        float top, width, height;
        // Set font size
        mBrush.setTextSize(DimHelp.DP2PX(fontSize, context));

        // Get font metrics
        Paint.FontMetrics metrics = mBrush.getFontMetrics();

        // Find bounding box
        baseline = -metrics.top;
        top = DimHelp.DP2PX(y, context) + metrics.top;
        height = metrics.bottom - metrics.top;
        width = mBrush.measureText(mText);

        System.out.println("hiii" + metrics.top + " " + metrics.bottom + " " + DimHelp.DP2PX(y, context) + " " + width + " " + height);
        System.out.println("brush size: " + mBrush.getTextSize() + " font size: " + DimHelp.DP2PX(fontSize, context));
        initFromParentCoordsPX(DimHelp.DP2PX(x, context), top, width, height);
    }


    /**
     * Draw the text on the Canvas
     * @param canvas the canvas that is drawn upon
     */
    protected void onDraw(Canvas canvas) {
        // TODO draw your text
        //  1) calculate the position of the baseline in y
        //  2) call drawText with the correct x and y (baseline)
        canvas.drawText(getText(), 0, baseline, getBrush());
    }
}
