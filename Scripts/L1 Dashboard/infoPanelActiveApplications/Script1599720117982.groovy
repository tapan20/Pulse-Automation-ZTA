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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import groovy.json.JsonSlurper as JsonSlurper


/*******************************************************************************
 
  * This test case aims to match the values of Active Applications Info Panel from JSON with Web.
 
  * Test Steps:
  * 1. Log in with valid creds.
  * 2. Apply right filters ( last calendar week for now )
  * 3. Click on Active Applications on web.
  * 4. Scroll after every 2 cards.
  * 5. Fetch the application name from web and match the Object from JSON.
  * 6. If matches, check for the remaining parameters.
  * 7. Print the results .
 
  ********************************************************************************/

CustomKeywords.'custom_keyword.keywords.login'()

WebUI.delay(2)

CustomKeywords.'custom_keyword.keywords.apply_filter'()

WebUI.delay(2)

WebUI.click(findTestObject('Summary Panel/Applications'))

count = WebUI.getText(findTestObject('infoPanelApplications/appCount'))

int iCount = Integer.parseInt(count)

println(iCount)

ArrayList<String> webTitle = new ArrayList<String>()

def slurper = new JsonSlurper()

File jsontxt = new File(System.getProperty('user.dir') + '/JSON/result.json')

def result = slurper.parse(jsontxt)

def applicationTitleMap = [:]

for (def object : result.top_application_panel.result.info_panel_items) {
    String appTitle = object.title.toLowerCase()

    (applicationTitleMap[appTitle]) = object
}

for (int j = 1; j <= iCount; j++) {
    if ((j % 2) == 0) {
        String cssScroll = ('#info-panel > div > div:nth-child(2) > div:nth-child(' + j) + ')'

        println(cssScroll)

        TestObject myScrollObject = new TestObject()

        myScrollObject.addProperty('css', ConditionType.EQUALS, cssScroll)

        WebUI.scrollToElement(myScrollObject, 0)

        WebUI.delay(3)
    }
    
    String applicationTypeCSS = ('#info-panel > div > div:nth-child(2) > div:nth-child(' + j) + ') > div.dashboard-sidepanel__content-panel > div > div:nth-child(1) > div:nth-child(1) > div > div:nth-child(1) > span.psui-compact-summary-card__content'

    String activeUsersCSS = ('#info-panel > div > div:nth-child(2) > div:nth-child(' + j) + ') > div.dashboard-sidepanel__content-panel > div > div:nth-child(1) > div:nth-child(2) > div > div:nth-child(1) > span.psui-compact-summary-card__content'

    String nonComplianceCSS = ('#info-panel > div > div:nth-child(2) > div:nth-child(' + j) + ') > div.dashboard-sidepanel__content-panel > div > div:nth-child(2) > div:nth-child(1) > div > div:nth-child(1) > span.psui-compact-summary-card__content'

    String activeDevicesCSS = ('#info-panel > div > div:nth-child(2) > div:nth-child(' + j) + ') > div.dashboard-sidepanel__content-panel > div > div:nth-child(2) > div:nth-child(2) > div > div:nth-child(1) > span.psui-compact-summary-card__content'

    String titleCSS = ('#info-panel > div > div:nth-child(2) > div:nth-child(' + j) + ') > div.dashboard-sidepanel__card-header > div > div.dashboard-sidepanel__card-header-left__titles > h5'

    WebTitle = new TestObject('customObject')

    WebTitle.addProperty('css', ConditionType.EQUALS, titleCSS)

    Webtitle = WebUI.getText(WebTitle)

    (webTitle[j]) = Webtitle

    println('web title : ' + (webTitle[j]))

    if (applicationTitleMap.containsKey((webTitle[j]).toLowerCase())) {
        // Snippet for App type match
        def appTitleObj = applicationTitleMap[(webTitle[j]).toLowerCase()]

        webApplicationType = new TestObject('webAppTypeObject')

        webApplicationType.addProperty('css', ConditionType.EQUALS, applicationTypeCSS)

        String webAppType = WebUI.getText(webApplicationType)

        // (webUsers[j]) = WebUsersName
        println('Application Type from Web : ' + webAppType)

        // (jsonActiveUsersCount[j]) = gatewayObj.nvt_items[0].value
        println('Application Type from JSON : ' + appTitleObj.nvt_items[3].trend)

        if (webAppType == appTitleObj.nvt_items[3].value.toString()) {
            println(((('Application Type from Web is : ' + webAppType) + ' and Application Type from JSON is:  ') + appTitleObj.nvt_items[
                3].trend) + ' , hence it matches')
        } else {
            println(((('Application Type from Web is : ' + webAppType) + ' and Application Type from JSON is:  ') + appTitleObj.nvt_items[
                3].trend) + ' , hence it does not matches')
        }
        
        // Snippet for Active Users Match
        webActiveUsers = new TestObject('webActiveUserObject')

        webActiveUsers.addProperty('css', ConditionType.EQUALS, activeUsersCSS)

        String webActiveUser = WebUI.getText(webActiveUsers)

        println('Active Users from Web : ' + webActiveUser)

        println('Actuve Users from JSON : ' + appTitleObj.nvt_items[0].value)

        if (webActiveUser == appTitleObj.nvt_items[0].value.toString()) {
            println(((('Active Users from Web is : ' + webActiveUser) + ' and Active Users from JSON is:  ') + appTitleObj.nvt_items[
                0].value) + ' , hence it matches')
        } else {
            println(((('Active Users from Web is : ' + webActiveUser) + ' and Active Users from JSON is:  ') + appTitleObj.nvt_items[
                0].value) + ' , hence it does not matches')
        }
        
        // Snippet for Non Compliance Count
        webNonCompliance = new TestObject('webNCObject')

        webNonCompliance.addProperty('css', ConditionType.EQUALS, nonComplianceCSS)

        String webNC = WebUI.getText(webNonCompliance)

        println('Non Compliance from Web : ' + webNC)

        println('Non Compliance from JSON : ' + appTitleObj.nvt_items[4].value)

        if (webNC == appTitleObj.nvt_items[4].value.toString()) {
            println(((('Non Compliance from Web is : ' + webNC) + ' and Non Compliance from JSON is:  ') + appTitleObj.nvt_items[
                4].value) + ' , hence it matches')
        } else {
            println(((('Non Compliance from Web is : ' + webNC) + ' and Non Compliance from JSON is:  ') + appTitleObj.nvt_items[
                4].value) + ' , hence it does not matches')
        }
        
        // Snippet for Active Devices
        webActiveDevice = new TestObject('webActiveDeviceObject')

        webActiveDevice.addProperty('css', ConditionType.EQUALS, activeDevicesCSS)

        String webActDevice = WebUI.getText(webActiveDevice)

        println('Active Device from Web : ' + webActDevice)

        println('Active Device from JSON : ' + appTitleObj.nvt_items[2].value)

       
        
        if (webActDevice == appTitleObj.nvt_items[2].value.toString()) {
            println(((('Active Device from Web is : ' + webActDevice) + ' and Active Device from JSON is:  ') + appTitleObj.nvt_items[
                2].value) + ' , hence it matches')
        } else {
            println(((('Active Device from Web is : ' + webActDevice) + ' and Active Device from JSON is:  ') + appTitleObj.nvt_items[
                2].value) + ' , hence it does not matches')
        }
    } else {
        println('Not match')
    }
}