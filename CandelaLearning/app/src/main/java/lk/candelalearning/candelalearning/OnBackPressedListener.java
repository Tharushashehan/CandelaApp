package lk.candelalearning.candelalearning;

//import android.support.v4.app.Fragment;
import android.app.Fragment;

/**
 * Created by tharu on 4/22/2018.
 */

public interface OnBackPressedListener {

    /**
     * Callback, which is called if the Back Button is pressed.
     * Fragments that extend MainFragment can/should override this Method.
     *
     * @return true if the App can be closed, false otherwise
     */
    boolean onBackPressedCheck();
    Fragment SetFragmentData();
}
