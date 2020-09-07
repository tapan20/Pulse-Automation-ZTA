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

CustomKeywords.'custom_keyword.keywords.login'()

WebUI.delay(3)

CustomKeywords.'custom_keyword.keywords.apply_filter'()

WebUI.mouseOver(findTestObject('Login objects/Non-compliance'))

WebUI.getText(findTestObject('Login objects/Tooltip value'))

def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/Non-compliance.json')

def result = slurper.parse(jsontxt)

try {
	ArrayList<String> UI = new ArrayList<String>()

	ArrayList<String> JSON_Name = new ArrayList<String>()

	ArrayList<String> JSON_Value = new ArrayList<String>()

	for (int i = 0; i < 5; i++) {
	
		
		String css = ('#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(1) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g > g:nth-child(1) > g:nth-child(2) > g:nth-child(3) >g > g > g > g > g > g > g > g:nth-child(' +
		(i + 1)) + ') > g > g > path'

		//String css = '#topRiskyUsersChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + i + ')'
		Non_compliance = new TestObject('customObject')

		Non_compliance.addProperty('css', ConditionType.EQUALS, css)

		System.out.println(css)

		
		WebUI.mouseOver(Non_compliance)

		try {
			Tooltip_nc = new TestObject('customObject')

			Tooltip_nc.addProperty('css', ConditionType.EQUALS, '#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g.amcharts-Container  > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

			(UI[(i + 1)]) = WebUI.getText(Tooltip_nc)

			print(UI[(i + 1)])

			String[] a = (UI[(i + 1)]).split(':')

			System.out.println(a[0])
			
			for(j=0; j<result.non_compliance_policies[j].size() ; j++){
				
				
				if(a[0] == result.non_compliance_policies[j].name)
				
				{
					
					if(WebUI.verifyEqual(a[1].trim(), result.non_compliance_policies[j].value))
					{
					
						print('values from json and UI are matched')
				}
					
					else{
						
						
						}
					
					}
			
			}
			

	
		}
		catch (Exception e) {
			e.print('No tooltip found')
		}
	}
}
catch (Exception e) {
	e.print('test end')
}

WebUI.closeBrowser()

