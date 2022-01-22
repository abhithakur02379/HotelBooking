package testutilities;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;
import testrunner.TestRunner;

import static org.slf4j.LoggerFactory.getLogger;

public class CustomExecutionListener extends RunListener {

    private static final Logger LOGGER = getLogger(TestRunner.class);


    public void testRunStarted(Description description) {
        LOGGER.info("Number of tests to execute: " + description.testCount());
    }

    public void testRunFinished(Result result) {
        LOGGER.info("Number of tests executed: " + result.getRunCount());
    }

    public void testStarted(Description description) {
        LOGGER.info("Starting: " + description.getMethodName());
    }

    public void testFinished(Description description) {
        LOGGER.info("Finished: " + description.getMethodName());
    }

    public void testFailure(Failure failure) {
        LOGGER.info("Failed: " + failure.getDescription().getMethodName());
    }

    public void testAssumptionFailure(Failure failure) {
        LOGGER.info("Failed: " + failure.getDescription().getMethodName());
    }

}