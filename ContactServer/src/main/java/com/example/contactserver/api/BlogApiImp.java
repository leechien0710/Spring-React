package com.example.contactserver.api;

import com.example.contactserver.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@Service
public class BlogApiImp implements BlogsApi {
    @Autowired private BlogsApiDelegate blogsApiDelegate;

    @Override
    public BlogsApiDelegate getDelegate() {
        return this.blogsApiDelegate;
    }
    @Override
    public ResponseEntity<List<Blog>> blogsGet() {
        return blogsApiDelegate.blogsGet();
    }

    @Override
    public ResponseEntity<Void> blogsIdDelete(Integer id) {
        return blogsApiDelegate.blogsIdDelete(id);
    }

    @Override
    public ResponseEntity<Blog> blogsIdGet(Integer id) {
        return blogsApiDelegate.blogsIdGet(id);
    }

    @Override
    public ResponseEntity<Blog> blogsIdPut(Integer id, Blog blog) {
        return blogsApiDelegate.blogsIdPut(id, blog);
    }

    @Override
    public ResponseEntity<Blog> blogsPost(Blog blog) {
        return blogsApiDelegate.blogsPost(blog);
    }
}
