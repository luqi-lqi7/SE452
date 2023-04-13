package edu.depaul.se452.restaurant_review.review_lu;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String userID;
    private String restaurantID;
    private String date; 
    private String content; 
    private int star; 
}
