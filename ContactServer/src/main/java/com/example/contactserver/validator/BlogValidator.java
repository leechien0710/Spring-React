package com.example.contactserver.validator;

import com.example.contactserver.exception.BadRequestException;
import com.example.contactserver.exception.EntityNotFoundException;
import com.example.contactserver.model.Blog;
import com.example.contactserver.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogValidator {
    @Autowired private BlogRepository blogRepository;
    public void ValidateAddBlog(Blog blog){
        if(blog.getTitle()==null||blog.getTitle().isEmpty()){
            throw new BadRequestException("Tiêu đề không được để trống");
        }
        else{
            if(blog.getTitle().length()>25){
                throw new BadRequestException("Tiêu đề không được vượt quá 25 kí tự");
            }
        }
        if(blog.getCategory()==null||blog.getCategory().isEmpty()){
            throw new BadRequestException("Thể loại không được trống");
        }
        if(blog.getDescription().length()>1000){
            throw new BadRequestException("Mô tả không được quá 1000 kí tự");
        }
    }
    public void ValidateBlogExit(Integer id){
        if(!blogRepository.existsById(id)){
            throw new EntityNotFoundException("Blog không tìm thấy");
        }
    }
}
