package graph.spring.data.neo4j.repositories;

import graph.spring.data.neo4j.domain.Publication;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author nagarwal
 */
// tag::repository[]
@RepositoryRestResource(collectionResourceRel = "publications", path = "publications")
public interface PublicationRepository extends GraphRepository<Publication> {
    @Query("MATCH (a:Author)<-[:AUTHORED_BY]-(p:Publication) WHERE a.AuthorId = {authorId} RETURN p LIMIT 50")
    Collection<Publication> findByAuthorId(@Param("authorId") String authorId);

    @Query("MATCH (p:Publication) WHERE p.PublicationId = {publicationId} RETURN p")
    Publication findByPublicationId(@Param("publicationId") String publicationId);

    // Queries used by the Service and not meant to be called directly from this repository
    @Query("MATCH (conference:Conference)<-[:APPEARED_IN]-(p:Publication)-[:AUTHORED_BY]->(author:Author),(edition:Edition)<-[:PUBLICATION_EDITION]-(p:Publication)-[:PUBLICATION_IN_COLLECTION]->(collection:Collection),(category:Category)<-[:PUBLICATION_IN_CATEGORY]-(p:Publication)-[:PUBLICATION_IN_JOURNAL]->(journal:Journal),(language:Language)<-[:PUBLICATION_LANGUAGE]-(p:Publication) WHERE p.PublicationId = {publicationId} RETURN p.PublicationId AS publication, conference, author, edition, collection, category, journal, language")
    List<Map<String,Object>> publicationDetails(@Param("publicationId") String publicationId);
}
// end::repository[]

