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