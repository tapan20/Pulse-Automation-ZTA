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
import org.openqa.selenium.TakesScreenshot
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import groovy.json.JsonSlurper as JsonSlurper
import org.openqa.selenium.remote.RemoteWebDriver as RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver as EventFiringWebDriver
import com.applitools.eyes.selenium.Eyes as Eyes
import com.applitools.eyes.selenium.StitchMode as StitchMode
import com.applitools.eyes.selenium.fluent.Target as Target
import com.applitools.eyes.visualgrid.model.DeviceName as DeviceName
import com.applitools.eyes.visualgrid.services.VisualGridRunner as VisualGridRunner
import org.openqa.selenium.OutputType
import org.apache.commons.io.FileUtils
import java.io.File;
import javax.imageio.ImageIO;
import org.openqa.selenium.firefox.FirefoxDriver;
import groovy.json.JsonOutput
import ru.yandex.qatools.ashot.AShot
import ru.yandex.qatools.ashot.Screenshot
import ru.yandex.qatools.ashot.coordinates.Coords
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider
import ru.yandex.qatools.ashot.shooting.ShootingStrategies

import internal.GlobalVariable

public class Applitools {

	//	@Keyword
	//	def Custom_capture(height, width){
	//

	//		GlobalVariable.Width, GlobalVariable.Height
	//		width, height
	//		WebUI.setViewPortSize(width, height)

	//		EventFiringWebDriver eventFiring = (DriverFactory.getWebDriver()) as EventFiringWebDriver
	//		WebDriver wrappedWebDriver = eventFiring.getWrappedDriver()
	//		RemoteWebDriver innerDriver = (wrappedWebDriver) as RemoteWebDriver
	//
	//		EyesRunner runner = null
	//
	//		// set this flag to true to make it run on Applitools Visual Grid
	//		boolean runOnGrid = false
	//
	//		String testName = 'L1 Dashboard'
	//		String appName = 'Pulse insight'
	//
	//		if (runOnGrid == true) {
	//			runner = new VisualGridRunner(10)
	//			testName += ' (Grid)'
	//		} else {
	//			runner = new ClassicRunner()
	//		}
	//
	//		Eyes eyes = new Eyes(runner)
	//		Configuration config = eyes.getConfiguration()
	//
	//		config.setApiKey('9wkJqyBoxcfKTKUDlbuvbtu5e01109eGuS99DPFuLmRyTc110')
	//		config.setViewportSize(new RectangleSize(height, width))
	//		config.setAppName(appName)
	//		config.setTestName(testName)
	//		eyes.setConfiguration(config)
	//
	//		eyes.open(innerDriver)
	//		eyes.check(Target.window().fully().withName('Login Checkpoint'))
	//		eyes.closeAsync()
	//
	//		TestResultsSummary result = runner.getAllTestResults(false)
	//
	//		WebUI.comment(result.toString())
	//	}

	@Keyword
	def captureScreenshot() throws IOException {
		
		
		//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//		FileUtils.copyFile(scrFile,
		//				new File(System.getProperty("user.dir") + "/Screenshots/" + screenshotName));
		
		Date d = new Date();
		String screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		WebDriver driver = DriverFactory.getWebDriver()
		Screenshot screenshot=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(),"PNG",new File(System.getProperty("user.dir") + '/Screenshots/' + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}
