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
import graphql.servlet.SimpleGraphQLHttpServlet;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Dynamically adding the Endpoint and Schema Servlets
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@WebListener
@Log
public class GraphQLListener implements ServletContextListener {
    @Inject
    private GraphQLSchema schema;
    @Inject @ConfigProperty(name = "microprofile.graphql.contextpath", defaultValue = "graphql")
    private String path;
    @Inject @ConfigProperty(name = "microprofile.graphql.servletname", defaultValue = "GraphQLServlet")
    private String servletname;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        // The Endpoint
        SimpleGraphQLHttpServlet graphQLServlet = SimpleGraphQLHttpServlet.newBuilder(schema).build();
        ServletRegistration.Dynamic endpointservlet = context.addServlet(servletname, graphQLServlet);
        endpointservlet.addMapping("/" + path + "/*");
        
        // The Schema
        GraphQLSchemaServlet graphQLSchemaServlet = new GraphQLSchemaServlet(schema);
        ServletRegistration.Dynamic schemaservlet = context.addServlet(servletname + "Schema", graphQLSchemaServlet);
        schemaservlet.addMapping("/" + path + "/schema.graphql");
    }
}

