package com.redteamobile.generator.config;

/**
 * @ClassName BizConfig
 * @Description 业务配置常量类
 * @Author zijian zhao
 * @Date 2019/12/06 11:09
 */
public class BizConfig {

    /**
     * 托管于Apollo配置中心的 namespace名称 统一使用该namespace 托管业务配置
     */
    public static final String APOLLO_CONFIG_BIZ = "general";

    public static final String APOLLO_CONFIG_DEFAULT = "es_general";

    public static final String APOLLO_MULTI_SIM = "multi-sim";

    public static final String APOLLO_TREM_BLANT = "tremblant";


    /**
     * UE鉴权Token失效时间 单位：天
     */
    public static final String KEY_TOKEN_EXPIRE_DAYS = "TOKEN_EXPIRE_DAYS";

    public static final Integer DEFAULT_TOKEN_EXPIRE_DAYS = 15;


    /**
     * 用户业务数据过期时间 单位：天
     */
    public static final String KEY_ACCOUNT_DATA_EXPIRE_DAYS = "ACCOUNT_DATA_EXPIRE_DAYS";

    public static final Integer DEFAULT_ACCOUNT_DATA_EXPIRE_DAYS = 10;

    /**
     * 一号多终端业务签约URL
     */
    public static final String KEY_MUTI_SIM_SIGNUP_URL = "MUTI_SIM_SIGN_UP_URL";

    public static final String DEFAULT_MUTI_SIM_SIGNUP_URL = "http://localhost:8093/rest/ws/ir/v1/signUp";

    /**
     * 一号多终端接口BASE URL
     */
    public static final String KEY_MUTI_SIM_BASE_URL = "MUTI_SIM_BASE_URL";

    public static final String DEFAULT_MUTI_SIM_BASE_URL = "http://localhost:8093/rest/ws/ir/v1";

    /**
     * 独立号业务签约URL
     */
    public static final String KEY_TREMBLANT_SIGNUP_URL = "TREMBLANT_SIGN_UP_URL";

    public static final String DEFAULT_TREMBLANT_SIGNUP_URL = "https://sampledomain.com/TREMBLANT/signup";

    /**
     * 一号多终端业务管理页面URL
     */
    public static final String KEY_MUTI_SIM_MANAGEMENT_URL = "MUTI_SIM_MANAGEMENT_URL";

    public static final String DEFAULT_MUTI_SIM_MANAGEMENT_URL = "http://localhost:8093/rest/ws/ir/v1/managementPage";

    /**
     * 独立号业务管理页面URL
     */
    public static final String KEY_TREMBLANT_MANAGEMENT_URL = "TREMBLANT_MANAGEMENT_URL";

    public static final String DEFAULT_TREMBLANT_MANAGEMENT_URL = "https://sampledomain.com/tremblant/management";

    /**
     * 电商系统业务数据缓存失效时间 单位：小时
     */
    public static final String KEY_ECS_CACHE_EXPIRE_HOURS = "ECS_CACHE_EXPIRE_HOURS";

    public static final Integer DEFAULT_ECS_CACHE_EXPIRE_HOURS = 24;

    /**
     * 电商系统业务数据缓存机制是否启用  默认： true 启用
     */
    public static final String KEY_ECS_CACHE_ENABLED = "ECS_CACHE_ENABLED";

    public static final Boolean DEFAULT_ECS_CACHE_ENABLED = true;

    /**
     * APNS device-token的过期时间 单位：小时
     */
    public static final String KEY_DEVICE_TOKEN_CACHE_EXPIRE_HOURS = "DEVICE_TOKEN_CACHE_EXPIRE_HOURS";

    public static final Integer DEFAULT_DEVICE_TOKEN_CACHE_EXPIRE_HOURS = 24;

    /**
     * 一号多端业务可挂载附卡最大数量
     */
    public static final String KEY_MAX_ATTACHED_ICCIDS = "MAX_ATTACHED_ICCIDS";

    public static final String DEFAULT_MAX_ATTACHED_ICCIDS = "5";

    public static final String DEFAULT_TREMBLANT_MAX_ATTACHED_ICCIDS = "5";

    /**
     * Postdata数据的有效时间 单位：秒
     */
    public static final String KEY_WEBSHEET_IDENTITY_LIFE_TIME_IN_SECONDS = "WEBSHEET_IDENTITY_LIFE_TIME_IN_SECONDS";

    public static final Integer DEFAULT_WEBSHEET_IDENTITY_LIFE_TIME_IN_SECONDS = 300;


    /**
     * ESIM-SERVER URL
     */
    public static final String KEY_ESIM_SERVER_URL = "ESIM_SERVER_URL";

