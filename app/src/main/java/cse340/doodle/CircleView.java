package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import cse340.doodle.ColorUtils;
import cse340.doodle.DimHelp;

// Documentation used
// View: https://developer.android.com/reference/android/view/View
// Canvas: https://developer.android.com/reference/android/graphics/Canvas
// Paint: https://developer.android.com/reference/android/graphics/Paint
public class CircleView extends DrawView {

    /**
     * Constructor of the CircleView
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param parentX Center x position in parent coordinates (in dp)
     * @param parentY Center y position in parent coordinates (in dp)
     * @param radius The radius of the circle (in dp)
     * @param brush The Paint object used to style the circle
     */
    public CircleView(Context context, float parentX, float parentY, float radius, Paint brush) {
        super(context, brush);
        setContentDescription(ColorUtils.GetColorName(brush.getColor()) + " circle with radius  " + radius);

        /*
         * TODO: Write the constructor for your CircleView class.
         * You should use the provided fields to define the bounding box for the
         * circle in parent coordinates, and then call super.initFromParentCoordsPX().
         * Note that CircleView is instantiated using dp but initFromParentCoordsPX expects px
         * to properly set up the view
         */
        float halfStroke = getThickness() / 2;
        initFromParentCoordsPX(DimHelp.DP2PX(parentX - radius, context) - halfStroke,
                DimHelp.DP2PX(parentY - radius, context) - halfStroke,
                DimHelp.DP2PX(radius + radius, context) + getThickness(),
                DimHelp.DP2PX(radius + radius, context) + getThickness());
    }

    /**
     * Draw the ColorPicker on the Canvas
     * @param canvas the canvas that is drawn upon
     */
    protected void onDraw(Canvas canvas) {
        /*
         * TODO: draw your circle onto the canvas!
         * If you've set things up properly in the constructor,
         * you can calculate the center and width of the circle
         * from this View's bounding box (i.e. using getWidth()
         * and/or getHeight(). Make sure to use your parent object's
         * paint object and to account for line thickness.
         *
         * Remember: onDraw should use px
         */
        float radius = (getWidth() - getThickness()) / 2;
        float halfStroke = getThickness() / 2;
        canvas.drawCircle(radius + halfStroke, radius + halfStroke, radius, getBrush());
    }
}
