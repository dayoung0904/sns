package com.sns.comment.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository {

	public void insertComment (int postId, int userId, String content);
}
