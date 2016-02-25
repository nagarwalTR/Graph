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

    // Queries used by the Service and not meant to be called directly from this repository
    @Query("MATCH (grant:Grant)<-[:AUTHOR_RECD_GRANT]-(a:Author)<-[:AUTHORED_BY]-(publication:Publication) WHERE a.AuthorId = {authorId} RETURN a.AuthorId AS author, publication, grant")
    List<Map<String,Object>> authorDetails(@Param("authorId") String authorId);

    @Query("MATCH (author:Author)-[:AUTHORED_BY*2]-(firstOrder:Author) WHERE author.AuthorId = {authorId} RETURN DISTINCT firstOrder LIMIT {limit}")
    Collection<Author> firstOrderConn(@Param("authorId") String authorId, @Param("limit") int limit);

    @Query("MATCH (author:Author)-[:AUTHORED_BY*4]-(secondOrder:Author) WHERE author.AuthorId = {authorId} RETURN DISTINCT secondOrder LIMIT {limit}")
    Collection<Author> secondOrderConn(@Param("authorId") String authorId, @Param("limit") int limit);

    @Query("MATCH (author:Author)-[:AUTHORED_BY*6]-(thirdOrder:Author) WHERE author.AuthorId = {authorId} RETURN DISTINCT thirdOrder LIMIT {limit}")
    Collection<Author> thirdOrderConn(@Param("authorId") String authorId, @Param("limit") int limit);
}
// end::repository[]

