import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.By as By
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper
import groovy.json.JsonOutput as JsonOutput
import java.text.SimpleDateFormat as SimpleDateFormat


// Call Login
CustomKeywords.'custom_keyword.keywords.login'()

<<<<<<< HEAD
CustomKeywords.'custom_keyword.keywords.apply_filter'()

WebUI.click(findTestObject('Summary Panel/Non-complaince'))
=======
// Call Apply Filter
CustomKeywords.'custom_keyword.keywords.apply_filter'()
>>>>>>> branch 'master' of https://github.com/perrygami/Pulse-ZTA.git

WebUI.click(findTestObject('Summary Panel/Non-complaince'))
WebUI.delay(2)

// Re-order JSON with Timestamp desc
//File jsontxt = new File('/Users/tapan.khimani/Downloads/NonCompilance.json')
File jsontxt = new File(System.getProperty('user.dir') + '/JSON/slidNonCompliance.json')
def slurper = new JsonSlurper()
def jsonData = slurper.parse(jsontxt)
def items = jsonData.info_panel_items
jsonData.info_panel_items = items.sort { a,b -> b.timestamp <=> a.timestamp}
def outputJson = JsonOutput.toJson(jsonData)
println(outputJson)

// Updated JSON File path : Create new file locally
String FILE_PATH = System.getProperty('user.dir') + '/JSON/slidNonCompliance'
String FILE_EXTENSION ='.json'

SimpleDateFormat jdf = new SimpleDateFormat(' dd:MMM:yyyy-hh:mm:ss')


File updatedJSONFile = new File(FILE_PATH + jdf.format(new Date())  + FILE_EXTENSION)
updatedJSONFile.append(outputJson)


// Get count for Non-Compilance data
count = WebUI.getText(findTestObject('count'))
int iCount=Integer.parseInt(count)

println('Count of Non Compilance = ' + iCount)

