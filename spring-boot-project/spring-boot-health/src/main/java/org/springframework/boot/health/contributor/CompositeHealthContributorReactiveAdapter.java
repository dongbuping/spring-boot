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

import java.util.Iterator;

import org.springframework.util.Assert;

/**
 * Adapts a {@link CompositeHealthContributor} to a
 * {@link CompositeReactiveHealthContributor} so that it can be safely invoked in a
 * reactive environment.
 *
 * @author Phillip Webb
 * @see ReactiveHealthContributor#adapt(HealthContributor)
 */
class CompositeHealthContributorReactiveAdapter implements CompositeReactiveHealthContributor {

	private final CompositeHealthContributor delegate;

	CompositeHealthContributorReactiveAdapter(CompositeHealthContributor delegate) {
		Assert.notNull(delegate, "'delegate' must not be null");
		this.delegate = delegate;
	}

	@Override
	public Iterator<ReactiveHealthContributors.Entry> iterator() {
		return this.delegate.stream()
			.map((entry) -> new ReactiveHealthContributors.Entry(entry.name(),
					ReactiveHealthContributor.adapt(entry.contributor())))
			.iterator();
	}

	@Override
	public ReactiveHealthContributor getContributor(String name) {
		HealthContributor contributor = this.delegate.getContributor(name);
		return (contributor != null) ? ReactiveHealthContributor.adapt(contributor) : null;
	}

}
