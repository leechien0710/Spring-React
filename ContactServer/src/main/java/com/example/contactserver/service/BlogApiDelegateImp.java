package com.example.contactserver.service;

import com.example.contactserver.api.BlogsApiDelegate;
import com.example.contactserver.entity.BlogEntity;
import com.example.contactserver.mapper.BlogMapper;
import com.example.contactserver.model.Blog;
import com.example.contactserver.repository.BlogRepository;
import com.example.contactserver.validator.BlogValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BlogApiDelegateImp implements BlogsApiDelegate {

    @Autowired private BlogRepository blogRepository;
    @Autowired private BlogMapper blogMapper;
    @Autowired private BlogValidator blogValidator;

    @Override
    public ResponseEntity<List<Blog>> blogsGet() {
        return new ResponseEntity<>(blogMapper.MapperBlogListFromBlogEntityList(blogRepository.findAll()), HttpStatus.OK); // Updated method name
    }

    @Override
    public ResponseEntity<Void> blogsIdDelete(Integer id) {
        blogValidator.ValidateBlogExit(id);
        blogRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Blog> blogsIdGet(Integer id) {
        blogValidator.ValidateBlogExit(id);
        return new ResponseEntity<>(blogMapper.MapperBlogFromBlogEntity(blogRepository.getById(id)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Blog> blogsIdPut(Integer id, Blog blog) {
        blogValidator.ValidateBlogExit(id);
        blogValidator.ValidateAddBlog(blog);
        BlogEntity blogSave = blogRepository.save(blogMapper.MapperBlogEntityFromBlog(id,blog));
        return new ResponseEntity<>(blogMapper.MapperBlogFromBlogEntity(blogSave), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Blog> blogsPost(Blog blog) {
        blogValidator.ValidateAddBlog(blog);
        BlogEntity blogEntity = blogMapper.MapperBlogEntityFromBlog(blog);
        return new ResponseEntity<>(blogMapper.MapperBlogFromBlogEntity(blogRepository.save(blogEntity)), HttpStatus.CREATED);
    }
}
