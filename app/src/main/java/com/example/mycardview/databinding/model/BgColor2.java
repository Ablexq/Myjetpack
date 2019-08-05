package com.example.mycardview.databinding.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;


public class BgColor2 extends BaseObservable {

    private boolean showColor;

    @Bindable
    public boolean isShowColor() {
        return showColor;
    }

    public void setShowColor(boolean showColor) {
        this.showColor = showColor;
//        notifyPropertyChanged(BR.showColor);
    }
}
