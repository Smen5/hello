package com.Smen5.HelloFeedService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Smen5.HelloFeedService.entity.Feed;

public interface FeedRepository extends JpaRepository<Feed,Long>{

}
