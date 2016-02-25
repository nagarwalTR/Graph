package graph.spring.data.neo4j.services;

import graph.spring.data.neo4j.domain.Author;
import graph.spring.data.neo4j.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AuthorService {

    @Autowired AuthorRepository authorRepository;

    public List<Map<String, Object>> authorDetails(String authorId) {
	List<Map<String, Object>> results = authorRepository.authorDetails(authorId);
	return results;
    }
    
    public Map<String, Object> authorConnections(String authorId, int limit) {
	Collection<Author> firstOrder = authorRepository.firstOrderConn(authorId, limit);
	Collection<Author> secondOrder = authorRepository.secondOrderConn(authorId, limit);
	Collection<Author> thirdOrder = authorRepository.thirdOrderConn(authorId, limit);

	Map<String, Object> result = new HashMap<String, Object>();
	result.put("firstOrder", firstOrder);
	result.put("secondOrder", secondOrder);
	result.put("thirdOrder", thirdOrder);
	return result;
    }
}
