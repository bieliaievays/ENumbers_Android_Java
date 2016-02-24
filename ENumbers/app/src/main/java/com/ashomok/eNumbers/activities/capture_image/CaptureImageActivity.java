package com.ashomok.eNumbers.activities.capture_image;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;

import com.ashomok.eNumbers.R;

/**
 * Created by Iuliia on 19.02.2016.
 */
public class CaptureImageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capture_image_layout);

        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.i(this.getClass().getCanonicalName(), "Activity finished");
//
//        setResult(RESULT_CANCELED);
//        finish();
//    }
}