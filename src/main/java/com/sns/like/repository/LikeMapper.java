package com.sns.like.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
//	public int selectLikeCountByPostIdUserId(
//		@Param("postId") int postId,
//		@Param("userId") int userId);
//	
//	public int selectLikeByPostId(int postId);
//	public int selectLikeByPostIdUserId(
//			@Param("postId") int postId,
//			@Param("userId") int userId);
	
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId,
			@Param("userId") Integer userId);

	public void insertLike(
			@Param("postId") int postId,
			@Param("userId") int userId);

	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId,
			@Param("userId") int userId);
	
	
}
