package com.github.phillipkruger.graphql.core;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import graphql.schema.GraphQLSchema;
import lombok.Setter;
import lombok.extern.java.Log;

/**
 * A simple place to keep the generated schema.
 * The Schema is generate on start up
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@Log
@ApplicationScoped
public class SchemaProducer {
    
    @Produces @Setter
    private GraphQLSchema graphQLSchema;
    
}