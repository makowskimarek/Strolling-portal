package com.walker.rest.resources.asm;

import com.walker.core.entities.BlogEntry;
import com.walker.rest.mvc.BlogEntryController;
import com.walker.rest.resources.BlogEntryResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by Rafal on 01.06.2017.
 */
public class BlogEntryResourceAsm extends ResourceAssemblerSupport<BlogEntry,BlogEntryResource>{

    public BlogEntryResourceAsm()
    {
        super(BlogEntryController.class, BlogEntryResource.class);
    }

    @Override
    public BlogEntryResource toResource(BlogEntry blogEntry) {
        BlogEntryResource res = new BlogEntryResource();
        res.setTitle((blogEntry.getTitle()));
        Link link = linkTo(BlogEntryController.class).slash(blogEntry.getId()).withSelfRel();
        res.add(link.withSelfRel());

        return res;
    }
}
