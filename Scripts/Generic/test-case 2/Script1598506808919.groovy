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

CustomKeywords.'custom_keyword.keywords.login'()

WebUI.delay(2)

WebUI.click(findTestObject('Summary Panel/Non-complaince'))

WebUI.delay(2)
try {
	ArrayList<String> UI = new ArrayList<String>()
	
	for(i=1 ; i < 5 ; i++){
		
		String css = '#info-panel > div > div:nth-child(2) > div:nth-child('+ i +') > div.dashboard-sidepanel__content-panel > div > div:nth-child(1) > div:nth-child(1) > div > div.dashboard-sidepanel__value'
		
				//String css = '#topRiskyUsersChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + i + ')'
				myTestOject = new TestObject('customObject')
		
				myTestOject.addProperty('css', ConditionType.EQUALS, css)
				policy = WebUI.getText(myTestOject)
				print(policy)
				
		
		
	}
}

catch (Exception e) {
	e.print('test end')
}