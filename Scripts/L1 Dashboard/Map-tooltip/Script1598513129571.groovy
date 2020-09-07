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

CustomKeywords.'custom_keyword.keywords.login'()

WebUI.delay(2)

CustomKeywords.'custom_keyword.keywords.apply_filter'()

WebUI.delay(3)

def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/Maptooltip.json')

def result = slurper.parse(jsontxt)

WebUI.mouseOver(findTestObject('Map tooltip objects/circle hover'))

String Name = (WebUI.getText(findTestObject('Map tooltip objects/Gateways - Text')))

WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))

String Gateway_name = result.data_points[0].active_gateways.name

String Gateway_value = result.data_points[0].active_gateways.value

//WebUI.verifyMatch((WebUI.getText(findTestObject('Map tooltip objects/Gateways - Text'))), Gateway_name ,false)

//WebUI.verifyMatchl(WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways')), Gateway_value , true)

if(Gateway_value ==  WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways')))
{
	
	println("Gateway value from Web is : " +WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))+ " and Gateway value JSON is : "+ Gateway_value +" thus, it is correct")
	
}

else {
	
	
	println("Gateway value from Web is : " +WebUI.getText(findTestObject('Map tooltip objects/Map-Gateways'))+ " and Gateway value JSON is : "+ Gateway_value +" thus, it is incorrect")
}

// FOR USERS

WebUI.getText(findTestObject('Map tooltip objects/Users - Text'))

WebUI.getText(findTestObject('Map tooltip objects/Map-Users'))



//String User_name = result.data_points[0].active_users.value

String User_value = result.data_points[0].active_users.value

if(User_value ==  WebUI.getText(findTestObject('Map tooltip objects/Map-Users')))
{
	
	print ('User value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-Users')) + ' User value from json is : ' + User_value + 'thus values are matched')
	
}

else {
	
	
	print ('User value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-Users')) + ' User value from json is : ' + User_value + 'thus values are not matched')
}

//DEVICES

WebUI.getText(findTestObject('Map tooltip objects/Devices - Text'))

WebUI.getText(findTestObject('Map tooltip objects/Map-Devices'))

String Device_value = result.data_points[0].active_devices.value

if(Device_value == WebUI.getText(findTestObject('Map tooltip objects/Map-Devices')))
{
	
	print ('Device value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-Devices')) + ' Device value from json is : ' + Device_value + 'thus values are matched')
	
}

else {
	
	
	print ('Device value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-Devices')) + ' Device value from json is : ' + Device_value + 'thus values are  not matched')
}


// Non- compliance

WebUI.getText(findTestObject('Map tooltip objects/Non compliance'))

WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance'))

String Non_compliance_value = result.data_points[0].non_compliance_count.value

if(Non_compliance_value == WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance')))
{
	
	print ('Non-compliance value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance')) + ' Non-compliance value from json is : ' + Non_compliance_value + 'thus values are matched')
	
}

else {
	
	
	print ('Non-compliance value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-non_compliance')) + ' Non-compliance value from json is : ' + Non_compliance_value + 'thus values are not matched')
}

//Critical errors
WebUI.getText(findTestObject('Map tooltip objects/Critical error -text'))

WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors'))

String critical_error_value = result.data_points[0].critical_errors.value

if(critical_error_value == WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors')))
{
	
	print ('critical_value value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors')) + ' critical_error_value from json is : ' + critical_error_value + 'thus values are matched')
	
}

else {
	
	
	print ('critical_value value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-critical_errors')) + ' critical_error_value from json is : ' + critical_error_value + 'thus values are not matched')
}

// Applications

WebUI.getText(findTestObject('Map tooltip objects/Applcation text'))

WebUI.getText(findTestObject('Map tooltip objects/Map-applications'))

String application_value = result.data_points[0].active_applications.value

if(application_value == WebUI.getText(findTestObject('Map tooltip objects/Map-applications')))
{
	
	print ('Application value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-applications')) + ' Application value from json is : ' + application_value + 'thus values are matched')
	
}

else {
	
	
	print ('Application value from web is : '   +WebUI.getText(findTestObject('Map tooltip objects/Map-applications')) + ' Application value from json is : ' + application_value + 'thus values are not matched')
}
