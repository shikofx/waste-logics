package uk.co.myyard.devmaster.extensions;

import org.junit.jupiter.api.extension.*;
import uk.co.myyard.devmaster.logger.TestLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LoggerExtension
        implements Extension, TestWatcher, BeforeAllCallback, BeforeEachCallback, AfterAllCallback {

    private final List<TestResultStatus> testResultsStatus = new ArrayList<>();

    private String testClassName = "";

    @Override
    public void beforeAll(ExtensionContext context) {
        testClassName = context.getDisplayName();
        final String message = String.format("Test class `%s`", context.getDisplayName());
        TestLogger.log(getClass(), "info", message);
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        String parentName = context.getParent().orElseThrow().getDisplayName();
        String testName;
        if (parentName.equals(testClassName)) {
            testName = context.getDisplayName();
        } else {
            testName = context.getParent().get().getDisplayName() + " -> " + context.getDisplayName();
        }
        final String message = String.format("Test `%s`", testName);
        TestLogger.log(getClass(), "info", message);
    }

    private enum TestResultStatus {
        SUCCESSFUL, ABORTED, FAILED, DISABLED
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        final String message = String.format("Test Disabled for test %s: with reason:- %s",
                context.getDisplayName(), reason.orElse("No reason"));
        TestLogger.log(getClass(), "error", message);

        testResultsStatus.add(TestResultStatus.DISABLED);
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        final String message = String.format("Test Successful for test %s: ", context.getDisplayName());
        TestLogger.log(getClass(), "info", message);
        testResultsStatus.add(TestResultStatus.SUCCESSFUL);
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        final String message = String.format("Test Aborted for test %s: with reason:- %s",
                context.getDisplayName(), cause.getLocalizedMessage());
        TestLogger.log(getClass(), "error", message);

        testResultsStatus.add(TestResultStatus.ABORTED);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        final String message = String.format("Test Aborted for test %s: with reason:- %s",
                context.getDisplayName(), cause.getLocalizedMessage());

        TestLogger.log(getClass(), "error", message);

        testResultsStatus.add(TestResultStatus.FAILED);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        Map<TestResultStatus, Long> summary = testResultsStatus.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final String message = String.format("Test result summary for %s %s",
                context.getDisplayName(), summary.toString());

        TestLogger.log(getClass(), "error", message);
    }


}
