package cse340.doodle;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.widget.FrameLayout;

public class Part1BActivity extends AbstractMainActivity {

    /**
     * Callback that is called when the activity is first created.
     * @param savedInstanceState contains the activity's previously saved state
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /**
     * Heavy duty test of the functions - once this screen is shown you can do a pixel
     * comparison of this screen with the one given as part of the assignmment to test your
     * new features.
     *
     * @param doodleView    Canvas on which to doodle.
     */
    @Override
    public void doodle(FrameLayout doodleView) {
        float radius = PHONE_DIMS.x/8;
        float maxX = PHONE_DIMS.x;
        float maxY = (float) (PHONE_DIMS.y-((float) radius)*1.5);
        float sizeS = radius;
        float sizeM = (float) (radius*1.5);
        float sizeL = radius*2;
        int purple = Color.rgb(51,0,111);

        // a generic paint brush
        Paint brush = new Paint();
        brush.setStyle(Paint.Style.STROKE);
        brush.setColor(purple);
        brush.setStrokeWidth(5);

        // Tests images
        addAllImagesFromData(doodleView, "data2.csv");

        // Tests circles (corners, edges, centers
        CircleView circleView = new CircleView(this, 0, 0, sizeS, brush);
        doodleView.addView(circleView);
        circleView = new CircleView(this, maxX, 0, sizeS, brush);
        doodleView.addView(circleView);
        circleView = new CircleView(this, maxX, maxY, sizeS, brush);
        doodleView.addView(circleView);
        circleView = new CircleView(this, 0, maxY, sizeS, brush);
        doodleView.addView(circleView);
        circleView = new CircleView(this, 0, maxY/2, sizeS, brush);
        doodleView.addView(circleView);
        circleView = new CircleView(this, maxX, maxY/2, sizeS, brush);
        doodleView.addView(circleView);
        circleView = new CircleView(this, maxX/2, 0, sizeS, brush);
        doodleView.addView(circleView);
        circleView = new CircleView(this, maxX/2, maxY, sizeS, brush);
        doodleView.addView(circleView);

        brush.setStrokeWidth(10);
        brush.setColor(Color.BLUE);
        circleView = new CircleView(this, maxX/2, maxY/2, sizeL,  brush);
        doodleView.addView(circleView);

        // Tests lines (all directions!)
        int lines = 8;
        float angles = 360/lines;
        brush.setStrokeWidth(15);
        Shader shader;

        // we want to draw lines inside the circle that is centered at
        // maxX/2, maxY/2
        // and has a radius of sizeL
        // the lines should go from r=20 to r=sizeM (which is slightly smaller than sizeL)

        for (int i = 0; i < lines; i++ ) {
            float angle = (float)Math.toRadians(angles * i);
            float x = (float) Math.cos(angle);  // x on the unit circle at angle
            float y = (float) Math.sin(angle);  // y on the unit circle at angle

            int startX = (int) ((maxX/2) + Math.floor((x*20.0)));  // x at radius=20, translated to maxX/2
            int startY = (int) ((maxY/2) + Math.floor((y*20.0)));  // y at radius=20, translated to maxY/2
            int endX = (int) ((maxX/2) + Math.floor((x*sizeM)));  // x at radius=sizeM, translated to maxX/2
            int endY = (int) ((maxY/2) + Math.floor((y*sizeM)));  // y at radius=sizeM, translated to maxY/2

            // if angle is 0, this is the default shader
            shader = new LinearGradient(0, 0, sizeM-sizeS, sizeM-sizeS, Color.GREEN,  Color.RED, Shader.TileMode.CLAMP);
            if ((angles * i) == 45) shader = new LinearGradient(0, 0, sizeM-sizeS, sizeM-sizeS, Color.CYAN,  Color.RED, Shader.TileMode.CLAMP);
            if ((angles * i) == 90) shader = new LinearGradient(0, 0, 0, sizeM-sizeS, Color.YELLOW,  Color.RED, Shader.TileMode.CLAMP);
            if ((angles * i) == 135) shader = new LinearGradient(sizeM-sizeS, 0, 0, sizeM-sizeS, Color.DKGRAY,  Color.RED, Shader.TileMode.CLAMP);
            if ((angles * i) == 180) shader = new LinearGradient(sizeM-sizeS, 0,0,0, Color.GRAY,  Color.RED, Shader.TileMode.CLAMP);
            if ((angles * i) == 225) shader = new LinearGradient(sizeM-sizeS, sizeM-sizeS, 0, 0,  Color.MAGENTA,  Color.RED, Shader.TileMode.CLAMP);
            if ((angles * i) == 270) shader = new LinearGradient(0, sizeM-sizeS, 0, 0, Color.BLUE,  Color.RED, Shader.TileMode.CLAMP);
            if ((angles * i) == 315) shader = new LinearGradient(0, sizeM-sizeS, sizeM-sizeS, 0, Color.BLACK,  Color.RED, Shader.TileMode.CLAMP);
            brush.setShader(shader);
            LineView lineView = new LineView(this, startX, startY, endX, endY, brush);

            doodleView.addView(lineView);
        }

        // Tests text
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);

        TextView textView = new TextView(this, maxX/3, maxY/4, 30, "Test Page", textPaint);
        doodleView.addView(textView);
    }

}
