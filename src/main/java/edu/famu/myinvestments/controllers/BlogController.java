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
@RequestMapping("/mysmartinvestments/blog")
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
    @GetMapping("/{user}")
    public String getPosts(@PathVariable("user")String user, Model model) throws ExecutionException, InterruptedException {
        List<Post> posts = userService.getPostByUserId(user);
        model.addAttribute("posts", posts);
        return "home";
    }


    /*
     * Post Detail Page
     * @param post id
     * @param model
     * @return name of the view
     */
    @GetMapping("/user/post/{id}")
    //HOW WOULD I USE THIS TO DISPLAY ALL POSTS ON THE SCREEN . IN SQUARE SHAPE SIMILAR TO THE MOVIES
    //LOOP THROUGH THE GET POSTS IN HTML?
    public String getPost(@PathVariable("id") String id, Model model) throws ExecutionException, InterruptedException {
        Post post = postService.getPostById(id);
        List<Comment> comments = postService.getPostComments(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post";
    }

    //Create New post
    @GetMapping("/new/post")
    public String newPost(){
        return "post";
    }

    //Save Data
    @PostMapping(path="/new/post/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String savePost(RestPost post) throws ExecutionException, InterruptedException {
        postService.createPost(post);
        return"redirect:/";
    }

    //Create New comment
    @GetMapping("/new/comment")
    public String newComment(){
        return "comment";
    }

    //Save Data
    @PostMapping(path="/new/comment/save", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String savePost(RestComment comment) throws ExecutionException, InterruptedException {
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

