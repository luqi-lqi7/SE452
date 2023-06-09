package edu.depaul.se452.restaurant_review.review_lu.relational;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name =  "Reviews") 
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userID;

    @Column(name = "restaurant_id")
    private long restaurantID; 

    @Column(name = "date")
    private LocalDate date; // or LocalDateTime, or Date, or Timestamp

    private String content; 
    
    private int star; 
}
