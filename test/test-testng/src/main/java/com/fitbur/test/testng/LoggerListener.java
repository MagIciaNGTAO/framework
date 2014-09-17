/*
 * Copyright 2014 Sharmarke Aden.
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
package com.fitbur.test.testng;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;

/**
 *
 * @author Sharmarke Aden
 */
public class LoggerListener implements
        ISuiteListener,
        IInvokedMethodListener {

    private final Logger logger = LoggerFactory.getLogger("test");

    @Override
    public void onStart(ISuite suite) {
        logger.info("Started Suite [{}]", suite.getName());
    }

    @Override
    public void onFinish(ISuite suite) {
        logger.info("Finished Suite [{}]", suite.getName());
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            logger.info("> Running [{}.{}]",
                    method.getTestMethod().getTestClass().getRealClass().getSimpleName(),
                    method.getTestMethod().getMethodName());
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            logger.debug("> Finished [{}.{}]",
                    method.getTestMethod().getTestClass().getRealClass().getSimpleName(),
                    method.getTestMethod().getMethodName());
        }
    }
}
