package com.codevault.snippets.service.impl;

import com.codevault.snippets.entity.Tag;
import com.codevault.snippets.repository.TagRepository;
import com.codevault.snippets.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag getOrCreateTag(String name) {

        return tagRepository.findByName(name)
                .orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(name);
                    return tagRepository.save(tag);
                });
    }

    @Override
    public Tag findOrCreate(String name) {

         return tagRepository.findByName(name)
                 .orElseGet(() -> {
                    Tag tag = new Tag();
                    tag.setName(name);
                    return tagRepository.save(tag);
                });
    }
}