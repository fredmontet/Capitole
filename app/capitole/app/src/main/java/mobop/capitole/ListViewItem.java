package mobop.capitole;

import android.graphics.drawable.Drawable;

/**
 * The data container for all the items in the listViews of Capitole App.
 *
 * Created by fredmontet on 05/11/15.
 */
public class ListViewItem {

    public final String title;        // the text for the ListView item title
    public final String description;  // the text for the ListView item description

    public ListViewItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

}
