package com.my.blog.domain.port.in;

//포트 (인터페이스)

import java.util.List;
import java.util.Optional;

import com.my.blog.domain.model.Post;

public interface PostUseCase {
	Post createPost(Post post);
	Optional<Post> getPostById(Long id);
	List<Post> getAllPosts();
	Post updatePost(Long id,Post post);
	void deletePost(Long id);
}
