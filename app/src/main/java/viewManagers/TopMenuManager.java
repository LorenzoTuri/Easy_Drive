package viewManagers;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lorenzo.germana.easydrive.MainActivity;
import com.lorenzo.germana.easydrive.R;

import eventListeners.OnTouchDesignButton;

/**
 * Created by loren on 13/04/2016.
 */
public class TopMenuManager {
    ViewGroup root;
    ViewGroup elementContainer;

    View ButtonMaps;
    View ButtonMusic;
    View ButtonMessages;
    View showTabBar;

    ViewGroup MenuElementContainer;
    boolean shown = false;

    public TopMenuManager(MainActivity mainActivity){

        final MainActivity activity = mainActivity;

        ViewGroup root = (ViewGroup)activity.findViewById(R.id.TopMenuContainer);
        final ViewGroup elementContainer = (ViewGroup)activity.findViewById(R.id.ElementContainer);
        ViewGroup menuElementContainer = (ViewGroup)activity.findViewById(R.id.OperationContainer);

        this.root = root;
        this.elementContainer = elementContainer;
        this.MenuElementContainer = menuElementContainer;

        ButtonMaps = root.findViewById(R.id.mapsButton);
        ButtonMusic = root.findViewById(R.id.musicButton);
        ButtonMessages = root.findViewById(R.id.callsMsgButton);

        showTabBar = root.findViewById(R.id.showTabBar);
        showTabBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen()) close();
                else open();
            }
        });

        ButtonMaps.setOnTouchListener(new OnTouchDesignButton());
        ButtonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementContainer.removeAllViews();
                elementContainer.addView(activity.mapViewManager.getView(), 0);

                ViewGroup.LayoutParams l = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                MenuElementContainer.removeAllViews();
                MenuElementContainer.addView(activity.mapViewManager.getMenu(), l);
            }
        });

        ButtonMusic.setOnTouchListener(new OnTouchDesignButton());
        ButtonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementContainer.removeAllViews();
                elementContainer.addView(activity.musicViewManager.getView(), 0);

                MenuElementContainer.removeAllViews();
                MenuElementContainer.addView(activity.musicViewManager.getMenu(), 0);
            }
        });

        ButtonMessages.setOnTouchListener(new OnTouchDesignButton());
        ButtonMessages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                elementContainer.removeAllViews();
                elementContainer.addView(activity.messageViewManager.getView(), 0);

                MenuElementContainer.removeAllViews();
                MenuElementContainer.addView(activity.messageViewManager.getMenu(), 0);
            }
        });
    }

    public boolean isOpen() {
        return shown;
    }

    public void open(){
        root.setY(0);
        shown = true;
    }

    public void close(){
        root.setY(-1 * (root.getHeight()) + showTabBar.getHeight());
        shown = false;
    }
}