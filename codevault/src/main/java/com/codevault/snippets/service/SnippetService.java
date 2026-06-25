package com.codevault.snippets.service;

import com.codevault.snippets.entity.Snippet;
import com.codevault.snippets.entity.User;

import java.util.List;

public interface SnippetService {

    Snippet saveSnippet(Snippet snippet);
    List<Snippet> getUserSnippets(User user);
    Snippet getSnippetById(Long id);
    void deleteSnippet(Long id);
    List<Snippet> searchByTitleAndUser(String title, User user);
    List<Snippet> filterByLanguageAndUser(String language, User user);
}