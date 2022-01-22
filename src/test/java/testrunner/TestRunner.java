package testrunner;

import booking.DeleteBooking;
import booking.GetBookingIds;
import booking.PartialUpdateBooking;
import org.junit.rules.TestWatcher;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import testutilities.CustomExecutionListener;

import static org.slf4j.LoggerFactory.getLogger;

public class TestRunner extends TestWatcher {

    private static final Logger LOGGER = getLogger(TestRunner.class);

    static JUnitCore junitCore;

    public static void main(String[] args) {

        LOGGER.info("********   EXECUTION STARTED   ***********");
        junitCore = new JUnitCore();
        junitCore.addListener(new CustomExecutionListener());

        Result result = junitCore.run(GetBookingIds.class, PartialUpdateBooking.class, DeleteBooking.class);
        for (Failure failure : result.getFailures()) {
            LOGGER.info(failure.toString());
        }

        LOGGER.info("Execution Successful : " + result.wasSuccessful());
        LOGGER.info("********   EXECUTION FINISHED   ***********");
    }
}

