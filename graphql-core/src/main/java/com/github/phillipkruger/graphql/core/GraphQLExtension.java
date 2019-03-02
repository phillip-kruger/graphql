/**
 * Copyright 2019 Phillip Kruger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.phillipkruger.graphql.core;

import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessBean;
import lombok.extern.java.Log;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;
import org.eclipse.microprofile.graphql.GraphQLApi;

/**
 * This is a CDI extension that detects GraphQL components 
 * and the GraphQL Schema
 * 
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@Log
public class GraphQLExtension implements Extension {

    private final List<Bean> graphQLComponents = new ArrayList<>();
    
    private GraphQLSchema schema;
    
    private void createGraphQLSchema(@Observes AfterDeploymentValidation abd, BeanManager beanManager) {
        GraphQLSchemaGenerator schemaGen = new GraphQLSchemaGenerator()
                .withResolverBuilders(new AnnotatedResolverBuilder());
        
        for(Bean component:graphQLComponents){
            Type beanClass = component.getBeanClass();
            CreationalContext<?> creationalContext = beanManager.createCreationalContext(component);
            schemaGen.withOperationsFromSingleton(
                    beanManager.getReference(component, beanClass, creationalContext), 
                    component.getBeanClass());
            log.log(Level.INFO, "GraphQL Component [{0}] registered", component.getBeanClass());
        }
        schema = schemaGen.generate();
        
        SchemaProducer schemaProducer = CDI.current().select(SchemaProducer.class).get();
        schemaProducer.setGraphQLSchema(schema);
        
        log.info("All GraphQL Components added to the endpoint.");    
    }
    
    // Detect and store GraphQLComponents
    private <X> void detectGraphQLApiBeans(@Observes ProcessBean<X> event) {
        if (event.getAnnotated().isAnnotationPresent(GraphQLApi.class)) {
            graphQLComponents.add(event.getBean());
        }
    }   
}