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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import groovy.json.JsonSlurper as JsonSlurper



def slurper = new JsonSlurper()

File jsontxt = new File('/Users/perry.gami/Downloads/config_008.json')

def result = slurper.parse(jsontxt)

try {
	ArrayList<String> UI = new ArrayList<String>()

	ArrayList<String> JSON_Name = new ArrayList<String>()

	ArrayList<String> JSON_Value = new ArrayList<String>()

	for (int i = 0; i < 2; i++) {
		(JSON_Name[i]) = result.buckets[i].name

		(JSON_Value[i]) = result.buckets[i].value

		//print("name is " + JSON[i]  )
		String css = ('#dashboard-l1-anomalies-chart > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(1) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g > g:nth-child(1) > g:nth-child(2) > g:nth-child(3) > g > g:nth-child(' +
		(i + 1)) + ') > g > g > g > g > g > g > g > g > path'

		//String css = '#topRiskyUsersChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + i + ')'
		myTestOject = new TestObject('customObject')

		myTestOject.addProperty('css', ConditionType.EQUALS, css)

		System.out.println(css)

		WebUI.mouseOver(myTestOject)

		try {
			Tooltip = new TestObject('customObject')

			Tooltip.addProperty('css', ConditionType.EQUALS, '#dashboard-l1-anomalies-chart > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(2) > g > g:nth-child(6) > g:nth-child(2) > g > g > text > tspan')

			(UI[(i + 1)]) = WebUI.getText(Tooltip)

			print(UI[(i + 1)])

			String[] a = (UI[(i + 1)]).split(':')

			System.out.println(a[0])

			WebUI.verifyMatch(a[0], JSON_Name[i], false)

			System.out.println((a[1]) + (JSON_Value[i]))

			WebUI.verifyEqual((a[1]).trim(), JSON_Value[i]) //if (JSON_Value[i].compareTo(a[1])){
			//	print("values are matched")
			//}
			//else {
			//	print("values are not matched")
			//}
			//WebUI.verifyMatch(a[1], JSON_Value[i], false)
		}
		catch (Exception e) {
			e.print('No tooltip found')
		}
	}
}
catch (Exception e) {
	e.print('test end')
}