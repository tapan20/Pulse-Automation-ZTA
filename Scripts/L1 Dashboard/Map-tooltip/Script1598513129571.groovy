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
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.util.KeywordUtil


//CustomKeywords.'custom_keyword.keywords.login'()
//
//WebUI.delay(2)
//
//CustomKeywords.'custom_keyword.keywords.apply_filter'()
//
//WebUI.delay(3)
//
//CustomKeywords.'custom_keyword.keywords.Log_file'()
//
//CustomKeywords.'custom_keyword.keywords.json'()

/*******************************************************************************
 
  
 
   * This test case aims to match the values of Active Applications Info Panel from JSON with Web.
 
  
 
   * Test Steps:
 
   * 1. Log in with valid creds.
 
   * 2. Apply right filters ( last calendar week for now )
 
   * 3. Mouse hover on Maptool -tip
 
   * 4. read all the data from that tooltip
 
   * 5. Fetch the  Object from JSON.
 
   * 6. If matches, check for the remaining parameters.
 
   * 7. Print the results .
 
  
 
   ********************************************************************************/
KeywordUtil.logInfo("Maptool-tip \n \n")

int loop_count = GlobalVariable.result.location_view.result.data_points.size()

print(loop_count)

for (i = 3; i < 3+ loop_count; i++) {
    String cssMapTooltip = ('#dashboard-overview > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-MapChart-group > g.amcharts-Container.amcharts-Component.amcharts-Chart.amcharts-SerialChart.amcharts-MapChart > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-MapChart-group > g.amcharts-Container.amcharts-Component.amcharts-Chart.amcharts-SerialChart.amcharts-MapChart > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(5) > g > g:nth-child(' + 
    i) + ') > g > g.amcharts-Sprite-group.amcharts-Circle-group > circle'

    TestObject map_tooltip = new TestObject('customObject')

    map_tooltip.addProperty('css', ConditionType.EQUALS, cssMapTooltip)

    WebUI.mouseOver(map_tooltip)

    //For Gateways
    String Name = WebUI.getText(findTestObject('Map tooltip objects/Gateways - Text'))

    WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))

    String Gateway_name = GlobalVariable.result.location_view.result.data_points[(i - 3)].active_gateways.name

    String Gateway_value = GlobalVariable.result.location_view.result.data_points[(i - 3)].active_gateways.value

    //WebUI.verifyMatch((WebUI.getText(findTestObject('Map tooltip objects/Gateways - Text'))), Gateway_name ,false)
    //WebUI.verifyMatchl(WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways')), Gateway_value , true)
    if (Gateway_value == WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))) {
		
        println(((('Gateway value from Web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))) + 
            ' and Gateway value JSON is : ') + Gateway_value) + ' thus, it is correct \n')
		
		KeywordUtil.logInfo(((('Gateway value from Web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))) + 
            ' and Gateway value JSON is : ') + Gateway_value) + ' thus, it is correct \n')
    } else {
        println(((('Gateway value from Web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))) + 
            ' and Gateway value JSON is : ') + Gateway_value) + ' thus, it is incorrect \n')
		
		KeywordUtil.logInfo(((('Gateway value from Web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))) + 
            ' and Gateway value JSON is : ') + Gateway_value) + ' thus, it is incorrect \n')
    }
    
    // FOR USERS
    WebUI.getText(findTestObject('Map tooltip objects/Users - Text'))

    WebUI.getText(findTestObject('Map tooltip objects/Map-Users'))

    //String User_name = result.data_points[0].active_users.value
    String User_value = GlobalVariable.result.location_view.result.data_points[(i - 3)].active_users.value

    if (User_value == WebUI.getText(findTestObject('Map tooltip objects/Map-Users'))) {
        print(((('User value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Users'))) + ' User value from json is : ') + 
            User_value) + 'thus values are matched \n')
		KeywordUtil.logInfo(((('User value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Users'))) + ' User value from json is : ') + 
            User_value) + 'thus values are matched \n')
    } else {
        print(((('User value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Users'))) + ' User value from json is : ') + 
            User_value) + 'thus values are not matched \n')
		KeywordUtil.logInfo(((('User value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Users'))) + ' User value from json is : ') + 
            User_value) + 'thus values are not matched \n')
    }
    
    //DEVICES
    WebUI.getText(findTestObject('Map tooltip objects/Devices - Text'))

    WebUI.getText(findTestObject('Map tooltip objects/Map-Devices'))

    String Device_value = GlobalVariable.result.location_view.result.data_points[(i - 3)].active_devices.value

    if (Device_value == WebUI.getText(findTestObject('Map tooltip objects/Map-Devices'))) {
        print(((('Device value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Devices'))) + ' Device value from json is : ') + 
            Device_value) + 'thus values are matched \n')
		
		KeywordUtil.logInfo(((('Device value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Devices'))) + ' Device value from json is : ') + 
            Device_value) + 'thus values are matched \n')
    } 
	else {
        print(((('Device value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Devices'))) + ' Device value from json is : ') + 
            Device_value) + 'thus values are  not matched \n')
		KeywordUtil.logInfo(((('Device value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-Devices'))) + ' Device value from json is : ') + 
            Device_value) + 'thus values are  not matched \n')
    }
    
    // Non- compliance
    WebUI.getText(findTestObject('Map tooltip objects/Non compliance'))

    WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance'))

    String Non_compliance_value = GlobalVariable.result.location_view.result.data_points[(i - 3)].non_compliance_count.value

    if (Non_compliance_value == WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance'))) {
        print(((('Non-compliance value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance'))) + 
            ' Non-compliance value from json is : ') + Non_compliance_value) + 'thus values are matched \n')
		
		KeywordUtil.logInfo(((('Non-compliance value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance'))) + 
            ' Non-compliance value from json is : ') + Non_compliance_value) + 'thus values are matched \n')
    } else {
        print(((('Non-compliance value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance'))) + 
            ' Non-compliance value from json is : ') + Non_compliance_value) + 'thus values are not matched \n')
		
		KeywordUtil.logInfo(((('Non-compliance value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance'))) + 
            ' Non-compliance value from json is : ') + Non_compliance_value) + 'thus values are not matched \n')
    }
    
    //Critical errors
    WebUI.getText(findTestObject('Map tooltip objects/Critical error -text'))

    WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors'))

    String critical_error_value = GlobalVariable.result.location_view.result.data_points[(i - 3)].critical_errors.value

    if (critical_error_value == WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors'))) {
        print(((('critical_value value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors'))) + 
            ' critical_error_value from json is : ') + critical_error_value) + 'thus values are matched \n')
		
		KeywordUtil.logInfo(((('critical_value value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors'))) + 
            ' critical_error_value from json is : ') + critical_error_value) + 'thus values are matched \n')
    } else {
        print(((('critical_value value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors'))) + 
            ' critical_error_value from json is : ') + critical_error_value) + 'thus values are not matched \n')
		
		KeywordUtil.logInfo(((('critical_value value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors'))) + 
            ' critical_error_value from json is : ') + critical_error_value) + 'thus values are not matched \n')
    }
    
    // Applications
    WebUI.getText(findTestObject('Map tooltip objects/Applcation text'))

    WebUI.getText(findTestObject('Map tooltip objects/Map-applications'))

    String application_value = GlobalVariable.result.location_view.result.data_points[(i - 3)].active_applications.value

    if (application_value == WebUI.getText(findTestObject('Map tooltip objects/Map-applications'))) {
        print(((('Application value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-applications'))) + 
            ' Application value from json is : ') + application_value) + 'thus values are matched \n')
		
		KeywordUtil.logInfo(((('Application value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-applications'))) + 
            ' Application value from json is : ') + application_value) + 'thus values are matched \n')
    } else {
        print(((('Application value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-applications'))) + 
            ' Application value from json is : ') + application_value) + 'thus values are not matched \n')
		
		KeywordUtil.logInfo(((('Application value from web is : ' + WebUI.getText(findTestObject('Map tooltip objects/Map-applications'))) + 
            ' Application value from json is : ') + application_value) + 'thus values are not matched \n')
    }
}

