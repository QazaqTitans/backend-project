package kz.reserve.backend.controller;

import kz.reserve.backend.payload.request.CommentRequest;
import kz.reserve.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comment")
@PreAuthorize("hasAuthority('client')")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping()
    public ResponseEntity<?> getComments(){return commentService.getComments();}

    @PostMapping()
    public ResponseEntity<?> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        return commentService.addComment(commentRequest);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTable(@Valid @Min(1) @PathVariable Long id, @Valid @RequestBody CommentRequest commentRequest) {
        return commentService.updateComment(id, commentRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTable(@Valid @Min(1) @PathVariable Long id) {
        return commentService.deleteComment(id);
    }


}
