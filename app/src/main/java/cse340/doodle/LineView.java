package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import cse340.doodle.ColorUtils;
import cse340.doodle.DimHelp;
import cse340.doodle.Quadrant;

// Documentation used
// View: https://developer.android.com/reference/android/view/View
// Canvas: https://developer.android.com/reference/android/graphics/Canvas
// Paint: https://developer.android.com/reference/android/graphics/Paint

public class LineView extends DrawView {

    /** The location of the start and end of the line in this box */
    private Quadrant mStart;
    private Quadrant mEnd;

    /**
     * Constructor of the Line
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param parentStartX The x position of the start of the line (in parent coordinates in dp)
     * @param parentStartY The x position of the start of the line (in parent coordinates in dp)
     * @param parentEndX The x position of the start of the line (in parent coordinates in dp)
     * @param parentEndY The x position of the start of the line (in parent coordinates in dp)
     * @param brush The Paint object used to style the line
     */
    public LineView(Context context, float parentStartX, float parentStartY, float parentEndX, float parentEndY, Paint brush) {
        super(context, brush);

        initFromParentLinePX(DimHelp.DP2PX(parentStartX, context),
                DimHelp.DP2PX(parentStartY, context),
                DimHelp.DP2PX(parentEndX, context),
                DimHelp.DP2PX(parentEndY, context));
        setContentDescription(ColorUtils.GetColorName(brush.getColor()) + " line from " + mStart + " to " + mEnd);
    }

    /* Initializes the line from parent coordinates. This sets the starting and ending
     * quadrant of the line, and assigns a correct width and height.
     * @param parentStartX The starting X position of the line in parent coordinates (in pixels)
     * @param parentStartY The starting Y position of the line in parent coordinates (in pixels)
     * @param parentEndX The ending X position of the line in parent coordinates (in pixels)
     * @param parentEndY The ending Y position of the line in parent coordinates (in pixels)
     */
    public void initFromParentLinePX(float parentStartX, float parentStartY, float parentEndX, float parentEndY) {
        /*
         * TODO calculate the left, right, top and bottom of the line's bounding box in parent coordinates
         * You should be able to do this with max and min.
         */
        float left, top;
        left = parentStartX == parentEndX ? parentStartX - getThickness() / 2 : Math.min(parentStartX, parentEndX);
        top = parentStartY == parentEndY ? parentStartY - getThickness() / 2 : Math.min(parentStartY, parentEndY);

        /*
         * TODO calculate the width and height of the line's bounding box
         */
        float height = parentStartY == parentEndY ? getThickness() : Math.abs(parentStartY - parentEndY);
        float width = parentStartX == parentEndX ? getThickness() : Math.abs(parentStartX - parentEndX);

        /*
         * TODO calculate what quadrant the line starts and ends in
         */
        // vertical line
        if (parentStartX == parentEndX) {
            mStart = mEnd = parentStartY < parentEndY ? Quadrant.VERTICALTOPBOTTOM : Quadrant.VERTICALBOTTOMTOP;
        // horizontal line
        } else if (parentStartY == parentEndY) {
            mStart = mEnd = parentStartX < parentEndX ? Quadrant.HORIZONTALLEFTRIGHT : Quadrant.HORIZONTALRIGHTLEFT;
        } else if (parentStartX < parentEndX) {
            if (parentStartY < parentEndY) {
                mStart = Quadrant.TOPLEFT;
                mEnd = Quadrant.BOTTOMRIGHT;
            } else {
                mStart = Quadrant.BOTTOMLEFT;
                mEnd = Quadrant.TOPRIGHT;
            }
        } else {
            if (parentStartY < parentEndY) {
                mStart = Quadrant.TOPRIGHT;
                mEnd = Quadrant.BOTTOMLEFT;
            } else {
                mStart = Quadrant.BOTTOMRIGHT;
                mEnd = Quadrant.TOPLEFT;
            }
        }

        /*
         * TODO horizontal and vertical lines require special logic in drawing.
         * We flag them by making mStart and mEnd equal and using
         * Quadrant.VERTICALTOPBOTTOM, Quadrant.VERTICALBOTTOMTOP, Quadrant.HORIZONTALLEFTRIGHT and
         * Quadrant.HORIZONTALRIGHTLEFT to capture their direction
         * We also need to make sure that the line has a width equivalent to
         * its thickness so that thick lines show properly
         */

        /*
         * TODO initialize bounding box from parent coordinates
         * use initializeFromParent
         */
        initFromParentCoordsPX(left, top, width, height);

    }

    /**
     * Draw a Line on the Canvas
     * @param canvas the canvas that is drawn upon
     */
    protected void onDraw(Canvas canvas) {
        /*
         * TODO draw the line (in child coordinates)
         * To do so you will need to decide where to start and end it
         * based on this view's bounding box and the quadrant stored
         * in mStart and mEnd
         *
         * Be sure to adjust properly for thickness in horizontal and vertical lines
         * You do *NOT* need to worry about thickness for angled lines, they will have
         * pointed ends due to clipping.
         *
         * Remember: onDraw should use px
         */
        float width, height, wCenter, hCenter;
        width = this.getWidth();
        height = this.getHeight();
        wCenter = width / 2;    // center of box width
        hCenter = height / 2;   // center of height width
        if (mStart == Quadrant.VERTICALTOPBOTTOM) {
            canvas.drawLine(wCenter, 0, wCenter, height, getBrush());
        } else if (mStart == Quadrant.VERTICALBOTTOMTOP) {
            canvas.drawLine(wCenter, height, wCenter, 0, getBrush());
        } else if (mStart == Quadrant.HORIZONTALLEFTRIGHT) {
            canvas.drawLine(0, hCenter, width, hCenter, getBrush());
        } else if (mStart == Quadrant.HORIZONTALRIGHTLEFT) {
            canvas.drawLine(width, hCenter, 0, hCenter, getBrush());
        } else if (mStart == Quadrant.TOPLEFT) {
            canvas.drawLine(0, 0, width, height, getBrush());
        } else if (mStart == Quadrant.TOPRIGHT) {
            canvas.drawLine(width, 0, 0, height, getBrush());
        } else if (mStart == Quadrant.BOTTOMLEFT) {
            canvas.drawLine(0, height, width, 0, getBrush());
        } else {
            canvas.drawLine(width, height, 0, 0, getBrush());
        }
    }
}