    public static final String DEFAULT_ESIM_SERVER_URL = "";


    /**
     * ALT_SMDP_FQDN
     */
    public static final String KEY_DOMAIN_ALT_SMDP_FQDN = "DOMAIN_ALT_SMDP_FQDN";

    public static final String DEFAULT_DOMAIN_ALT_SMDP_FQDN = "";

    /**
     * 电商系统调用接口时传入的APP KEY
     */
    public static final String KEY_ECS_APP_KEY = "ECS_APP_KEY";

    public static final String DEFAULT_ECS_APP_KEY = "com.aop.app.hpe";

    /**
     * getCarrierSpaceUpdateAction 后台刷新间隔 单位：小时
     */
    public static final String KEY_BACKGROUND_REFRESH_INTERVAL_HOURS = "BACKGROUND_REFRESH_INTERVAL_HOURS";

    public static final String DEFAULT_BACKGROUND_REFRESH_INTERVAL_HOURS = "24";

    /**
     * 电商系统接口调用时传入的methodName 查询套餐列表
     */
    public static final String KEY_ECS_METHOD_QUERY_CARRIER_PLAN = "ECS_METHOD_QUERY_CARRIER_PLAN";

    public static final String DEFAULT_ECS_METHOD_QUERY_CARRIER_PLAN = "com.aop.method.carrierplans";


    /**
     * 电商系统接口调用时传入的methodName 查询套餐使用量
     */
    public static final String KEY_ECS_METHOD_QUERY_NETWORK_USAGE = "KEY_ECS_METHOD_QUERY_NETWORK_USAGE";

    public static final String DEFAULT_ECS_METHOD_QUERY_NETWORK_USAGE = "com.aop.method.networkusage";


    /**
     * 电商系统接口调用时传入的methodName 订购套餐
     */
    public static final String KEY_ECS_METHOD_ORDER_PACKAGE = "ECS_METHOD_ORDER_PACKAGE";

    public static final String DEFAULT_ECS_METHOD_ORDER_PACKAGE = "com.aop.method.roamanddatapackagechg";

    /**
     * getEntitlement 支持的业务
     */
    public static final String KEY_SUPPORTED_ENTITLEMENTS = "SUPPORTED_ENTITLEMENTS";

    /**
     * 苹果文档指定的类型为：
     * ”tethering”
     * “VoLTE”, “MCA”, “VVM”
     * 目前只支持tethering， VOLTE 后面有需要再说， 有多个值得话 用逗号分隔，如 tethering,VoLTE
     */
    public static final String DEFAULT_SUPPORTED_ENTITLEMENTS = "Multi-SIM,sa-watch,sa-watch-transfer";

    /**
     * 业务服务内部证书失效的预警时间
     */
    public static final String KEY_CERT_EXPIRE_ALARM_DAYS = "KEY_CERT_EXPIRE_ALARM_DAYS";

    public static final String DEFAULT_KEY_CERT_EXPIRE_ALARM_DAYS  = "10";

    /**
     * enablenotification 请求传入的 notification name 的取值
     */
    public static final String KEY_ALLOWED_NOTIFICATION_NAMES = "ALLOWED_NOTIFICATION_NAMES";

    /**
     * 有多个值得话 用逗号分隔，如 Multi-SIM,XXX
     */
    public static final String DEFAULT_ALLOWED_NOTIFICATION_NAMES = "Multi-SIM,sa-watch";

    /**
     * 调用BSS 接口时最大重试次数
     */
    public static final String KEY_INTERFACE_INVOKE_MAX_RETRY_TIMES = "INTERFACE_INVOKE_MAX_RETRY_TIMES";

    public static final Integer DEFAULT_INTERFACE_INVOKE_MAX_RETRY_TIMES = 3;

    /**
     * 调用BSS 接口时 超时时间
     */
    public static final String KEY_INTERFACE_INVOKE_TIMEOUT_IN_SECONDS = "INTERFACE_INVOKE_TIMEOUT_IN_SECONDS";

    public static final Integer DEFAULT_INTERFACE_INVOKE_TIMEOUT_IN_SECONDS = 120;

    /**
     * 使用AES加密Webseet postdata时 所使用的secretkey
     */
    public static final String KEY_POST_DATA_AES_SECRET_KEY = "POST_DATA_AES_SECRET_KEY";

    //public static final String DEFAULT_POST_DATA_AES_SECRET_KEY = "ChinaUnicom_1";
    public static final String DEFAULT_POST_DATA_AES_SECRET_KEY = "d36146970b7e4b37ad9f61bf2a66c1c8";

