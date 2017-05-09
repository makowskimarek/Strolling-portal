package com.walker.test

import com.walker.config.DataBaseConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

import javax.sql.DataSource

import static org.junit.Assert.assertNotNull;


/**
 * Created by Rafal on 01.05.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataBaseConfig.class)
class DataBaseConfigTest extends GroovyTestCase {

    @Autowired
    private DataSource ds;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void dataSourceShouldNotBeNull() {
        assertNotNull(ds);
    }

    @Test
    void jdbcTemplateShouldNotBeNull() {
        assertNotNull(jdbcTemplate);
    }

}
