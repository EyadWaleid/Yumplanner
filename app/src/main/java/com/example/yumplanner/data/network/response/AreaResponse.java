package com.example.yumplanner.data.network.response;

import com.example.yumplanner.data.home.model.dto.AreaDTO;

import java.util.List;

public class AreaResponse {
    List<AreaDTO> meals;
    public  List<AreaDTO> getAreas (){return meals ; }

}
