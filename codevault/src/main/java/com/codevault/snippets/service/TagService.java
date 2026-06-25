package com.codevault.snippets.service;

import com.codevault.snippets.entity.Tag;

public interface TagService {

    Tag getOrCreateTag(String name);
    Tag findOrCreate(String name);
}