/*
 * JBoss, Home of Professional Open Source
 * Copyright 2009, Red Hat Middleware LLC, and individual contributors
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

import org.jboss.arquillian.seam2.test.simple.FluidOuncesConverter;
import org.jboss.seam.annotations.In;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public abstract class ComponentInjectionTestCase
{
   @In
   FluidOuncesConverter fluidOuncesConverter;

   @Test
   public void shouldInjectSeamComponent() throws Exception
   {
      assertThat(fluidOuncesConverter).isNotNull();
   }

   @Test
   public void shouldConvertFluidOuncesToMillilitres() throws Exception
   {
      // given
      Double ouncesToConver = Double.valueOf(8.0d);
      Double expectedMillilitres = Double.valueOf(236.5882368d);

      // when
      Double millilitres = fluidOuncesConverter.convertToMillilitres(ouncesToConver);

      // then
      assertThat(millilitres).isEqualTo(expectedMillilitres);

   }

}
