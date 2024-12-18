package com.org.vetconnect.platform.profiles.interfaces.rest;

import com.org.vetconnect.platform.profiles.domain.model.commands.CreateReviewCommand;
import com.org.vetconnect.platform.profiles.domain.model.commands.DeleteReviewCommand;
import com.org.vetconnect.platform.profiles.domain.model.entities.Review;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllReviewsByVetCenterIdQuery;
import com.org.vetconnect.platform.profiles.domain.model.queries.GetAllReviewsQuery;
import com.org.vetconnect.platform.profiles.domain.services.ReviewCommandService;
import com.org.vetconnect.platform.profiles.domain.services.ReviewQueryService;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.Reviews.CreateReviewResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.resources.Reviews.ReviewResource;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.Reviews.CreateReviewCommandFromResourceAssembler;
import com.org.vetconnect.platform.profiles.interfaces.rest.transform.Reviews.ReviewResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.sql.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/api/v1/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Reviews Management Endpoints")
public class ReviewsController {

    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    // se usan los ensambladores explicitamente inyectados como dependencias
    private final CreateReviewCommandFromResourceAssembler createReviewCommandFromResourceAssembler;
    private final ReviewResourceFromEntityAssembler reviewsResourceFromEntityAssembler;

    public ReviewsController
            (ReviewCommandService reviewCommandService,
             ReviewQueryService reviewQueryService,
             CreateReviewCommandFromResourceAssembler createReviewCommandFromResourceAssembler,
             ReviewResourceFromEntityAssembler reviewsResourceFromEntityAssembler) {

        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
        this.createReviewCommandFromResourceAssembler = createReviewCommandFromResourceAssembler;
        this.reviewsResourceFromEntityAssembler = reviewsResourceFromEntityAssembler;
    }

    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource createReviewResource) {
        CreateReviewCommand createReviewCommand = createReviewCommandFromResourceAssembler.toCommandFromResource(createReviewResource);
        Review review = reviewCommandService.handle(createReviewCommand);

        if (review == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Manejo de error genérico
        }

        ReviewResource reviewResource = reviewsResourceFromEntityAssembler.toResourceFromEntity(review);
        return ResponseEntity.created(URI.create("/api/v1/reviews/" + review.getId())).body(reviewResource);
    }


    @GetMapping("/vet-center/{vetCenterId}")
    public ResponseEntity<List<ReviewResource>> getReviewsByVetCenterId(@PathVariable Long vetCenterId) {
        GetAllReviewsByVetCenterIdQuery getAllReviewsByVetCenterIdQuery = new GetAllReviewsByVetCenterIdQuery(vetCenterId);

        List<Review> reviews = reviewQueryService.handle(getAllReviewsByVetCenterIdQuery);
        List<ReviewResource> reviewResources = reviews.stream()
                .map(reviewsResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResources);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResource>> getAllReviews() {
        GetAllReviewsQuery getAllReviewsQuery = new GetAllReviewsQuery();
        List<Review> reviews = reviewQueryService.handle(getAllReviewsQuery);
        List<ReviewResource> reviewResources = reviews.stream()
                .map(reviewsResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reviewResources);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@RequestParam Long reviewId) {
        DeleteReviewCommand deleteReviewCommand = new DeleteReviewCommand(reviewId);
        reviewCommandService.handle(deleteReviewCommand);
        return ResponseEntity.ok("Review successfully deleted");
    }

}
