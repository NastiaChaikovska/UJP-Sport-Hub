package com.softserve.edu.sporthubujp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.softserve.edu.sporthubujp.dto.ArticleDTO;
import com.softserve.edu.sporthubujp.service.ArticleService;

@RestController
@RequestMapping("api/v1/articles")

public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER', 'ADMIN')")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
            articleService.getArticleById(id));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") String articleId)
    {
        articleService.deleteArticleById(articleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
