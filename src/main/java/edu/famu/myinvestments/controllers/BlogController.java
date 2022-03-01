package edu.famu.myinvestments.controllers;

import edu.famu.myinvestments.models.*;
import edu.famu.myinvestments.services.PostService;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;


@Controller
public class BlogController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public BlogController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /*
     * Home Page
     * @param user id
     * @param model. Front end value passed by default to the controller
     * @return name of the view (html file)
     */

    //
    @GetMapping("blog/")
    public String getBlogPage(){
        return "blog";
    }

    @GetMapping("blog/{user}")
    public String getPosts(@PathVariable("user") String user, Model model) throws ExecutionException, InterruptedException {
        //The model is essentially a Map with unique keys. You really should define unique keys:
        //These keys will be
        model.addAttribute("posts", userService.getPostByUserId(user));
        model.addAttribute("username", userService.getUserByID(user).getUsername());
        //model.addAttribute("firstName", .getFirstName());
        return "blog";
    }



    /*
     * Post Detail Page
     * @param post id
     * @param model
     * @return name of the view
     */

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable("id") String id, Model model) throws ExecutionException, InterruptedException {
        Post post = postService.getPostById(id);
        List<Comment> comments = postService.getPostComments(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post";
    }


    @GetMapping("/post/comment/{id}")
    public String getCommentById(@PathVariable("id") String id, Model model) throws ExecutionException, InterruptedException {
        User user = userService.getUserByID(id);
        Comment comment = postService.getCommentById(id);
        model.addAttribute("user", user);
        model.addAttribute("commentId", comment.getId());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("content", comment.getContent());
        return "post";
    }

    //Create New post
    @GetMapping("/new/post")
    public String newPost(){
        return "blog";
    }

    //Save Data
    @PostMapping(path="/new/post/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String savePost(RestPost Post) throws ExecutionException, InterruptedException {
        postService.createPost(Post);
        return"redirect:/";
    }

    //Create New comment
    @GetMapping("/new/comment")
    public String newComment(){
        return "post";
    }

    //Save Data
    @PostMapping(path="/new/comment/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String saveComment(RestComment comment) throws ExecutionException, InterruptedException {
        postService.createComment(comment);
        return"redirect:/";
    }

    //HOWWC Would I return post? or "redirect:/"
    @DeleteMapping("/post/{id}")
    public String deleteComment(String id, Model model) throws ExecutionException, InterruptedException {
        postService.deletePostComment(id);
        return "post";

    }
}

