package com.my.blog.adapter.in.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.blog.domain.model.Post;
import com.my.blog.domain.port.in.PostUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostUseCase postUseCase;

	@PostMapping
	public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
		Post post = Post.builder()
			.title(request.title())
			.content(request.content())
			.author(request.author())
			.build();

		Post savedPost = postUseCase.createPost(post);
		return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> getPostById(@PathVariable Long id) {
		return postUseCase.getPostById(id)
			.map(post -> new ResponseEntity<>(post, HttpStatus.OK))
			.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<Post>> getAllPosts() {
		List<Post> posts = postUseCase.getAllPosts();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostRequest request) {
		Post post = Post.builder()
			.title(request.title())
			.content(request.content())
			.build();

		try {
			Post updatedPost = postUseCase.updatePost(id, post);
			return new ResponseEntity<>(updatedPost, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable Long id) {
		postUseCase.deletePost(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
