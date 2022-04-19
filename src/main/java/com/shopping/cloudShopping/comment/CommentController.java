package com.shopping.cloudShopping.comment;

import com.shopping.cloudShopping.ReturnResult;
import com.shopping.cloudShopping.user.User;
import com.shopping.cloudShopping.user.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
    @Resource
    CommentMapper commentMapper;
    @Resource
    UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/addComment")
    public ReturnResult addFood(@RequestBody Comment comment) {
        if(commentMapper.getCommentByUserIdAndFoodId(comment.getUserId(), comment.getFoodId())!=null)
            return new ReturnResult(ReturnResult.SUCCESS,"您已评论");
        comment.setTime(new Date());
        commentMapper.addComment(comment);
       return new ReturnResult(ReturnResult.SUCCESS,"添加成功");
    }

    @ResponseBody
    @RequestMapping("/getComments")
    public List<Comment> getComments(Integer foodId) {
        List<Comment> comments = commentMapper.getCommentByFoodId(foodId);
        for(Comment comment:comments){
            if(comment.getUserId()==null)
                continue;
            User user = userMapper.getUserById(comment.getUserId());
            comment.setUser(user);
        }
        return comments;
    }
}
