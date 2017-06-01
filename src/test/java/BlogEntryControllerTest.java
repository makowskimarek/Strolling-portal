import com.walker.core.entities.BlogEntry;
import com.walker.core.services.BlogEntryService;
import com.walker.rest.mvc.BlogEntryController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.core.util.DefaultIndenter;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Matchers.endsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;



/**
 * Created by Rafal on 27.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BlogEntryControllerTest {

    @InjectMocks
    private BlogEntryController controller;


    @Mock
    private BlogEntryService service;

    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                    .build();
    }

    /*@Test
    public void test() throws Exception {
        mockMvc.perform(post("/test")
                        .content("{\"title\":\"Test test test\"}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.title",is("Test test test")))
                .andDo(print());

    }*/

    @Test
    public void getExistingBlogEntry() throws Exception{
        BlogEntry entry = new BlogEntry();

        entry.setId(1L);
        entry.setTitle("Test title");

        when(service.find(1L)).thenReturn(entry);

        mockMvc.perform(get("/rest/blog-entries/1"))
                .andDo(print())
                .andExpect(jsonPath("$.title", is(entry.getTitle())))
                //.andExpect(jsonPath("$.links[*].href",hasItem(endsWith("/blog-entries/1"))))
                .andExpect(status().isOk());

    }



}
