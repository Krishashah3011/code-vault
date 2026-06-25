package com.codevault.snippets.repository;

import java.util.List;
import com.codevault.snippets.entity.Snippet;
import com.codevault.snippets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnippetRepository extends JpaRepository<Snippet, Long> {

    List<Snippet> findByUser(User user);
    List<Snippet> findByTitleContainingIgnoreCaseAndUser(String title, User user);
    List<Snippet> findByLanguageIgnoreCaseAndUser(String language, User user);
}