package com.org.vetconnect.platform.profiles.domain.services;

import com.org.vetconnect.platform.profiles.domain.model.entities.Review;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllReviewsByVetCenterIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllReviewsQuery;

import java.util.List;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsByVetCenterIdQuery query);
    List<Review> handle(GetAllReviewsQuery query);
}
