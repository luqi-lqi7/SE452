package edu.depaul.se452.restaurant_review.review_lu.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.depaul.se452.restaurant_review.review_lu.relational.Review;
import edu.depaul.se452.restaurant_review.review_lu.relational.ReviewRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repo;

    @GetMapping
    public String list(Model model, HttpSession session) {
        model.addAttribute("review", repo.findAll());
        if (session.getAttribute("review") == null) {
            model.addAttribute("review", new Review());
            model.addAttribute("btnAddOrModifyLabel", "Add");
        } else {
            model.addAttribute("review", session.getAttribute("review"));
            model.addAttribute("btnAddOrModifyLabel", "Modify");
        }
        return "reviews";
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") Long id, Model model) {
        Review review = repo.findById(id).orElse(new Review());
        model.addAttribute("review", review);
        return "reviews";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model, HttpSession session) {
        repo.deleteById(id);
        return "redirect:/reviews";
    }

    @PostMapping
    public String save(@ModelAttribute Review review, HttpSession session) {
        if (review.getId() == 0)
            repo.save(review);
        else {
            var editReview = repo.findById(review.getId()).get();
            editReview.setDate(review.getDate());
            editReview.setContent(review.getContent());
            editReview.setStar(review.getStar());
            repo.save(editReview);
            session.setAttribute("reviews", null);
        }
        return "redirect:/reviews";
    }
    
}
