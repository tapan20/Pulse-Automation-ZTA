package custom_keyword

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonSlurper
import internal.GlobalVariable
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
import java.util.LinkedHashMap
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObjectBuilder
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.SimpleDateFormat
import com.kms.katalon.core.util.KeywordUtil

public class L1_003 {

	@Keyword
	def loggedFile(){

		//For log file name
		def date = new Date()
		def sdf = new SimpleDateFormat("dd:MMM:yyyy-hh:mm:ss")
		println(sdf.format(date))

		//To store in Logs folder
		File out = new File(System.getProperty('user.dir') + '/Logs/' + sdf.format(date) + '.log')
		if (out.exists()) {
			out.delete()
		}
		KeywordUtil.metaClass.static.logInfo = { String message ->
			out.append(message)
			delegate.logger.logInfo(message)
		}
		return KeywordUtil
	}

	@Keyword
	def getJsonData(a){
		ArrayList<String> JSON_Name = new ArrayList<String>()
		ArrayList<String> JSON_Value = new ArrayList<String>()
		int size

		//Reading data from result.json
		File jsontxt = new File(System.getProperty('user.dir') + '/JSON/result.json')
		def slurper = new JsonSlurper()
		def result_json = slurper.parse(jsontxt)

		switch(a) {
			case 'Top active gateways':
				JSON_Name = result_json.top_active_gateways.result.gateways.name
				JSON_Value = result_json.top_active_gateways.result.gateways.value
				size = result_json.top_active_gateways.result.gateways.size()
				break

			case 'Top active locations':
				JSON_Name = result_json.top_user_access_locations.result.user_access_locations.name
				JSON_Value = result_json.top_user_access_locations.result.user_access_locations.value
				size = result_json.top_user_access_locations.result.user_access_locations.size()
				break

			case 'Top active applications':
				JSON_Name = result_json.top_active_applications.result.applications.name
				JSON_Value = result_json.top_active_applications.result.applications.value
				size = result_json.top_active_applications.result.applications.size()
				break

			case 'Anamolies':
				JSON_Name = result_json.anomalies.result.buckets.name
				JSON_Value = result_json.anomalies.result.buckets.value
				size = result_json.anomalies.result.buckets.size()
				break

			case 'NonCompliance':
				JSON_Name = result_json.top_non_compliance.result.non_compliance_policies.name
				JSON_Value = result_json.top_non_compliance.result.non_compliance_policies.value
				size = result_json.top_non_compliance.result.non_compliance_policies.size()
				break


			default:
				print('')
				break
		}

		def obj = new LinkedHashMap();
		obj.JSON_Name = JSON_Name
		obj.JSON_Value = JSON_Value
		obj.size = size
		//	def obj = [value1, tooltipCSS]
		return obj
	}

	@Keyword
	def getLocatorsFromUI(a, i){
		String valueCSS = ''
		String mouseHoverCss = ''
		String tooltipCSS = ''

		switch(a) {
			case 'Top active gateways':
				valueCSS = '#dashboard-radar-gateway-health > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' +
				(i + 3) + ') > g > text > tspan'
				mouseHoverCss = '#dashboard-radar-gateway-health > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' +
						(i + 1) + ') > g > g > g > g > path'
				tooltipCSS = '#dashboard-radar-gateway-health > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan'
				break

			case 'Top active locations':
				valueCSS = '#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' +
				(i + 3) + ') > g > text > tspan'
				mouseHoverCss = '#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' +
						(i + 1) + ') > g > g > g > g > path'
				tooltipCSS = '#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan'
				break

			case 'Top active applications':
				valueCSS = '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' +
				(i + 3) + ') > g > text > tspan'
				mouseHoverCss = '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' +
						(i + 1) + ') > g > g > g > g > path'
				tooltipCSS = '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan'
				break

			case 'Anamolies':
				mouseHoverCss = ('#dashboard-l1-anomalies-chart > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(1) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g > g:nth-child(1) > g:nth-child(2) > g:nth-child(3) > g > g:nth-child(' +
				(i + 1)) + ') > g > g > g > g > g > g > g > g > path'

			// 5 or 6
				tooltipCSS = '#dashboard-l1-anomalies-chart > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(6) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan'
				break

			case 'NonCompliance':
				mouseHoverCss = ('#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(1) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g > g:nth-child(1) > g:nth-child(2) > g:nth-child(3) >g > g > g > g > g > g > g > g:nth-child(' +
				(i + 1)) + ') > g > g > path'


			tooltipCSS = '#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g.amcharts-Container  > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan'

				//tooltipCSS = '#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(2) > g > g:nth-child(5) > g:nth-child(2) > g > g > text > tspan'
			default:
				print('')
				break
		}

		def obj = new LinkedHashMap();
		obj.valueCSS = valueCSS
		obj.mouseHoverCss = mouseHoverCss
		obj.tooltipCSS = tooltipCSS

		return obj
	}

