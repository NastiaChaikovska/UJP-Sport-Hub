package com.softserve.edu.sporthubujp.dto.comment;

import lombok.Data;

@Data
public class LikeDislikeStatusDTO {
    private String id;
    private Boolean likedDisliked;
    private String commentId;
    private String userId;
}
