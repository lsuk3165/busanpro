package com.com.busantourisme.model.festival;

import com.com.busantourisme.model.User;

import java.time.LocalDateTime;

public class FestivalComment {
    private int commentId;
    private String content;
    private String commentImageUrl;
    private Festival festival;
    private User user;
    private LocalDateTime createDate;
}
