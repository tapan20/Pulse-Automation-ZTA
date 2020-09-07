import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import groovy.json.JsonSlurper as JsonSlurper

CustomKeywords.'custom_keyword.keywords.login'()

CustomKeywords.'custom_keyword.keywords.apply_filter'()

WebUI.scrollToElement(findTestObject('Filter object/ScrollToApplications'), 2)

def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/TopActiveApplications.json')

def result = slurper.parse(jsontxt)

try {
    ArrayList<String> tooltip = new ArrayList<String>()

    ArrayList<String> appName = new ArrayList<String>()

    ArrayList<String> JSON_Name = new ArrayList<String>()

    ArrayList<String> JSON_Value = new ArrayList<String>()

    for (int i = 0; i < 6; i++) {
         String css1 = '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' + 
        (i + 4) + ') > g > text > tspan'

        myTestOject1 = new TestObject('customObject')

        myTestOject1.addProperty('css', ConditionType.EQUALS, css1)
		
		

        (appName[i]) = WebUI.getText(myTestOject1)

        for (j = 0; j < 6; j++) {
            if (result[j].name == (appName[i])) {
                myTestOject = new TestObject('customObject')
                myTestOject.addProperty('css', ConditionType.EQUALS, css)
                WebUI.mouseOver(myTestOject)

                WebUI.delay(2)

                try {
                    Tooltip = new TestObject('customObject')
                    Tooltip.addProperty('css', ConditionType.EQUALS, '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')
                    (tooltip[j]) = WebUI.getText(Tooltip)
                    WebUI.verifyEqual(tooltip[j], result[j].value)

                    break
                }
                catch (Exception e) {
                    e.print('test end')
                } 
            }
        }
    }
}
catch (Exception e) {
    e.print('test end')
}