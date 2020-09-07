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

WebUI.delay(3)

WebUI.click(findTestObject('Filter object/filter_button'))

WebUI.click(findTestObject('Filter object/set_filter'))

WebUI.click(findTestObject('Filter object/down_arrow'))

WebUI.click(findTestObject('Filter object/Custom_filter'))

WebUI.click(findTestObject('Filter object/Custom_Aug'))

WebUI.click(findTestObject('Filter object/Custom_Aug_27'))

WebUI.click(findTestObject('Filter object/Apply filter'))

WebUI.scrollToElement(findTestObject('Filter object/ScrollToApplications'), 2)

def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/TopActiveApplications.json')

def result = slurper.parse(jsontxt)

try {
    ArrayList<String> tooltip = new ArrayList<String>()

    ArrayList<String> appName = new ArrayList<String>()

    ArrayList<String> JSON_Name = new ArrayList<String>()

    ArrayList<String> JSON_Value = new ArrayList<String>()

    for (int i = 0; i < result.size(); i++) {
        print(result.size)

        (JSON_Name[i]) = result[i].name

        (JSON_Value[i]) = result[i].value

        String css1 = ('#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' + 
        (i + 4)) + ') > g > text > tspan'

        myTestOject1 = new TestObject('customObject')

        myTestOject1.addProperty('css', ConditionType.EQUALS, css1)

        (appName[i]) = WebUI.getText(myTestOject1)

        // WebUI.delay(2)
        String css = ('#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' + 
        (i + 1)) + ') > g > g > g > g > path'

        myTestOject = new TestObject('customObject')

        myTestOject.addProperty('css', ConditionType.EQUALS, css)

        WebUI.mouseOver(myTestOject)

        //  WebUI.delay(2)
        try {
            Tooltip = new TestObject('customObject')

            Tooltip.addProperty('css', ConditionType.EQUALS, '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

            (tooltip[i]) = WebUI.getText(Tooltip //            WebUI.verifyEqual(tooltip[i], JSON_Value[i])
                ) //
            //            WebUI.verifyEqual(appName[i], JSON_Name[i])
        }
        catch (Exception e) {
            e.print('No tooltip found')
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

