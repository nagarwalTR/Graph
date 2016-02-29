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
// tag::journal[]
@NodeEntity
public class Journal {
    @GraphId Long id;

    String journalId;
    String title;
    String issue;
    String coverData;
    String volume;
    
// end::journal[]

    public Journal() { }

    public String getJournalId() {
	return journalId;
    }

    public String getTitle() {
	return title;
    }

    public String getIssue() {
	return issue;
    }

    public String getCoverData() {
	return coverData;
    }

    public String getVolume() {
	return volume;
    }
}
