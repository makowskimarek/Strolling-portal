package com.walker.rest.resources;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Rafal on 01.06.2017.
 */
public class BlogEntryResource extends ResourceSupport{
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
