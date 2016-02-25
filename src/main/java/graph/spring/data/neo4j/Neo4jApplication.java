package graph.spring.data.neo4j;

import graph.spring.data.neo4j.services.AuthorService;
import graph.spring.data.neo4j.services.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author nagarwal
 */
@Configuration
@Import(MyNeo4jConfiguration.class)
@RestController("/")
public class Neo4jApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Neo4jApplication.class, args);
    }

    @Autowired
    GraphService graphService;

    @RequestMapping("/authorDeets")
    public List<Map<String, Object>> authorDetails(@RequestParam(value = "limit",required = false) Integer limit) {
        return graphService.authorDetails(limit == null ? 100 : limit);
    }

    @Autowired
    AuthorService authorService;

    @RequestMapping("/authorDetails")
    public List<Map<String, Object>> authorDetails(@RequestParam(value = "authorId") String authorId) {
        return authorService.authorDetails(authorId);
    }

    @RequestMapping("/authorConnections")
    public Map<String, Object> authorConnections(@RequestParam(value = "limit",required = false) Integer limit,
						 @RequestParam(value = "authorId") String authorId) {
        return authorService.authorConnections(authorId, (limit == null ? 10 : limit));
    }
}
