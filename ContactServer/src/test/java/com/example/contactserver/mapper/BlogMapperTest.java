package com.example.contactserver.mapper;

import com.example.contactserver.entity.BlogEntity;
import com.example.contactserver.mock.BlogData;
import com.example.contactserver.model.Blog;
import com.example.contactserver.repository.BlogRepository;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BlogMapperTest {
    @InjectMocks BlogMapper blogMapper;
    @Mock
    BlogRepository blogRepository;
    @Test
    public void ensureMapBlogEntityFromBlogForAdd(){
        Blog blog = BlogData.MockBlog();
        BlogEntity blogEntity = blogMapper.MapperBlogEntityFromBlog(blog);
        Assert.assertThat(blogEntity.getId(), Is.is(blog.getId()));
        Assert.assertThat(blogEntity.getTitle(), Is.is(blog.getTitle()));
        Assert.assertThat(blogEntity.getCategory(), Is.is(blog.getCategory()));
        Assert.assertThat(blogEntity.getCreated_at(), Is.is(blog.getCreatedAt()));
        Assert.assertThat(blogEntity.getDescription(), Is.is(blog.getDescription()));
    }
    @Test
    public void ensureMapBlogFromBlogEntity(){
        BlogEntity blogEntity = BlogData.MockBlogEntity();
        Blog blog = blogMapper.MapperBlogFromBlogEntity(blogEntity);
        Assert.assertThat(blog.getId(), Is.is(blogEntity.getId()));
        Assert.assertThat(blog.getTitle(), Is.is(blogEntity.getTitle()));
        Assert.assertThat(blog.getCategory(), Is.is(blogEntity.getCategory()));
        Assert.assertThat(blog.getCreatedAt(), Is.is(blogEntity.getCreated_at()));
        Assert.assertThat(blog.getDescription(), Is.is(blogEntity.getDescription()));
    }
    @Test
    public void eensureMapBlogEntityFromBlogForUpdate(){
        BlogEntity blogEntityMock = BlogData.MockBlogEntity();
        Blog blog = BlogData.MockBlog();
        Mockito.when(blogRepository.getOne(BlogData.id)).thenReturn(blogEntityMock);
        BlogEntity blogEntity = blogMapper.MapperBlogEntityFromBlog(BlogData.id,blog);
        Mockito.verify(blogRepository, Mockito.times(1)).getOne(BlogData.id);
        Assert.assertThat(blogEntity.getId(), Is.is(BlogData.id));
        Assert.assertThat(blogEntity.getTitle(), Is.is(blog.getTitle()));
        Assert.assertThat(blogEntity.getCategory(), Is.is(blog.getCategory()));
        Assert.assertThat(blogEntity.getCreated_at(), Is.is(blog.getCreatedAt()));
        Assert.assertThat(blogEntity.getDescription(), Is.is(blog.getDescription()));
    }
    @Test
    public void ensureMapBlogEntityListFromBlogList(){
        BlogEntity blogEntityMock = BlogData.MockBlogEntity();
        List<BlogEntity> blogEntities = new ArrayList<>();
        blogEntities.add(blogEntityMock);
        List<Blog> blogs = blogMapper.MapperBlogListFromBlogEntityList(blogEntities);
        Assert.assertThat(blogs.size(),Is.is(1));
        Assert.assertThat(blogs.get(0).getId(),Is.is(blogEntities.get(0).getId()));
        Assert.assertThat(blogs.get(0).getCategory(),Is.is(blogEntities.get(0).getCategory()));
        Assert.assertThat(blogs.get(0).getTitle(),Is.is(blogEntities.get(0).getTitle()));
        Assert.assertThat(blogs.get(0).getDescription(),Is.is(blogEntities.get(0).getDescription()));
        Assert.assertThat(blogs.get(0).getImage(),Is.is(blogEntities.get(0).getImage()));
    }
}
