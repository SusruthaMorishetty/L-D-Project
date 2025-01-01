package com.ust.empoweru.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FeedbackRequest {
        Long enrollmentId;
        String comment;
        int rating;
    };
