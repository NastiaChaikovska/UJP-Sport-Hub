package com.softserve.edu.sporthubujp.controller;

import java.security.Principal;
import java.util.List;
import com.softserve.edu.sporthubujp.dto.ArticleListDTO;
import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.entity.Article;
import com.softserve.edu.sporthubujp.entity.User;
import com.softserve.edu.sporthubujp.service.ArticleService;
import com.softserve.edu.sporthubujp.dto.CommentDTO;
import com.softserve.edu.sporthubujp.service.CommentService;
import com.softserve.edu.sporthubujp.service.UserService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("api/v1")
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, CommentService commentService, UserService userService) {
        this.articleService = articleService;
        this.commentService = commentService;
        this.userService = userService;
    }


    @GetMapping("/articles/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        log.info("Get article by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
            articleService.getArticleById(id));
    }

    @GetMapping("/{id}/comments")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<CommentDTO>> getAllCommentByArticleId(@PathVariable String id) {
        log.info("Get all comments by article id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
            commentService.getAllCommentByArticleId(id));
    }
    @DeleteMapping("/articles/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") String articleId) {
        log.info("Delete article by id {}", articleId);
        articleService.deleteArticleById(articleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/articles/subscription")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<ArticleDTO>>
    getAllArticlesBySubscription(@NotNull Principal principal) {
        String email= principal.getName();
        log.info("Get all articles of the user with an email under {} subscription",email);
        String idUser = userService.findUserByEmail(email);
        log.info("Id user = {}",idUser);
        return ResponseEntity.status(HttpStatus.OK).body(
            articleService.getAllArticlesBySubscription(idUser));
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Article> updateArticle(@RequestBody Article newArticle,
        @PathVariable("id") String id) {
        log.info("Update article by id {}", id);
        articleService.updateArticle(newArticle, id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin/articles")
    public ResponseEntity<List<ArticleDTO>> getAllArticles() {
        log.info("Get all article");
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.getAllArticles());
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin/articles/category_id/{id}")
    public ResponseEntity<List<ArticleListDTO>>
    getAllArticlesByCategoryId(@PathVariable String id, Pageable pageable) {
        log.info("Get all articles by category id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.getAllArticlesByCategoryId(id,pageable));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/admin/articles/category_id/{id}/isactive/{isactive}")
    public ResponseEntity<List<ArticleListDTO>>
    getAllArticlesByCategoryIdAndIsActive(@PathVariable String id, @PathVariable boolean isactive, Pageable pageable) {
        log.info("Get all articles by category id {} and if article is active", id);
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.getAllArticlesByCategoryIdAndIsActive(id, isactive, pageable));
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    @GetMapping("/category_id")
//    public ResponseEntity<List<ArticleListDTO>> getAllArticlesByCategoryId(@RequestParam String id, @RequestParam boolean isactive, Pageable pageable) {
//        return ResponseEntity.status(HttpStatus.OK).body(
//                articleService.getAllArticlesByCategoryIdAndIsActive(id, isactive, pageable));
//    }

}
