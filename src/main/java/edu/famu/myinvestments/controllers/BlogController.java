package edu.famu.myinvestments.controllers;

import edu.famu.myinvestments.models.Comment;
import edu.famu.myinvestments.models.Investments;
import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.services.PostService;
import edu.famu.myinvestments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    //  public List<Post> getPostByUserId(String id) WOULD I RETURN THIS FOR A BLOG
    @GetMapping("/user/{user}")
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

}

