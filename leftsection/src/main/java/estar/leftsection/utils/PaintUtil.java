package estar.leftsection.utils;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by xueliang on 2018/3/14.
 */

public class PaintUtil {

    public static float getTextWidth(Paint textPaint,String text){
        return textPaint.measureText(text);
    }

    public static float getTextHeight(Paint textPaint,String text){
        Rect rect = new Rect();
        textPaint.getTextBounds(text,0, text.length(),rect);
        return rect.height();
    }
}
