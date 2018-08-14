# mHealth Integration Library

This library provides integration into the mHealthStudio application developed by hearX Group (Pty) Ltd.
The library provides mechanisms to both request tests and retrieve test, patient and facility information from mHealthStudio.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

A sample application using this library is provided in the 'sample' directory

### Prerequisites
1. Include the following in your app build.gradle file
```
//LIBRARIES REQUIRED FOR mhealthintegration lib TO WORK (TEMPORARY SOLUTION. WILL SOON PUBLISH TO MAVEN AND ONLY android-module-mhealthintegration-release will be required)
//INTERNAL AAR LIBRARIES
implementation(name: 'android-module-mhealthintegration-release-v1002', ext: 'aar')
implementation(name: 'android-module-hearx-release-v1002', ext: 'aar')
//RX JAVA
implementation 'io.reactivex.rxjava2:rxandroid:2.0.2'
implementation 'io.reactivex.rxjava2:rxjava:2.1.12'
//GUAVA
implementation 'com.google.guava:guava:23.6-android'
//GSON
implementation 'com.google.code.gson:gson:2.8.2'
//PHONENUMBER
implementation 'io.michaelrocks:libphonenumber-android:8.9.9'
//JODA TIME
implementation 'joda-time:joda-time:2.9.9'
```
2. Retrieve the android-module-mhealthintegration-release-v1002.aar and android-module-hearx-release-v1002.aar files from the sample projects app/libs folder and copy to your projects app/libs folder

Note. The above dependencies are only a temporary solution. Once this library is hosted on maven the above dependencies will not be required.

3. Ensure your manifest contains an intent filter as below inside an activity tag to allow mHealth to return to your application after testing.
```
<intent-filter>
   <action android:name="YOUR_RETURN_INTENT_ACTION_NAME" />
   <category android:name="android.intent.category.DEFAULT" />
   <data android:mimeType="text/plain" />
 </intent-filter>
```
4. Request a test by building a patient object (Optional) and using the MHealthTestRequest.build function
Eg.
```
//GENERATE UNIQUE 24 CHAR TEST ID
 String testId = RANDOM_24CHAR_SEQUENCE;
 //GENERATE PATIENT (NOT REQUIRED)
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
                 "com.hearxgroup.mhealthintegrationdemo.mhealthtest", //ACTION NAME AS DEFINED IN YOUR MANIFEST
                 demoPatient); //PATIENT OBJECT OR NULL
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
        mHealthGeneratedTestId);

  TestRequestHelper.retrievePatient(
                  MainActivity.this,
                  getLoaderManager(),
                  MainActivity.this,
                  patientGeneratedId);
```
## License

This project is licensed under the MIT License

## Authors

* **David Howe** - *Lead Mobile Developer* - [hearX Group (Pty) Ltd](https://www.hearxgroup.com).
