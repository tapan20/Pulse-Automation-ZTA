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
import groovy.json.JsonSlurper as JsonSlurper
import java.text.SimpleDateFormat as SimpleDateFormat

CustomKeywords.'custom_keyword.keywords.login'()

def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/Activity_time.json')

def result = slurper.parse(jsontxt)

String start = WebUI.getText(findTestObject('Activity/Start time'))

println(start.substring(5, 31))

String end = WebUI.getText(findTestObject('Activity/End time'))

println(end.substring(5, 31))

println('Start Time for Network Activity : ' + start)

println('End Time for Network Activity : ' + end)

long jsonStartTime = result.start_time

println('JSON Start TimeStamp =  ' + jsonStartTime)

long jsonEndTime = result.end_time

println('JSON End TimeStamp = ' + jsonEndTime)

//println(end.substring(5, 32))
Date startDate = new Date(jsonStartTime * 1000)

SimpleDateFormat jdf = new SimpleDateFormat('"dd MMM yyyy hh:mm:ss a zzz "')

jdf.setTimeZone(TimeZone.getTimeZone('GMT'))

String java_Startdate = jdf.format(startDate)

//System.out.println(('\n' + java_Startdate) + '\n')

String webStartDate = java_Startdate.substring(1, 28)

println('Web Start Date' + webStartDate)

if (start.substring(5, 31) == webStartDate) {
    println('Start Time Matched')

    println((('Start Time from Web is : ' + start.substring(5, 31)) + ' and from JSON is : ') + webStartDate)
} else {
    println('Start Time not matched')

    println((('Start Time from Web is : ' + start.substring(5, 31)) + ' and from JSON is : ') + webStartDate)
}

Date endDate = new Date(jsonEndTime * 1000)

SimpleDateFormat jdf1 = new SimpleDateFormat('"dd MMM yyyy hh:mm:ss a zzz "')

jdf1.setTimeZone(TimeZone.getTimeZone('GMT'))

String java_Enddate = jdf1.format(endDate)

System.out.println(('\n' + java_Enddate) + '\n')

String webEndDate = java_Enddate.substring(1, 28)

println('Web End Date' + webEndDate)

if (end.substring(5, 31) == webEndDate) {
    println('End Time Matched')

    println((('End Time from Web is : ' + end.substring(5, 31)) + ' and from Json is : ') + webEndDate)
} else {
    println('End Time Not Matched')

    println((('End Time from Web is : ' + start.substring(5, 31)) + ' and from Json is : ') + webEndDate)
}

