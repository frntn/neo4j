/**
 * Copyright (c) 2002-2015 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.cypher.internal.compiler.v2_2.functions

import org.neo4j.cypher.internal.compiler.v2_2.symbols._

class ToStringTest extends FunctionTestBase("toString")  {

  test("should accept correct types") {
    testValidTypes(CTString)(CTString)
    testValidTypes(CTFloat)(CTString)
    testValidTypes(CTInteger)(CTString)
  }

  test("should fail type check for incompatible arguments") {
    testInvalidApplication(CTCollection(CTAny))(
      "Type mismatch: expected Float, Integer or String but was Collection<Any>"
    )

    testInvalidApplication(CTNode)(
      "Type mismatch: expected Float, Integer or String but was Node"
    )
  }

  test("should fail if wrong number of arguments") {
    testInvalidApplication()(
      "Insufficient parameters for function 'toString'"
    )
    testInvalidApplication(CTString, CTString)(
      "Too many parameters for function 'toString'"
    )
  }
}
