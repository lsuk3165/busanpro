package com.com.busantourisme.model.tour;

import android.graphics.drawable.Drawable;

import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
    private int tourId;
    private String tourTitle;
    private String tourAddr;
    private String usageDay;
    private String tourArea;
    private String homepage;
    private String thumb;
    private String traffic;
    private String tel;
    private String lat;
    private String lng;

}
