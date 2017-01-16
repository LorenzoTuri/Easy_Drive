package viewManagers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lorenzo.germana.easydrive.R;

/**
 * Created by loren on 13/04/2016.
 */
public class MessageViewManager {
    ViewGroup root;
    ViewGroup menu;

    public MessageViewManager(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.tabmessage, null);
        ViewGroup menu = (ViewGroup)inflater.inflate(R.layout.tabmessagemenu, null);

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
