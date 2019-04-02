# mHealth Integration Library

This library provides integration into the mHealthStudio application developed by hearX Group (Pty) Ltd.
The library provides mechanisms to both request tests and retrieve test, patient and facility information from mHealthStudio.

## Getting Started
Note. A sample application using this library is provided in the 'sample' directory

Refer to the IntegrationViaIntentActivity for most common usage of mHealth integration. (Recommended)

You may also refer to IntegrationViaFileActivity if your application is not able to receive intent data from external applications (Not recommended)

### Prerequisites
1. Include the following in your app build.gradle file
```
implementation 'com.github.hearSmart:android-module-mhealthintegration:v2003'
```

2. Ensure you have jitpack maven repo defined in your top level build.gradle repositories list
```
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
```

3. Ensure your manifest contains an intent filter as below inside an activity tag to allow mHealth to return to your application after testing.
```
<intent-filter>
   <action android:name="YOUR_RETURN_INTENT_ACTION_NAME" />
   <category android:name="android.intent.category.DEFAULT" />
   <data android:mimeType="text/plain" />
 </intent-filter>
```
4. Request a test by building a patient object (Optional) and using the MHealthTestRequest.build function
```
//GENERATE UNIQUE 24 CHAR TEST ID
String testId = UUID.randomUUID().toString().replaceAll("-", "");
//BUILD PATIENT (NOT REQUIRED)
Patient demoPatient = Patient.build(
         "Will",//firstName (Mandatory)
         "Turner",//lastName (Mandatory)
         "1987-05-07",//YYYY-MM-dd (Mandatory)
         "male", //male/female (Mandatory)
         "eng",//iso3 languageCode (Mandatory)
         null,//email (Not Mandatory)
         null,//contactnumber (Not Mandatory)
         null,//identificationNumber (Users national identification number) (Not Mandatory)
         null);//referenceNumber (Any reference string you have to connect with your system) (Not Mandatory)
//BUILD TEST REQUEST
MHealthTestRequest testRequest =
         MHealthTestRequest.build(
                 testId, //UNIQUE TEST ID
                 YOUR_RETURN_INTENT_ACTION_NAME, //ACTION NAME AS DEFINED IN YOUR MANIFEST OR use "close" if you just want mHealth app to close after a test
                 demoPatient, //PATIENT OBJECT OR NULL
                 INDEX_HEARSCREEN); //REQUIRED TEST(INDEX_HEARSCREEN, INDEX_HEARTEST, INDEX_PEEK, INDEX_SEALCHECK, INDEX_HEARSCOPE, CODE_UNSET)
//UTILITY TO HELP YOU VALIDATE YOUR TEST REQUEST
String requestValidationResponse = Util.validateTestRequest(MainActivity.this, testRequest);
if(requestValidationResponse==null)
    //VALIDATION WAS PASSED, INITIATE TEST REQUEST
    TestRequestHelper.startTest(MainActivity.this, testRequest);
else
    //VALIDATION ERROR OCCURRED
    Log.e("MainActivity", "Validation error:"+requestValidationResponse);
```

5. Ensure your activity implements MHealthTestRetrieverContract.ContentRetrieverInterface
```
public class MainActivity extends AppCompatActivity implements MHealthTestRetrieverContract.ContentRetrieverInterface {
  ...

  @Override
    public void onRetrieveTestHearScreen(HearscreenTest hearscreenTest) {
    }

    @Override
    public void onRetrieveTestHearTest(HeartestTest heartestTest) {
    }

    @Override
    public void onRetrieveTestPeekAcuity(PeekAcuityTest peekAcuityTest) {
    }

    @Override
    public void onRetrievePatient(Patient patient) {
    }

    @Override
    public void onRetrieveFacility(Facility facility) {
    }

    @Override
    public void onRetrieveContentError(String errorMessage) {
    }
}
```

6. Use the TestRequestHelper class to retrieve your test, patient and facility information
```
TestRequestHelper.retrieveTestResult(
        MainActivity.this,
        getLoaderManager(),
        MainActivity.this,
        TestRequestHelper.getTestTypeFromIntent(intent),
        testId); //OBTAIN FROM RETURN INTENT

TestRequestHelper.retrievePatient(
                  MainActivity.this,
                  getLoaderManager(),
                  MainActivity.this,
                  patientId); //OBTAIN FROM TEST ENTRY
```
The above instructions assume you are using the process as shown in the IntegrationViaIntentActivity

Refer to the IntegrationViaFileActivity if your application is not capable of receiving intent data from external applications

## License

This project is licensed under the MIT License

## Authors

* **David Howe** - *Lead Mobile Developer* - [hearX Group (Pty) Ltd](https://www.hearxgroup.com).
