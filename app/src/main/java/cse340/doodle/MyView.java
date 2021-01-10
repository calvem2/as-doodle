package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import cse340.doodle.ColorUtils;
import cse340.doodle.DimHelp;

// Documentation used
// Canvas/drawOval: https://developer.android.com/reference/android/graphics/Canvas#drawOval(float,%20float,%20float,%20float,%20android.graphics.Paint)
// Canvas/rotate: https://developer.android.com/reference/android/graphics/Canvas#rotate(float,%20float,%20float)
// drawing rotated shapes: https://stackoverflow.com/questions/19837489/android-how-to-rotate-rect-object/19837787
// pointF: https://developer.android.com/reference/android/graphics/PointF
// rotating points: https://math.stackexchange.com/questions/270194/how-to-find-the-vertices-angle-after-rotation#:~:text=When%20a%20point%20(x%2Cy,%2Bycos(%CE%B8).

// Convex lens shape
public class MyView extends DrawView {

    /** The angle by which to rotate the oval */
    private float angle;
    /** The width and height of the oval */
    private float oWidth;
    private float oHeight;

    /**
     * Constructor of MyView (oval)
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param x Center x position in parent coordinates (in dp)
     * @param y Center y position in parent coordinates (in dp)
     * @param width The width of the oval (in dp)
     * @param height The height of the oval (in dp)
     * @param angle The angle to rotate the oval by
     * @param brush The Paint object used to style the oval
     */
    public MyView(Context context, float x, float y, float width, float height, float angle, Paint brush) {
        super(context, brush);
        setContentDescription(ColorUtils.GetColorName(brush.getColor()) + " " + width + "x" + height +
                " oval centered at (" + x + ", " + y + ") rotated by " + angle + "degrees");

        oWidth = DimHelp.DP2PX(width, context);
        this.oHeight = DimHelp.DP2PX(height, context);
        this.angle = angle;

        // find bounding rectangle of pre-rotated oval
        float left = (float) (x - width / 2.0);
        float top = (float) (y - height / 2.0);
        float right = (float) (x + width / 2.0);
        float bottom = (float) (y + height / 2.0);

        // rotate rectangle and find smallest bounding box
        PointF topLeft = rotatePoint(x, y, angle, left, top);
        PointF bottomRight = rotatePoint(x, y, angle, right, bottom);
        PointF topRight = rotatePoint(x, y, angle, right, top);
        PointF bottomLeft = rotatePoint(x, y, angle, left, bottom);
        left = Math.min(Math.min(topLeft.x, topRight.x), Math.min(bottomLeft.x, bottomRight.x));
        right = Math.max(Math.max(topLeft.x, topRight.x), Math.max(bottomLeft.x, bottomRight.x));
        top = Math.min(Math.min(topLeft.y, topRight.y), Math.min(bottomLeft.y, bottomRight.y));
        bottom = Math.max(Math.max(topLeft.y, topRight.y), Math.max(bottomLeft.y, bottomRight.y));

        // adjust to account for thickness for ovals that are vertical or horizontal
        if (angle % 90 == 0) {
            float halfStroke = (float) (DimHelp.PX2DP(getThickness(), context) / 2.0);
            left -= halfStroke;
            top -= halfStroke;
            bottom += halfStroke;
            right += halfStroke;
        }
        initFromParentCoordsPX(DimHelp.DP2PX(left, context),
                DimHelp.DP2PX(top, context),
                DimHelp.DP2PX(right - left, context),
                DimHelp.DP2PX(bottom - top, context));
    }

    /**
     * Draw the Oval on the Canvas
     * @param canvas the canvas that is drawn upon
     */
    protected void onDraw(Canvas canvas) {
        // find rectangle bounding rectangle for pre-rotated oval
        float cx = (float) (getWidth() / 2.0);
        float cy = (float) (getHeight() / 2.0);
        float top = cy - (float) (oHeight / 2.0);
        float left = cx - (float) (oWidth / 2.0);
        float right = cx + (float) (oWidth / 2.0);
        float bottom = cy + (float) (oHeight / 2.0);

        // rotate canvas and draw using bounding rectangle
        canvas.rotate(angle, cx, cy);
        canvas.drawOval(left, top, right, bottom, getBrush());
    }

    /* Helper function for rotating a point about another point */
    private PointF rotatePoint(float cx,float cy, float angle, float x, float y) {
        float s = (float) Math.sin(Math.toRadians(angle));
        float c = (float) Math.cos(Math.toRadians(angle));

        // rotate point
        float xNew = (x - cx) * c - (y - cy) * s + cx;
        float yNew = (x - cx) * s + (y - cy) * c + cy;

        return new PointF(xNew, yNew);
    }
}
