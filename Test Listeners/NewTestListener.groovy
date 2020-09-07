import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

//import com.applitools.eyes.BatchInfo
//import com.applitools.eyes.RectangleSize
//import com.applitools.eyes.TestResultContainer
//import com.applitools.eyes.TestResultsSummary
//import com.applitools.eyes.config.Configuration
//import com.applitools.eyes.selenium.ClassicRunner
//import com.applitools.eyes.selenium.Eyes
//import com.applitools.eyes.BatchInfo
//import com.applitools.eyes.RectangleSize
//import com.applitools.eyes.TestResultContainer
//import com.applitools.eyes.TestResultsSummary
//import com.applitools.eyes.config.Configuration
//import com.applitools.eyes.selenium.ClassicRunner
//import com.applitools.eyes.selenium.Eyes

//import org.openqa.selenium.chrome.ChromeDriver
//import org.testng.ITestContext
//import org.testng.ITestResult
//import org.testng.annotations.AfterMethod
//import org.testng.annotations.BeforeMethod
//
//import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
//import com.kms.katalon.core.model.FailureHandling as FailureHandling
//import com.kms.katalon.core.testcase.TestCase as TestCase
//import com.kms.katalon.core.testdata.TestData as TestData
//import com.kms.katalon.core.testobject.TestObject as TestObject
//
//import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
//import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
//import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
//
//import internal.GlobalVariable as GlobalVariable
//
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
//
//class NewTestListener {
//	/**
//	 * Executes before every test case starts.
//	 * @param testCaseContext related information of the executed test case.
//	 */
//	
//	@BeforeMethod
//	public void beforeEachTest(ITestResult result) {
//		// Create the Eyes instance for the test and associate it with the runner
//		eyes = new Eyes(runner);
//		// Set the configuration values we set up in beforeTestSuite
//		eyes.setConfiguration(suiteConfig);
//		// Create a WebDriver for the test
//		webDriver = new ChromeDriver();
//		
//	}
//	
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseVariables()
	}
//
//	/**
//	 * Executes after every test case ends.
//	 * @param testCaseContext related information of the executed test case.
//	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseStatus()
	}
//
//	/**
//	 * Executes before every test suite starts.
//	 * @param testSuiteContext: related information of the executed test suite.
//	 */
////	@BeforeTestSuite
////	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
////			runner = new ClassicRunner();
////		// Create a configuration object, we will use this when setting up each test
////		   suiteConfig = new Configuration()
////			   // Checkpoint configurations
////			   .setForceFullPageScreenshot(true)
////			   .setStitchMode(StitchMode.CSS)
////			   .setHideScrollbars(true)
////			   .setHideCaret(true)
////			   .setViewportSize( new RectangleSize(viewPortWidth, viewPortHeight))
////			   // Test suite configurations
////			   .setApiKey(apiKey)
////			   .setServerUrl(myEyesServer)
////			   .setAppName(appName)
////			   .setBatch(new BatchInfo(batchName)
////			   /* ... more configurations */ );
////	
////	}
//
//	/**
//	 * Executes after every test suite ends.
//	 * @param testSuiteContext: related information of the executed test suite.
//	 */
//	
//	@AfterMethod
//	public void afterEachTest(ITestResult result) {
//		// check if an exception was thrown
//		boolean testPassed = result.getStatus() != ITestResult.FAILURE;
//		if (testPassed) {
//		   // Close the Eyes instance, no need to wait for results, we'll get those at the end in afterTestSuite
//			eyes.closeAsync();
//		} else {
//			// There was an exception so the test may be incomplete - abort the test
//			eyes.abortAsync();
//		}
//		webDriver.quit();
//	}
//
//	
////	@AfterTestSuite
////	public void afterTestSuite(ITestContext testContext) {
////		//Wait until the test results are available and retrieve them
////		TestResultsSummary allTestResults = runner.getAllTestResults(false);
////		for (TestResultContainer result : allTestResults) {
////			handleTestResults(result);
////		}
////	}
//}