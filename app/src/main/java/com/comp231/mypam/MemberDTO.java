package com.comp231.mypam;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.AndroidViewModel;

import java.text.DecimalFormat;
import java.util.Date;

public class MemberDTO {
    public int addButtonImage;
    public String category;
    public String date;
    public String amount;
    public int removeButtonImage;

    public MemberDTO() {
        this.addButtonImage = R.drawable.add_button;
        this.category = "Category";
        this.date = "Date";
        this.amount = "Amount";
        this.removeButtonImage = R.drawable.remove_button;
    }
}
