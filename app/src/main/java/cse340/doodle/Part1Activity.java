package cse340.doodle;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.Log;
import android.widget.FrameLayout;

public class Part1Activity extends AbstractMainActivity {

    /**
     * Callback that is called when the activity is first created.
     * @param savedInstanceState contains the activity's previously saved state
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * This is the function that does the actual doodling! It gets a doodleView and can then
     * add LineView, TextViews, and CircleViews to it.
     *
     * @param doodleView Canvas on which to doodle.
     */
    @Override
    public void doodle(FrameLayout doodleView) {
        // Adds all images as a heart collage.
        addAllImagesFromData(doodleView, "data.csv");

        // a gold paint brush
        Paint gold = new Paint();
        gold.setColor(Color.rgb(145,123,76));
        gold.setStyle(Paint.Style.STROKE);

        // a purple paint brush
        Paint purple = new Paint();
        purple.setColor(Color.rgb(51,0,111));
        purple.setStyle(Paint.Style.STROKE);

        // a generic paint brush
        Paint brush = new Paint();
        brush.setStyle(Paint.Style.STROKE);

        // To allow this to run on multiple screen sizes, we define all coordinates
        // based on screen size

        float center = PHONE_DIMS.x / 2;
        float bottom_offset_line = PHONE_DIMS.y - PHONE_DIMS.y/4;
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(gold.getColor());
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);

        float font_size = 50; // font size in display independent pixels (dp)
        float cross = 50;

        // Adds "I",  "UW" and "CSE340" text.
        TextView textView = new TextView(this, cross,  font_size, (int) font_size, "I", textPaint);
        doodleView.addView(textView);

        float small_offset_UW = 25;

        TextView uw= new TextView(this, small_offset_UW, bottom_offset_line, (int) font_size, "UW", textPaint);
        doodleView.addView(uw);

        float cse_font = 40; // font size in display independent pixels
        textPaint.setTextSize(cse_font);
        float cse_width = textPaint.measureText("CSE340");
        float cse_offset_x = center - cse_width/2;
        float cse_offset_y = PHONE_DIMS.y/3;

        textView = new TextView(this, cse_offset_x, cse_offset_y, (int) cse_font, "CSE340", textPaint);
        doodleView.addView(textView);

        purple.setStrokeWidth(7);
        // Adds a line under "UW".
        LineView lineView = new LineView(this, 0, bottom_offset_line,
                PHONE_DIMS.x, bottom_offset_line, purple);
        doodleView.addView(lineView);
        Log.i("line", "doodle line position: x " + 0 + ", y " + bottom_offset_line);

        brush.setColor(Color.GREEN);
        brush.setStrokeWidth(20);

        // Adds lines in the top corner, crossing each brush
        lineView = new LineView(this,  0,0, cross, cross, brush);
        doodleView.addView(lineView);


        brush.setColor(Color.RED);
        brush.setStrokeWidth(15);
        lineView = new LineView(this, cross,0,0, cross, brush);
        doodleView.addView(lineView);

        float radius_large = 50;
        float radius_medium = 35;
        float radius_small = 20;
        float radius_tiny = 1;

        // Add four concentric circles
        brush.setColor(Color.DKGRAY);
        brush.setStrokeWidth(10);
        Log.i("Part1Activity", "Large Circle");
        CircleView circleView = new CircleView(this, center, bottom_offset_line, radius_large, brush);

        brush.setColor(Color.GRAY);
        doodleView.addView(circleView);
        circleView = new CircleView(this, center, bottom_offset_line, radius_medium, brush);
        doodleView.addView(circleView);

        brush.setColor(Color.LTGRAY);
        circleView = new CircleView(this, center, bottom_offset_line, radius_small, brush);
        doodleView.addView(circleView);

        circleView = new CircleView(this, center, bottom_offset_line, radius_tiny, brush);
        doodleView.addView(circleView);


        // TODO: Do your animation with the UW text view here! It's stored in the "uw" variable.
        float right = DimHelp.DP2PX(PHONE_DIMS.x - PHONE_DIMS.x/4, this);

    }
}
