package cse340.doodle;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * A type of activity which provides a getter and setter for persistent storage
 * of an integer across the application lifecycle via Bundle. Convenient to store
 * current tab view ID.
 */
public abstract class TabActivity extends AppCompatActivity {
    protected static final String CURRENT_TAB_ID_KEY = "ACTIVITY_TAB_ID";
    protected ViewGroup mMenu;
    public void setMenu(ViewGroup menu) { this.mMenu = menu; }

    @IdRes
    private int mTabId = -1;

    /**
     * Invoked when the activity may be temporarily destroyed, save the instance state here.
     * @param outState State to save out through the bundle
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_TAB_ID_KEY, mTabId);
    }

    /**
     * Called after onCreate
     * need to override this because when the system first displays itself
     * we need to update the tab and the relevant alt text.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // Tabs at the bottom of screen.
        TabView nav = findViewById(R.id.bottom_nav);
        // Setup the connections used to support accessibility
        setMenu(nav);
        nav.setTabHolder(this);

        // call this to set initial selection
        nav.setSelectedItemId(mTabId);
    }

    /**
     * Restores the current tab id
     * @param state Stores the current tab ID
     * @param defaultVal Default value if no state exists
     * @return The tab id
     */
    @IdRes
    protected int getSavedTabId(Bundle state, @IdRes int defaultVal) {
        if (state == null) {
            return defaultVal;
        }
        return state.getInt(CURRENT_TAB_ID_KEY, defaultVal);
    }

    /**
     * Sets the current tab
     *
     * @param id The id of the current tab
     */
    protected final void setCurrentTabId(@IdRes int id) {
        mTabId = id;
    }

    /**
     * Retrieves the string to use for describing a tab
     * @param id The id of the menu item to get content for
     * @param checked Whether or not that menu item is checked
     * @return A string resource with the same id as the menu item which is alt text for that item
     */
    public String getContentDescription(int id, boolean checked) {
        String name = getResources().getResourceEntryName(id);
        String tab = getString(getResources().getIdentifier(name, "string", this.getPackageName()));
        return checked ? getString(R.string.accessibility_tab_format_selected, tab):
                getString(R.string.accessibility_tab_format, tab);
    }
}