try {
    ArrayList<String> UI = new ArrayList<String>()

    for (int i = 1; i <= iCount; i++) {
        String poilcyCss = '#info-panel > div > div:nth-child(2) > div:nth-child('+i+') > div.dashboard-sidepanel__content-panel > div > div:nth-child(1) > div:nth-child(1) > div > div.dashboard-sidepanel__value'							
        String deviceTypeCss = '#info-panel > div > div:nth-child(2) > div:nth-child('+i+') > div.dashboard-sidepanel__content-panel > div > div:nth-child(1) > div:nth-child(2) > div > div.dashboard-sidepanel__value'
        String applicationNameCss = '#info-panel > div > div:nth-child(2) > div:nth-child('+i+') > div.dashboard-sidepanel__content-panel > div > div:nth-child(2) > div > div > div.dashboard-sidepanel__value'
        String deviceIdCss = '#info-panel > div > div:nth-child(2) > div:nth-child(' + i + ') > div.dashboard-sidepanel__content-panel > div > div:nth-child(3) > div > div > div.dashboard-sidepanel__value'


		//File jsontxt = new File(System.getProperty('user.dir') + '/JSON/slidNonCompliance.json')

		def result = slurper.parse(updatedJSONFile)

		
		// Check for Web App Name
		ArrayList<String> webAppName = new ArrayList<String>()
		WebapplicationName = new TestObject('customObject')
		WebapplicationName.addProperty('css', ConditionType.EQUALS, applicationNameCss)
		WebapplicationName = WebUI.getText(WebapplicationName)
		webAppName[i] = WebapplicationName
		println("App Name from Web : " + webAppName[i])
	
	
		// Check for Json APP Name
		ArrayList<String> jsonApplicationName = new ArrayList<String>()
		jsonApplicationName[i] = result.info_panel_items[i-1].application_name
		println("Activity Name from JSON : " + i + " "+ jsonApplicationName[i])
		//WebUI.verifyMatch(webAppName[i], jsonApplicationName[i], false)
		if(webAppName[i] == jsonApplicationName[i])
		{
		
		println("Application Name from Web is : " +webAppName[i]+ " and Application Name from JSON is : "+jsonApplicationName[i] +" thus, it is correct")
		}
		else{
		println("Application Name from Web is : " +webAppName[i]+ " and Application Name from JSON is : "+jsonApplicationName[i] +" thus, it is in-correct")
		}
	

		// Check for Web Policy Name
		ArrayList<String> webPolicyName = new ArrayList<String>()
		webPolicy = new TestObject('customObject')
		webPolicy.addProperty('css', ConditionType.EQUALS, poilcyCss)
		webPolicy = WebUI.getText(webPolicy)
		webPolicyName[i] = webPolicy
		println("Policy Name from Web : " + webPolicyName[i])
	
	
		// Check for Json Policy Name
		ArrayList<String> jsonPolicyName = new ArrayList<String>()
		jsonPolicyName[i] = result.info_panel_items[i-1].non_compliance_policy_name
		println("Activity Name from JSON : " + i + " "+ jsonPolicyName[i])
		//WebUI.verifyMatch(webAppName[i], jsonApplicationName[i], false)
		if(webPolicyName[i] == jsonPolicyName[i])
		{
			
			println("Policy Name from Web is : " +webPolicyName[i]+ " and Policy Name from JSON is : "+jsonPolicyName[i] +" thus, it is correct")
		}
		else{
					println("Policy Name from Web is : " +webPolicyName[i]+ " and Policy Name from JSON is : "+jsonPolicyName[i] +" thus, it is in-correct")
		}
			
	
		// Check for Web Device Type
		ArrayList<String> webDeviceType = new ArrayList<String>()
		webDeviceTypeObject = new TestObject('customObject')
		webDeviceTypeObject.addProperty('css', ConditionType.EQUALS, deviceTypeCss)
		webDeviceTypeObject = WebUI.getText(webDeviceTypeObject)
		webDeviceType[i] = webDeviceTypeObject
		println("Device Type from Web : " + webDeviceType[i])
	
	
		// Check for Json Device Type
		ArrayList<String> jsonDeviceType = new ArrayList<String>()
		jsonDeviceType[i] = result.info_panel_items[i-1].device_type
		println("Device Type from JSON : " + i + " "+ jsonDeviceType[i])
		//WebUI.verifyMatch(webAppName[i], jsonApplicationName[i], false)
		if(webDeviceType[i] == jsonDeviceType[i])
		{
			println("Device Type  from Web is : " +webDeviceType[i]+ " and Device Type from JSON is : "+jsonDeviceType[i] +" thus, it is correct")
		}
		else{
			println("Device Type from Web is : " +webDeviceType[i]+ " and Device Type from JSON is : "+jsonDeviceType[i] +" thus, it is in-correct")
		}
	

		//Check for Web Device Id
		ArrayList<String> webDeviceId = new ArrayList<String>()
		webDeviceIdObject = new TestObject('customObject')
		webDeviceIdObject.addProperty('css', ConditionType.EQUALS, deviceIdCss)
		webDeviceIdObject = WebUI.getText(webDeviceIdObject)
		webDeviceId[i] = webDeviceIdObject
		println("Device Id from Web : " + webDeviceId[i])
	
	
		// Check for Json Device Id
		ArrayList<Long> jsonDeviceId = new ArrayList<Long>()
		jsonDeviceId[i] = result.info_panel_items[i-1].device_id
		println("Device Id from JSON : " + i + " "+ jsonDeviceId[i])
		//WebUI.verifyMatch(webAppName[i], jsonApplicationName[i], false)
		if(webDeviceId[i] == jsonDeviceId[i])
		{		
		println("Device Id from Web is : " +webDeviceId[i]+ " and Device Id from JSON is : "+jsonDeviceId[i] +" thus, it is correct")
		}
		else{
		println("Device Id from Web is : " +webDeviceId[i]+ " and Device Id from JSON is : "+jsonDeviceId[i] +" thus, it is in-correct")
		}

}


}
catch (Exception e) {
<<<<<<< HEAD
    e.print('test end')

    e.printStackTrace()
} 

=======
	e.printStackTrace()      
}
     
>>>>>>> branch 'master' of https://github.com/perrygami/Pulse-ZTA.git
