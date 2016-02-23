package graph.spring.data.neo4j.services;

import graph.spring.data.neo4j.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class GraphService {

    @Autowired AuthorRepository authorRepository;

    /*
    private Map<String, Object> toD3Format(Iterator<Map<String, Object>> result) {
        List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> rels= new ArrayList<Map<String,Object>>();
        int i=0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(map("authorId",row.get("author"),"label","authorId"));
            int target=i;
            i++;
            for (Object name : (Collection) row.get("cast")) {
                Map<String, Object> pub = map("publicationId", name,"label","publicationId");
                int source = nodes.indexOf(pub);
                if (source == -1) {
                    nodes.add(pub);
                    source = i++;
                }
                rels.add(map("source",source,"target",target));
            }
        }
        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String,Object>(2);
        result.put(key1,value1);
        result.put(key2,value2);
        return result;
    }

    public Map<String, Object> authorGraph(int limit) {
        Iterator<Map<String, Object>> result = authorRepository.graph(limit).iterator();
        return toD3Format(result);
    }
    */

    private List<Map<String, Object>> toResult(Iterator<Map<String, Object>> result) {
	List<Map<String,Object>> nodes = new ArrayList<Map<String,Object>>();
	while (result.hasNext()) {
	    nodes.add(result.next());
	}
	return nodes;
    }

    public List<Map<String, Object>> authorDetails(int limit) {
	Iterator<Map<String, Object>> result = authorRepository.graph(limit).iterator();
	return toResult(result);
    }
}
