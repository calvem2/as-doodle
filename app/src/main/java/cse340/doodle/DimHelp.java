package cse340.doodle;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * This class was written by Nathan Esquanazi https://gist.github.com/nesquena
 * to help students with display independent pixels and modified/commented
 * by the CSE340 staff
 */

public class DimHelp {

    /**
     * Returns the width of this device's display
     * @param context The activity context
     * @return display width in pixels
     */
    public static int getDisplayWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * Returns the height of this device's display
     * @param context The activity context
     * @return Display height in pixels
     */
    public static int getDisplayHeight(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * Returns the width of this device's display in DP
     * @param context The activity context
     * @return display width in dp
     */
    public static float getDisplayWidthDP(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return PX2DP(displayMetrics.widthPixels, context);
    }

    /**
     * Returns the height of this device's display in dp
     * @param context The activity context
     * @return Display height in dp
     */
    public static float getDisplayHeightDP(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return PX2DP(displayMetrics.heightPixels, context);
    }

    /**
     * Converts from display independent pixels to pixels
     * @param dp Density independent pixels
     * @param context The activity context
     * @return Regular Pixels
     */
    public static float DP2PX(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    /**
     * Converts from display independent pixels to pixels
     * @param px Regular Pixels
     * @param context The activity context
     * @return  Density independent pixels
     */
    public static float PX2DP(float px, Context context){
        return px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}