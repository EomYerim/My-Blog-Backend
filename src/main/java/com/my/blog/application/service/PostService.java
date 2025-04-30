package com.my.blog.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.my.blog.domain.model.Post;
import com.my.blog.domain.port.in.PostUseCase;
import com.my.blog.domain.port.out.PostRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

//애플리케이션 서비스 (유스케이스 구현)

@Service
@RequiredArgsConstructor
@Transactional
public class PostService implements PostUseCase {

	private final PostRepository postRepository;

	@Override
	public Post createPost(Post post) {
		Post newPost = Post.builder()
			.title(post.getTitle())
			.content(post.getContent())
			.author(post.getAuthor())
			.createdAt(LocalDateTime.now())
			.updatedAt(LocalDateTime.now())
			.build();
		return postRepository.save(newPost);
	}

	@Override
	public Optional<Post> getPostById(Long id) {
		return postRepository.findById(id);
	}

	@Override
	public List<Post> getAllPosts() {
		return postRepository.findAll();
	}

	@Override
	public Post updatePost(Long id, Post post) {
		Post existingPost = postRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다: " + id));

		existingPost.update(post.getTitle(), post.getContent());
		return postRepository.save(existingPost);
	}

	@Override
	public void deletePost(Long id) {
		postRepository.delete(id);

	}
}
