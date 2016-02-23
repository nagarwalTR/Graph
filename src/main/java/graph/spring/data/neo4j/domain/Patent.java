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
// tag::patent[]
@NodeEntity
public class Patent {
    @GraphId Long id;

    String patentId;
    String title;
    String publicationDate;
    
    @Relationship(type="PATENT_LITERATURE_CITATION", direction = Relationship.OUTGOING) List<Publication> publications;

// end::patent[]

    public Patent() { }

    public String getPatentId() {
	return patentId;
    }
    
    public String getTitle() {
	return title;
    }
    
    public String getPublicationDate() {
	return publicationDate;
    }
    
    public Collection<Publication> getPublications() {
        return publications;
    }
}
