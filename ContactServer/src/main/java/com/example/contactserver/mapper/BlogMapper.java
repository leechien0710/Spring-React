package com.example.contactserver.mapper;

import com.example.contactserver.entity.BlogEntity;
import com.example.contactserver.model.Blog;
import com.example.contactserver.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BlogMapper {
    @Autowired private BlogRepository blogRepository;
    public Blog MapperBlogFromBlogEntity(BlogEntity blogEntity ){
        Blog blog = new Blog();
        blog.setId(blogEntity.getId());
        blog.setCategory(blogEntity.getCategory());
        blog.setImage(blogEntity.getImage());
        blog.setTitle(blogEntity.getTitle());
        blog.setCreatedAt(blogEntity.getCreated_at());
        blog.setDescription(blogEntity.getDescription());

        return blog;
    }


    public BlogEntity MapperBlogEntityFromBlog(Blog blog ){
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(blog.getId());
        blogEntity.setCategory(blog.getCategory());
        blogEntity.setCreated_at(blog.getCreatedAt());
        blogEntity.setImage(blog.getImage());
        blogEntity.setTitle(blog.getTitle());
        blogEntity.setDescription(blog.getDescription());
        return blogEntity;
    }
    public BlogEntity MapperBlogEntityFromBlog(Integer id,Blog blog){
        BlogEntity blogEntity = blogRepository.getOne(id);
        blogEntity.setCategory(blog.getCategory());
        blogEntity.setCreated_at(blog.getCreatedAt());
        blogEntity.setImage(blog.getImage());
        blogEntity.setTitle(blog.getTitle());
        blogEntity.setDescription(blog.getDescription());
        return blogEntity;
    }

    public List<Blog> MapperBlogListFromBlogEntityList(List<BlogEntity> blogEntities){
        return blogEntities.stream()
                .map(this::MapperBlogFromBlogEntity)
                .collect(Collectors.toList());
    }
}

