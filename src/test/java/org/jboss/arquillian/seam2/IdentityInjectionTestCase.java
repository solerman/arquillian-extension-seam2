/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.seam2;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.seam2.test.identity.IdentityStub;
import org.jboss.arquillian.seam2.test.simple.FluidOuncesConverter;
import org.jboss.seam.annotations.In;
import org.jboss.seam.security.Identity;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Arquillian.class)
public class IdentityInjectionTestCase
{
   @Deployment
   public static Archive<?> createDeployment()
   {
      return ShrinkWrap.create(WebArchive.class, "test.war")
                       .addClass(IdentityStub.class)
                       .addPackages(true, "org.fest")
                       .addPackages(true, "org.dom4j") // Required for JBoss AS 4.2.3.GA
                       .addAsResource(EmptyAsset.INSTANCE, "seam.properties")
                       .setWebXML("web.xml");
   }

   @In
   Identity identity;

   @Test
   public void shouldInjectIdentity() throws Exception
   {
      assertThat(identity).isNotNull().isInstanceOf(IdentityStub.class);
   }

}
