package com.github.phillipkruger.graphql.core;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import graphql.schema.GraphQLSchema;
import lombok.Setter;
import lombok.extern.java.Log;

@Log
@ApplicationScoped
public class SchemaProducer {
    
    @Produces @Setter
    private GraphQLSchema graphQLSchema;
    
}