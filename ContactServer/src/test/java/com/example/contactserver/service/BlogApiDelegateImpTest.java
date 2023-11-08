package com.example.contactserver.service;

import com.example.contactserver.api.BlogsApiDelegate;
import com.example.contactserver.entity.BlogEntity;
import com.example.contactserver.mapper.BlogMapper;
import com.example.contactserver.model.Blog;
import com.example.contactserver.repository.BlogRepository;
import com.example.contactserver.validator.BlogValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BlogApiDelegateImpTest {

    @InjectMocks
    private BlogApiDelegateImp blogApiDelegate;

    @Mock
    private BlogRepository blogRepository;

    @Mock
    private BlogMapper blogMapper;

    @Mock
    private BlogValidator blogValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBlogsGet() {
        List<BlogEntity> blogEntities = new ArrayList<>();
        when(blogRepository.findAll()).thenReturn(blogEntities);

        List<Blog> blogs = new ArrayList<>();
        when(blogMapper.MapperBlogListFromBlogEntityList(blogEntities)).thenReturn(blogs);

        ResponseEntity<List<Blog>> response = blogApiDelegate.blogsGet();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blogs, response.getBody());

        verify(blogRepository, times(1)).findAll();
        verify(blogMapper, times(1)).MapperBlogListFromBlogEntityList(blogEntities);
    }

    @Test
    public void testBlogsIdDelete() {
        int id = 1;
        when(blogRepository.existsById(id)).thenReturn(true);

        ResponseEntity<Void> response = blogApiDelegate.blogsIdDelete(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(blogValidator, times(1)).ValidateBlogExit(id);
        verify(blogRepository, times(1)).deleteById(id);
    }
    @Test
    public void testBlogsIdDeleteNotFound() {
        Integer idToDelete = 1;

        when(blogRepository.existsById(idToDelete)).thenReturn(false);


        ResponseEntity<Void> response = blogApiDelegate.blogsIdDelete(idToDelete);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(blogRepository, Mockito.never()).deleteById(idToDelete);
    }
    @Test
    public void testBlogsIdGet() {
        int id = 1;
        when(blogRepository.existsById(id)).thenReturn(true);

        BlogEntity blogEntity = new BlogEntity();
        when(blogRepository.getById(id)).thenReturn(blogEntity);

        Blog blog = new Blog();
        when(blogMapper.MapperBlogFromBlogEntity(blogEntity)).thenReturn(blog);

        ResponseEntity<Blog> response = blogApiDelegate.blogsIdGet(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(blog, response.getBody());

        verify(blogValidator, times(1)).ValidateBlogExit(id);
        verify(blogMapper, times(1)).MapperBlogFromBlogEntity(blogEntity);
    }

    @Test
    public void testBlogsIdGetNotFound() {
        int id = 1;
        when(blogRepository.existsById(id)).thenReturn(false);
        ResponseEntity<Blog> response = blogApiDelegate.blogsIdGet(id);


        verify(blogValidator, times(1)).ValidateBlogExit(id);
        verify(blogRepository, times(1)).existsById(id);
        verify(blogMapper, never()).MapperBlogFromBlogEntity(any());
    }

    @Test
    public void testBlogsIdPut() {
        int id = 1;
        Blog blog = new Blog();
        blog.setId(id);

        when(blogRepository.existsById(id)).thenReturn(true);

        BlogEntity savedBlogEntity = new BlogEntity();
        when(blogMapper.MapperBlogEntityFromBlog(id, blog)).thenReturn(savedBlogEntity);
        when(blogRepository.save(savedBlogEntity)).thenReturn(savedBlogEntity);

        Blog savedBlog = new Blog();
        when(blogMapper.MapperBlogFromBlogEntity(savedBlogEntity)).thenReturn(savedBlog);

        ResponseEntity<Blog> response = blogApiDelegate.blogsIdPut(id, blog);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedBlog, response.getBody());

        verify(blogValidator, times(1)).ValidateBlogExit(id);
        verify(blogValidator, times(1)).ValidateAddBlog(blog);
        verify(blogRepository, never()).existsById(id);
        verify(blogMapper, times(1)).MapperBlogEntityFromBlog(id, blog);
        verify(blogRepository, times(1)).save(savedBlogEntity);
        verify(blogMapper, times(1)).MapperBlogFromBlogEntity(savedBlogEntity);
    }

    @Test
    public void testBlogsIdPutNotFound() {
        int id = 1;
        Blog blog = new Blog();
        blog.setId(id);

        when(blogRepository.existsById(id)).thenReturn(false);

        ResponseEntity<Blog> response = blogApiDelegate.blogsIdPut(id, blog);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

        verify(blogValidator, times(1)).ValidateBlogExit(id);
        verify(blogRepository, never()).save(any());
        verify(blogMapper, never()).MapperBlogFromBlogEntity(any());
    }

    @Test
    public void testBlogsPost() {
        Blog blog = new Blog();

        // Mock the save operation
        BlogEntity savedBlogEntity = new BlogEntity();
        when(blogMapper.MapperBlogEntityFromBlog(blog)).thenReturn(savedBlogEntity);
        when(blogRepository.save(savedBlogEntity)).thenReturn(savedBlogEntity);

        // Mock the response
        Blog savedBlog = new Blog();
        when(blogMapper.MapperBlogFromBlogEntity(savedBlogEntity)).thenReturn(savedBlog);

        ResponseEntity<Blog> response = blogApiDelegate.blogsPost(blog);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedBlog, response.getBody());

        verify(blogValidator, times(1)).ValidateAddBlog(blog);
        verify(blogRepository, times(1)).save(savedBlogEntity);
        verify(blogMapper, times(1)).MapperBlogFromBlogEntity(savedBlogEntity);
    }
}

