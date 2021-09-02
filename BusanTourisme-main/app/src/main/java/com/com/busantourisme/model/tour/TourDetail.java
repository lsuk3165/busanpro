package com.com.busantourisme.model.tour;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDetail {
    int id;
    int idImg;
    String title;
    String content;
    int favorite;
    int countFav;
    int Comment;
    int countCom;
}
