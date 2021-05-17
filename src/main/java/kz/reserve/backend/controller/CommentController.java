package kz.reserve.backend.controller;

import kz.reserve.backend.domain.Restaurant;
import kz.reserve.backend.payload.request.CommentRequest;
import kz.reserve.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/api/comment")

public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getComments(@Valid @Min(1) @PathVariable Long id){
        return commentService.getComments(id);}

    @PreAuthorize("hasAuthority('client')")
    @PostMapping()
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        return commentService.addComment(commentRequest);
    }



}
