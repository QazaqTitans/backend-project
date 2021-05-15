package kz.reserve.backend.payload.response;


import kz.reserve.backend.domain.Comment;

import java.util.List;

public class CommentResponse {
    private List<Comment> commentList;

    public CommentResponse(List<Comment> comments) {
        this.commentList = comments;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> comments) {
        this.commentList = comments;
    }
}
