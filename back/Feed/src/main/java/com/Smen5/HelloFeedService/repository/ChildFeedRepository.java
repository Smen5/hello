package com.Smen5.HelloFeedService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Smen5.HelloFeedService.entity.ChildFeed;
import com.Smen5.HelloFeedService.entity.Feed;

public interface ChildFeedRepository extends JpaRepository<ChildFeed,Long>{
	List<ChildFeed> findAllByParent(Feed parent);
}
