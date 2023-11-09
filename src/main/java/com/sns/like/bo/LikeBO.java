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
			if (likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
				// 삭제
				likeMapper.deleteLikeByPostIdUserId(postId, userId);
			} else {
				// 추가
				likeMapper.insertLike(postId, userId);
			}
		}
		
		// input : 어느글에		output: 몇개(int)
		public int findLikeByPostId(int postId) {
			return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
		}
		
		// input : 어느글에 누가		output : 채워진지 여부(boolean)
		public boolean filledLike(int postId, Integer userId) {
			// 비로그인
			if(userId == null) {
				return false;
			}
			
			// 로그인
			// 0보다 큰 경우 있음(채운다 true) 그렇지 않으면 false(빈하트)
			return likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0;
		}
		
		// input: 어느글에		output:X
		public void deleteLikeByPostId(int postId) {
			likeMapper.deleteLikeByPostIdUserId(postId, null);
		}
}
