package com.example.springbootnewsportal.mappers.comment;
import com.example.springbootnewsportal.entities.Comment;
import com.example.springbootnewsportal.web.models.comments.CommentListResponse;
import com.example.springbootnewsportal.web.models.comments.CommentRequest;
import com.example.springbootnewsportal.web.models.comments.CommentResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(CommentMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    Comment requestToComment(CommentRequest commentRequest);

    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, CommentRequest commentRequest);

    default CommentResponse commentToResponse(Comment comment) {
        return new CommentResponse(comment.getComment(), comment.getUserCreatorComment().getName());
    }

    default CommentListResponse commentListToCommentListResponse(List<Comment> comments) {
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setComments(comments.stream().map(this::commentToResponse).collect(Collectors.toList()));
        return commentListResponse;
    }
}