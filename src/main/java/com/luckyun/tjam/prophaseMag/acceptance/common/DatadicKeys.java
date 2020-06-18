package com.luckyun.tjam.prophaseMag.acceptance.common;

public class DatadicKeys {

	/** 已打标签卡 */
	public static final String CAP_ACCEPTANCE_EQUIP_ISCARD = "cap_acceptance_equip_iscard";
	
	/** 所属线路 */
	public static final String CAP_ACCEPTANCE_EQUIP_ILINEID = "cap_acceptance_equip_ilineid";
	
	/** 核验状态 */
	public static final String CAP_ACCEPTANCE_EQUIP_ISTRANS = "cap_acceptance_equip_istrans";
	
	/** 是否含有重要组成 */
	public static final String CAP_ACCEPTANCE_EQUIP_IHASSONINVENTORY = "cap_acceptance_equip_ihassoninventory";
	
	/** 分配状态 */
	public static final String CAP_ACCEPTANCE_EQUIP_IDISTSTATUS = "cap_acceptance_equip_idiststatus";
	
	/** 数据来源 */
	public static final String CAP_ACCEPTANCE_EQUIP_ISOURCE = "cap_acceptance_equip_isource";
	
	/** 数据接入 */
	public static final String CAP_ACCEPTANCE_EQUIP_IACCESS = "cap_acceptance_equip_iaccess";
	
	/** 是否生成核验计划 */
	public static final String CAP_ACCEPTANCE_EQUIP_IDATASTATE = "cap_acceptance_equip_idatastate";
	
	/** 计划状态:0.未下发;1.已下发;2.完结 */
	public static final String CAP_VERIFICATION_PLAN_IRELEASETYPE = "cap_verification_plan_ireleasetype";
	
	/** 计划类型 */
	public static final String CAP_VERIFICATION_PLAN_IPLANTYPE = "cap_verification_plan_iplantype";
	
	/** 核验对象 */
	public static final String CAP_VERIFICATION_PLAN_IVERIFICATIONOBJID = "cap_verification_plan_iverificationobjid";
	
	/** 核验计划明细生成方式:0.实物资产模块生成;1.自增 */
	public static final String CAP_VERIFICATION_PLAN_DETAIL_EQUIP_IGENERATETYPE = "cap_verification_plan_detail_equip_igeneratetype";

	/**处理状态：数据字典，一共两个值(0.新建/1.待分摊/2.已完成，0为默认值)；*/
	public static final String CAP_VERIFICATION_PLAN_IDEALSTATUS="cap_verification_plan_idealstatus";

	/**来源*/
	public static final String CAP_VERIFICATION_PLAN_ISOURCE="cap_verification_plan_isource";

	/**分摊方式 (0.平均分摊；1.按比例分摊)*/
	public static final String CAP_VERIFICATION_PLAN_IAPPMTTYPE="cap_verification_plan_iappmttype";
	/**添加方式 （）*/
	public static final String CAP_VERIFICATION_PLAN_IADDTYPE = "cap_verification_plan_iaddtype";

	/**“类别”:数据字典，值包括:0.设备设施；1.办公用品；2.工器具；*/
	public static final String AM_BASIC_INF_ICATEGORYID="am_basic_inf_icategoryid";

	/**“卡片类型”:数据字典，值包括:0.原始资产；1.拆分资产，2，组合资产*/
	public static final String AM_BASIC_INF_ICARDTYPE="am_basic_inf_icardtype";

	/**“运营类别”:数据字典，值包括:0.自持(默认值)；1.代持；*/
	public static final String AM_BASIC_INF_ICATEGORYID_IOPERGORYID="am_basic_inf_icategoryid_iopergoryid";

	/**“资产状态”:数据字典，值包括:0.正常(默认值)；1.损坏；2.报废；*/
	public static final String AM_BASIC_INF_ICATEGORYID_IAMSTATE="am_basic_inf_icategoryid_iamstate";

	/**资产类别”:数据字典，值包括:0.直接运营资产(默认值)；1.间接运营资产*/
	public static final String AM_BASIC_INF_ICATEGORYID_IAMCLASSID="am_basic_inf_icategoryid_iamclassid";

	/**“重要等级”:数据字典，值包括:0.A；1.B；2.C；3.D；*/
	public static final String AM_BASIC_INF_ICATEGORYID_IIMPORTLEVELID="am_basic_inf_icategoryid_iimportlevelid";

	/**"使用状态"：数据字典，值包括：值包括:-0.正常使用；1.非正常使用；	*/
	public static final String AM_BASIC_INF_ICATEGORYID_IUSESTATE="am_basic_inf_icategoryid_iusestate";

	/**寿命计量单位:数据字典，0，年；1，月*/
	public static final String AM_BASIC_INF_ICATEGORYID_SDESIGNUNIT="am_basic_inf_icategoryid_sdesignunit";

	/**融资状态:数据字典，0，已融资、1,未融资*/
	public static final String AM_BASIC_INF_ICATEGORYID_IFINANCSTATE="am_basic_inf_icategoryid_ifinancstate";

	/**折旧方法:数据字典，0，不提折旧，1，平均年限法*/
    public static final String AM_BASIC_INF_ICATEGORYID_SDEPRECMETHOD="am_basic_inf_icategoryid_sdeprecmethod";

    /**是否总价合同,0：否，1：是*/
    public static final String AM_BASIC_INF_ICATEGORYID_ICONTSUMFLAG="am_basic_inf_icategoryid_icontsumflag";

    /**下发状态(0.未下发,1.已下发)*/
    public static final String AM_BASIC_INF_ICATEGORYID_ITRANSFERSTATE="am_basic_inf_icategoryid_itransferstate";

    /**下发至(0,轨道运营)*/
    public static final String AM_BASIC_INF_ICATEGORYID_ITRANSFERTO="am_basic_inf_icategoryid_itransferto";

	/**审批状态(0,为下发)*/
	public static final String AM_BASIC_INF_ICATEGORYID_IAPPROVALSTATE="am_basic_inf_icategoryid_iapprovalstate";

	/**"调出类型（1.内部 2,捐赠(外部) 3,无偿(外部) 4,投资(外部) 5,租赁(外部)）"*/
	public static final String AM_BASIC_INF_ICATEGORYID_IILEAVETYPEID="am_basic_inf_icategoryid_ileavetypeid";
	
	/**"处理状态（0.待分摊,1.分摊中,2.已完成）"*/
	public static final String AM_WAIT_APPMT_IDEALSTATUS = "am_wait_appmt_idealstatus";

	/**
	 * 审批状态
	 */
	public static final String BPM_IAPPROVALSTATE = "bpm_iapprovalstate";

}
