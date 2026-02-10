package com.example.yumplanner.data.network.response;

import com.example.yumplanner.data.home.model.dto.CategoryDto;

import java.util.List;

public class CategoryResponse {
    List<CategoryDto> categories;
    public  List<CategoryDto> getCategories (){return categories ; }

}
