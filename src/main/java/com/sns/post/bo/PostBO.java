package com.sns.post.bo;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.Entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private FileManagerService fileManager;
	
	//input: X		output: PostEntity
	public List<PostEntity> getPostList(){
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public PostEntity addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = null;

		// 이미지가 있으면 업로드 후 imagePath를 받아옴
		if (file != null) {
			imagePath = fileManager.saveFile(userLoginId, file);
		}

		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	// 글 삭제
	@Transactional
	public void deletePostById(int postId) {
		// 기존 글 가져오기
		PostEntity post = postRepository.findById(postId).orElse(null);
		if(post == null) {
			logger.info("[글 삭제] post가 null. postId:{}", postId);
			return;
		}
		// 기존글이 null이 아닌 경우 이미지 삭제
		if(post != null) {
			fileManager.deleteFile(post.getImagePath());
		}
		// db 글 삭제
			postRepository.deleteById(postId);
			
		// db 댓글 삭제
			commentBO.deleteCommentByPostId(postId);
			
		// db 좋아요 삭제
			likeBO.deleteLikeByPostId(postId);
	}
}
