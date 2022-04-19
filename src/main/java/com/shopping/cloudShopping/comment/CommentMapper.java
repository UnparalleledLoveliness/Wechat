package com.shopping.cloudShopping.comment;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> getCommentByFoodId(Integer id);
    void addComment(Comment comment);
    List<Comment> getCommentByUserIdAndFoodId(Integer userId,Integer foodId);
}