	@Keyword
	def dataCompare(scriptName){

		//logger implementation
		def log = loggedFile()

		//WebUI.scrollToElement(findTestObject('Filter object/ScrollToApplications'), 2)

		try {
			ArrayList<String> tooltip = new ArrayList<String>()
			ArrayList<String> appName = new ArrayList<String>()

			//called getJsonData
			def obj_new = getJsonData(scriptName)
			ArrayList<String> JSON_Name = obj_new.JSON_Name
			ArrayList<String> JSON_Value = obj_new.JSON_Value
			int size = obj_new.size

			for (int i = 0; i < size; i++) {

				//called getLocatorsFromUI
				def obj1 = getLocatorsFromUI(scriptName, i)
				def valueCSS = obj1.valueCSS
				def mouseHoverCss = obj1.mouseHoverCss
				def tooltipCSS = obj1.tooltipCSS

				//				if(!(scriptName == 'Anamolies' || scriptName == 'NonCompliance')){
				//					//Fetching name from UI
				//									String topApplicationsValueCSS = valueCSS
				//									def topApplications_Value = new TestObject('customObject')
				//									topApplications_Value.addProperty('css', ConditionType.EQUALS, topApplicationsValueCSS)
				//									(appName[i]) = WebUI.getText(topApplications_Value)
				//
				//				}

				//Fetching name from UI
				//				String topApplicationsValueCSS = valueCSS
				//				def topApplications_Value = new TestObject('customObject')
				//				topApplications_Value.addProperty('css', ConditionType.EQUALS, topApplicationsValueCSS)
				//				(appName[i]) = WebUI.getText(topApplications_Value)

				//Fecting value from Tooltip
				String topApplicationsTooltipCSS = mouseHoverCss
				def mouseHover_App = new TestObject('customObject')
				mouseHover_App.addProperty('css', ConditionType.EQUALS, topApplicationsTooltipCSS)
				WebUI.mouseOver(mouseHover_App)

				try {
					def topApplications_Tooltip = new TestObject('customObject')
					topApplications_Tooltip.addProperty('css', ConditionType.EQUALS, tooltipCSS)
					WebUI.waitForElementPresent(topApplications_Tooltip, 2)
					(tooltip[i]) = WebUI.getText(topApplications_Tooltip)


					if(scriptName == 'Anamolies' || scriptName == 'NonCompliance')
					{
						ArrayList<String> UI = new ArrayList<String>()
						(UI[(i + 1)]) = tooltip[i]

						print(UI[(i + 1)])

						String[] a = (UI[(i + 1)]).split(':')

						System.out.println(a[0])

						for(int j =0; j < size ; j++){
							if ((a[0]) == JSON_Name[j]) {
								if ((a[1]).trim().toString() == JSON_Value[j].toString()) {
									print('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  JSON_Value[j] + '\n')
									KeywordUtil.logInfo('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  JSON_Value[j] + '\n \n')
								} else {


									print('Incorrect \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  JSON_Value[j] + '\n')
									KeywordUtil.logInfo('Incorrect \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  JSON_Value[j] + '\n \n')
								}
							}
						}
					}

				}
				catch (Exception e) {
					e.print('No tooltip found')
				}
				//				println(JSON_Name[i])
				//				println(JSON_Value[i])
				//				println(appName[i])
				//				println(tooltip[i])
			}

			for (int j = 0; j < size; j++) {
				for (int k = 0; k < size; k++) {
					if ((JSON_Name[j]) == (appName[k])) {
						if (WebUI.verifyEqual(tooltip[j], JSON_Value[k])) {
							println('values from json and UI are matched ' + JSON_Name[j] +' '+ appName[k] +' '+ JSON_Value[k] + ' '+tooltip[j])
							log.logInfo("Test 123")
							log.logInfo('test-case \n')
							log.logInfo('Matched \n' + '  Value from UI is ' + appName[k] + ' and ' + tooltip[j] + '  Value from json is ' + JSON_Name[j] + ' and ' + JSON_Value[k] + '\n')
						} else {
						}
					}else {
						//print('values from json and UI are not matched')
					}
				}
			}
		}
		catch (Exception e) {

			e.print('testend')
			print(e)
		}
	}
}