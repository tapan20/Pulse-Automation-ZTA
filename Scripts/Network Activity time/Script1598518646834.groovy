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
import java.util.Date as Date
import java.util.TimeZone as TimeZone
import java.text.DateFormat as DateFormat
import java.text.ParseException as ParseException
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Calendar as Calendar
import groovy.time.TimeCategory as TimeCategory
import groovy.json.JsonSlurper as JsonSlurper

CustomKeywords.'custom_keyword.keywords.login'()

String jsonFile = '/Users/perry.gami/Downloads/Activity.json'

def slurper = new JsonSlurper()

File jsontxt = new File(jsonFile)

def result = slurper.parse(jsontxt)

String start = WebUI.getText(findTestObject('Activity/Start time'))

String end = WebUI.getText(findTestObject('Activity/End time'))

println('Start Time for Network Activity : ' + start)

println('/n')

println('End Time for Network Activity : ' + end)

long jsonStartTime = result.start_time

println('JSON Start TimeStamp =  ' + jsonStartTime)

long jsonEndTime = result.end_time

println('JSON End TimeStamp = ' + jsonEndTime)

println(start.substring(6, 24))

Date date = new Date(jsonStartTime * 1000)

SimpleDateFormat jdf = new SimpleDateFormat('"dd MMM yyyy hh:mm:ss a zzz "')

jdf.setTimeZone(TimeZone.getTimeZone('GMT'))

String java_date = jdf.format(date)

System.out.println(('\n' + java_date) + '\n')

String webStart_time = java_date

println('Web Start Date' + webStart_time)

if (jsonStartTime == webStart_time){
	
	print('values are matched')
	
}

else {
	
	print('values are not matched')
	
}

