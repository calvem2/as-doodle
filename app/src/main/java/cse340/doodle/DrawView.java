package cse340.doodle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.text.TextPaint;
import android.widget.ImageView;

import cse340.doodle.DimHelp;
import cse340.doodle.ColorUtils;

// Documentation used
// View: https://developer.android.com/reference/android/view/View
@SuppressLint("AppCompatCustomView")
public class DrawView extends ImageView
{

    /**
     * A the brush that the circle is going to be painted in
     */
    private TextPaint mBrush = new TextPaint();

    /****************************************/
    /** GETTERS AND SETTERS                **/
    /****************************************/

    public TextPaint getBrush() {
        return mBrush;
    }

    public void setBrush(Paint brush) {
        this.mBrush.set(brush);
    }

    /**
     * Constructor for a basic Draw View
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param brush   A paint object for styling when drawing
     */
    public DrawView(Context context, Paint brush) {
        super(context);

        // Setup the paint brush
        mBrush.set(brush);
        setContentDescription("Drawing");
    }

    /**
     * Constructor for DrawView that contains an image
     *
     * @param context     The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param imageName   name of the image being drawn
     * @param description accessible description of image being drawn
     * @param packageName packagename to find the image among the resources
     *
     */
    public DrawView(Context context, String imageName, String description, String packageName) {
        super(context);
        int resID = getResources().getIdentifier(imageName, "drawable", packageName);
        setImageResource(resID);
        setContentDescription(description);
    }

    /**
     * Constructor for DrawView that contains an image
     *
     * @param context     The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param imageName   name of the image being drawn
     * @param description accessible description of image being drawn
     * @param packageName packagename to find the image among the resources
     * @param parentX     The parentX position of the image in parent coordinates (in display independent pixels)
     * @param parentY     The parentY position of the image in parent coordinates (in display independent pixels)
     * @param size        The width/height of the image (in display independent pixels)
     */
    public DrawView(Context context, String imageName, String description, String packageName, float parentX, float parentY, float size) {
        super(context);
        int resID = getResources().getIdentifier(imageName, "drawable", packageName);
        setImageResource(resID);

        // convert from dp to px before calling initFromParentCoordsPX(), which expects px
        initFromParentCoordsPX(DimHelp.DP2PX(parentX, context),
                DimHelp.DP2PX(parentY, context),
                DimHelp.DP2PX(size, context),
                DimHelp.DP2PX(size, context));
        setContentDescription(description);
    }

    /**
     * Gets the thickness of the stroke being used for drawing
     *
     * @return The thickness associated with the Paint brush
     */
    public float getThickness() {
        return getBrush().getStrokeWidth();
    }

    /**
     * Sets the position of the view in parent coordinates (in pixels)
     *
     * @param parentX parentX position (passed in as pixels)
     * @param parentY parentY position (passed in as pixels)
     */

    protected void initFromParentCoordsPX(float parentX, float parentY) {
        // TODO: Figure out how to implement this.
        // Your code should position this view so that all drawing can
        // start at (0,0) in child coordinates, and will be displayed
        // at (parentX, parentY) in parent coordinates (specified in pixels)
        this.setX(parentX);
        this.setY(parentY);
    }

    /**
     * Sets up the bounding box of the view in parent coordinates (in pixels)
     *
     * @param parentX parentX position (passed in as pixels)
     * @param parentY parentY position (passed in as pixels)
     * @param width   width (passed in as pixels)
     * @param height  height (passed in as pixels)
     */
    protected void initFromParentCoordsPX(float parentX, float parentY, float width, float height) {
        this.setMaxWidth((int) width);
        this.setMaxHeight((int) height);
        this.initFromParentCoordsPX(parentX, parentY);
    }

    @Override
    /**
     * This is how we make sure the width is correct
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getMaxWidth(), getMaxHeight());
    }
}
