package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.like.bo.LikeBO;
import com.sns.post.Entity.PostEntity;
import com.sns.post.bo.PostBO;
import com.sns.timeline.domain.CardView;
import com.sns.user.Entity.UserEntity;
import com.sns.user.bo.UserBO;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	// input: userId(비로그인/로그인 허용 null도 허용)		output: List<CardView>
	public List<CardView> generateCardViewList(Integer userId){
		List<CardView> cardViewList = new ArrayList<>();
		
		// 글 목록을 가져온다. List<PostEntity>
		List<PostEntity> postEntityList = postBO.getPostList();
		
		// 글 목록 반복문 순회
		// postEntity => CardView	=> cardViewList에 담는다.
		for(PostEntity post : postEntityList) {
			// post 하나에 대응되는 하나의 카드를 만든다.
			CardView cardView = new CardView();
			
			// 글 1개
			cardView.setPost(post);
			
			// 글쓴이 정보
			UserEntity user = userBO.getUserEntityById(post.getUserId());
			cardView.setUser(user);
			
			// 댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			cardView.setCommentList(commentList);
			
			// 좋아요 갯수
			int likeCount = likeBO.findLikeByPostId(post.getId());
			cardView.setLikeCount(likeCount);
			
			// 내가 좋아요 눌렀는지 여부
			// false: 비로그인 혹은 누리지 않았을 때
			boolean filledLike = likeBO.filledLike(post.getId(), userId);
			cardView.setFilledLike(filledLike);
			
			// ★★★★★★ 마지막에 cardViewList에 꼭 넣어준다.
			cardViewList.add(cardView);
		}
		
		return cardViewList;
	}
}
