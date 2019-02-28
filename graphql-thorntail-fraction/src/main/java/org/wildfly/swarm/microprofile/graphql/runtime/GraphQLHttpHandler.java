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


import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;
import io.undertow.util.Methods;

/**
 * @author Phillip Kruger (phillip.kruger@redhat.com)
 */
public class GraphQLHttpHandler implements HttpHandler {

    private static final String PATH = "/graphql";
    
    private final HttpHandler next;

    public GraphQLHttpHandler(HttpHandler next) {
        this.next = next;
    }

    /**
     * @see io.undertow.server.HttpHandler#handleRequest(io.undertow.server.HttpServerExchange)
     */
    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        if (PATH.equalsIgnoreCase(exchange.getRequestPath())) {
            if (exchange.getRequestMethod().equals(Methods.GET)) {
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
                exchange.getResponseSender().send("{'hello':'world'}");
            } else {
                next.handleRequest(exchange);
            }
        } else {
            next.handleRequest(exchange);
        }
    }

}
