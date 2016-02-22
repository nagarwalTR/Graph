package graph.spring.data.neo4j.domain;

import org.neo4j.graphdb.Direction;
import org.neo4j.ogm.annotation.*;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonInclude(Include.NON_NULL)
// tag::author[]
@NodeEntity
public class Author {
    @GraphId Long id;

    String authorId;
    //    String DaisId;
    //String ResearcherId;
    //String OrcId;

    @Relationship(type="AUTHORED_BY", direction = Relationship.OUTGOING) List<Publication> publications;

// end::author[]

    public Author() { }

    public String getAuthorId() {
        return authorId;
    }

    /*
    public String getResearcherId() {
        return ResearcherId;
    }
    */

    public Collection<Publication> getPublications() {
        return publications;
    }
}
