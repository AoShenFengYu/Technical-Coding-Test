package com.example.david.technical_coding_test.tools;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by David on 2018/1/3.
 */

public class Tools {

    /**
     * 取得螢幕的大小
     *
     * @return int[]{x, y}, x is width, y is height.
     **/
    public static int[] getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        return new int[]{size.x, size.y - getStatusBarHeight(context)};
    }

    /**
     * 取得Status Bar高度
     **/
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int result = 0;
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");

        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static void delayToDo(Runnable runnable, int delayMillis) {
        new Handler().postDelayed(runnable, delayMillis);
    }
}
