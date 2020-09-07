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

WebUI.delay(2)

CustomKeywords.'custom_keyword.keywords.apply_filter'()

WebUI.delay(4)

//CustomKeywords.'custom_keyword.keywords.active_applications'()

def slurper = new JsonSlurper()



		File jsontxt = new File('/Users/perry.gami/Downloads/active_application.json')



		def result = slurper.parse(jsontxt)


		try {


			ArrayList<String> UI = new ArrayList<String>()



			ArrayList<String> JSON_Name = new ArrayList<String>()



			ArrayList<String> JSON_Value = new ArrayList<String>()



			for (int i = 0; i < 7; i++) {


				//(JSON_Name[i]) = result[i].name


				(JSON_Value[i]) = result[i].value



				TestObject myTestObject = new TestObject('customObject')

				String css = '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child('+ (i+1) +') > g > g > g > g > path'

				myTestObject.addProperty('css', ConditionType.EQUALS, css)

				WebUI.mouseOver(myTestObject)



				try {
					

					TestObject Tool = new TestObject('customObject')

					Tool.addProperty('css', ConditionType.EQUALS, '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')


					(UI[(i + 1)]) = WebUI.getText(Tool)



					print(UI[(i + 1)])



				String[] a = (UI[(i + 1)]).split(':')



				System.out.println(a[0])



					//WebUI.verifyMatch(a[0], JSON_Name[i], false)



			//	System.out.println((a[1]) + (JSON_Value[i]))



					WebUI.verifyEqual(a[0], JSON_Value[i] )
				}

				catch (Exception e) {
					e.print('No tooltip found')
					print(e)
				}
			}
		}
		catch (Exception e) {
			e.print('test end')
			print(e)
		}



