package ru.cft.clorental.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.clorental.model.request_forms.CardMessage;
import ru.cft.clorental.repos.model.CardEntity;
import ru.cft.clorental.service.FeedService;

import java.util.List;

@RestController
@RequestMapping("feed")
@Api(value = "feed")
public class Feed {
    final FeedService feedService;
    @Autowired
    public Feed(FeedService feedService){
        this.feedService = feedService;
    }

    @GetMapping
    @ApiOperation("cards getting")
    public ResponseEntity<List<CardMessage>> getCardsByIdRange(@Param("from") Long from,
                                                               @Param("to") Long to){
        return ResponseEntity.ok(feedService.getFromTo(from, to));
    }
}
