package graph.spring.data.neo4j;

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

    @RequestMapping("/graph")
    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        return graphService.graph(limit == null ? 100 : limit);
    }

}