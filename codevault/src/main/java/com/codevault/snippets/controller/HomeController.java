package com.codevault.snippets.controller;

import com.codevault.snippets.entity.User;
import com.codevault.snippets.entity.Snippet;
import com.codevault.snippets.service.UserService;
import com.codevault.snippets.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private SnippetService snippetService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Snippet> snippets = snippetService.getUserSnippets(user);
        long total = snippets.size();
        long favorites = snippets.stream()
                .filter(Snippet::isFavorite)
                .count();

        List<Snippet> recent = snippets.stream()
                .limit(3)
                .toList();

        model.addAttribute("total", total);
        model.addAttribute("favorites", favorites);
        model.addAttribute("recent", recent);
        return "dashboard";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }
}