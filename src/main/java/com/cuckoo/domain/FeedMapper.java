package com.cuckoo.domain;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedMapper {

    @Insert("INSERT INTO feed_table (uid,author,content,created) VALUES (#{uid},#{author},#{content},#{created})")
    void addFeed(Feed feed);

    @Delete("DELETE FROM feed_table WHERE id = #{id}")
    void deleteFeedById(Integer id);

    @Select("SELECT * FROM feed_table")
    List<Feed> getAllFeeds();

    @Select("SELECT * FROM feed_table WHERE id = #{id}")
    Feed getFeedById(Integer id);

    @Select("SELECT * FROM feed_table WHERE uid = #{uid}")
    List<Feed> getFeedByUid(Integer uid);

}
