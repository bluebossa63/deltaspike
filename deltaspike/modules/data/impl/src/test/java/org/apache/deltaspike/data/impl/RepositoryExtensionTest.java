/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.deltaspike.data.impl;

import static org.apache.deltaspike.data.test.util.TestDeployments.finalizeDeployment;
import static org.apache.deltaspike.data.test.util.TestDeployments.initDeployment;
import static org.junit.Assert.assertNotNull;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.apache.deltaspike.data.test.domain.Simple;
import org.apache.deltaspike.data.test.service.ExtendedRepositoryInterface;
import org.apache.deltaspike.data.test.service.RepositoryInterface;
import org.apache.deltaspike.data.test.service.SimpleRepository;
import org.apache.deltaspike.test.category.WebProfileCategory;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Category(WebProfileCategory.class)
public class RepositoryExtensionTest
{

    @Deployment
    public static Archive<?> deployment()
    {
        return finalizeDeployment(RepositoryExtensionTest.class,
                initDeployment()
                    .addClasses(RepositoryInterface.class,
                                ExtendedRepositoryInterface.class,
                                SimpleRepository.class)
                    .addPackages(true, Simple.class.getPackage()));
    }

    @Inject
    Instance<RepositoryInterface> repo;

    @Inject
    Instance<ExtendedRepositoryInterface> extendedRepo;

    @Inject
    Instance<SimpleRepository> extendedClassRepo;

    @Test
    public void should_inject()
    {
        assertNotNull(repo.get());
        assertNotNull(extendedRepo.get());
        assertNotNull(extendedClassRepo.get());
    }

}
