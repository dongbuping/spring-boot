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

package org.springframework.boot.health.contributor;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.util.Assert;

/**
 * A {@link ContributedHealth} that is composed of other {@link ContributedHealth}
 * instances. Used to provide a unified view of related components. For example, a
 * database health indicator may be a composite containing the {@link Health} of each
 * datasource connection.
 *
 * @author Phillip Webb
 * @since 4.0.0
 */
public non-sealed class CompositeHealth extends ContributedHealth {

	private final Status status;

	private final Map<String, ContributedHealth> components;

	public CompositeHealth(Status status, Map<String, ContributedHealth> components) {
		Assert.notNull(status, "'status' must not be null");
		this.status = status;
		this.components = (components != null) ? new TreeMap<>(components) : components;
	}

	@Override
	public Status getStatus() {
		return this.status;
	}

	@JsonInclude(Include.NON_EMPTY)
	public Map<String, ContributedHealth> getComponents() {
		return this.components;
	}

}
