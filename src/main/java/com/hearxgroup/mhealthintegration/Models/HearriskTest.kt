package com.hearxgroup.mhealthintegration.Models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.hearxgroup.orbit.logic.Const

class HearriskTest {


    @SerializedName("server_id")
    var serverId : Long = Const.CODE_UNSET_LONG
    @SerializedName("patient_id")
    var patientId: Long= Const.CODE_UNSET_LONG
    @SerializedName("facility_id")
    var facilityId: Long= Const.CODE_UNSET_LONG

    var uuid : String = "" //device generated id. Not yet implemented on server
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
    var mhealthVersion: Int= Const.CODE_UNSET //Internal use
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

    @SerializedName("test_date")
    var testDate: Long = Const.CODE_UNSET_LONG
    @SerializedName("protocol_obj")
    var protocolObj: String?=null
    @SerializedName("patient_signature")
    var patientSignature: String?=null
    var duration: Int= Const.CODE_UNSET
    var notes: String?=null
    var software_version: String=""
            
    var questionnaires : List<Questionnaire>? = null
    var algorithms : List<Algorithm>? = null
    @SerializedName("result_message")
    var resultMessage: RESULT? = null
    @SerializedName("result_message_text")
    var resultMessageText: String = ""

    var retest: Boolean = false
    var state : Int? = null


    companion object{
        fun fromJson(jsonData: String): HearriskTest{
            return Gson().fromJson(jsonData, HearriskTest::class.java)
        }

        fun toJson(): String? {
            return Gson().toJson(this)
        }
    }
}
enum class RESPONSE_TYPE {SINGLE, MULTIPLE}
enum class ALGORITHM_SOURCE {CHL_V1, AHL_V1}
enum class QUESTION_SOURCE {CEDRA_V1, FDA_V1}
enum class RESULT {AT_RISK, NO_RISK, SKIPPED}

data class Questionnaire (

        var version : Int = 1,
        var questions : List<QuestionNode>?,
        var source : QUESTION_SOURCE,
        var score_total : Int,
        var result : RESULT? = null
    ) {

    data class QuestionOption (
            var version : Int = 1,
            var text : String,
            var selected: Boolean,
            var points : Int,
            var selection_nodes: List<QuestionNode>? = null
            )
}

data class QuestionNode(
        var version : Int = 1,
        var node_id: Long,
        var response_type: RESPONSE_TYPE,
        var question_text: String?,
        var options: List<Questionnaire.QuestionOption>
)

data class Algorithm(
        var version : Int = 1,
        var calc_inputs : CalcInputs,
        var source : ALGORITHM_SOURCE,
        var result : RESULT? = null
)

data class CalcInputs (
        var version : Int = 1,
        var pta_left : Double? = null,
        var pta_right : Double? = null,
        var age : Int? = null,
        var srt_diotic : Double? = null,
        var srt_antiphasic : Double? = null,
        var th_500_left: Int? = null,
        var th_500_right: Int? = null
)