    /**
     * 使用AES加密Webseet postdata时 所使用的加密向量
     */
    public static final String KEY_POST_DATA_AES_INIT_VECTOR = "POST_DATA_AES_INIT_VECTOR";

    /**
     * AES/CBC/128/PKCS5Padding 加密所用初始向量 16字节
     */
    public static final String DEFAULT_POST_DATA_AES_INIT_VECTOR = "0000000000000000";

    /**
     * Websheet所使用的鉴权identity的有效时间
     */
    public static final String KEY_IDENTITY_PERIOD_IN_SECONDS = "IDENTITY_PERIOD_IN_SECONDS";

    public static final String DEFAULT_IDENTITY_PERIOD_IN_SECONDS = "1800";

    /**
     * BSS 接口类型  取值 ChinaUnicom： 联通接口   RedTea：红茶自定义接口
     */
    public static final String KEY_BSS_INTERFACE_TYPE = "BSS_INTERFACE_TYPE";

    public static final String DEFAULT_BSS_INTERFACE_TYPE = "ChinaUnicom";

    /**
     * BSS 对接URL
     */
    public static final String KEY_BSS_SOAP_URL = "BSS_SOAP_URL";

    public static final String DEFAULT_BSS_SOAP_URL = "http://localhost:8083/v1/soap/query";

    /**
     * 运营商 电商系统 对接URL
     */
    public static final String KEY_ECS_ACCESS_URL = "ECS_ACCESS_URL";

    /**
     * 电商系统如果限流重试次数
     */
    public static final String KEY_ECS_RETRY_TIMES = "ECS_RETRY_TIMES";

    /**
     * 电商系统如果限流重试次数
     */
    public static final int DEFAULT_ECS_RETRY_TIMES = 1;

    // ==========  ESIM CLIENT , esim-server(sm dp+) config ==============//

    /**
     * ESIM-CLIENT 调用SM-DP+(暂时东信和平) 时鉴权用的用户名
     */
    public static final String KEY_SM_DP_HEADER_AUTH_USERNAME = "KEY_SM_DP_HEADER_AUTH_USERNAME";

    public static final String DEFAULT_KEY_SM_DP_HEADER_AUTH_USERNAME  = "userName";

    /**
     * ESIM-CLIENT 调用SM-DP+(暂时东信和平) 时鉴权用的密码
     */
    public static final String KEY_SM_DP_HEADER_AUTH_PASSWORD = "KEY_SM_DP_HEADER_AUTH_PASSWORD";

    public static final String DEFAULT_KEY_SM_DP_HEADER_AUTH_PASSWORD  = "pwd";


    /**
     * ES2+ 接口 header中传入的x-admin-type
     */
    public static final String KEY_SM_DP_HEADER_X_ADMIN_TYPE = "KEY_SM_DP_HEADER_X_ADMIN_TYPE";

    public static final String DEFAULT_KEY_SM_DP_HEADER_X_ADMIN_TYPE  = "gsma/rsp/v2.0.0";

    /**
     * ES2+ download order接口 请求参数中传入的profile type
     */
    public static final String KEY_ES2_PLUS_DOWNLOAD_ORDER_PROFILE_TYPE = "KEY_ES2_PLUS_DOWNLOAD_ORDER_PROFILE_TYPE";

    public static final String DEFAULT_KEY_ES2_PLUS_DOWNLOAD_ORDER_PROFILE_TYPE  = "myProfileType";

    /**
     * ES2+ download order接口的url
     */
    public static final String KEY_ES2_PLUS_DOWNLOAD_ORDER_ACCESS_URL = "KEY_ES2_PLUS_DOWNLOAD_ORDER_ACCESS_URL";

    public static final String DEFAULT_KEY_ES2_PLUS_DOWNLOAD_ORDER_ACCESS_URL  = "http://localhost:8083/v1/es2plus/downloadOrder";

    /**
     * ES2+ confirm order接口的url
     */
    public static final String KEY_ES2_PLUS_CONFIRM_ORDER_ACCESS_URL = "KEY_ES2_PLUS_CONFIRM_ORDER_ACCESS_URL";

    public static final String DEFAULT_KEY_ES2_PLUS_CONFIRM_ORDER_ACCESS_URL  = "http://localhost:8083/v1/es2plus/confirmOrder";

    /**
     * ES2+ cancel order接口的url
     */
    public static final String KEY_ES2_PLUS_CANCEL_ORDER_ACCESS_URL = "KEY_ES2_PLUS_CANCEL_ORDER_ACCESS_URL";

