/*
 * Copyright (c) 2015 Recruit Marketing Partners Co., Ltd. All rights reserved.
 * Created by kgmyshin on 2016/04/18.
 */

package com.kgmyshin.esa.common;

import android.databinding.BindingAdapter;
import android.text.format.DateFormat;
import android.widget.TextView;

import java.util.Date;

public final class TextViewBindingAdapter {

    @BindingAdapter("dateText")
    public static void setDateText(TextView textView, Date date) {
        textView.setText(DateFormat.format("yyyy.MM.dd", date));
    }
}
