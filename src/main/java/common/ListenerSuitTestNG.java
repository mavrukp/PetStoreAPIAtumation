package common;

import org.testng.*;


public class ListenerSuitTestNG implements ISuiteListener, ITestListener {

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Finishing test suite: " + suite.getName());
        String suiteName = suite.getName();
    }

    @Override
    public void onStart(ISuite suite) {
        //String suiteName = suite.getName();

        System.out.println("onStart suite started");

    }

    public void onStart(ITestContext context) {
        System.out.println("onStart method started");
        System.out.println("onStart method started " + context.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println("onFinish method started");
    }

    public void onTestStart(ITestResult result) {
        System.out.println("New Test Started: " + result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess Method " + result.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure test " + result.getName());
        System.out.println("onTestFailure Method " + result.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped Method" +result.getName());

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage" +result.getName());

    }

}
