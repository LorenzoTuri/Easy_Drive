package eventListeners;

import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import com.lorenzo.germana.easydrive.EasyDrive;
import com.lorenzo.germana.easydrive.R;

/**
 * Created by loren on 13/04/2016.
 */
public class OnTouchDesignButton implements View.OnTouchListener {
    Drawable clickedBackground = EasyDrive.getContext().getResources().getDrawable(R.drawable.roundshapeclicked);
    Drawable normalBackground;

    public boolean onTouch(View v, MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            normalBackground = v.getBackground();
            v.setBackgroundDrawable(clickedBackground);
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            v.setBackgroundDrawable(normalBackground);
        }
        return false;
    }
}