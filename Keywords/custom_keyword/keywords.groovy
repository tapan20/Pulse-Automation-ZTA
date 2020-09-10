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




	@Keyword
	//This function will generate log-file according to current time when we run the test-case
	def Log_file(){
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
	}


	@Keyword
	def login() {
		WebUI.openBrowser('')

		WebUI.maximizeWindow()

		WebUI.navigateToUrl(GlobalVariable.URL1)

		WebUI.delay(3)

		WebUI.setText(findTestObject('Login objects/username'), GlobalVariable.UserAdmin)

		WebUI.setText(findTestObject('Login objects/password'), GlobalVariable.UserPass)

		//WebUI.sendKeys(findTestObject('Login objects/singin button'), Keys.chord(Keys.ENTER))

		WebUI.click(findTestObject('Object Repository/Login objects/singin button'))
	}

	@Keyword
	//This function will use to read json data from result file
	def json(){

		def slurper = new JsonSlurper()

		File jsontxt = new File(System.getProperty('user.dir') + '/JSON/result.json')

		def result = slurper.parse(jsontxt)

		GlobalVariable.result = result

		//print(GlobalVariable.result)
	}



	@Keyword
	// Filter for Last calander week
	def apply_filter(){

		WebUI.click(findTestObject('Filter object/filter_button'))
		WebUI.click(findTestObject('Filter object/set_filter_lentills'))
		WebUI.click(findTestObject('Filter object/down_arrow'))
		WebUI.click(findTestObject('Filter object/last calander week'))
		WebUI.click(findTestObject('Filter object/Apply filter'))
	}

	@Keyword
	//Filter for current day
	def apply_CurrentDayfilter(){

		WebUI.click(findTestObject('Filter object/filter_button'))
		WebUI.click(findTestObject('Filter object/set_filter'))
		WebUI.click(findTestObject('Filter object/down_arrow'))
		WebUI.click(findTestObject('Filter object/current_day'))
		WebUI.click(findTestObject('Filter object/Apply filter'))
	}

	@Keyword
	def active_anomalies()  {
		//for log file date


		KeywordUtil.logInfo("Anomalies test-case \n \n")

		
		int loop_count = GlobalVariable.result.anomalies.result.buckets.size()

		print('loop count is ' + loop_count)

		try {
			ArrayList<String> UI = new ArrayList<String>()

			ArrayList<String> JSON_Name = new ArrayList<String>()

			ArrayList<String> JSON_Value = new ArrayList<String>()
			// To read one by one data from the UI according to different css
			for (int i = 0; i < loop_count; i++) {
				String css = ('#dashboard-l1-anomalies-chart > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(1) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g > g:nth-child(1) > g:nth-child(2) > g:nth-child(3) > g > g:nth-child(' +
						(i + 1)) + ') > g > g > g > g > g > g > g > g > path'

				//creating run time locator object to hover the chart element
					
				TestObject Anomalies = new TestObject('customObject')

				Anomalies.addProperty('css', ConditionType.EQUALS, css)

				System.out.println(css)

				WebUI.mouseOver(Anomalies)

				try {
					//creating run time object to get tooltip data
					TestObject Tooltip_anomalies = new TestObject('customObject')

					Tooltip_anomalies.addProperty('css', ConditionType.EQUALS, '#dashboard-l1-anomalies-chart > div:nth-child(2) > svg > g > g.amcharts-Container > g:nth-child(2) > g > g:nth-child(6) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

					(UI[(i + 1)]) = WebUI.getText(Tooltip_anomalies)

					print(UI[(i + 1)])

					String[] a = (UI[(i + 1)]).split(':')

					System.out.println(a[0])

					for (int j = 0; j < loop_count; j++) {
						if ((a[0]) == GlobalVariable.result.anomalies.result.buckets[j].name) {
							if ((a[1]).trim() == GlobalVariable.result.anomalies.result.buckets[j].value) {
								print('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  GlobalVariable.result.anomalies.result.buckets[j].value)
								KeywordUtil.logInfo('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  GlobalVariable.result.anomalies.result.buckets[j].value + '\n \n')
							} else {


								print('Incorrect \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  GlobalVariable.result.anomalies.result.buckets[j].value)
								KeywordUtil.logInfo('Incorrect \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  GlobalVariable.result.anomalies.result.buckets[j].value + '\n n')
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


	@Keyword

	def Non_compliance(){



		KeywordUtil.logInfo("Non-compliance test-case \n \n")

		int loop_count = GlobalVariable.result.top_non_compliance.result.non_compliance_policies.size()

		print('loop count is' + loop_count)

		try {
			ArrayList<String> UI = new ArrayList<String>()

			ArrayList<String> JSON_Name = new ArrayList<String>()

			ArrayList<String> JSON_Value = new ArrayList<String>()
			// To read one by one data from the UI according to different css
			for (int i = 0; i < loop_count ; i++) {
				String css = ('#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g:nth-child(2) > g:nth-child(1) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g > g:nth-child(1) > g:nth-child(2) > g:nth-child(3) >g > g > g > g > g > g > g > g:nth-child(' +
						(i + 1)) + ') > g > g > path'

				//String css = '#topRiskyUsersChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + i + ')'
					//run time object for Non-compliance blocks reading on chart
				TestObject Non_compliance = new TestObject('customObject')

				Non_compliance.addProperty('css', ConditionType.EQUALS, css)

				print(css)

				WebUI.mouseOver(Non_compliance)

				try {
					//run time object for tooltip
					TestObject  Tooltip_nc = new TestObject('customObject')
					Tooltip_nc.addProperty('css', ConditionType.EQUALS, '#dashboard-l1-non-compliance > div:nth-child(2) > svg > g > g.amcharts-Container  > g:nth-child(2) > g > g:nth-child(5) > g.amcharts-Container.amcharts-Tooltip > g > g > text > tspan')

					(UI[(i + 1)]) = WebUI.getText(Tooltip_nc)

					print(UI[(i + 1)])

					String[] a = (UI[(i + 1)]).split(':')

					println(a[0])

					for (int j = 0; j < loop_count ; j++) {
						print(GlobalVariable.result.top_non_compliance.result.non_compliance_policies[j].name)

						if ((a[0]) == GlobalVariable.result.top_non_compliance.result.non_compliance_policies[j].name) {
							if ((a[1]).trim().toString() == GlobalVariable.result.top_non_compliance.result.non_compliance_policies[j].value.toString()) {


								print('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  GlobalVariable.result.top_non_compliance.result.non_compliance_policies[j].value)
								KeywordUtil.logInfo('correct \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' + GlobalVariable.result.top_non_compliance.result.non_compliance_policies[j].value + '\n \n')

								break
							} else {
								print('Incorrect \n' + '  value from UI is ' + a[1].trim()  + '  value from json is ' + GlobalVariable.result.top_non_compliance.result.non_compliance_policies[j].value)
								KeywordUtil.logInfo('Incorrect \n' + '  value from UI is ' + a[1].trim() + '  value from json is ' +  GlobalVariable.result.top_non_compliance.result.non_compliance_policies[j].value + '\n \n')
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


	@Keyword
	def Summary_panel(){


		
		
		KeywordUtil.logInfo("Summary Panel \n \n")
		
		ArrayList<String> Summary_tooltip = new ArrayList<String>()
		//To read all the summary data one by one from UI
		for (int j = 0; j < 6; j++) {
			String css = ('#dashboard-summary__container > div:nth-child(' + (j + 1)) + ') > div > div.custom-compact-summary-card__flex-row.bottomRow > span'

			//String css = '#topRiskyUsersChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + i + ')'
			TestObject Summary_panel = new TestObject('customObject')

			Summary_panel.addProperty('css', ConditionType.EQUALS, css)

			System.out.println(css)

			WebUI.getText(Summary_panel)

			for (int k = 0; k < 6; k++) {
				if (WebUI.getText(Summary_panel).toLowerCase() == GlobalVariable.result.summary.result[k].name) {
					//Gateway_name = result[0].name
					//For gateway
					if (GlobalVariable.result.summary.result[k].name == 'gateways') {
						print('into the gateway')

						String Gateway_value = GlobalVariable.result.summary.result[j].actual_value
						print(Gateway_value)

						String Gateway_totalvalue = GlobalVariable.result.summary.result[j].total_value
						print(Gateway_totalvalue)

						def UI_gateway = WebUI.getText(findTestObject('Summary Panel/Gateways'))

						print(UI_gateway)

						def g = UI_gateway.split(' ')

						for (int i = 0; i < g.length; i++) {
							System.out.println(((('g[' + i) + '] = "') + (g[i])) + '"')
						}

						String  final_string1 = (g[2]).substring(0,1)

						if(Gateway_value == g[0] ){

							print('Gateway values are matched \n' +'from json  '  + Gateway_value+ '\n' +'from UI  ' + g[0] + '\n' )
							KeywordUtil.logInfo('Gateway values are matched \n' +'from json  '  + Gateway_value+ '\n' +'from UI  ' + g[0] + '\n' )
						}

						else {

							print('Gateway values are not matched \n' + 'from json   '   + Gateway_value+ '\n' +'from UI  '  + g[0] + '\n' )
							KeywordUtil.logInfo('Gateway values are not matched \n' +'from json  '  + Gateway_value+ '\n' +'from UI  ' + g[0] + '\n' )
						}

						if(Gateway_totalvalue == final_string1){

							print('\n Gateway total values are  matched \n'+ 'from json  '  + Gateway_totalvalue+ '\n' + 'from UI  '  +  final_string1 + '\n')
							KeywordUtil.logInfo('\n Gateway total values are  matched \n'+ 'from json  '  + Gateway_totalvalue+ '\n' + 'from UI  '  +  final_string1 + '\n')
						}

						else {

							print('\n Gateway total values are not matched \n'+ 'from json  ' +Gateway_totalvalue+ '\n'+ 'from UI  ' + final_string1 + '\n')
							KeywordUtil.logInfo('\n Gateway total values are not  matched \n'+ 'from json  '  + Gateway_totalvalue+ '\n' + 'from UI  '  +  final_string1 + '\n')
						}
						break

						//For users
					} else if (GlobalVariable.result.summary.result[k].name == 'users') {
						String Users_value = GlobalVariable.result.summary.result[j].actual_value

						def Users_totalvalue = GlobalVariable.result.summary.result[j].total_value

						def UI_users = WebUI.getText(findTestObject('Summary Panel/Users'))

						print(UI_users)

						def u = UI_users.split(' ')

						for (int i = 0; i < u.length; i++) {
							System.out.println(((('u[' + i) + '] = "') + (u[i])) + '"')
						}

						String final_string2 = (u[2]).substring(0, 4)

						if(Users_value.toString() == u[0].toString()){

							print('Usersvalues are matched \n'+ 'from json  ' +Users_value+ '\n' + 'from UI  '+ u[0]+'\n'  )
							KeywordUtil.logInfo('Users values are matched \n'+ 'from json  ' +Users_value+ '\n' + 'from UI  '+ u[0]+'\n'  )

						}

						else {

							print('Users values are not matched \n'+ 'from json  ' +Users_value+ '\n'+ 'from UI  ' + u[0]+'\n'  )
							KeywordUtil.logInfo('Users values are not matched \n'+ 'from json  ' +Users_value+ '\n' + 'from UI  '+ u[0]+'\n'  )

						}
						if(Users_totalvalue.toString() == final_string2.toString()){

							print('Users total values are matched \n'+ 'from json  ' +Users_totalvalue+ '\n'+ 'from UI  ' + final_string2+'\n'  )
							KeywordUtil.logInfo('Users total values are matched \n'+ 'from json  ' +Users_totalvalue+ '\n'+ 'from UI  ' + final_string2+'\n'  )
						}
						else {


							print('Users total values are not matched \n'+ 'from json  ' +Users_totalvalue+ '\n' + 'from UI  '+ final_string2+'\n'  )
							KeywordUtil.logInfo('Users total values are not matched \n'+ 'from json  ' +Users_totalvalue+ '\n'+ 'from UI  ' + final_string2+'\n'  )
						}

						break
						
						//For devices
					} else if (GlobalVariable.result.summary.result[k].name == 'devices') {
						def Device_value = GlobalVariable.result.summary.result[j].actual_value

						def Device_totalvalue = GlobalVariable.result.summary.result[j].total_value

						def UI_devices = WebUI.getText(findTestObject('Summary Panel/Devices'))

						print(UI_devices)

						def d = UI_devices.split(' ')

						for (int i = 0; i < d.length; i++) {
							System.out.println(((('d[' + i) + '] = "') + (d[i])) + '"')
						}

						String final_string3 = (d[2]).substring(0, 1)

						if(Device_value.toString() == d[0].toString()){

							print('Devices values are matched \n'+ 'from json  ' + Device_value + '\n'+ 'from UI  ' + d[0] + '\n' )
							KeywordUtil.logInfo('Devices values are matched \n'+ 'from json  ' + Device_value + '\n'+ 'from UI  ' + d[0] + '\n' )
						}
						else {

							print('Devices values are not matched \n'+ 'from json  ' + Device_value + '\n' + 'from UI  '+ d[0] + '\n' )
							KeywordUtil.logInfo('Devices values are matched \n'+ 'from json  ' + Device_value + '\n'+ 'from UI  ' + d[0] + '\n' )
						}

						if(Device_totalvalue.toString() == final_string3.toString()){
							print('Devices total values are matched \n'+ 'from json  ' + Device_totalvalue + '\n' + 'from UI  '+ final_string3 + '\n' )
							KeywordUtil.logInfo('Devices total values are matched \n'+ 'from json  ' + Device_totalvalue + '\n' + 'from UI  '+ final_string3 + '\n' )
						}
						else {

							print('Devices total values are not matched \n'+ 'from json  ' + Device_totalvalue + '\n' + 'from UI  '+ final_string3 + '\n' )
							KeywordUtil.logInfo('Devices total values are not matched \n'+ 'from json  ' + Device_totalvalue + '\n' + 'from UI  '+ final_string3 + '\n' )
						}

						break
						
						//For applications
					} else if (GlobalVariable.result.summary.result[k].name == 'applications') {
						def Application_value = GlobalVariable.result.summary.result[j].actual_value

						def Application_totalvalue = GlobalVariable.result.summary.result[j].total_value

						def UI_Applications = WebUI.getText(findTestObject('Summary Panel/Applications'))

						print(UI_Applications)

						def a = UI_Applications.split(' ')

						for (int i = 0; i < a.length; i++) {
							System.out.println(((('a[' + i) + '] = "') + (a[i])) + '"')

							print(a[i])
						}

						String  final_string4 = (a[2]).substring(0, 2)

						if(Application_value.toString() ==  a[0].toString()){

							print('Applications values are matched \n'+ 'from json  ' + Application_value + '\n' + 'from UI  '+ a[0] + '\n' )
							KeywordUtil.logInfo('Applications values are matched \n'+ 'from json  ' + Application_value + '\n' + 'from UI  '+ a[0] + '\n' )
						}
						else {

							print('Applications values are not matched \n'+ 'from json  ' + Application_value + '\n'+ 'from UI  ' + a[0] + '\n' )
							KeywordUtil.logInfo('Applications values are not matched \n'+ 'from json  ' + Application_value + '\n' + 'from UI  '+ a[0] + '\n' )
						}
						if(Application_totalvalue.toString() == final_string4.toString()){
							print('Applications total values are matched \n'+ 'from json  ' + Application_totalvalue + '\n' + 'from UI  '+ final_string4 + '\n' )
							KeywordUtil.logInfo('Applications total values are matched \n'+ 'from json  ' + Application_totalvalue + '\n' + 'from UI  '+ final_string4 + '\n' )
						}
						else {

							print('Applications values are not matched \n' + 'from json  '+ Application_totalvalue + '\n' + 'from UI  '+ final_string4 + '\n' )
							KeywordUtil.logInfo('Applications total values are not matched \n'+ 'from json  ' + Application_totalvalue + '\n' + 'from UI  '+ final_string4 + '\n' )
						}
						break


						
						//For Non-compliance
					} else if (GlobalVariable.result.summary.result[k].name == 'non_compliance') {
						def  non_compliance_value = GlobalVariable.result.summary.result[j].actual_value

						def  non_compliance_totalvalue = GlobalVariable.result.summary.result[j].total_value

						def UI_non_compliance = WebUI.getText(findTestObject('Summary Panel/Non-complaince'))

						print(UI_non_compliance)

						def n = UI_non_compliance.substring(0, 1)

						if(non_compliance_totalvalue.toString() == n.toString()){

							print('NC values are  matched \n'+ 'from json  ' + non_compliance_totalvalue + '\n' + 'from UI  '+ n + '\n' )
							KeywordUtil.logInfo('NC values are  matched \n'+ 'from json  ' + non_compliance_totalvalue + '\n' + 'from UI  '+ n + '\n' )
						}
						else {

							print('NC values are not matched \n' + 'from json  '+ non_compliance_totalvalue + '\n'+ 'from UI  ' + n + '\n' )
							KeywordUtil.logInfo('NC values are not  matched \n'+ 'from json  ' + non_compliance_totalvalue + '\n' + 'from UI  '+ n + '\n' )
						}
						break
						
						//For anomalies
					} else if (GlobalVariable.result.summary.result[k].name == 'anomalies') {
						def anomalies_name = GlobalVariable.result.summary.result[j].name

						def anomalies_value = GlobalVariable.result.summary.result[j].actual_value

						def anomalies_totalvalue = GlobalVariable.result.summary.result[j].total_value

						String UI_anomalies = WebUI.getText(findTestObject('Summary Panel/Anomalies'))

						String aa = UI_anomalies.substring(0, 1)

						if(anomalies_totalvalue.toString() == aa.toString()){
							print('Anomalies values are matched \n' + 'from json  '+ anomalies_totalvalue + '\n' + 'from UI  '+ aa + '\n' )
							KeywordUtil.logInfo('Anomalies values are matched \n' + 'from json  '+ anomalies_totalvalue + '\n' + 'from UI  '+ aa + '\n' )
						}
						else {

							print('Anomales values are not matched \n'+ 'from json  ' + anomalies_totalvalue + '\n'+ 'from UI  ' + aa + '\n' )
							KeywordUtil.logInfo('Anomalies values are not matched \n' + 'from json  '+ anomalies_totalvalue + '\n' + 'from UI  '+ aa + '\n' )
						}
						break
					}
				} //////////
			}


		}




	}
}



