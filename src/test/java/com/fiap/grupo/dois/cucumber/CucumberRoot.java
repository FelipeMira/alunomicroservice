package com.fiap.grupo.dois.cucumber;

import java.util.Collections;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.fiap.grupo.dois.AlunoApplication;

@SpringBootTest(classes = AlunoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration
public class CucumberRoot {

    @Autowired
    protected TestRestTemplate template;

    @Before
    public void before() {
        // demo to show how to add custom header Globally for the http request in spring test template , like IV user header
        template.getRestTemplate().setInterceptors(Collections.singletonList((request, body, execution) -> {
            request.getHeaders();
            return execution.execute(request, body);
        }));
    }

}
