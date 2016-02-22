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
    @Query("MATCH (a:Author) WHERE a.AuthorId = {authorId} RETURN a LIMIT 50")
    Author findByAuthorId(@Param("authorId") String authorId);

    @Query("MATCH (a:Author)-[:AUTHORED_BY]->(p:Publication) WHERE p.PublicationId = {publicationId} RETURN a")
    Collection<Author> findByPublicationId(@Param("publicationId") String publicationId);

    @Query("MATCH (author:Author)-[:AUTHORED_BY*2]-(coauthor:Author) WHERE author.AuthorId = {authorId} RETURN coauthor LIMIT 50")
    Collection<Author> findCoAuthors(@Param("authorId") String authorId);

    // TODO: Need to find out how to translate this into the first order and second order results to be shown correctly
    @Query("MATCH (author:Author)-[:AUTHORED_BY*2]-(firstOrder:Author),(author:Author)-[:AUTHORED_BY*4]-(secondOrder:Author),(author:Author)-[:AUTHORED_BY*6]-(thirdOrder:Author) WHERE author.AuthorId = {authorId} RETURN DISTINCT firstOrder, secondOrder, thirdOrder LIMIT 50")
    Collection<Author> findAuthorConnections(@Param("authorId") String authorId);

    @Query("MATCH (o:Publication)<-[:AUTHORED_BY]-(author:Author)-[:AUTHORED_BY]->(p:Publication)<-[:AUTHORED_BY]-(coauthor:Author) WHERE o.PublicationId = {publicationId} RETURN coauthor LIMIT 50")
    Collection<Author> findCoAuthorsByPublicationId(@Param("publicationId") String publicationId);

    @Query("MATCH (a:Author)-[:AUTHORED_BY]->(p:Publication) RETURN a.AuthorId as author, collect(p.PublicationId) as cast LIMIT {limit}")
    List<Map<String,Object>> graph(@Param("limit") int limit);
}
// end::repository[]

