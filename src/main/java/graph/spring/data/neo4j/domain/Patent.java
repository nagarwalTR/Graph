package graph.spring.data.neo4j.domain;

import org.neo4j.graphdb.Direction;
import org.neo4j.ogm.annotation.*;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@JsonIdentityInfo(generator=JSOGGenerator.class)
// tag::patent[]
@NodeEntity
public class Patent {
    @GraphId Long id;

    String patentId;
    
    @Relationship(type="LITERATURE_PATENT_CITATION", direction = Relationship.OUTGOING) List<Publication> publications;

// end::patent[]

    public Patent() { }

    public String getPatentId() {
	return patentId;
    }
    
    public Collection<Publication> getPublications() {
        return publications;
    }
}
