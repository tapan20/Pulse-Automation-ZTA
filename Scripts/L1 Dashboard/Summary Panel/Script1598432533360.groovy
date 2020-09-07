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

WebUI.delay(2)

//WebUI.mouseOver(findTestObject('Summary Panel/hover-gateway'))
//WebUI.getText(findTestObject('Summary Panel/tooltip-gateways'))
def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/Summarypanel.json')
	
def result = slurper.parse(jsontxt)

// For gateway
ArrayList<String> Summary_tooltip = new ArrayList<String>()

for (j = 0; j < 6; j++) {
    String css = ('#dashboard-summary__container > div:nth-child(' + (j + 1)) + ') > div > div.custom-compact-summary-card__flex-row.bottomRow > span'

    //String css = '#topRiskyUsersChart > div:nth-child(2) > svg > g > g.amcharts-Container > g.amcharts-Sprite-group.amcharts-Container-group.amcharts-Component-group.amcharts-Chart-group.amcharts-SerialChart-group.amcharts-XYChart-group > g > g:nth-child(2) > g > g > g > g:nth-child(1) > g > g:nth-child(1) > g.amcharts-Container > g:nth-child(3) > g > g > g > g.amcharts-Sprite-group.amcharts-Container-group > g > g > g > g:nth-child(' + i + ')'
    Summary_panel = new TestObject('customObject')

    Summary_panel.addProperty('css', ConditionType.EQUALS, css)

    System.out.println(css)

    WebUI.getText(Summary_panel)

    for (k = 0; k < 6; k++) {
        if (WebUI.getText(Summary_panel).toLowerCase() == result[k].name) {
            //Gateway_name = result[0].name
            if (result[k].name == 'gateways') {
                print('into the gateway')

                Gateway_value = result[k].actual_value

                Gateway_totalvalue = result[k].total_value

                UI_gateway = WebUI.getText(findTestObject('Summary Panel/Gateways'))

                print(UI_gateway)

                g = UI_gateway.split(' ')

                for (int i = 0; i < g.length; i++) {
                    System.out.println(((('g[' + i) + '] = "') + (g[i])) + '"')
                }
                
                final_string1 = (g[2]).substring(0, 1)

                WebUI.verifyEqual(Gateway_value, g[0], FailureHandling.CONTINUE_ON_FAILURE)

                WebUI.verifyEqual(Gateway_totalvalue, final_string1, FailureHandling.CONTINUE_ON_FAILURE)

                break
               
            } else if (result[k].name == 'users') {
                Users_value = result[k].actual_value

                Users_totalvalue = result[k].total_value

                UI_users = WebUI.getText(findTestObject('Summary Panel/Users'))

                print(UI_users)

                u = UI_users.split(' ')

                for (int i = 0; i < u.length; i++) {
                    System.out.println(((('u[' + i) + '] = "') + (u[i])) + '"')
                }
                
                final_string1 = (u[2]).substring(0, 4)

                WebUI.verifyEqual(Users_value, u[0], FailureHandling.CONTINUE_ON_FAILURE)

                WebUI.verifyEqual(Users_totalvalue, final_string1, FailureHandling.CONTINUE_ON_FAILURE)

                break
            } else if (result[k].name == 'devices') {
                Device_value = result[j].actual_value

                Device_totalvalue = result[j].total_value

                UI_devices = WebUI.getText(findTestObject('Summary Panel/Devices'))

                print(UI_devices)

                d = UI_devices.split(' ')

                for (int i = 0; i < d.length; i++) {
                    System.out.println(((('d[' + i) + '] = "') + (d[i])) + '"')
                }
                
                final_string3 = (d[2]).substring(0, 1)

                WebUI.verifyEqual(Device_value, d[0], FailureHandling.CONTINUE_ON_FAILURE)

                WebUI.verifyEqual(Device_totalvalue, final_string3, FailureHandling.CONTINUE_ON_FAILURE)

                break
            } else if (result[k].name == 'applications') {
                Application_value = result[j].actual_value

                Application_totalvalue = result[j].total_value

                UI_Applications = WebUI.getText(findTestObject('Summary Panel/Applications'))

                print(UI_Applications)

                a = UI_Applications.split(' ')

                for (int i = 0; i < a.length; i++) {
                    System.out.println(((('a[' + i) + '] = "') + (a[i])) + '"')

                    print(a[i])
                }
                
                final_string4 = (a[2]).substring(0, 2)

                WebUI.verifyEqual(Application_value, a[0], FailureHandling.CONTINUE_ON_FAILURE)

                WebUI.verifyEqual(Application_totalvalue, final_string4, FailureHandling.CONTINUE_ON_FAILURE)

                break
            } else if (result[k].name == 'non_compliance') {
                non_compliance_value = result[j].actual_value

                non_compliance_totalvalue = result[j].total_value

                UI_non_compliance = WebUI.getText(findTestObject('Summary Panel/Non-complaince'))

                print(UI_non_compliance)

                n = UI_non_compliance.substring(0, 1)

                WebUI.verifyEqual(non_compliance_totalvalue, n, FailureHandling.CONTINUE_ON_FAILURE)

                break
            } else if (result[k].name == 'anomalies') {
                anomalies_name = result[j].name

                anomalies_value = result[j].actual_value

                anomalies_totalvalue = result[j].total_value

                UI_anomalies = WebUI.getText(findTestObject('Summary Panel/Anomalies'))

                aa = UI_anomalies.substring(0, 2)

                WebUI.verifyEqual(anomalies_totalvalue, aa, FailureHandling.CONTINUE_ON_FAILURE)

                break
            }
        } //////////
    }
}