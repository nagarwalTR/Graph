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
// tag::edition[]
@NodeEntity
public class Edition {
    @GraphId Long id;

    String editionId;
    String editionCode;
    
// end::edition[]

    public Edition() { }

    public String getEditionId() {
	return editionId;
    }
    
    public String getEditionCode() {
	return editionCode;
    }
}
