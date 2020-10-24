package net.springBootAuthentication.springBootAuthentication.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.asm.Advice.Local;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomComment;
import net.springBootAuthentication.springBootAuthentication.customModel.CustomForum;
import net.springBootAuthentication.springBootAuthentication.customModel.Forum;
import net.springBootAuthentication.springBootAuthentication.exception.ResourceNotFoundException;
import net.springBootAuthentication.springBootAuthentication.model.CommentLikesModel;
import net.springBootAuthentication.springBootAuthentication.model.CommentsModel;
import net.springBootAuthentication.springBootAuthentication.model.ForumPostModel;
import net.springBootAuthentication.springBootAuthentication.model.ForumTransactionsModel;
import net.springBootAuthentication.springBootAuthentication.repository.CommentLikesRepository;
import net.springBootAuthentication.springBootAuthentication.repository.CommentsRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ForumPostRepository;
import net.springBootAuthentication.springBootAuthentication.repository.ForumTransactionsRepository;

@RestController
@RequestMapping("/ltp")
public class ForumController {
    @Autowired
    private ForumPostRepository forumPostRepository;
    
    @Autowired
    private ForumTransactionsRepository forumTransactionRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private CommentLikesRepository commentsLikesRepository;

    @PostMapping("/post")
    public ResponseEntity<?> post(@RequestBody Forum entity){
        ForumTransactionsModel fModel = new ForumTransactionsModel();
        ForumPostModel forumPostModel =new ForumPostModel();
        LocalDate date = LocalDate.now();

        forumPostModel.setDescription(entity.getDescription());
        forumPostModel.setTopic(entity.getTopic());
        forumPostModel.setDatePosted(date);
        forumPostRepository.save(forumPostModel);
        forumPostRepository.flush();

        // System.out.println(entity.getAccountId());
        fModel.setAccountId(entity.getAccountId());
        fModel.setPostId(forumPostModel.getId());
        fModel.setDate(date);
        forumTransactionRepository.save(fModel);

        List<Object> list = new ArrayList<>();

        list.add(forumPostModel);
        list.add(fModel);


        return ResponseEntity.ok(list);
    }

    @GetMapping("/getPost")
    public ResponseEntity<?> getPost(){
       List<CustomForum> forum = forumTransactionRepository.getPost();
        return ResponseEntity.ok(forum);
    }

    @PostMapping(value = "/getForumDetails")
    public ResponseEntity<?> postMethodName(@RequestBody ForumTransactionsModel entity) throws ResourceNotFoundException {
        try {
            Long id = entity.getPostId();
            System.out.println(id);
            List<CustomForum> details = forumTransactionRepository.getForumDetails(id);
            System.out.println(details);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.FORBIDDEN);
        }

    }
    @PostMapping(value = "/saveComment")
    public ResponseEntity<?>postSaveMethod(@RequestBody CustomComment entity) throws ResourceNotFoundException{
        Long id = entity.getPostId();
        CommentsModel comments = new CommentsModel();
        ForumTransactionsModel forum = new ForumTransactionsModel();
        LocalDate date = LocalDate.now();

        comments.setComment(entity.getComment());
        comments.setCommentById(entity.getCommentedById());
        comments.setDateCommented(date);
        commentsRepository.save(comments);
        commentsRepository.flush();
        
        ForumTransactionsModel tModel = forumTransactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found"));
        tModel.getCommentId();
        if(tModel.getCommentId() == null){
            tModel.setCommentId(comments.getId());
            // tModel.setAccountId(tModel.getAccountId());
            // tModel.setDate(tModel.getDate());
            // tModel.setPostId(tModel.getPostId());
            // System.out.println(String.format("%d,%d,%d", comments.getId(), tModel.getAccountId(), tModel.getPostId()));
            System.out.println("null");
            forumTransactionRepository.save(tModel);
        }else{
            forum.setCommentId(comments.getId());
            forum.setPostId(tModel.getPostId());
            forum.setDate(date);
            forum.setAccountId(entity.getCommentedById());
            System.out.println("naa");
            forumTransactionRepository.save(forum);
        }
        // forumTransactionRepository.save(forum);

        return ResponseEntity.ok(comments);
    }

    @PostMapping(value="/getComment")
    public ResponseEntity<?> getComment(@RequestBody CustomComment entity) {
        Long id = entity.getPostId();

        List<CustomForum> list = forumTransactionRepository.getComment(id);
        
        return ResponseEntity.ok(list);
    }
    
    @PostMapping(value="/like")
    public ResponseEntity<?> addLIke(@RequestBody CommentLikesModel entity) {
        Long id = entity.getCommentId();
        
        CommentLikesModel likes = new CommentLikesModel();
        LocalDate date = LocalDate.now();
        likes.setCommentId(entity.getCommentId());
        likes.setLikeById(entity.getLikeById());
        likes.setDateCommented(date);
        commentsLikesRepository.save(likes);
        
        return ResponseEntity.ok("");
    }
}