package testrunner;

import booking.DeleteBooking;
import booking.GetBookingIds;
import booking.PartialUpdateBooking;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class TestRunner {

    private static final Logger LOGGER = getLogger(TestRunner.class);


    public static void main(String[] args) {

        Result result = JUnitCore.runClasses(GetBookingIds.class, PartialUpdateBooking.class, DeleteBooking.class);

        for (Failure failure : result.getFailures()) {
            LOGGER.info("********   EXECUTION FAILED, CHECK LOGS    ***********");
        }

        LOGGER.info("********   EXECUTION SUCCESSFUL   ***********");
    }
}
