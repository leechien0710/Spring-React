package com.example.contactserver.mock;

import com.example.contactserver.entity.BlogEntity;
import com.example.contactserver.model.Blog;

import java.time.LocalDate;

public class BlogData {
    public static final Integer id =1;
    public static final String title="TITLE";
    public static final String category="CATEGORY";
    public static final String description = "ĐESCRIPTION";
    public static final LocalDate create_at= LocalDate.of(2023,10,7);
    public static Blog MockBlog(){
        Blog blog = new Blog();
        blog.setId(id);
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setCreatedAt(create_at);
        blog.setCategory(category);
        return blog;
    }
    public static BlogEntity MockBlogEntity(){
        BlogEntity blog = new BlogEntity();
        blog.setId(id);
        blog.setTitle(title);
        blog.setDescription(description);
        blog.setCreated_at(create_at);
        blog.setCategory(category);
        return blog;
    }
}
