package com.com.busantourisme.model.tour;

import com.com.busantourisme.model.User;

import java.time.LocalDateTime;

public class TourComment {
    private int commentId;
    private String content;
    private String commentImageUrl;
    private User user;
    private Tour tour;
    private LocalDateTime createDate;
}
