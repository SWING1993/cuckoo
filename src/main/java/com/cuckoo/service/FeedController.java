package com.cuckoo.service;

import com.cuckoo.domain.Feed;
import com.cuckoo.domain.FeedMapper;
import com.cuckoo.utils.RestResult;
import com.cuckoo.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value="/feed")
public class FeedController {

    private FeedMapper feedMapper;

    public FeedMapper getFeedMapper() {
        return feedMapper;
    }

    @Autowired
    public void setFeedMapper(FeedMapper feedMapper) {
        this.feedMapper = feedMapper;
    }

    @RequestMapping(value = "/add",method= RequestMethod.POST)
    public RestResult<Feed> addFeed(@ModelAttribute Feed feed) throws Exception {
        feed.setCreated(new Date());
        feedMapper.addFeed(feed);
        return RestResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/delete",method= RequestMethod.POST)
    public RestResult<Feed> delete(@RequestParam HashMap requestMap) throws Exception {
        Integer id = Integer.parseInt(requestMap.get("id").toString());
        feedMapper.deleteFeedById(id);
        return RestResultGenerator.genSuccessResult();
    }

    @RequestMapping(value = "/getById",method= RequestMethod.POST)
    public RestResult<Feed> getById(@RequestParam HashMap requestMap) throws Exception {
        Integer id = Integer.parseInt(requestMap.get("id").toString());
        Feed feed = feedMapper.getFeedById(id);
        return RestResultGenerator.genSuccessResult(feed);
    }

    @RequestMapping(value = "/getByUid",method= RequestMethod.POST)
    public RestResult<List<Feed>> getByUid(@RequestParam HashMap requestMap) throws Exception {
        Integer uid = Integer.parseInt(requestMap.get("uid").toString());
        List<Feed> feeds = feedMapper.getFeedByUid(uid);
        return RestResultGenerator.genSuccessResult(feeds);
    }


    @RequestMapping(value = "/getAll",method= RequestMethod.POST)
    public RestResult<List<Feed>> getAll() throws Exception {
        List<Feed> feeds = feedMapper.getAllFeeds();
        return RestResultGenerator.genSuccessResult(feeds);
    }

}
