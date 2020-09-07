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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl(GlobalVariable.URL)

WebUI.setText(findTestObject('Login objects/input_Username_username'), GlobalVariable.UserAdmin)

WebUI.setText(findTestObject('Login objects/input_Password_password'), GlobalVariable.UserPass)

WebUI.click(findTestObject('Login objects/singin button'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

CustomKeywords.'custom_keyword.keywords.apply_CurrentDayfilter'()
WebUI.scrollToElement(findTestObject('Filter object/ScrollToApplications'), 2)

def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/TopActiveApplications.json')

def result = slurper.parse(jsontxt)

try {
    ArrayList<String> tooltip = new ArrayList<String>()

    ArrayList<String> appName = new ArrayList<String>()

    ArrayList<String> JSON_Name = new ArrayList<String>()

    ArrayList<String> JSON_Value = new ArrayList<String>()

    for (i = 0; i < result.size(); i++) {
		
        (JSON_Name[i]) = result[i].name
        (JSON_Value[i]) = result[i].value

        String topApplicationsValueCSS = ('#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' + 
        (i + 3)) + ') > g > text > tspan'
        topApplications_Value = new TestObject('customObject')
        topApplications_Value.addProperty('css', ConditionType.EQUALS, topApplicationsValueCSS)
        (appName[i]) = WebUI.getText(topApplications_Value)

        String topApplicationsTooltipCSS = ('#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' + 
        (i + 1)) + ') > g > g > g > g > path'
        mouseHover_App = new TestObject('customObject')
        mouseHover_App.addProperty('css', ConditionType.EQUALS, topApplicationsTooltipCSS)
        WebUI.mouseOver(mouseHover_App)

        try {
            topApplications_Tooltip = new TestObject('customObject')
            topApplications_Tooltip.addProperty('css', ConditionType.EQUALS, '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')
			WebUI.waitForElementPresent(topApplications_Tooltip, 2)
            (tooltip[i]) = WebUI.getText(topApplications_Tooltip)
        }
        catch (Exception e) {
            e.print('No tooltip found')
        } 
    }
	
//	for (j = 0; j < result.size(); j++) {
//		for (k = 0; k < result.size(); k++) {
//			if ((JSON_Name[j]) == (appName[k])) {
//				if (WebUI.verifyEqual(tooltip[j], JSON_Value[k])) {
//					print('values from json and UI are matched ' + JSON_Name[j] +' '+ appName[k] +' '+ JSON_Value[k] + ' '+tooltip[j])
//				} else {
//					
//				}
//			}else {
//			//print('values from json and UI are not matched')
//				}
//		}
//	}
	
	
	for (j = 0; j < result.size(); j++) {
	
			if (JSON_Name.contains(appName[j])) {
				def index = JSON_Name.indexOf(appName[j])
				print(index)
				if (WebUI.verifyEqual(tooltip[j], JSON_Value[index])) {
					println('true')
				} else {
					
				}
			}else {
			println('false')
				}
		
	}
	
    print(tooltip)
    print(appName)
    print(JSON_Value)
    print(JSON_Name)
}
catch (Exception e) {
    e.print('test end')
} 

WebUI.closeBrowser()

