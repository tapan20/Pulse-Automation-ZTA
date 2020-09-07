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

WebUI.sendKeys(findTestObject('Login objects/input_Password_password'), Keys.chord(Keys.ENTER))

WebUI.delay(2)

CustomKeywords.'custom_keyword.keywords.apply_CurrentDayfilter'()

WebUI.scrollToElement(findTestObject('Filter object/ScrollToLocations'), 2)

def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/TopLocations.json')

def result = slurper.parse(jsontxt)

try {
    ArrayList<String> tooltip = new ArrayList<String>()

    ArrayList<String> locName = new ArrayList<String>()

    ArrayList<String> JSON_Name = new ArrayList<String>()

    ArrayList<String> JSON_Value = new ArrayList<String>()

    for (int i = 0; i < result.size(); i++) {
        (JSON_Name[i]) = result[i].name

        (JSON_Value[i]) = result[i].value

        String topLocationsValueCSS = ('#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' + 
        (i + 3)) + ') > g > text > tspan'

        topLocations_Value = new TestObject('customObject')

        topLocations_Value.addProperty('css', ConditionType.EQUALS, topLocationsValueCSS)

        (locName[i]) = WebUI.getText(topLocations_Value)

        String topLocationsTooltipCSS = ('#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' + 
        (i + 1)) + ') > g > g > g > g > path'

        mouseHover_loc = new TestObject('customObject')

        mouseHover_loc.addProperty('css', ConditionType.EQUALS, topLocationsTooltipCSS)

        WebUI.mouseOver(mouseHover_loc)

        try {
            topLocations_Tooltip = new TestObject('customObject')

            topLocations_Tooltip.addProperty('css', ConditionType.EQUALS, '#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

            (tooltip[i]) = WebUI.getText(topLocations_Tooltip)
        }
        catch (Exception e) {
            e.print('No tooltip found')
        } 
    }
    
    for (j = 0; j < result.size(); j++) {
        for (k = 0; k < result.size(); k++) {
            if ((JSON_Name[j]) == (locName[k])) {
                if (WebUI.verifyEqual(tooltip[j], JSON_Value[k])) {
                    print('values from json and UI are matched ' + JSON_Name[j] + ' ' + locName[k] + ' ' + 
                        tooltip[j] + ' ' + JSON_Value[k])
                } //print('values from json and UI are not matched')
                else {
                }
            } else {
            }
        }
    }
    
    print(tooltip)

    print(locName)

    print(JSON_Value)

    print(JSON_Name)
}
catch (Exception e) {
    e.print('test end')
} 

WebUI.closeBrowser()

