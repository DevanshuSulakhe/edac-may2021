package retryMechanism;


import org.testng.ITestListener;

import org.testng.ITestResult;

import testBase.TestBase;




public class ITestListner extends TestBase implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		captureScreenShot(result.getMethod().getMethodName() + ".jpg");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Test is starting....");
	}
}