package common;

import org.testng.*;
import org.testng.log4testng.Logger;


public class ListenerSuitTestNG implements ISuiteListener, ITestListener {

    private static Logger logger = Logger.getLogger(ListenerSuitTestNG.class);

    @Override
    public void onFinish(ISuite suite) {
        logger.info("Finishing test suite: " + suite.getName());
    }

    @Override
    public void onStart(ISuite suite) {
        //String suiteName = suite.getName();

        logger.info("onStart suite started");

    }

    public void onStart(ITestContext context) {
        logger.info("onStart method started");
        logger.info("onStart method started " + context.getName());
    }

    public void onFinish(ITestContext context) {
        logger.info("onFinish method started");
    }

    public void onTestStart(ITestResult result) {
        logger.info("New Test Started: " + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        logger.info("onTestSuccess Method " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        logger.error("onTestFailure test " + result.getName());
        logger.error("onTestFailure Method " + result.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult result) {
        logger.error("onTestSkipped Method" +result.getName());

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        logger.error("onTestFailedButWithinSuccessPercentage" +result.getName());

    }

}
