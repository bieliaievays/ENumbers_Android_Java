package com.ashomok.eNumbers.activities.ocr_task;

import android.graphics.Bitmap;

/**
 * Created by Iuliia on 24.12.2015.
 */
public interface BinarizeTaskDelegate {
    void TaskCompletionResult(Bitmap[] result);
}
