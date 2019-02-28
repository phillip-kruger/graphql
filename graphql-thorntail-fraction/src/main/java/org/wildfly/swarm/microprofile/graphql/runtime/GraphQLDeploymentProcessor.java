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

package org.wildfly.swarm.microprofile.graphql.runtime;

import java.util.Optional;
import javax.inject.Inject;
import lombok.extern.java.Log;
import org.jboss.shrinkwrap.api.Archive;

import org.wildfly.swarm.container.runtime.cdi.DeploymentContext;
import org.wildfly.swarm.spi.api.DeploymentProcessor;
import org.wildfly.swarm.spi.runtime.annotations.DeploymentScoped;
import org.wildfly.swarm.undertow.WARArchive;
import org.wildfly.swarm.undertow.descriptors.JBossWebContainer;


/**
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
@DeploymentScoped
@Log
public class GraphQLDeploymentProcessor implements DeploymentProcessor {

    private static final String LISTENER_CLASS = "com.github.phillipkruger.graphql.core.GraphQLListener";

    private final Archive archive;

    @Inject
    DeploymentContext deploymentContext;

    /**
     * Constructor.
     *
     * @param archive
     */
    @Inject
    public GraphQLDeploymentProcessor(Archive archive) {
        this.archive = archive;
    }

    /**
     * Process the deployment in order to produce an GraphQL document.
     *
     * @see org.wildfly.swarm.spi.api.DeploymentProcessor#process()
     */
    @Override
    public void process() throws Exception {
//        // if the deployment is Implicit, we don't want to process it
        if (deploymentContext != null && deploymentContext.isImplicit()) {
            return;
        }
//        try {
            //Register GraphQLListener
//            WARArchive warArchive = archive.as(WARArchive.class)
//                .addDependency("com.graphql-java:graphql-java:11.0")
//                .addDependency("com.graphql-java-kickstart:graphql-java-servlet:7.0.0")
//                .addDependency("io.leangen.graphql:spqr:1.0.0-SNAPSHOT")
//                .addDependency("com.github.phillip-kruger.graphql:graphql-core:1.0.0-SNAPSHOT")
//                .addDependency("io.microprofile.sandbox:microprofile-graphql-api:1.0.0-SNAPSHOT")
//                .addDependency("io.github.classgraph:classgraph:4.6.7")
//                .addDependency("io.leangen.geantyref:geantyref:1.3.6")
//                .addDependency("org.reactivestreams:reactive-streams:1.0.2");
            
//            .addAllDependencies();
            
//            warArchive.findWebXmlAsset().addListener(LISTENER_CLASS);
            
            // first pass: jboss-web context root
//            Optional<String> jbossWebContext = Optional.empty();
  
//            if (archive.getName().endsWith(".war")) {
//                JBossWebContainer war = archive.as(WARArchive.class);
//                if (war.getContextRoot() != null) {
//                    jbossWebContext = Optional.of(war.getContextRoot());
//                }
//            }
//            if(jbossWebContext.isPresent()){
//                log.severe("jbossWebContext >>>>>>>>>>>>> " + jbossWebContext.get());
//            }
//            String context = warArchive.getContextRoot();
//            log.severe("context >>>>>>>>>>>>> " + jbossWebContext.get());
            
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to register OpenAPI listener", e);
//        }

    }

}
