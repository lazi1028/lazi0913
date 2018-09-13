package com.bcht.axletempmonitor.utils;

/**
 * Created by lazi 2018/08/17
 * description:
 */
public enum ResultStatus {
    SUCCESS(200, "成功"),
    FAILED(-200, "失败"),

    USERNAME_AND_PASSWORD_NOT_NULL(-1000,"用户名、密码、验证码必输"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    VERTIFYCODE_WRONG_OR_OUTOFTIME(-1002,"验证码不正确或已过期"),

    USER_NOT_LOGIN(-1100,"用户未登录"),
    USER_HAS_NO_PERMISSION(-1101,"没有该权限"),
    USER_NOT_EXIST(-1102,"用户不存在"),
   /* PARAM_INVALID(-1000, "参数错误"),

    USERNAME_OR_PASSWORD_ERROR(-1002, "用户名或密码错误"),

    USER_NOT_AUTH(-2000, "功能未授权"),

    MSG_SMS_OUTOFTIME(-4001, "验证码过时，重新获取"),
    MSG_SMS_WRONG(-4005, "验证码不正确"),*/
    PASSWORD_NOT_NULL(-4003,"请先输入用户密码"),
   // PASSWORD_CHECKED_ERROR(-4006,"用户密码不正确"),

    USERADD_PARAM_NOT_NULL(-1010,"用户真实姓名、用户名、角色、密码都不能为空"),
    USERADD_USERNAME_CHECKED(-1011,"用户名为2-30位数字字母组合"),
    USERADD_REALNAME_CHECKED(-1012,"真实姓名最多10个汉字"),
    USERADD_PASSWORD_CHECKED(-1013,"密码为6-20位数字字母组合"),
    USERADD_MOBILEPHNE_CHECKED(-1014,"手机号必须为11位数字"),
    USERADD_DEPARTMENT_CHECKED(-1015,"部门最多20个汉字"),
    USER_PASSWORD_DOUBLE_CHECKED(-1016,"两次输入的密码不一致"),
    USER_ORIGIN_PASSWORD_CHECKED(-1017,"当前用户密码不正确"),
    USER_PASSWORD_NOT_NULL(-1018,"当前用户密码不能为空"),
    USER_SEX_CHECKED(-1019,"性别只能为F或M,F代表女，M代表男"),

    ROLEADD_ROLENAME_NOT_NULL(-1020,"角色名称不能为空"),
    ROLEADD_ROLEPERMS_NOT_NULL(-1021,"必须为角色分配权限"),
    ROLEADD_ROLENAME_LENCHECKED(-1022,"角色名称最多20个汉字"),
    ROLEADD_ROLEDESC_LENCHECKED(-1023,"角色描述最多200个汉字"),

    UPDATE_ZERO_ITEM(-1024,"未修改一项"),

    TRAINNUM_NOT_INPUT(-3000,"请选择列车编号"),
    TRAINNUM_NOT_EXIST(-3001,"列车编号不存在"),
    TRAINVO_NOT_NULL(-3002,"列车编号、车厢号、起始时间都不能为空,(传感器主机也不能为空)"),
    TRAINVO_TIME_GAP(-3003,"起始时间和终止时间跨度不能超过1小时且终止时间大于等于起始时间"),
    TRAIN_EXCEP_NOT_NULL(-3004,"列车编号、起始时间都不能为空"),
    TRAIN_EXCEP_ITEM_NULL(-3005,"自定义列表项时列车编号必选"),
    BRKDWN_COND_NOT_NULL(-3006,"列车编号、故障类型、起始时间都不能为空"),
    BRKDWN_VIEW_NOT_NULL(-3007,"列车编号、车厢号、传感器名称、故障时间都不能为空"),
    TRAIN_BDMF_NOT_NULL(-3008,"标动车车型传感器主机不能为空"),

    SELECTED_NOT_NULL(-3100,"请选择要删除的记录"),
    FILE_UPLOAD_NOT_NULL(-3101,"请选择要上传的文件"),

    REPORT_TIME_NOT_NULL(-3200,"起始时间不能为空"),
    REPORT_PARAM_NOT_NULL(-3201,"列车编号、车厢号、传感器主机、传感器参数至少选一个"),

    TRAINADD_PARAM_NOT_NULL(-3300,"添加列车时，列车编号、配属、车型都不能为空"),
    TRAINADD_TRNNUM_CHECKED(-3301,"列车编号为4位数字"),

    BRKDWNCODE_PARAM_NOT_NULL(-3400,"故障代码、故障等级、故障名称、车型都不能为空"),
    BRKDWNCODE_CODE_CHECKED(-3401,"故障代码为6位字母数字组合"),
    BRKDWNCODE_NAME_CHECEKED(-3402,"故障名称最多200个汉字"),
    BRKDWNCODE_CAUSE_CHECKED(-3403,"故障原因最多200个汉字"),

    STARTTIME_ENDTIME_COMPARE(-3500,"开始时间必须小于等于结束时间"),

    FILE_UPLOAD_SUCCESS(6200,"文件上传成功"),
    FILE_UPLOAD_FAILED(-6200,"文件上传失败"),
    FILE_UPLOAD_ISNULL(-6002,"没有符合规则的文件被选择，请选择需要上传的文件"),
    FILE_TYPE_ERROR(-6000, "文件名格式错误，请修改后重新上传"),
    FILE_TRNTYPE_ERROR(-6001, "文件名格式与车型不匹配，请重新确认列车编号");



    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}