/*
 * Copyright 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.endpoint.web;

import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.Operation;

/**
 * Information describing an endpoint that can be exposed by registering a servlet.
 *
 * @author Phillip Webb
 * @since 2.0.0
 * @deprecated since 3.3.0 in favor of {@code @Endpoint} and {@code @WebEndpoint}
 */
@Deprecated(since = "3.3.0", forRemoval = true)
@SuppressWarnings("removal")
public interface ExposableServletEndpoint extends ExposableEndpoint<Operation>, PathMappedEndpoint {

	/**
	 * Return details of the servlet that should be registered.
	 * @return the endpoint servlet
	 */
	EndpointServlet getEndpointServlet();

}
