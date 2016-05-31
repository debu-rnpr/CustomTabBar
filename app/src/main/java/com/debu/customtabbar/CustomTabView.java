package com.debu.customtabbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;


import java.util.ArrayList;

/**
 * TODO: document your custom view class.
 */
public class CustomTabView extends LinearLayout implements View.OnClickListener {

    /**
     * interface for callback from custom tab to the holder Activity/view
     */
    public interface CustomTabCallback {
        void onTabPressed(int tab);
    }
    static int NO_ID = -1;
    private int current = NO_ID;
    private CustomTabCallback listener;
    private int selectorListID = NO_ID;
    private String selectorList[];
    private ArrayList<ImageView> tabItems = new ArrayList<>();

    public CustomTabView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomTabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTabBar);
        selectorListID = arr.getResourceId(R.styleable.CustomTabBar_tabSelectors, selectorListID);
        Log.e("selectorListID", selectorListID + " " + getResources().getStringArray(selectorListID)[0]);
        selectorList = selectorListID != NO_ID ? getResources().getStringArray(selectorListID) : null;
        if (null != selectorList) {
            setupTabs();
        }
    }

    private void setupTabs() {
        for (int i = 0; i < selectorList.length; i++) {
            ImageView iv = new ImageView(getContext());
            LayoutParams lp = (LayoutParams) generateDefaultLayoutParams();
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.weight = 1;
            iv.setLayoutParams(lp);
            iv.setBackgroundResource(getResources().getIdentifier(selectorList[i], "drawable", getContext().getPackageName()));
            iv.setTag(Integer.valueOf(i));
            iv.setOnClickListener(this);
            this.addView(iv);
            tabItems.add(iv);

            Log.e("tabItems", tabItems.size() + " " + selectorList[i]);
        }
        invalidate();
    }

    public void setSelection(int pos) {
        if (pos != current) {
            if (current != NO_ID && tabItems.get(current).isSelected()) {
                tabItems.get(current).setSelected(false);
            }
            tabItems.get(pos).setSelected(true);
            if (listener != null) {
                listener.onTabPressed(pos);
            }

            current = pos;
        }
    }

    public int getCurrentTab() {
        return current;
    }

    public void setOnTabClickedListener(CustomTabCallback callback) {
        listener = callback;
    }

    @Override
    public void onClick(View v) {
        setSelection((int) v.getTag());
    }
}