    public static final String DEFAULT_KEY_ES2_PLUS_CANCEL_ORDER_ACCESS_URL  = "http://localhost:8083/v1/es2plus/cancelOrder";


    /**
     * ES2+ release profile接口的url
     */
    public static final String KEY_ES2_PLUS_RELEASE_PROFILE_ACCESS_URL = "KEY_ES2_PLUS_RELEASE_PROFILE_ACCESS_URL";

    public static final String DEFAULT_KEY_ES2_PLUS_RELEASE_PROFILE_ACCESS_URL  = "http://localhost:8083/v1/es2plus//releaseProfile";


    /**
     * SMDP+ 的默认提供商  用于ESIMClient调用时根据该配置加载证书
     */
    public static final String KEY_ES2_PLUS_PROVIDER_NAME = "ES2_PLUS_PROVIDER_NAME";

    public static final String DEFAULT_ES2_PLUS_PROVIDER_NAME  = "eastcompeace";

    // ==========  Tremblant transger config ==============//

    /**
     * tremblant 一键迁移 所使用的方式，是跳转websheet处理  还是直接由es与esimclient直接交互
     */
    public static final String KEY_TREMBLANT_ONE_CLICK_TRANSFER_METHOD = "KEY_TREMBLANT_ONE_CLICK_TRANSFER_METHOD";

    public static final String DEFAULT_KEY_TREMBLANT_ONE_CLICK_TRANSFER_METHOD  = "ES";

    /**
     * 独立号业务  一键转移的Websheet页面URL
     */
    public static final String KEY_TREMBLANT_ONE_CLICK_TRANSFER_WS_URL = "KEY_TREMBLANT_ONE_CLICK_TRANSFER_WS_URL";

    public static final String DEFAULT_KEY_TREMBLANT_ONE_CLICK_TRANSFER_WS_URL  = "https://sampledomain.com/mutisim/signup";

    /**
     * 独立号一键转移的transfer type 详见苹果API文档
     */
    public static final String KEY_TREMBLANT_ONE_CLICK_TRANSFER_TYPE = "KEY_TREMBLANT_ONE_CLICK_TRANSFER_TYPE";

    public static final String DEFAULT_KEY_TREMBLANT_ONE_CLICK_TRANSFER_TYPE  = "1";


    /**
     * 独立号一键转移的transfer token过期时间 单位：天
     */
    public static final String KEY_TREMBLANT_TRANSFER_TOKEN_EXPIRE_DAYS = "KEY_TREMBLANT_TRANSFER_TOKEN_EXPIRE_DAYS";

    public static final String DEFAULT_KEY_TREMBLANT_TRANSFER_TOKEN_EXPIRE_DAYS  = "10";

    /**
     * 独立号 auth trust flag 生成的随机 Nonce 过期时间 单位：小时
     */
    public static final String KEY_TREMBLANT_AUTH_TRUST_FLAG_HOURS = "KEY_TREMBLANT_AUTH_TRUST_FLAG_HOURS";

    public static final String DEFAULT_KEY_TREMBLANT_AUTH_TRUST_FLAG_HOURS  = "24";

    /**
     * 独立号 TrustFlag 机制 生成的token过期时间 单位：天
     */
    public static final String KEY_TREMBLANT_TRUST_FLAG_TOKEN_EXPIRE_DAYS = "KEY_TREMBLANT_TRUST_FLAG_TOKEN_EXPIRE_DAYS";

    public static final String DEFAULT_KEY_TREMBLANT_TRUST_FLAG_TOKEN_EXPIRE_DAYS  = "10";


    /**
     * 默认的国家代码
     */
    public static final String KEY_COUNTRY_CODE= "KEY_COUNTRY_CODE";

    public static final String DEFAULT_KEY_COUNTRY_CODE = "86";

    /**
     * 默认的电话号码校验规则 正则
     */
    public static final String KEY_MSISDN_VALIDATE_PATTERN= "MSISDN_VALIDATE_PATTERN";

    public static final String DEFAULT_MSISDN_VALIDATE_PATTERN = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";

    /**
     * 默认的nickname校验规则 正则
     */
    public static final String KEY_NICKNAME_VALIDATE_PATTERN= "NICKNAME_VALIDATE_PATTERN";

    public static final String DEFAULT_NICKNAME_VALIDATE_PATTERN = "[0-9a-zA-Z]+";

    /**
     * SOAP Adapter 类加载全路径
     */
    public static final String KEY_SOAP_ADAPTER_CALSS_PATH= "SOAP_ADAPTER_CALSS_PATH";


