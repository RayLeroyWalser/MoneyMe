package id.co.adiyatmubarak.moneyme.utilities;

import android.content.Context;
import android.graphics.Typeface;

import id.co.adiyatmubarak.moneyme.models.User;
import io.realm.RealmQuery;

/**
 * Created by Administrator on 2015-02-08.
 */
public class CommonHelper {

    public static final String FONT_NORMAL_PATH = "fonts/roboto-light.ttf";
    public static final String FONT_BOLD_PATH = "fonts/roboto-bold.ttf";

    /**
     * Function returned typeface by selected enum
     *
     * @param ctx       Activity context
     * @param fontType  Enumeration BOLD or NORMAL
     * @return typeface Depend on selected font type
     */
    public static Typeface getTypeFace(Context ctx, FontEnum fontType) {
        if (fontType == FontEnum.NORMAL) {
            return Typeface.createFromAsset(ctx.getAssets(), FONT_NORMAL_PATH);
        } else if (fontType == FontEnum.BOLD) {
            return Typeface.createFromAsset(ctx.getAssets(), FONT_BOLD_PATH);
        }
        return null;
    }

}
