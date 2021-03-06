/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Lars Grefer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
		classes = JpaWebAutoConfiguration.class,
		webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
public class JpaWebAutoConfigurationTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void testFilterEnabled() {
		OpenEntityManagerInViewFilter filter = this.applicationContext.getBean(OpenEntityManagerInViewFilter.class);

		assertThat(filter).isNotNull();
	}

	@Test(expected = NoSuchBeanDefinitionException.class)
	public void testInterceptorDisabled() {
		this.applicationContext.getBean(OpenEntityManagerInViewInterceptor.class);
	}
}