    /**
     * ESIM CLIENT Adapter 类加载全路径
     */
    public static final String KEY_ESIMCLIENT_ADAPTER_CALSS_PATH= "ESIMCLIENT_ADAPTER_CALSS_PATH";


    /**
     * 已经绑定的附设备是否允许后台自动解绑
     */
    public static final String KEY_MULTI_SIM_ATTACHED_DEVICE_UNBOUND_ALLOWD= "MULTI_SIM_ATTACHED_DEVICE_UNBOUND_ALLOWD";

    public static final boolean DEFAULT_MULTI_SIM_ATTACHED_DEVICE_UNBOUND_ALLOWD = true;

    /**
     * 一号多端解绑后 重新绑定时需要等待的时间 单位：秒
     */
    public static final String KEY_MULTI_SIM_REBOUNDING_AFTER_UNBOUND= "MULTI_SIM_REBOUNDING_AFTER_UNBOUND";

    public static final String DEFAULT_MULTI_SIM_REBOUNDING_AFTER_UNBOUND = "10";

    /**
     * 一号多端confirm order 请求后需要等待profile就绪的延迟时间 单位：秒
     */
    public static final String KEY_MULTI_SIM_DELAY_AFTER_CONFIRM_ORDER= "MULTI_SIM_DELAY_AFTER_CONFIRM_ORDER";

    public static final String DEFAULT_MULTI_SIM_DELAY_AFTER_CONFIRM_ORDER = "10";

    public static final String KEY_GEO_SYNC_KEY = "GEO_SYNC_KEY";

    public static final String DEFAULT_GEO_SYNC_VALUE = "apns_device_token,account_auth,account_data,redis_sync";

    /**
     * MSISDN 电话号码 国家代码前缀
     */
    public static final String KEY_MSISDN_COUNTRY_CODE_PREFIX= "MSISDN_COUNTRY_CODE_PREFIX";

    public static final String DEFAULT_MSISDN_COUNTRY_CODE_PREFIX = "86";

    /**
     * getPhoneNumber 证书名称
     */
    public static final String KEY_GETPHONENUMBER_CERT_NAME= "GETPHONENUMBER_CERT_NAME";

    /**
     * getPhoneNumber 证书密码
     */
    public static final String KEY_GETPHONENUMBER_CERT_PWD= "GETPHONENUMBER_CERT_PWD";

    public static final String DEFAULT_GETPHONENUMBER_CERT_PWD = "Changeme_123";

    /**
     * 运行模式
     */
    public static final String KEY_BUSINESS_OPERATION_MODE = "BUSINESS_OPERATION_MODE";
    public static final String DEFAULT_BUSINESS_OPERATION_MODE = "PROD";
    public static final String BUSINESS_OPERATION_MODE_CUT = "CUT";

    /**
     * eSIMCard开户是否通过定时任务确认
     */
    public static final String KEY_ESIM_MGR_SCHEDULE_OPEN= "ESIM_MGR_SCHEDULE_OPEN";

    public static final Integer DEFAULT_ESIM_MGR_SCHEDULE_OPEN = 0;

    /**
     * eSIMCard定时任务间隔时间
     */
    public static final String KEY_ESIM_MGR_SCHEDULE_PERIOD= "ESIM_MGR_SCHEDULE_PERIOD";

    public static final Integer DEFAULT_ESIM_MGR_SCHEDULE_PERIOD = 8;

    /**
     * eSIMCard定时任务触发次数
     */
    public static final String KEY_ESIM_MGR_SCHEDULE_TIMES= "ESIM_MGR_SCHEDULE_TIMES";

    public static final Integer DEFAULT_ESIM_MGR_SCHEDULE_TIMES = 3;

    /**
     * 开关控制项开启或关闭
     */
    public static final String CONTROLL_ITEM_ENABLED = "enabled";

    public static final String CONTROLL_ITEM_DISABLED = "disabled";

    /**
     * 在ES signupforsimservice请求中是否开启最大附设备数量校验
     */
    public static final String KEY_SIGNUPSERVICE_MAX_ICCID_VALIDATION_ENABLED = "SIGNUPSERVICE_MAX_ICCID_VALIDATION_ENABLED";

    public static final String DEFAULT_SIGNUPSERVICE_MAX_ICCID_VALIDATION_ENABLED = "enabled";

    /**
     * 强制去BSS查询的时限
     */
    public static final String KEY_SIGNUP_WHEN_QUERY_BOSS_WAITING_TIME = "SIGNUP_WHEN_QUERY_BOSS_WAITING_TIME";

    public static final int DEFAULT_SIGNUP_WHEN_QUERY_BOSS_WAITING_TIME = 5 * 24 * 60 * 60;
}
