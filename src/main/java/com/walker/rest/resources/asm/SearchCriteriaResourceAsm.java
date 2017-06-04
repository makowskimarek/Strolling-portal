package com.walker.rest.resources.asm;

import com.walker.core.entities.SearchCriteria;
import com.walker.rest.mvc.SearchController;
import com.walker.rest.resources.SearchCriteriaResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Rafal on 04.06.2017.
 */
public class SearchCriteriaResourceAsm extends ResourceAssemblerSupport<SearchCriteria, SearchCriteriaResource> {

    public SearchCriteriaResourceAsm()
    {
        super(SearchController.class,SearchCriteriaResource.class);
    }

    @Override
    public SearchCriteriaResource toResource(SearchCriteria searchCriteria) {
        SearchCriteriaResource res = new SearchCriteriaResource(searchCriteria.getUserId(),
                searchCriteria.getAgeFrom(),
                searchCriteria.getAgeTo(),
                searchCriteria.getDistance(),
                searchCriteria.getUserLatitude(),
                searchCriteria.getUserLongtitude()
                );

        Link link = linkTo(SearchController.class).withSelfRel();
        res.add(link.withSelfRel());
        return res;
    }
}
