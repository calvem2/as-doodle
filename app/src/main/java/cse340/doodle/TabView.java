package cse340.doodle;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class TabView extends BottomNavigationView {
    protected TabActivity mTabHolder;

    public TabView(Context context) {
        super(context);
    }

    public TabView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /**
     * Sets up a pointer to the Activity holding these tabs
     * @param tabHolder the TabActivity holding these tabs
     */
    public void setTabHolder(TabActivity tabHolder) {
        this.mTabHolder = tabHolder;
    }


    /**
     * Gets the view associated with a menu item
     * @param id of the menu item
     * @return the view
     */
    private View getMenuItemView(int id) {
        ViewGroup items = (ViewGroup) getChildAt(0);
        View item = items.getChildAt(id);
        return item;
    }

    /**
     * Override the draw message to update the nonvisual content of the menu
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Menu menu = getMenu();

        // keep track of which view is currently checked
        boolean isChecked = false;

        // loop through the tabs
        for (int i = 0; i < menu.size(); i++) {
            // get this one
            View menuItemView = getMenuItemView(i);
            // store it if if it is checked
            isChecked = menu.getItem(i).isChecked();
            // set teh content description for the navbar to show which tab is selected
            if (isChecked) {
                setContentDescription(getResources().getString(R.string.accessible_tab, menu.size(), i+1));
            }

            // set up the content description for the menu item, indicating wheter it is seleced
            menuItemView.setContentDescription(mTabHolder.getContentDescription(menuItemView.getId(), isChecked));
        }
    }
}
