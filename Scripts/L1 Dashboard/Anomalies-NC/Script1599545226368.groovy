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

/* This case is consist of below test-case
 * login in for given cluster in profile
 * It will apply last week filter
 * It will then fectch data for Active anomalies 
 * It will then fectch data from Non-compliance chart and compare it with result.json
 * It will then read data from summary panel
 * 
 * */
//calling json reading funcation from keyword to read json data from file
CustomKeywords.'custom_keyword.keywords.json'()

CustomKeywords.'custom_keyword.keywords.login'()

WebUI.delay(2)

CustomKeywords.'custom_keyword.keywords.apply_filter'()

WebUI.delay(2)

//calling log file funcation to create one log file to see end results of comparision 
CustomKeywords.'custom_keyword.keywords.Log_file'()

CustomKeywords.'custom_keyword.keywords.Summary_panel'()

WebUI.callTestCase(findTestCase('L1 Dashboard/Map-tooltip'), [:], FailureHandling.STOP_ON_FAILURE)

CustomKeywords.'custom_keyword.keywords.active_anomalies'()

WebUI.delay(2)

CustomKeywords.'custom_keyword.keywords.Non_compliance'()

WebUI.delay(2)

WebUI.closeBrowser()

