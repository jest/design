package org.osgi.cdi.examples.component;

import javax.enterprise.event.Observes;

import org.osgi.cdi.examples.Foo;
import org.osgi.cdi.examples.Holder;
import org.osgi.service.cdi.ReferenceEvent;
import org.osgi.service.cdi.ReferenceEventCustomizer;
import org.osgi.service.cdi.ReferenceServiceObjects;
import org.osgi.service.cdi.annotations.Component;
import org.osgi.service.cdi.annotations.Greedy;
import org.osgi.service.cdi.annotations.Reference;

/*
 * Examples of multiple, dynamic, greedy references
 */

@Component
public class CE6 {

	void observeWithCustomizer(@Observes @Greedy @Reference ReferenceEvent<ReferenceServiceObjects<Foo>> event) {
		event.dispatch(new ReferenceEventCustomizer<ReferenceServiceObjects<Foo>, Holder<ReferenceServiceObjects<Foo>>>() {

			@Override
			public Holder<ReferenceServiceObjects<Foo>> adding(ReferenceServiceObjects<Foo> foo) {
				return new Holder<>(foo);
			}

			@Override
			public void modified(ReferenceServiceObjects<Foo> foo, Holder<ReferenceServiceObjects<Foo>> h) {
				// stub
			}

			@Override
			public void removed(ReferenceServiceObjects<Foo> foo, Holder<ReferenceServiceObjects<Foo>> h) {
				// stub
			}

		});
	}

	void observeWithCallbacks(@Observes @Greedy @Reference ReferenceEvent<ReferenceServiceObjects<Foo>> event) {
		event.dispatch(foo -> new Holder<>(foo), (foo,h) -> {}, (foo,h) -> {});
	}

}