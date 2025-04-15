package com.sideProject.qrOrder.controller.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    @GetMapping("/manage")
    public String manageCategoryPage() {
        return "/manager/pages/category/manageCategory";
    }
}
