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



public class L1 {

	@Keyword
	def top_application(){
		//		int a = 2
		//		switch(a) {
		//			case 2:
		//			//File file = new File("E:/Example.txt")
		//			//File jsontxt = new File("/Users/maitri.brahmakshatriya/git/Pulse-ZTA/JSON/TopActiveApplications.json")
		//			String test = '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' +
		//(i + 3) + ') > g > text > tspan'
		//			break;
		//			case topGateway:
		//			break;
		//			case topLocation:
		//			break;
		//			default:
		//            println("The value is unknown");
		//            break;
		//
		//		}
		WebUI.scrollToElement(findTestObject('Filter object/ScrollToApplications'), 2)
		File jsontxt = new File(System.getProperty('user.dir') + '/JSON/TopActiveApplications.json')
		def slurper = new JsonSlurper()
		def result = slurper.parse(jsontxt)

		try {
			ArrayList<String> tooltip = new ArrayList<String>()

			ArrayList<String> appName = new ArrayList<String>()

			ArrayList<String> JSON_Name = new ArrayList<String>()

			ArrayList<String> JSON_Value = new ArrayList<String>()

			for (int i = 0; i < result.size(); i++) {

				(JSON_Name[i]) = result[i].name
				(JSON_Value[i]) = result[i].value

				String topApplicationsValueCSS = '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' +
						(i + 3) + ') > g > text > tspan'
				def topApplications_Value = new TestObject('customObject')
				topApplications_Value.addProperty('css', ConditionType.EQUALS, topApplicationsValueCSS)
				(appName[i]) = WebUI.getText(topApplications_Value)

				String topApplicationsTooltipCSS = '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' +
						(i + 1) + ') > g > g > g > g > path'
				def mouseHover_App = new TestObject('customObject')
				mouseHover_App.addProperty('css', ConditionType.EQUALS, topApplicationsTooltipCSS)
				WebUI.mouseOver(mouseHover_App)

				try {
					def topApplications_Tooltip = new TestObject('customObject')
					topApplications_Tooltip.addProperty('css', ConditionType.EQUALS, '#dashboard-radar-apps-accessed > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')
					WebUI.waitForElementPresent(topApplications_Tooltip, 2)
					(tooltip[i]) = WebUI.getText(topApplications_Tooltip)
				}
				catch (Exception e) {
					e.print('No tooltip found')
				}

			}
		//	log.logInfo("Top Applications")
			for (int j = 0; j < result.size(); j++) {

				if (JSON_Name.contains(appName[j])) {
					def index = JSON_Name.indexOf(appName[j])
					//print(index)
					if (WebUI.verifyEqual(tooltip[j], JSON_Value[index])) {
						println('true')
					} else {

					}
				}else {
					println('false')
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

	}

	@Keyword
	def top_Gateways(){

		WebUI.scrollToElement(findTestObject('Filter object/ScrollTopGateways'), 2)
		def slurper = new JsonSlurper()

		File jsontxt = new File(System.getProperty('user.dir') + '/JSON/TopGateways.json')

		def result = slurper.parse(jsontxt)

		try {
			ArrayList<String> tooltip = new ArrayList<String>()

			ArrayList<String> gatewayName = new ArrayList<String>()

			ArrayList<String> JSON_Name = new ArrayList<String>()

			ArrayList<String> JSON_Value = new ArrayList<String>()

			for (int i = 0; i < result.size(); i++) {
				(JSON_Name[i]) = result[i].name

				(JSON_Value[i]) = result[i].value

				String topGatewaysValueCSS = ('#dashboard-radar-gateway-health > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' +
						(i + 3)) + ') > g > text > tspan'

				def topGateways_Value = new TestObject('customObject')

				topGateways_Value.addProperty('css', ConditionType.EQUALS, topGatewaysValueCSS)

				(gatewayName[i]) = WebUI.getText(topGateways_Value)

				String topGatewaysTooltipCSS = ('#dashboard-radar-gateway-health > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' +
						(i + 1)) + ') > g > g > g > g > path'

				def mouseHover_Gateway = new TestObject('customObject')

				mouseHover_Gateway.addProperty('css', ConditionType.EQUALS, topGatewaysTooltipCSS)

				WebUI.mouseOver(mouseHover_Gateway)

				try {
					def topGateways_Tooltip = new TestObject('customObject')

					topGateways_Tooltip.addProperty('css', ConditionType.EQUALS, '#dashboard-radar-gateway-health > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

					(tooltip[i]) = WebUI.getText(topGateways_Tooltip)
				}
				catch (Exception e) {
					e.print('No tooltip found')
				}

			}

			for (int j = 0; j < result.size(); j++) {

				if (JSON_Name.contains(gatewayName[j])) {
					def index = JSON_Name.indexOf(gatewayName[j])
					//print(index)
					if (WebUI.verifyEqual(tooltip[j], JSON_Value[index])) {
						println('true')
					} else {

					}
				}else {
					println('false')
				}

			}
			print(tooltip)

			print(gatewayName)

			print(JSON_Value)

			print(JSON_Name)
		}
		catch (Exception e) {
			e.print('test end')
		}

	}


	@Keyword
	def top_Locations(){
		WebUI.scrollToElement(findTestObject('Filter object/ScrollToLocations'), 2)
		def slurper = new JsonSlurper()

		File jsontxt = new File(System.getProperty('user.dir') + '/JSON/TopLocations.json')

		def result = slurper.parse(jsontxt)

		try {
			ArrayList<String> tooltip = new ArrayList<String>()

			ArrayList<String> locName = new ArrayList<String>()

			ArrayList<String> JSON_Name = new ArrayList<String>()

			ArrayList<String> JSON_Value = new ArrayList<String>()

			for (int i = 0; i < result.size(); i++) {
				(JSON_Name[i]) = result[i].name

				(JSON_Value[i]) = result[i].value

				String topLocationsValueCSS = ('#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Axis-group.amcharts-CategoryAxis-group > g > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-AxisRenderer-group.amcharts-AxisRendererCircular-group > g > g:nth-child(' +
						(i + 3)) + ') > g > text > tspan'

				def topLocations_Value = new TestObject('customObject')

				topLocations_Value.addProperty('css', ConditionType.EQUALS, topLocationsValueCSS)

				(locName[i]) = WebUI.getText(topLocations_Value)

				String topLocationsTooltipCSS = ('#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group.amcharts-RadarChart-group > g > g > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(1) > g > g:nth-child(1) > g > g > g > g > g > g > g > g:nth-child(' +
						(i + 1)) + ') > g > g > g > g > path'

				def mouseHover_loc = new TestObject('customObject')

				mouseHover_loc.addProperty('css', ConditionType.EQUALS, topLocationsTooltipCSS)

				WebUI.mouseOver(mouseHover_loc)

				try {
					def topLocations_Tooltip = new TestObject('customObject')

					topLocations_Tooltip.addProperty('css', ConditionType.EQUALS, '#dashboard-radar-access-locations > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

					(tooltip[i]) = WebUI.getText(topLocations_Tooltip)
				}
				catch (Exception e) {
					e.print('No tooltip found')
				}
			}

			for (int j = 0; j < result.size(); j++) {

				if (JSON_Name.contains(locName[j])) {
					def index = JSON_Name.indexOf(locName[j])
				//	print(index)
					if (WebUI.verifyEqual(tooltip[j], JSON_Value[index])) {
						println('true')
					} else {

					}
				}else {
					println('false')
				}

			}

			print(tooltip)

			print(locName)

			print(JSON_Value)

			print(JSON_Name)
		}
		catch (Exception e) {
			e.print('test end')
		}

	}






}
