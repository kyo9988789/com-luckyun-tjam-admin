
package com.luckyun.tjam.prophaseMag.verification.model;

import java.util.Date;
import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.model.data.DocEntity;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceFiasset;
import com.luckyun.tjam.prophaseMag.maintenance.model.AmAcceptanceSpare;

import lombok.Data;
import lombok.EqualsAndHashCode;


/*
 *  实物资产核验计划表(包含设备设施 + 备品备件,iplantype区分类型)
 *
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月10日 14:39:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AmVrfPlan extends DocEntity{

    @Describe("主键")
    private Long indocno;

    @Describe("核验计划名称")
    private String splannm;

    @Describe("核验计划编号")
    private String splanno;

    @Describe("核验对象ID")
    private Integer ivrfobjid;

    @Describe("所属线路ID")
    private Integer ilineid;

    @Describe("核验年度")
    private Integer ivrfyear;

    @Describe("核验开始日期")
    private Date dvrfstart;

    @Describe("核验结束日期")
    private Date dvrfend;

    @Describe("核验部门ID")
    private Long ivrfdeptid;

    @Describe("计划状态(0.未下发;1.已下发;2.完结)")
    private Integer iplanstate;

    @Describe("下发日期")
    private Date drelease;

    @Describe("完成日期")
    private Date dfinish;

    @Describe("资产数量")
    private Integer iqty;

    @Describe("备注")
    private String snote;

    @Describe("创建部门ID")
    private Long icreatedeptid;

    @Describe("附件名称")
    private String sname;

    @Describe("附件路径")
    private String spath;

    @Describe("附件大小")
    private Integer isize;

    @Describe("附件描述")
    private String sattdescribe;

    @Describe("删除（0 未删 1 虚拟删除）")
    private Integer idel;

    @Describe("创建人ID")
    private Long sregid;

    @Describe("创建时间")
    private Date dregt;

    @Describe("状态（0无效1有效）")
    private Integer istate;

    @Describe("创建人名称")
    private String sregnm;

    @Describe("修改人id")
    private Long smodid;

    @Describe("修改人名称")
    private String smodnm;

    @Describe("修改时间")
    private Date dmodt;

    @Describe("计划类型(0.设备设施计划;1.备品备件计划)")
    private Integer iplantype;

    @Describe("核验计划生成方式:0.实物资产模块生成;1.自增")
    private Integer igeneratetype;

    @Describe("设备设施资产核验计划：明细页展示")
    List<AmVrfPlanDtFiasset> equipList;

    @Describe("设备设施资产核验计划组成部分：明细页展示")
    List<AmVrfPlanDtFiasset> sonEquipList;

    @Describe("备品备件资产核验计划")
    List<AmVrfPlanDtSpare> spareList;

    @Describe("备品备件资产核验计划组成部分")
    List<AmVrfPlanDtSpare> sonSpareList;

    @Describe("修改设备设施资产核验计划时，前台传来的待删除集合：核验计划明细数据")
    private List<AmVrfPlanDtFiasset> delEquipLists;

    @Describe("修改、新增设备设施资产核验计划时，前台传来的待新增集合：设备设施资产维护数据")
    private List<AmAcceptanceFiasset> addEquipLists;

    @Describe("修改备品备件资产核验计划时，前台传来的待删除集合：核验计划明细数据")
    private List<AmVrfPlanDtSpare> delSpareLists;

    @Describe("修改、新增备品备件资产核验计划时，前台传来的待新增集合：备品备件资产维护数据")
    private List<AmAcceptanceSpare> addSpareLists;

    @Describe("计划状态(0.未下发;1.已下发;2.完结)")
    private String sreleasetype;

    @Describe("核验对象ID:0.内部,1.外部")
    private String sverificationobj;

    @Describe("所属线路名称")
    private String slinenm;

    @Describe("核验部门名称")
    private String sdeptnm;

    @Describe("创建部门名称")
    private String screatedeptnm;

	@Override
	public boolean __isTrueDel() {
		return false;
	}

	@Override
	public String __getTableName() {
		return "am_vrf_plan";
	}

}

