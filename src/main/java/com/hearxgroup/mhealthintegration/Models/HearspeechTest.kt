package com.hearxgroup.mhealthintegration.Models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.hearxgroup.orbit.logic.Const
import com.hearxgroup.orbit.logic.Const.CODE_UNSET

class HearspeechTest {

    private val version = 1

    //TEST IDs
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("server_id")
    var serverId : Long = Const.CODE_UNSET_LONG

    @SerializedName("memberId")
    var serverMemberId: Long? = null

    @SerializedName("patient_id")
    var patientId: Long= Const.CODE_UNSET_LONG
    @SerializedName("facility_id")
    var facilityId: Long= Const.CODE_UNSET_LONG

    @SerializedName("adminId")
    var adminId: Long? = null
    var uuid : String = ""
    @SerializedName("headset_id")
    var headsetId: Long= Const.CODE_UNSET_LONG
    @SerializedName("headset_cal_id")
    var headsetCalId: Long= Const.CODE_UNSET_LONG
    @SerializedName("device_std_id")
    var deviceStdId: Long= Const.CODE_UNSET_LONG
    @SerializedName("patient_uuid")
    var patientUuid: String= ""
    @SerializedName("facility_uuid")
    var facilityUuid: String= ""
    @SerializedName("patient_session_id")
    var patientSessionId : String? = null
    @SerializedName("license_ids")
    var licenseIds: List<Long> = emptyList()
    var lat: Double= Const.CODE_UNSET_DOUBLE
    var lng: Double= Const.CODE_UNSET_DOUBLE
    @SerializedName("mhealth_version")
    var mhealthVersion: Int= CODE_UNSET //Internal use
    @SerializedName("created_at")
    var createdAt: Long= Const.CODE_UNSET_LONG
    @SerializedName("pending_upload")
    var pendingUpload: Boolean= true
    @SerializedName("pending_retrieval")
    var pendingRetrieval: Boolean= true
    @SerializedName("mhealth_device_mode")
    var mhealthDeviceMode : Int = com.hearxgroup.mhealth.resources.Const.DEVICE_MODE_TESTER_ASSISTED
    @SerializedName("terms_accepted")
    var termsAccepted : Boolean = false
    @SerializedName("terms_url")
    var termsUrl : String = ""
    @SerializedName("patient_gender")
    var patientGender : String ?= null
    @SerializedName("patient_birthdate")
    var patientBirthdate : String?= null
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
    @SerializedName("known_impairment_type")
    var knownImpairmentType : Int ?= CODE_UNSET

    /*----SET BY HEARSPEECH-----*/
    @SerializedName("test_date")
    var testDate: Long = Const.CODE_UNSET_LONG
    @SerializedName("protocol_obj")
    var protocolObj: ProtocolHearSpeech?=null
    @SerializedName("patient_signature")
    var patientSignature: String?=null
    var notes: String?=null
    @SerializedName("software_version")
    var softwareVersion: String=""
    @SerializedName("subtest_digit")
    var subtestDigit: DinSubtest ?=null
    @SerializedName("result_message")
    var resultMessage: String = ""

    @SerializedName("result_ucl_mcl_left")
    var resultUclMclLeft: UCLResult? = null
    @SerializedName("result_ucl_mcl_right")
    var resultUclMclRight: UCLResult? = null

    var retest: Boolean = false
    var state : Int? = null

    @SerializedName("last_upload_attempt")
    private val lastUploadAttempt: Long? = null

    @SerializedName("patient_pta_left")
    var patientPtaLeft: Double? = null
    @SerializedName("patient_pta_right")
    var patientPtaRight: Double? = null

    companion object{
        fun fromJson(jsonData: String): HearspeechTest{
            return Gson().fromJson(jsonData, HearspeechTest::class.java)
        }

        fun toJson(): String? {
            return Gson().toJson(this)
        }
    }

}

class UCLResult {
    var ucl: Double? = null
    var mcl: Double? = null
    var pta: Double? = null
    var mclMin: Double? = null
    var mclMax: Double? = null
    var duration: Long = 0
    var startingIntensity: Double = 0.0
    var maxOutput : Double ? = 0.0
    var maxReached: Boolean = false
    var iterations: List<UCLIteration>? = emptyList()
    var meanResponseTime: Double? = 0.0
    var stdDevResponseTime: Double? = 0.0
    var state: Int = com.hearxgroup.mhealth.resources.Const.TEST_STATE_UNSET


    data class UCLIteration(
            var ucl_a: Double? = null,
            var ucl_b: Double? = null,
            var ucl: Double? = null,
            var mcl: MutableList<Double>? = mutableListOf(),
            var starting_intensity: Double? = null,
            var max_output_reached: Boolean = false,
            var responses: MutableList<UCLResponse> = mutableListOf()
    )

    data class UCLResponse(
            var intensity: Double,
            var response: Int,
            var durationMillis: Long
    )
}

data class DinSubtest(

        var triplets: List<DigitTriplet>? = emptyList(),
        var state: Int? = com.hearxgroup.mhealth.resources.Const.TEST_STATE_INCOMPLETE,
        var result_srt: List<Double>? = null,
        var result_message: String = "",
        var duration: Int = CODE_UNSET,
        var norm_dataset: String? = "",
        var norm_group: String? = "",
        var norm_srt_p10: Double = Const.CODE_UNSET_DOUBLE,
        var norm_srt_p50: Double = Const.CODE_UNSET_DOUBLE,
        var norm_srt_p90: Double = Const.CODE_UNSET_DOUBLE,
        var mean_response_time: Double? = Const.CODE_UNSET_DOUBLE,
        var std_dev_response_time: Double? = Const.CODE_UNSET_DOUBLE,
        var volume: Int = CODE_UNSET)
{
    data class DigitTriplet(
            val presented: String,
            val responded: String,
            val pass: Boolean,
            val duration: Long,
            val snr: Double,
            val step: Int,
            val protocol_test_index: Int? = 0)

}