package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import cse340.doodle.ColorUtils;
import cse340.doodle.DimHelp;
import cse340.doodle.Quadrant;

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

        /*
         * TODO calculate the width and height of the line's bounding box
         */

        /*
         * TODO calculate what quadrant the line starts and ends in
         */

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
    }
}

