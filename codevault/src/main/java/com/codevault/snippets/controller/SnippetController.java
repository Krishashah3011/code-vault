package com.codevault.snippets.controller;

import com.codevault.snippets.entity.Snippet;
import com.codevault.snippets.entity.User;
import com.codevault.snippets.service.SnippetService;
import com.codevault.snippets.service.UserService;
import com.codevault.snippets.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/snippets")
public class SnippetController {

    @Autowired
    private SnippetService snippetService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    // ✅ VIEW USER-SPECIFIC SNIPPETS
    @GetMapping
    public String getAllSnippets(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Snippet> snippets = snippetService.getUserSnippets(user);
        model.addAttribute("snippets", snippets);
        return "snippets";
    }

    // ✅ SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("snippet", new Snippet());
        return "add-snippet";
    }

    // ✅ SAVE SNIPPET (LINKED TO USER)
    @PostMapping("/save")
    public String saveSnippet(@ModelAttribute Snippet snippet, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        snippet.setUser(user);
        snippetService.saveSnippet(snippet);
        return "redirect:/snippets";
    }

    // ✅ DELETE SNIPPET
    @GetMapping("/delete/{id}")
    public String deleteSnippet(@PathVariable Long id) {
        snippetService.deleteSnippet(id);
        return "redirect:/snippets";
    }

    // ✅ SEARCH (USER-SPECIFIC)
    @GetMapping("/search")
    public String searchSnippets(@RequestParam(required = false) String title,
                                 @RequestParam(required = false) String language,
                                 Model model,
                                 Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Snippet> snippets;

        if (title != null && !title.isEmpty()) {
            snippets = snippetService.searchByTitleAndUser(title, user);
        } else if (language != null && !language.isEmpty()) {
            snippets = snippetService.filterByLanguageAndUser(language, user);
        } else {
            snippets = snippetService.getUserSnippets(user);
        }
        model.addAttribute("snippets", snippets);
        return "snippets";
    }

    // ✅ FAVORITE TOGGLE
    @GetMapping("/favorite/{id}")
    public String toggleFavorite(@PathVariable Long id) {
        Snippet snippet = snippetService.getSnippetById(id);
        snippet.setFavorite(!snippet.isFavorite());
        snippetService.saveSnippet(snippet);
        return "redirect:/snippets";
    }

    // ✅ EXPORT AS TXT
    @GetMapping("/export/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> exportSnippet(@PathVariable Long id) {
        Snippet snippet = snippetService.getSnippetById(id);
        byte[] content = snippet.getCode().getBytes();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=snippet.txt")
                .body(content);
    }

    // ✅ EDIT SNIPPET
    @GetMapping("/edit/{id}")
    public String editSnippet(@PathVariable Long id, Model model) {
        Snippet snippet = snippetService.getSnippetById(id);
        model.addAttribute("snippet", snippet);
        return "add-snippet";
    }
}