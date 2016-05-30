package cn.appleye.randomcontact.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import cn.appleye.randomcontact.R;

/**
 * Created by liuliaopu on 2016/5/30.
 */
public class MenuPopupWindow extends PopupWindow{

    private View mMenuView;
    private View mBasicView;
    private View mAdvancedView;

    public MenuPopupWindow(Context context, View.OnClickListener listener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.layout_random_contact_menu, null);
        mBasicView = mMenuView.findViewById(R.id.menu_basic_settings);
        mAdvancedView = mMenuView.findViewById(R.id.menu_advanced_settings);
        if (listener != null) {
            mBasicView.setOnClickListener(listener);
            mAdvancedView.setOnClickListener(listener);
        }

        setContentView(mMenuView);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        setAnimationStyle(R.style.MenuPopupAni);
        ColorDrawable dw = new ColorDrawable(0x00000000);
        setBackgroundDrawable(dw);
    }
}
