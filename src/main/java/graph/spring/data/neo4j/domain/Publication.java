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
// tag::publication[]
@NodeEntity
public class Publication {
    @GraphId Long id;

    String publicationId;
    
    //@Relationship(type="AUTHORED_BY", direction = Relationship.INCOMING) List<Author> authors;

// end::publication[]

    public Publication() { }

    public String getPublicationId() {
	return publicationId;
    }
    
    /*
    public Collection<Author> getAuthors() {
        return authors;
    }
    */
}
