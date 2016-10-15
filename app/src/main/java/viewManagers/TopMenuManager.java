package viewManagers;

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

    public TopMenuManager(final ViewGroup root, final ViewGroup elementContainer, final ViewGroup MenuElementContainer){
        this.root = root;
        this.elementContainer = elementContainer;
        this.MenuElementContainer = MenuElementContainer;

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
                elementContainer.addView(MainActivity.mapViewManager.getView(), 0);

	            ViewGroup.LayoutParams l = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                MenuElementContainer.removeAllViews();
                MenuElementContainer.addView(MainActivity.mapViewManager.getMenu(),l);
            }
        });

        ButtonMusic.setOnTouchListener(new OnTouchDesignButton());
        ButtonMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementContainer.removeAllViews();
                elementContainer.addView(MainActivity.musicViewManager.getView(), 0);

                MenuElementContainer.removeAllViews();
                MenuElementContainer.addView(MainActivity.musicViewManager.getMenu(),0);
            }
        });

        ButtonMessages.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                elementContainer.removeAllViews();
                elementContainer.addView(MainActivity.messageViewManager.getView(), 0);

                MenuElementContainer.removeAllViews();
                MenuElementContainer.addView(MainActivity.messageViewManager.getMenu(),0);
            }
        });
        ButtonMessages.setOnTouchListener(new OnTouchDesignButton());
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