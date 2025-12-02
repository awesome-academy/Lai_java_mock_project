package com.example.booking_tour.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.booking_tour.service.CategoryService;
import com.example.booking_tour.dto.CategoryCreateRequest;
import jakarta.validation.Valid;
import com.example.booking_tour.entity.Category;
import com.example.booking_tour.repository.CategoryRepository;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("title", "Category Management");
        model.addAttribute("categories", categories);
        return "admin/category";
    }

    @PostMapping("")
    public String store(@Valid @ModelAttribute CategoryCreateRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                throw new RuntimeException("Validation failed");
            }
            categoryService.createCategory(request);
            redirectAttributes.addFlashAttribute("success", "Category created successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/admin/categories";
    }

    @PostMapping("/update/{categoryId}")
    public String update(@PathVariable("categoryId") Long categoryId,
            @Valid @ModelAttribute CategoryCreateRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                throw new RuntimeException("Validation failed");
            }
            categoryService.updateCategory(categoryId, request);
            redirectAttributes.addFlashAttribute("success", "Category updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/delete/{categoryId}")
    public String delete(@PathVariable("categoryId") Long categoryId,
            RedirectAttributes redirectAttributes) {
        try {
            categoryService.deleteCategory(categoryId);
            redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/categories";
    }
}
