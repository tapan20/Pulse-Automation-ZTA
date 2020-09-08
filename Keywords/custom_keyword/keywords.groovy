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
import com.kms.katalon.core.testobject.TestObjectBuilder
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.Keys as Keys
import internal.GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.eclipse.persistence.internal.oxm.record.json.JSONParser.object_return
import org.junit.After as After

import com.kms.katalon.core.testobject.ConditionType as ConditionType
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.SimpleDateFormat
import com.kms.katalon.core.util.KeywordUtil

public class keywords {
	
//	def date = new Date()
//	def sdf = new SimpleDateFormat("dd:MMM:yyyy-hh:mm:ss")
//	println sdf.format(date)
//
//	File out = new File(System.getProperty('user.dir') + '/Logs/' + sdf.format(date) + '.log')
//	if (out.exists()) {
//		out.delete()
//	}
//	KeywordUtil.metaClass.static.logInfo = { String message ->
//		out.append(message)
//		delegate.logger.logInfo(message)
//	}



	@Keyword
	def login() {
		WebUI.openBrowser('')

		WebUI.maximizeWindow()

		WebUI.navigateToUrl(GlobalVariable.URL)

		WebUI.delay(3)

		WebUI.setText(findTestObject('Login objects/username'), GlobalVariable.username)

		WebUI.setText(findTestObject('Login objects/password'), GlobalVariable.password)

		//WebUI.sendKeys(findTestObject('Login objects/singin button'), Keys.chord(Keys.ENTER))

		WebUI.click(findTestObject('Object Repository/Login objects/singin button'))
	}



	@Keyword
	def active_anomalies()  {
		//for log file date
		
		def date = new Date()
		def sdf = new SimpleDateFormat("dd:MMM:yyyy-hh:mm:ss")
		println sdf.format(date)

		File out = new File(System.getProperty('user.dir') + '/Logs/' + sdf.format(date) + '.log')
		if (out.exists()) {
			out.delete()
		}
		KeywordUtil.metaClass.static.logInfo = { String message ->
			out.append(message)
			delegate.logger.logInfo(message)
		}

		KeywordUtil.logInfo("Anomalies test-case \n")

		def slurper = new JsonSlurper()

		File jsontxt = new File(System.getProperty('user.dir') + '/JSON/ActiveAnomalies.json')

		def result = slurper.parse(jsontxt)

		int loop_count = result.buckets.size()

		print(loop_count)

		try {
			ArrayList<String> UI = new ArrayList<String>()

			ArrayList<String> JSON_Name = new ArrayList<String>()

			ArrayList<String> JSON_Value = new ArrayList<String>()

			for (int i = 0; i < loop_count; i++) {
				String css = ('#dashboard-l1-anomalies-chart > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(1) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g > g:nth-child(1) > g:nth-child(2) > g:nth-child(3) > g > g:nth-child(' +
						(i + 1)) + ') > g > g > g > g > g > g > g > g > path'

				//String css = '#topRiskyUsersChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + i + ')'
				TestObject Anomalies = new TestObject('customObject')

				Anomalies.addProperty('css', ConditionType.EQUALS, css)

				System.out.println(css)

				WebUI.mouseOver(Anomalies)

				try {
					TestObject Tooltip_anomalies = new TestObject('customObject')

					Tooltip_anomalies.addProperty('css', ConditionType.EQUALS, '#dashboard-l1-anomalies-chart > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(6) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

					(UI[(i + 1)]) = WebUI.getText(Tooltip_anomalies)

					print(UI[(i + 1)])

					String[] a = (UI[(i + 1)]).split(':')

					System.out.println(a[0])

					for (int j = 0; j < loop_count; j++) {
						if ((a[0]) == result.buckets[j].name) {
							if ((a[1]).trim() == result.buckets[j].value) {
								print('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  result.buckets[j].value)
								KeywordUtil.logInfo('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  result.buckets[j].value + '\n')
							} else {


								print('Incorrect \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  result.buckets[j].value)
								KeywordUtil.logInfo('Incorrect \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  result.buckets[j].value + '\n')
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





	}


	@Keyword

	def apply_filter(){

		WebUI.click(findTestObject('Filter object/filter_button'))
		WebUI.click(findTestObject('Filter object/set_filter'))
		WebUI.click(findTestObject('Filter object/down_arrow'))
		WebUI.click(findTestObject('Filter object/last calander week'))
		WebUI.click(findTestObject('Filter object/Apply filter'))


	}

	@Keyword
	def apply_CurrentDayfilter(){

		WebUI.click(findTestObject('Filter object/filter_button'))
		WebUI.click(findTestObject('Filter object/set_filter'))
		WebUI.click(findTestObject('Filter object/down_arrow'))
		WebUI.click(findTestObject('Filter object/current_day'))
		WebUI.click(findTestObject('Filter object/Apply filter'))


	}
	
	
	
	@Keyword

	def Non_compliance(){

		def date = new Date()
		def sdf = new SimpleDateFormat("dd:MMM:yyyy-hh:mm:ss")
		println sdf.format(date)

		File out = new File(System.getProperty('user.dir') + '/Logs/' + sdf.format(date) + '.log')
		if (out.exists()) {
			out.delete()
		}
		KeywordUtil.metaClass.static.logInfo = { String message ->
			out.append(message)
			delegate.logger.logInfo(message)
		}

		KeywordUtil.logInfo("Non-compliance test-case \n")
		def slurper = new JsonSlurper()

		File jsontxt = new File(System.getProperty('user.dir') + '/JSON/Non-compliance.json')

		def result = slurper.parse(jsontxt)

		try {
			ArrayList<String> UI = new ArrayList<String>()

			ArrayList<String> JSON_Name = new ArrayList<String>()

			ArrayList<String> JSON_Value = new ArrayList<String>()

			for (int i = 0; i < result.non_compliance_policies.size(); i++) {
				String css = ('#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(1) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g > g:nth-child(1) > g:nth-child(2) > g:nth-child(3) >g > g > g > g > g > g > g > g:nth-child(' +
						(i + 1)) + ') > g > g > path'

				//String css = '#topRiskyUsersChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + i + ')'
				
				TestObject Non_compliance = new TestObject('customObject')
				
				Non_compliance.addProperty('css', ConditionType.EQUALS, css)

				print(css)

				WebUI.mouseOver(Non_compliance)

				try {
					TestObject  Tooltip_nc = new TestObject('customObject')
					Tooltip_nc.addProperty('css', ConditionType.EQUALS, '#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g.amcharts-Container  > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

					(UI[(i + 1)]) = WebUI.getText(Tooltip_nc)

					print(UI[(i + 1)])

					String[] a = (UI[(i + 1)]).split(':')

					println(a[0])

					for (int j = 0; j < result.non_compliance_policies.size(); j++) {
						print(result.non_compliance_policies[j].name)

						if ((a[0]) == result.non_compliance_policies[j].name) {
							if ((a[1]).trim().toString() == result.non_compliance_policies[j].value.toString()) {


								print('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  result.non_compliance_policies[j].value)
								KeywordUtil.logInfo('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  result.non_compliance_policies[j].value + '\n')

								break
							} else {
								print('Incorrect \n' + '  value from UI is ' + a[1].trim()  + '  value from json is ' +  result.non_compliance_policies[j].value)
								KeywordUtil.logInfo('Incorrect \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  result.non_compliance_policies[j].value + '\n')
								break
							}
						}
					}
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



	}

}



