package com.example.contactserver.validator;

import com.example.contactserver.exception.BadRequestException;
import com.example.contactserver.exception.EntityNotFoundException;
import com.example.contactserver.model.Blog;
import com.example.contactserver.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class BlogValidatorTest {

    @InjectMocks
    private BlogValidator blogValidator;

    @Mock
    private BlogRepository blogRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidateAddBlogWithValidBlog() {
        Blog blog = new Blog();
        blog.setTitle("Valid Title");
        blog.setCategory("Valid Category");
        blog.setDescription("Valid Description");

        blogValidator.ValidateAddBlog(blog);
    }

    @Test
    public void testValidateAddBlogWithEmptyTitle() {
        Blog blog = new Blog();
        blog.setTitle("");
        blog.setCategory("Valid Category");
        blog.setDescription("Valid Description");

        assertThrows(BadRequestException.class, () -> {
            blogValidator.ValidateAddBlog(blog);
        });
    }

    @Test
    public void testValidateAddBlogWithTitleTooLong() {
        Blog blog = new Blog();
        blog.setTitle("This is a very long title that exceeds 25 characters");
        blog.setCategory("Valid Category");
        blog.setDescription("Valid Description");

        assertThrows(BadRequestException.class, () -> {
            blogValidator.ValidateAddBlog(blog);
        });
    }

    @Test
    public void testValidateAddBlogWithEmptyCategory() {
        Blog blog = new Blog();
        blog.setTitle("Valid Title");
        blog.setCategory("");
        blog.setDescription("Valid Description");

        assertThrows(BadRequestException.class, () -> {
            blogValidator.ValidateAddBlog(blog);
        });
    }

    @Test
    public void testValidateAddBlogWithDescriptionTooLong() {
        Blog blog = new Blog();
        blog.setTitle("Valid Title");
        blog.setCategory("Valid Category");
        blog.setDescription("This is a very long description that exceeds 1000 characters and is not allowed.Prepare to embark on an extraordinary odyssey through the boundless expanse of deep space, where the infinite cosmos beckon with untold mysteries and celestial marvels. Our captivating blog series,Exploring the Wonders of Deep Space,is a celestial tapestry woven with knowledge, imagination, and the passion of stargazers.In these digital pages, we invite you to join us on an epic journey, where the stories of the universe unfold before your eyes. We will traverse the depths of the cosmos, venturing into the darkest corners of space to reveal the hidden truths of our galaxy and beyond.Our cosmic voyage begins with a cosmic overture, introducing you to the majesty of the night sky, the constellations that have guided humanity for millennia, and the birth of stars in stellar nurseries.Embark on an extraordinary odyssey through the vast expanse of the cosmos, where the celestial wonders of the universe come to life. Our captivating blog series,Unveiling the Mysteries of the Universe,is a celestial tapestry woven with the threads of curiosity, discovery, and the unwavering pursuit of knowledge. In these digital pages, we invite you to join us on an epic voyage, where the enigmas of the cosmos unravel before your eyes.Our cosmic journey begins with a cosmic overture, where the night skys grandeur greets you, constellations that have been the companions of humanity for millennia, and the miraculous birth of stars in the cradles of stellar nurseries. As we venture deeper into the cosmos, we will explore the enigmatic phenomena of black holes, where the fabric of space and time is stretched to its limits.We ponder the age-old question of extraterrestrial life, contemplating the prospects of otherworldly existence and interstellar communication. Could we be sharing our universe with intelligent beings? This question has captivated the minds of astronomers, scientists, and dreamers.This blog series is more than just a repository of knowledge; it is an ode to wonder, curiosity, and the unquenchable human spirit that drives us to explore the cosmos. Join us as we navigate the celestial seas and delve into the unknown, for the universe has stories yet untold, and we are the storytellers of the cosmos.");

        assertThrows(BadRequestException.class, () -> {
            blogValidator.ValidateAddBlog(blog);
        });
    }

    @Test
    public void testValidateBlogExitWhenBlogExists() {
        when(blogRepository.existsById(1)).thenReturn(true);

        blogValidator.ValidateBlogExit(1);
    }

    @Test
    public void testValidateBlogExitWhenBlogDoesNotExist() {
        when(blogRepository.existsById(2)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> {
            blogValidator.ValidateBlogExit(2);
        });
    }
}

