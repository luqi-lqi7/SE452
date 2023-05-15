package edu.depaul.se452.restaurant_review.food_arshain.relational;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "food_id")
    private long foodID;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


}
