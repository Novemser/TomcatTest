/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.el.parser;

import junit.framework.TestCase;
import org.apache.jasper.el.ELContextImpl;
import org.junit.Test;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

public class TestELParser extends TestCase {

    @Test
    public void testJavaKeyWordSuffix() {
        ExpressionFactory factory = ExpressionFactory.newInstance();
        ELContext context = new ELContextImpl();

        TesterBeanA beanA = new TesterBeanA();
        beanA.setInt("five");
        ValueExpression var =
                factory.createValueExpression(beanA, TesterBeanA.class);
        context.getVariableMapper().setVariable("beanA", var);

        // Should fail
        Exception e = null;
        try {
            ValueExpression ve = factory.createValueExpression(context, "${beanA.int}",
                    String.class);
        } catch (ELException ele) {
            e = ele;
        }
        assertNotNull(e);
    }

    public void testBug49081() {
        // OP's report
        testExpression("#${1+1}", "#2");
        
        // Variations on a theme
        testExpression("#", "#");
        testExpression("##", "##");
        testExpression("###", "###");
        testExpression("$", "$");
        testExpression("$$", "$$");
        testExpression("$$$", "$$$");
        testExpression("#$", "#$");
        testExpression("#$#", "#$#");
        testExpression("$#", "$#");
        testExpression("$#$", "$#$");

        testExpression("#{1+1}", "2");
        testExpression("##{1+1}", "#2");
        testExpression("###{1+1}", "##2");
        testExpression("${1+1}", "2");
        testExpression("$${1+1}", "$2");
        testExpression("$$${1+1}", "$$2");
        testExpression("#${1+1}", "#2");
        testExpression("#$#{1+1}", "#$2");
        testExpression("$#{1+1}", "$2");
        testExpression("$#${1+1}", "$#2");
}

    private void testExpression(String expression, String expected) {
        ExpressionFactory factory = ExpressionFactory.newInstance();
        ELContext context = new ELContextImpl();
        
        ValueExpression ve = factory.createValueExpression(
                context, expression, String.class);

        String result = (String) ve.getValue(context);
        assertEquals(expected, result);
    }
}
