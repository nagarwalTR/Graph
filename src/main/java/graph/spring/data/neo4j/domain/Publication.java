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
    
    @Relationship(type="AUTHORED_BY", direction = Relationship.OUTGOING) List<Author> authors;
    @Relationship(type="PUBLICATION_EDITION", direction = Relationship.OUTGOING) List<Edition> editions;
    @Relationship(type="APPEARED_IN", direction = Relationship.OUTGOING) List<Conference> conferences;
    @Relationship(type="PUBLICATION_IN_COLLECTION", direction = Relationship.OUTGOING) List<PubCollection> collections;
    @Relationship(type="PUBLICATION_IN_CATEGORY", direction = Relationship.OUTGOING) List<Category> categories;
    @Relationship(type="PUBLICATION_IN_JOURNAL", direction = Relationship.OUTGOING) List<Journal> journals;
    @Relationship(type="PUBLICATION_LANGUAGE", direction = Relationship.OUTGOING) List<Language> languages;

// end::publication[]

    public Publication() { }

    public String getPublicationId() {
	return publicationId;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    public Collection<Edition> getEditions() {
        return editions;
    }

    public Collection<Conference> getConferences() {
        return conferences;
    }

    public Collection<PubCollection> getCollections() {
        return collections;
    }

    public Collection<Category> getCategories() {
        return categories;
    }

    public Collection<Journal> getJournals() {
        return journals;
    }

    public Collection<Language> getLangauges() {
        return languages;
    }
}
