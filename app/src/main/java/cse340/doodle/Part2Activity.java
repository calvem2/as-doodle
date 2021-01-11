package cse340.doodle;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;


// Documentation used
// ObjectAnimator: https://developer.android.com/reference/android/animation/ObjectAnimator
// fade in: https://stackoverflow.com/questions/6796139/fade-in-fade-out-android-animation-in-java/41306130
// View: https://developer.android.com/reference/android/view/View
// Path: https://developer.android.com/reference/android/graphics/Path
// drawing a wave with Path https://stackoverflow.com/questions/32986371/how-to-draw-sine-wave-curve-using-sin-function-and-canvas-in-android
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

        ///////////////////////////////////   SUN    /////////////////////////////////////////////
        // rays format
        float widthPX = DimHelp.DP2PX(PHONE_DIMS.x, this);
        float radius = Math.min((float) (PHONE_DIMS.x / 3.0), (float) (PHONE_DIMS.y / 3.0));
        float quarterOffset = (float) (PHONE_DIMS.x / 4.0);
        float xRayOffset = 60;
        float yRayOffset = -100;
        float rayThickness = 200;
        int lightYellow = Color.rgb(255,255,180);
        int yellowOrange = Color.rgb(255,230,117);
        int darkYellow = Color.rgb(255,255,160);
        List<DrawView> sun = new ArrayList<>();

        // right rays
        brush.setStrokeWidth(widthPX / 2);
        brush.setColor(lightYellow);
        LineView lineView = new LineView(this, 3 * quarterOffset, 0,
                3 * quarterOffset, PHONE_DIMS.y, brush);
        doodleView.addView(lineView);
        sun.add(lineView);

        brush.setStrokeWidth(rayThickness);
        brush.setColor(darkYellow);
        lineView = new LineView(this, PHONE_DIMS.x / 2 + xRayOffset, yRayOffset,
                PHONE_DIMS.x + xRayOffset, PHONE_DIMS.y + yRayOffset, brush);
        doodleView.addView(lineView);
        sun.add(lineView);

        brush.setColor(lightYellow);
        lineView = new LineView(this, PHONE_DIMS.x / 2 + xRayOffset, yRayOffset - rayThickness / 2,
                PHONE_DIMS.x + xRayOffset, PHONE_DIMS.y + (yRayOffset - 2 * rayThickness), brush);
        doodleView.addView(lineView);
        sun.add(lineView);

        // left rays
        xRayOffset *= -1;

        brush.setStrokeWidth(widthPX / 2);
        brush.setColor(darkYellow);
        lineView = new LineView(this, quarterOffset, 0, quarterOffset, PHONE_DIMS.y, brush);
        doodleView.addView(lineView);
        sun.add(lineView);

        brush.setStrokeWidth(rayThickness);
        brush.setColor(lightYellow);
        lineView = new LineView(this, PHONE_DIMS.x / 2 + xRayOffset, yRayOffset,
                xRayOffset, PHONE_DIMS.y + yRayOffset, brush);
        doodleView.addView(lineView);
        sun.add(lineView);

        brush.setColor(darkYellow);
        lineView = new LineView(this,
                PHONE_DIMS.x / 2 + xRayOffset, yRayOffset - rayThickness / 2,
                xRayOffset, PHONE_DIMS.y + (yRayOffset - 2 * rayThickness), brush);
        doodleView.addView(lineView);
        sun.add(lineView);

        // sun
        brush.setColor(yellowOrange);
        CircleView circleView = new CircleView(this, PHONE_DIMS.x / 2, 0, radius, brush);
        doodleView.addView(circleView);
        sun.add(0, circleView);

        ///////////////////////////////////   BACKGROUND    ////////////////////////////////////////
        brush.setStrokeWidth(DimHelp.DP2PX(PHONE_DIMS.x, this));
        brush.setColor(Color.LTGRAY);
        LineView fog = new LineView(this, PHONE_DIMS.x / 2, 0,
                PHONE_DIMS.x / 2, PHONE_DIMS.y, brush);
        doodleView.addView(fog);

        /////////////////////////////////// TOP DIRT /////////////////////////////////////////////
        int brown = Color.rgb(165, 104, 42);
        brush.setStrokeWidth(100);
        brush.setColor(brown);
        lineView = new LineView(this, 0, PHONE_DIMS.y - 100,
                PHONE_DIMS.x, PHONE_DIMS.y - 100, brush);
        doodleView.addView(lineView);

        /////////////////////////////////// FLOWERS /////////////////////////////////////////////
        // flower brush
        Paint flowers = new Paint();
        flowers.setColor(brown);
        flowers.setStrokeWidth(10);
        flowers.setStyle(Paint.Style.STROKE);
        int green = Color.rgb(36, 161, 89);

        // flower format
        float translationOffset = 250;                                  // translation offset for animation
        float petalHeight = 50;
        float petalWidth = 20;
        float stemOffset = 250;                                         // stem height
        float cx = PHONE_DIMS.x / 2;                                    // center of ovals
        float cy = (PHONE_DIMS.y - stemOffset / 2 - petalHeight / 2)
                + translationOffset;
        float rotationY = cy + (float) (petalHeight / 2.0);             // y value to rotate around
        List<DrawView> flowerParts = new ArrayList<>();

        //////// FLOWER ONE
        // stem 1
        flowers.setColor(green);
        lineView = new LineView(this, PHONE_DIMS.x / 2, PHONE_DIMS.y - 50 + translationOffset,
                PHONE_DIMS.x / 2, PHONE_DIMS.y - stemOffset + translationOffset, flowers);
        doodleView.addView(lineView);
        flowerParts.add(lineView);

        // leaves 1
        flowers.setStyle(Paint.Style.FILL);
        MyView oval = new MyView(this, cx, cy, petalWidth, petalHeight, 35, cx, rotationY, flowers);
        doodleView.addView(oval);
        flowerParts.add(oval);
        cy = (float) ((PHONE_DIMS.y - stemOffset / 1.5 - petalHeight / 2) + translationOffset);
        rotationY = cy + (float) (petalHeight / 2.0);
        oval = new MyView(this, cx, cy, petalWidth, petalHeight, -35,  cx, rotationY, flowers);
        doodleView.addView(oval);
        flowerParts.add(oval);

        // petals 1
        cy = (PHONE_DIMS.y - stemOffset - petalHeight / 2) + translationOffset;
        rotationY = cy + (float) (petalHeight / 2.0);
        flowers.setColor(Color.rgb(232, 194, 181));
        for (int i = 0; i < 8; i++) {
            oval = new MyView(this, cx, cy, petalWidth, petalHeight, 45 * i, cx, rotationY, flowers);
            doodleView.addView(oval);
            flowerParts.add(oval);
        }

        flowers.setColor(Color.rgb(230, 145, 135));
        for (int i = 0; i < 8; i++) {
            oval = new MyView(this, cx, cy, petalWidth, petalHeight, 45 * i + 23, cx, rotationY, flowers);
            doodleView.addView(oval);
            flowerParts.add(oval);
        }

        // center 1
        flowers.setColor(Color.WHITE);
        circleView = new CircleView(this, cx, PHONE_DIMS.y - stemOffset + translationOffset, 8, flowers);
        doodleView.addView(circleView);
        flowerParts.add(circleView);

        // Flower 1 animations
        List<ObjectAnimator> flowerAnimations = new ArrayList();
        for (int i = 0; i < flowerParts.size(); i++) {
            float start = flowerParts.get(i).getY();
            float end = start - DimHelp.DP2PX(translationOffset, this);
            flowerAnimations.add(ObjectAnimator.ofFloat(flowerParts.get(i), "translationY", start, end));
            flowerAnimations.get(i).setDuration(4000);
        }

        //////// FLOWER TWO
        // stem 2
        stemOffset = 200;
        flowerParts.clear();
        flowers.setColor(green);
        lineView = new LineView(this, PHONE_DIMS.x / 4, PHONE_DIMS.y - 50 + translationOffset,
                PHONE_DIMS.x / 4, PHONE_DIMS.y - stemOffset + translationOffset, flowers);
        doodleView.addView(lineView);
        flowerParts.add(lineView);

        petalHeight = 40;
        petalWidth = 15;
        cx = PHONE_DIMS.x / 4;
        cy = (PHONE_DIMS.y - stemOffset / 2 - petalHeight / 2) + translationOffset;
        rotationY = cy + (float) (petalHeight / 2.0);

        // leaves 2
        oval = new MyView(this, cx, cy, petalWidth, petalHeight, -35, cx, rotationY, flowers);
        doodleView.addView(oval);
        flowerParts.add(oval);

        cy = (float) ((PHONE_DIMS.y - stemOffset / 1.5 - petalHeight / 2) + translationOffset);
        rotationY = cy + (float) (petalHeight / 2.0);
        oval = new MyView(this, cx, cy, petalWidth, petalHeight, 35, cx, rotationY, flowers);
        doodleView.addView(oval);
        flowerParts.add(oval);

        // petals 2
        cy = (PHONE_DIMS.y - stemOffset - petalHeight / 2) + translationOffset;
        rotationY = cy + (float) (petalHeight / 2.0);
        flowers.setColor(Color.rgb(129, 156, 200));
        for (int i = 0; i < 8; i++) {
            oval = new MyView(this, cx, cy, petalWidth, petalHeight, 45 * i + 23, cx, rotationY, flowers);
            doodleView.addView(oval);
            flowerParts.add(oval);
        }

        flowers.setColor(Color.rgb(139, 208, 250));
        for (int i = 0; i < 8; i++) {
            oval = new MyView(this, cx, cy, petalWidth, petalHeight, 45 * i, cx, rotationY, flowers);
            doodleView.addView(oval);
            flowerParts.add(oval);
        }

        // center 2
        flowers.setColor(Color.rgb(241, 207, 140));
        circleView = new CircleView(this, cx, PHONE_DIMS.y - stemOffset + translationOffset, 5, flowers);
        doodleView.addView(circleView);
        flowerParts.add(circleView);

        // Flower 2 animations
        for (int i = 0; i < flowerParts.size(); i++) {
            float start = flowerParts.get(i).getY();
            float end = start - DimHelp.DP2PX(translationOffset, this);
            flowerAnimations.add(ObjectAnimator.ofFloat(flowerParts.get(i), "translationY", start, end));
            flowerAnimations.get(flowerAnimations.size() - 1).setDuration(4000);
        }

        //////// FLOWER THREE
        // stem 3
        stemOffset = 225;
        flowers.setColor(green);
        flowerParts.clear();
        lineView = new LineView(this, (float) (PHONE_DIMS.x * 3.0 / 4.0), PHONE_DIMS.y - 50 + translationOffset,
                (float) (PHONE_DIMS.x * 3.0 / 4.0), PHONE_DIMS.y - stemOffset + translationOffset, flowers);
        doodleView.addView(lineView);
        flowerParts.add(lineView);

        petalHeight = 45;
        petalWidth = 15;
        cx = (float) (PHONE_DIMS.x * 3.0 / 4.0);
        cy = (PHONE_DIMS.y - stemOffset / 2 - petalHeight / 2) + translationOffset;
        rotationY = cy + (float) (petalHeight / 2.0);

        // leaves 3
        oval = new MyView(this, cx, cy, petalWidth, petalHeight, -35, cx, rotationY, flowers);
        doodleView.addView(oval);
        flowerParts.add(oval);

        cy = (float) ((PHONE_DIMS.y - stemOffset / 1.5 - petalHeight / 2) + translationOffset);
        rotationY = cy + (float) (petalHeight / 2.0);
        oval = new MyView(this, cx, cy, petalWidth, petalHeight, 35, cx, rotationY, flowers);
        doodleView.addView(oval);
        flowerParts.add(oval);

        // petals 3
        cy = (PHONE_DIMS.y - stemOffset - petalHeight / 2) + translationOffset;
        rotationY = cy + (float) (petalHeight / 2.0);
        flowers.setColor(Color.rgb(192, 174, 196));
        for (int i = 0; i < 8; i++) {
            oval = new MyView(this, cx, cy, petalWidth, petalHeight, 45 * i + 23, cx, rotationY, flowers);
            doodleView.addView(oval);
            flowerParts.add(oval);
        }

        flowers.setColor(Color.rgb(178, 142, 212));
        for (int i = 0; i < 8; i++) {
            oval = new MyView(this, cx, cy, petalWidth, petalHeight, 45 * i, cx, rotationY, flowers);
            doodleView.addView(oval);
            flowerParts.add(oval);
        }

        // center 3
        flowers.setColor(Color.rgb(235, 235, 174));
        circleView = new CircleView(this, cx, PHONE_DIMS.y - stemOffset + translationOffset, 5, flowers);
        doodleView.addView(circleView);
        flowerParts.add(circleView);


        // Flower 3 animations
        for (int i = 0; i < flowerParts.size(); i++) {
            float start = flowerParts.get(i).getY();
            float end = start - DimHelp.DP2PX(translationOffset, this);
            flowerAnimations.add(ObjectAnimator.ofFloat(flowerParts.get(i), "translationY", start, end));
            flowerAnimations.get(flowerAnimations.size() - 1).setDuration(4000);
        }

        /////////////////////////////////// BOTTOM DIRT ////////////////////////////////////////////
        lineView = new LineView(this, 0, PHONE_DIMS.y - 50,
                PHONE_DIMS.x, PHONE_DIMS.y - 50, brush);
        doodleView.addView(lineView);

        /////////////////////////////////// BEE /////////////////////////////////////////////
        float beeY = PHONE_DIMS.y / 2;
        float pathWidth = DimHelp.DP2PX(Math.min(PHONE_DIMS.x, PHONE_DIMS.y) / 5, this);
        DrawView drawView = new DrawView(this, "bee", "cartoon bee", this.getPackageName(), -100, beeY, 100);
        Path beePath = new Path();
        beePath.moveTo(-150, beeY);
        beePath.rQuadTo(pathWidth / 2, 50, pathWidth, 0);
        beePath.rQuadTo(pathWidth / 2, -50, pathWidth, 0);
        beePath.rQuadTo(pathWidth / 2, 50, pathWidth, 0);
        beePath.rQuadTo(pathWidth / 2, -50, pathWidth, 0);
        doodleView.addView(drawView);

        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        TextView textView = new TextView(this, (float) (Math.min(PHONE_DIMS.x, PHONE_DIMS.y) * 4.0 / 5),
                DimHelp.PX2DP(beeY + 50, this), 10, "bee happy :)", textPaint);
        textView.setAlpha(0f);
        doodleView.addView(textView);

        /////////////////////////////////// ANIMATIONS /////////////////////////////////////////////
        // fade out gray overlay
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(fog, "alpha",  1f, 0f);
        fadeOut.setDuration(1000);
        fadeOut.setStartDelay(1000);
        fadeOut.start();

        // start flower animations
        for (int i = 0; i < flowerAnimations.size(); i++) {
            flowerAnimations.get(i).setStartDelay(1000);
            flowerAnimations.get(i).start();
        }

        // bee animation
        ObjectAnimator beeAnimation = ObjectAnimator.ofFloat(drawView, DrawView.X, DrawView.Y, beePath);
        beeAnimation.setStartDelay(4000);
        beeAnimation.setDuration(3000);
        beeAnimation.start();

        // text animation
        ObjectAnimator textAnimation =  ObjectAnimator.ofFloat(textView, "alpha",  0f, 1f);
        textAnimation.setStartDelay(7000);
        textAnimation.setDuration(100);
        textAnimation.start();
    }
}
