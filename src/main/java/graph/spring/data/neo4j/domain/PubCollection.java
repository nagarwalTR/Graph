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
// tag::pubCollection[]
@NodeEntity
public class PubCollection {
    @GraphId Long id;

    String collectionId;
    String collectionCode;
    
// end::pubCollection[]

    public PubCollection() { }

    public String getCollectionId() {
	return collectionId;
    }
    
    public String getCollectionCode() {
	return collectionCode;
    }
}
