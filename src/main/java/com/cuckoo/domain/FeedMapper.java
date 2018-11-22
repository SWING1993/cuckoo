package com.cuckoo.domain;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedMapper {

    @Insert("INSERT INTO feed_table (uid,author,avatar,content,imageUrls,created) VALUES (#{uid},#{author},#{avatar},#{content},#{imageUrls},#{created})")
    void addFeed(Feed feed);

    @Delete("DELETE FROM feed_table WHERE id = #{id}")
    void deleteFeedById(Long id);

    @Select("SELECT * FROM feed_table")
    List<Feed> getAllFeeds();

    @Select("SELECT * FROM feed_table WHERE id = #{id}")
    Feed getFeedById(Long id);

    @Select("SELECT * FROM feed_table WHERE uid = #{uid}")
    List<Feed> getFeedByUid(Long uid);

}
