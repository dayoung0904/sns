package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.repository.LikeMapper;

@Service
public class LikeBO {
	
	@Autowired
	private LikeMapper likeMapper;
	
		// input: 누가, 어느글에
		// output: X
		public void likeToggle(int postId, int userId) {
			// 셀렉트 => count(*)
			if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
				// 삭제
				likeMapper.deleteLikeByPostIdUserId(postId, userId);
			} else {
				// 추가
				likeMapper.insertLike(postId, userId);
			}
		}
		
		// input : 어느글에		output: 몇개
		public int findLikeByPostId(int postId) {
			return likeMapper.selectLikeByPostId(postId);
		}
		
		// input : 어느글에 누가		output : boolean
		public boolean findLikeByPostIdUserId(int postId, int userId) {
			
			
			return
		}
}
