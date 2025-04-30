package com.my.blog.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.my.blog.domain.model.Post;

//아웃바운드 포트
public interface PostRepository {
	Post save(Post post);
	Optional<Post> findById(Long id);
	List<Post> findAll();
	void delete(Long id);

}
