package com.walker.rest.mvc;


//import org.springframework.http.ResponseEntity;
import com.walker.core.entities.BlogEntry;
import com.walker.core.services.BlogEntryService;
import com.walker.rest.resources.BlogEntryResource;
import com.walker.rest.resources.asm.BlogEntryResourceAsm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Rafal on 27.05.2017.
 */
@Controller
@RequestMapping("rest/blog-entries")
public class BlogEntryController {

    private BlogEntryService service;

    /*public BlogEntryController(BlogEntryService service)
    {
        this.service = service;
    }*/



    @RequestMapping(value = "/{blogEntryId}",
                    method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResource> getBlogEntry(
            @PathVariable Long blogEntryId)
    {
        BlogEntry entry = service.find(blogEntryId);
        BlogEntryResource res = new BlogEntryResourceAsm().toResource(entry);
        return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);

    }
}
