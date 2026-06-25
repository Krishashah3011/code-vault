package com.codevault.snippets.service.impl;

import com.codevault.snippets.entity.Snippet;
import com.codevault.snippets.entity.User;
import com.codevault.snippets.repository.SnippetRepository;
import com.codevault.snippets.service.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SnippetServiceImpl implements SnippetService {

    @Autowired
    private SnippetRepository snippetRepository;

    @Override
    public Snippet saveSnippet(Snippet snippet) {
        return snippetRepository.save(snippet);
    }

    @Override
    public List<Snippet> getUserSnippets(User user) {
        return snippetRepository.findByUser(user);
    }

    @Override
    public Snippet getSnippetById(Long id) {
        return snippetRepository.findById(id)
                .orElse(null);
    }

    @Override
    public void deleteSnippet(Long id) {
        snippetRepository.deleteById(id);
    }

    @Override
    public List<Snippet> searchByTitleAndUser(String title, User user) {
        return snippetRepository.findByTitleContainingIgnoreCaseAndUser(title, user);
    }

    @Override
    public List<Snippet> filterByLanguageAndUser(String language, User user) {
        return snippetRepository.findByLanguageIgnoreCaseAndUser(language, user);
    }
}