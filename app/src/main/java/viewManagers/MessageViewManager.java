package viewManagers;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by loren on 13/04/2016.
 */
public class MessageViewManager {
    ViewGroup root;
    ViewGroup menu;

    public MessageViewManager(ViewGroup root, ViewGroup menu){
        this.root = root;
        this.menu = menu;
    }

    public View getView(){
        return root;
    }

    public View getMenu(){
        return menu;
    }
}
