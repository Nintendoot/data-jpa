package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.Category;
import by.nintendo.datajpa.model.Pet;
import by.nintendo.datajpa.model.Tag;
import by.nintendo.datajpa.storage.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> createTag(List<Tag> tags) {
        List<Tag> tagsList = new ArrayList<>();
        for (Tag tag : tags) {
            Tag t = tagRepository.findTagByName(tag.getName()) != null ? tagRepository.findTagByName(tag.getName()) : tag;
            tagsList.add(t);
        }
        return tagsList;
    }
}
