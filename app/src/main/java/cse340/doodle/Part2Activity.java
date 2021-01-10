package cse340.doodle;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class Part2Activity extends AbstractMainActivity {

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
     * call addLine, addCircle, addImage, and addText to doodle on the it.
     *
     * @param doodleView    Canvas on which to doodle.
     */
    public void doodle(FrameLayout doodleView) {
        // TODO: Implement your own doodle code below!

        // a generic paint brush
        Paint brush = new Paint();
        brush.setStyle(Paint.Style.FILL);

        // rays
        float widthPX = DimHelp.DP2PX(PHONE_DIMS.x, this);
        float radius = (float) (PHONE_DIMS.x / 3.0);
        float quarterOffset = (float) (PHONE_DIMS.x / 4.0);
        float xRayOffset = 60;
        float yRayOffset = -100;
        float rayThickness = 200;
        int lightYellow = Color.rgb(255,255,180);
        int yellowOrange = Color.rgb(255,230,117);
        int darkYellow = Color.rgb(255,255,160);

        // right rays
        brush.setStrokeWidth(widthPX / 2);
        brush.setColor(lightYellow);
        LineView lineView = new LineView(this, 3 * quarterOffset, 0,
                3 * quarterOffset, PHONE_DIMS.y, brush);
        doodleView.addView(lineView);

        brush.setStrokeWidth(rayThickness);
        brush.setColor(darkYellow);
        lineView = new LineView(this, PHONE_DIMS.x / 2 + xRayOffset, yRayOffset,
                PHONE_DIMS.x + xRayOffset, PHONE_DIMS.y + yRayOffset, brush);
        doodleView.addView(lineView);

        brush.setColor(lightYellow);
        lineView = new LineView(this, PHONE_DIMS.x / 2 + xRayOffset, yRayOffset - rayThickness / 2,
                PHONE_DIMS.x + xRayOffset, PHONE_DIMS.y + (yRayOffset - 2 * rayThickness), brush);
        doodleView.addView(lineView);

        // left rays
        xRayOffset *= -1;

        brush.setStrokeWidth(widthPX / 2);
        brush.setColor(darkYellow);
        lineView = new LineView(this, quarterOffset, 0, quarterOffset, PHONE_DIMS.y, brush);
        doodleView.addView(lineView);

        brush.setStrokeWidth(rayThickness);
        brush.setColor(lightYellow);
        lineView = new LineView(this, PHONE_DIMS.x / 2 + xRayOffset, yRayOffset,
                xRayOffset, PHONE_DIMS.y + yRayOffset, brush);
        doodleView.addView(lineView);

        brush.setColor(darkYellow);
        lineView = new LineView(this,
                PHONE_DIMS.x / 2 + xRayOffset, yRayOffset - rayThickness / 2,
                xRayOffset, PHONE_DIMS.y + (yRayOffset - 2 * rayThickness), brush);
        doodleView.addView(lineView);

        // sun
        brush.setColor(yellowOrange);
        CircleView circleView = new CircleView(this, PHONE_DIMS.x / 2, 0, radius, brush);
        doodleView.addView(circleView);

        // bottom dirt
        int brown = Color.rgb(165, 104, 42);
        brush.setColor(brown);
        lineView = new LineView(this, 0, PHONE_DIMS.y - 50,
                PHONE_DIMS.x, PHONE_DIMS.y - 50, brush);
        doodleView.addView(lineView);

        // flowers

        // bottom dirt
        brush.setColor(brown);
        lineView = new LineView(this, 0, PHONE_DIMS.y - 100,
                PHONE_DIMS.x, PHONE_DIMS.y - 100, brush);
        doodleView.addView(lineView);

        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(circleView, "alpha",  .3f, 1f);
        fadeIn.setDuration(2000);
        fadeIn.start();
    }
}
