package com.hearxgroup.mhealthintegration.Models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.hearxgroup.orbit.logic.Const

class VulaVisionTest {


    @SerializedName("server_id")
    var serverId : Long = Const.CODE_UNSET_LONG
    @SerializedName("patient_id")
    var patientId: Long= Const.CODE_UNSET_LONG
    @SerializedName("facility_id")
    var facilityId: Long= Const.CODE_UNSET_LONG

    var uuid : String = ""

    var patientUuid: String= ""
    @SerializedName("facility_uuid")
    var facilityUuid: String= ""
    @SerializedName("patient_session_id")
    var patientSessionId : String? = null
    @SerializedName("license_ids")
    var licenseIds: List<Long> = emptyList()
    @SerializedName("device_std_id")
    var deviceStdId: Long= Const.CODE_UNSET_LONG

    var lat: Double= Const.CODE_UNSET_DOUBLE
    var lng: Double= Const.CODE_UNSET_DOUBLE

    @SerializedName("mhealth_version")
    var mhealthVersion: Int= Const.CODE_UNSET //Internal use
    @SerializedName("created_at")
    var createdAt: Long= Const.CODE_UNSET_LONG
    @SerializedName("pending_upload")
    var pendingUpload: Boolean= true
    @SerializedName("pending_retrieval")
    var pendingRetrieval: Boolean= true

    @SerializedName("known_impairment_type")
    var knownImpairmentType : Int ?= Const.CODE_UNSET
    var glasses: Boolean= false

    @SerializedName("mhealth_device_mode")
    var mhealthDeviceMode : Int = com.hearxgroup.mhealth.resources.Const.DEVICE_MODE_TESTER_ASSISTED
    @SerializedName("terms_accepted")
    var termsAccepted : Boolean = false
    @SerializedName("terms_url")
    var termsUrl : String = ""

    @SerializedName("patient_job_title")
    var patientJobTitle: String?=null
    @SerializedName("patient_company")
    var patientCompany: String?=null
    @SerializedName("patient_employment_date")
    var patientEmploymentDate: String?=null
    @SerializedName("patient_department")
    var patientDepartment: String?=null
    @SerializedName("patient_noise_exposure")
    var patientNoiseExposure : String?=null

    /*----SET BY TEST-----*/

    @SerializedName("test_date")
    var testDate: Long = Const.CODE_UNSET_LONG
    @SerializedName("patient_signature")
    var patientSignature: String?=null
    var duration: Int= Const.CODE_UNSET
    var notes: String?=null

    @SerializedName("software_version")
    var softwareVersion: String=""
    var progression: Int= Const.CODE_UNSET

    @SerializedName("vula_version_number")
    var vulaVersionNumber: String = "1.0.0"
    @SerializedName("test_config_progression")
    var testConfigProgression: String = "Clinical"
    @SerializedName("test_config_distance_protocol")
    var testConfigDistanceProtocol: Double = Const.CODE_UNSET_DOUBLE
    @SerializedName("test_config_refer_logmar_one_eye")
    var testConfigReferLogmarOneEye: Double = Const.CODE_UNSET_DOUBLE
    @SerializedName("test_config_refer_logmar_both_eyes")
    var testConfigReferLogmarBothEyes: Double = Const.CODE_UNSET_DOUBLE
    @SerializedName("test_progression_logmar")
    var testProgressionLogmar: List<LogmarTestProgression>? = null
    @SerializedName("result_left_status")
    var resultLeftStatus: Int = Const.CODE_UNSET
    @SerializedName("result_right_status")
    var resultRightStatus: Int = Const.CODE_UNSET
    @SerializedName("result_left_logmar")
    var resultLeftLogmar: Double= Const.CODE_UNSET_DOUBLE
    @SerializedName("result_right_logmar")
    var resultRightLogmar: Double = Const.CODE_UNSET_DOUBLE
    @SerializedName("result_left_snellen")
    var resultLeftSnellen: String = ""
    @SerializedName("result_right_snellen")
    var resultRightSnellen: String = ""
    @SerializedName("protocol_threshold")
    var protocolThreshold: Int = 0 //from LogmarResult test_threshold
    @SerializedName("average_brightness")
    var averageBrightness: Double= Const.CODE_UNSET_DOUBLE

    var retest: Boolean = false
    var state : Int? = null

    companion object{
        fun fromJson(jsonData: String): VulaVisionTest{
            return Gson().fromJson(jsonData, VulaVisionTest::class.java)
        }

        fun toJson(): String? {
            return Gson().toJson(this)
        }
    }
}

data class LogmarTestProgression(
        val test_eye : Int = 0,
        val vision_logmar: Double = 0.0,
        val vision_average_ambient_light: Double = 0.0,
        val vision_response_button: String = "",
        val vision_response_swipe: String = "",
        val vision_actual: Int = 0,
        val vision_pass: Boolean = false,
        val vision_step_completed: Boolean = false,
        val step: Int = 0,
        val step_start_time: Int = 0,
        val step_end_time: Int = 0,
        val vision_response: Int = 0 //only used for fingers test. Not currently valid
)