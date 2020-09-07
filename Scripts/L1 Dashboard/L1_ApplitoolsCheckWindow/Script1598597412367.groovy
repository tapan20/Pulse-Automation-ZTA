import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.URL)

WebUI.setText(findTestObject('Login objects/input_Username_username'), GlobalVariable.Username)

WebUI.setText(findTestObject('Login objects/input_Password_password'), GlobalVariable.Password)

WebUI.sendKeys(findTestObject('Login objects/input_Password_password'), Keys.chord(Keys.ENTER))

WebUI.delay(2)

//WebUI.click(findTestObject('Filter/1stColumn'))
//
////WebUI.click(findTestObject('Filter/1stColumn'))
//WebUI.click(findTestObject('Filter/2ndColumn'))
//
//WebUI.click(findTestObject('Filter/3rdColumn'))
//
//WebUI.click(findTestObject('Filter/4rdColumn'))
//
//WebUI.click(findTestObject('Filter/5rdColumn'))
// Cast Katalon's WebDriver into EventFiringWebDriver
EventFiringWebDriver eventFiring = ((DriverFactory.getWebDriver()) as EventFiringWebDriver)

// Get the driver wrapped inside
WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()

// Cast the wrapped driver into RemoteWebDriver
RemoteWebDriver innerDriver = ((wrappedWebDriver) as RemoteWebDriver)

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

config.setViewportSize(new RectangleSize(1440, 800))

config.setAppName(appName)

config.setTestName(testName)

//if (runOnGrid == true) {
//    config.addBrowser(1200, 800, BrowserType.CHROME)
//
//    config.addBrowser(1200, 800, BrowserType.FIREFOX)
//} else {
//    config.setForceFullPageScreenshot(true)
//
//    config.setStitchMode(StitchMode.CSS)
//}
eyes.setConfiguration(config)

eyes.open(innerDriver)

eyes.check(Target.window().fully().withName('Login Checkpoint'))

eyes.closeAsync()

TestResultsSummary result = runner.getAllTestResults(false)

WebUI.comment(result.toString())

WebUI.closeBrowser()

