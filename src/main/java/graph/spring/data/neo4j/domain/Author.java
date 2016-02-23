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
    String daisId;
    String researcherId;
    String orcId;
    String fullName;

    @Relationship(type="AUTHORED_BY", direction = Relationship.INCOMING) List<Publication> publications;
    @Relationship(type="AUTHOR_RECD_GRANT", direction = Relationship.OUTGOING) List<Grant> grants;

// end::author[]

    public Author() { }

    public String getAuthorId() {
        return authorId;
    }

    public String getDaisId() {
        return daisId;
    }

    public String getResearcherId() {
        return researcherId;
    }

    public String getOrcId() {
        return orcId;
    }

    public String getFullName() {
        return fullName;
    }

    public Collection<Publication> getPublications() {
        return publications;
    }

    public Collection<Grant> getGrants() {
        return grants;
    }
}
