package com.api.gateway.feign.category;

import com.api.gateway.dto.post.category.CategoryDto;
import com.api.gateway.dto.post.category.CategoryRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "categoryClient", url = "${POST_MICROSERVICE}")
public interface CategoryClient {

    @PostMapping
    CategoryDto saveCategory(@RequestBody CategoryRequestDto requestDto);

    @GetMapping("/{id}")
    CategoryDto getCategoryById(@PathVariable Long id);

    @GetMapping("/name/{name}")
    CategoryDto getCategoryByName(@PathVariable String name);

}
