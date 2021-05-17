package kz.reserve.backend.service;

import kz.reserve.backend.domain.Comment;
import kz.reserve.backend.domain.Restaurant;
import kz.reserve.backend.domain.User;
import kz.reserve.backend.payload.request.CommentRequest;
import kz.reserve.backend.payload.response.CommentResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.repository.CommentRepository;
import kz.reserve.backend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ServiceUtils serviceUtils;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public ResponseEntity<?> getComments(Long id) {
//        User user = serviceUtils.getPrincipal();
        Restaurant restaurant = restaurantRepository.getOne(id);
        List<Comment> commentList = commentRepository.findAllByRestaurant(restaurant);
        return ResponseEntity.ok(new CommentResponse(commentList));
    }
    private void commentCreator(CommentRequest commentRequest, Comment comment){
        Restaurant restaurant = restaurantRepository.getOne(commentRequest.getRestaurantId());
        User user = serviceUtils.getPrincipal();
        comment.setStar(commentRequest.getStar());
        comment.setText(commentRequest.getText());
        comment.setRestaurant(restaurant);
        comment.setClient(user);

        commentRepository.save(comment);
    }
    public ResponseEntity<?> addComment(CommentRequest commentRequest) {
        try {
            Comment comment = new Comment();
            User user = serviceUtils.getPrincipal();
            comment.setRestaurant(user.getRestaurant());
            commentCreator(commentRequest,comment);


       } catch (Exception e) {
          return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }


}
