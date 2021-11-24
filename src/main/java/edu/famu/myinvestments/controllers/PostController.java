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

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class PostController {

    private PostService postService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /*
     * Home Page
     * @param user id
     * @param model. Front end value passed by default to the controller
     * @return name of the view (html file)
     */

    @GetMapping("/home/{user}")
    public String getInvestments(@PathVariable("user")String user, Model model) throws ExecutionException, InterruptedException {
        List<Investments> investments = userService.getInvestmentByUserId(user);
        model.addAttribute("investments", investments);
        return "home";
    }

    @GetMapping("/blog/{user}")
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
    @GetMapping("/post/{id}")
    public String getPost(@PathVariable("id") String id, Model model) throws ExecutionException, InterruptedException {
        Post post = postService.getPostById(id);
        List<Comment> comments = postService.getPostComments(id);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post";
    }

}

