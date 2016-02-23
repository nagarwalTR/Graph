package graph.spring.data.neo4j.repositories;

import graph.spring.data.neo4j.domain.Author;
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
@RepositoryRestResource(collectionResourceRel = "authors", path = "authors")
public interface AuthorRepository extends GraphRepository<Author> {
    // TODO: This should return all author info, including links -- similar to how we get it in /authors/
    @Query("MATCH (a:Author) WHERE a.AuthorId = {authorId} RETURN a LIMIT 50")
    Author findByAuthorId(@Param("authorId") String authorId);

    @Query("MATCH (a:Author)<-[:AUTHORED_BY]-(p:Publication) WHERE p.PublicationId = {publicationId} RETURN a")
    Collection<Author> findByPublicationId(@Param("publicationId") String publicationId);

    @Query("MATCH (author:Author)-[:AUTHORED_BY*2]-(coauthor:Author) WHERE author.AuthorId = {authorId} RETURN coauthor LIMIT 50")
    Collection<Author> findCoAuthors(@Param("authorId") String authorId);

    @Query("MATCH (o:Publication)-[:AUTHORED_BY]->(author:Author)<-[:AUTHORED_BY]-(p:Publication)-[:AUTHORED_BY]->(coauthor:Author) WHERE o.PublicationId = {publicationId} RETURN coauthor LIMIT 50")
    Collection<Author> findCoAuthorsByPublicationId(@Param("publicationId") String publicationId);

    @Query("MATCH (grant:Grant)<-[:AUTHOR_RECD_GRANT]-(a:Author)<-[:AUTHORED_BY]-(publication:Publication) RETURN a.AuthorId AS author, publication, grant LIMIT {limit}")
    List<Map<String,Object>> graph(@Param("limit") int limit);
}
// end::repository[]

