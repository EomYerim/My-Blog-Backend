package com.my.blog.adapter.out.pesistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.my.blog.domain.model.Post;
import com.my.blog.domain.port.out.PostRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PostPersistenceAdapter implements PostRepository {

	private final PostJpaRepository postJpaRepository;

	@Override
	public Post save(Post post) {
		PostEntity entity = mapToEntity(post);
		PostEntity savedEntity = postJpaRepository.save(entity);
		return mapToDomain(savedEntity);
	}

	@Override
	public Optional<Post> findById(Long id) {
		return postJpaRepository.findById(id)
			.map(this::mapToDomain);
	}

	@Override
	public List<Post> findAll() {
		return postJpaRepository.findAll().stream()
			.map(this::mapToDomain)
			.toList();
	}

	@Override
	public void delete(Long id) {
		postJpaRepository.deleteById(id);
	}

	private PostEntity mapToEntity(Post post) {
		return PostEntity.builder()
			.id(post.getId())
			.title(post.getTitle())
			.content(post.getContent())
			.author(post.getAuthor())
			.createdAt(post.getCreatedAt())
			.updatedAt(post.getUpdatedAt())
			.build();
	}

	private Post mapToDomain(PostEntity entity) {
		return Post.builder()
			.id(entity.getId())
			.title(entity.getTitle())
			.content(entity.getContent())
			.author(entity.getAuthor())
			.createdAt(entity.getCreatedAt())
			.updatedAt(entity.getUpdatedAt())
			.build();
	}
}