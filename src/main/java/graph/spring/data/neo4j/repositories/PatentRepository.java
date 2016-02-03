package graph.spring.data.neo4j.repositories;

import graph.spring.data.neo4j.domain.Author;
import graph.spring.data.neo4j.domain.Patent;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author nagarwal
 */
// tag::repository[]
@RepositoryRestResource(collectionResourceRel = "patents", path = "patents")
public interface PatentRepository extends GraphRepository<Patent> {
    @Query("MATCH (p:Patent) USING INDEX p:Patent(PatentId) WHERE p.PatentId = {patentId} RETURN p LIMIT 50")
    Patent findByPatentId(@Param("patentId") String patentId);

    @Query("MATCH (a:Author)-[:AUTHORED_BY]->(p:Publication)<-[:LITERATURE_PATENT_CITATION]-(pa:Patent) USING INDEX pa:Patent(PatentId) WHERE pa.PatentId = {patentId} RETURN a LIMIT 50")
    Collection<Author> findPatentRefAuthors(@Param("patentId") String patentId);
}
// end::repository[]

