package custom_keyword

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.junit.After as After
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import groovy.json.JsonSlurper as JsonSlurper
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.applitools.eyes.EyesRunner as EyesRunner
import com.applitools.eyes.RectangleSize as RectangleSize
import com.applitools.eyes.TestResultsSummary as TestResultsSummary
import com.applitools.eyes.selenium.BrowserType as BrowserType
import com.applitools.eyes.selenium.ClassicRunner as ClassicRunner
import com.applitools.eyes.selenium.Configuration as Configuration
import com.applitools.eyes.selenium.Eyes as Eyes
import com.applitools.eyes.selenium.StitchMode as StitchMode
import com.applitools.eyes.selenium.fluent.Target as Target
import com.applitools.eyes.visualgrid.model.DeviceName as DeviceName
import com.applitools.eyes.visualgrid.services.VisualGridRunner as VisualGridRunner

import internal.GlobalVariable

public class Applitools {

	@Keyword
	def Custom_capture(height, width){

		EventFiringWebDriver eventFiring = (DriverFactory.getWebDriver()) as EventFiringWebDriver
		WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
		RemoteWebDriver innerDriver = (wrappedWebDriver) as RemoteWebDriver

		EyesRunner runner = null

		// set this flag to true to make it run on Applitools Visual Grid
		boolean runOnGrid = false

		String testName = 'L1 Dashboard'
		String appName = 'Pulse insight'

		if (runOnGrid == true) {
			runner = new VisualGridRunner(10)
			testName += ' (Grid)'
		} else {
			runner = new ClassicRunner()
		}

		Eyes eyes = new Eyes(runner)
		Configuration config = eyes.getConfiguration()

		config.setApiKey('9wkJqyBoxcfKTKUDlbuvbtu5e01109eGuS99DPFuLmRyTc110')
		config.setViewportSize(new RectangleSize(height, width))
		config.setAppName(appName)
		config.setTestName(testName)
		eyes.setConfiguration(config)

		eyes.open(innerDriver)
		eyes.check(Target.window().fully().withName('Login Checkpoint'))
		eyes.closeAsync()

		TestResultsSummary result = runner.getAllTestResults(false)

		WebUI.comment(result.toString())
	}
}
