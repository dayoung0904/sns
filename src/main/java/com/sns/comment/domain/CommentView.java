package com.sns.comment.domain;

import com.sns.user.Entity.UserEntity;

import lombok.Data;

// 한 개의 댓글
@Data
public class CommentView {
	// 댓글 내용, 댓글 쓴이
	
	private UserEntity user;
	
	private Comment comment;

}
