package graph.spring.data.neo4j.services;

import graph.spring.data.neo4j.domain.Publication;
import graph.spring.data.neo4j.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PublicationService {

    @Autowired PublicationRepository publicationRepository;

    public List<Map<String, Object>> publicationDetails(String publicationId) {
	List<Map<String, Object>> results = publicationRepository.publicationDetails(publicationId);
	return results;
    }
}
