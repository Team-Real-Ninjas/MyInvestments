package edu.famu.myinvestments.controllers;

import edu.famu.myinvestments.models.Comment;
import edu.famu.myinvestments.models.Post;
import edu.famu.myinvestments.models.RestComment;
import edu.famu.myinvestments.models.RestPost;
import edu.famu.myinvestments.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    public PostService postService;

    @Autowired
    public PostRestController(PostService postService)
    {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    //GET SPECIFIC POST BASED OFF OF POSTID
    public Post getPostById(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return postService.getPostById(id);
    }

    @GetMapping("/{id}/comments")
    //GET COMMENTS BASED OFF A POSTID
    public List<Comment> getCommentByPost(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return postService.getPostComments(id);
    }

    @PostMapping(path = "/")
    //CREATE NEW POST
    public String createPost(@RequestBody RestPost post) throws ExecutionException, InterruptedException {
        return postService.createPost(post);
    }
    @PostMapping(path = "/comments/")
    //CREATES A NEW COMMENT
    public String createComment(@RequestBody RestComment comment) throws ExecutionException, InterruptedException {
        return postService.createComment(comment);
    }

    //HOWWC TO CALL DELETING MAP


    @DeleteMapping ("/comments/{id}")
    //DELETES COMMENT BASED OFF OF COMMENT ID
    public Boolean deletePostComment(@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return postService.deletePostComment(id);
    }

}