package com.cuckoo.service;

import com.cuckoo.domain.Feed;
import com.cuckoo.domain.FeedMapper;
import com.cuckoo.domain.User;
import com.cuckoo.domain.UserMapper;
import com.cuckoo.utils.RestResult;
import com.cuckoo.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private UserMapper userMapper;

    public UserMapper getUserMapper() {
        return userMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 添加Feed
    @PostMapping(value = "/add")
    public RestResult<Feed> addFeed(@ModelAttribute Feed feed) throws Exception {
        long uid = feed.getUid();
        User user = userMapper.getUserById(uid);
        if (user == null) {
            return RestResultGenerator.genErrorResult("该用户不存在");
        }
        feed.setAuthor(user.getNickname());
        feed.setAvatar(user.getAvatar());
        feedMapper.addFeed(feed);
        return RestResultGenerator.genSuccessResult();
    }

    // 删除Feed
    @DeleteMapping(value = "/delete")
    public RestResult<Feed> delete(@RequestParam HashMap requestMap) throws Exception {
        Long id = Long.parseLong(requestMap.get("id").toString());
        feedMapper.deleteFeedById(id);
        return RestResultGenerator.genSuccessResult();
    }

    // 根据Id查询Feed
    @PostMapping(value = "/getById")
    public RestResult<Feed> getById(@RequestParam HashMap requestMap) throws Exception {
        Long id = Long.parseLong(requestMap.get("id").toString());
        Feed feed = feedMapper.getFeedById(id);
        return RestResultGenerator.genSuccessResult(feed);
    }

    // 查询用户所有的Feed
    @PostMapping(value = "/getByUid")
    public RestResult<List<Feed>> getByUid(@RequestParam HashMap requestMap) throws Exception {
        Long uid = Long.parseLong(requestMap.get("uid").toString());
        List<Feed> feeds = feedMapper.getFeedByUid(uid);
        return RestResultGenerator.genSuccessResult(feeds);
    }

    // 查询所有Feed
    @GetMapping(value = "/getAll")
    public RestResult<List<Feed>> getAll() throws Exception {
        List<Feed> feeds = feedMapper.getAllFeeds();
        return RestResultGenerator.genSuccessResult(feeds);
    }
}
