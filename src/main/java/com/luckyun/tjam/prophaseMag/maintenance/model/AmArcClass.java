
package com.luckyun.tjam.prophaseMag.maintenance.model;

import com.luckyun.annotation.Describe;
import com.luckyun.model.data.DocEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


/*
 * 
 * 设备分类
 * @action
 * @since JDK 1.8
 * @author zrw
 * @date: 2019年12月3日 20:26:21
 */


@Data
@EqualsAndHashCode(callSuper = false)
public class AmArcClass extends DocEntity{


    @Describe("主键")
    private Long indocno;

    @Describe("分类编码")
    private String sclassno;

    @Describe("分类名称")
    private String sclassnm;

    @Describe("分类别名")
    private String sclassnmas;

    @Describe("上级位置id")
    private Long iparentid;

    @Describe("上级位置编码")
    private String sparentno;

    @Describe("父节点名称")
    private String sparentnm;

    @Describe("父子节点关系")
    private String scc;

    @Describe("排序")
    private Integer isort;

    @Describe("级别")
    private Long ilevel;

    @Describe("京投编码")
    private String sjtno;

    @Describe("分类状态")
    private Integer iclassstate;

    @Describe("备注")
    private String snote;

    @Describe("附件路径")
    private String spath;

    @Describe("描述")
    private String sdescribe;

    @Describe("删除（0 未删 1 虚拟删除）")
    private Integer idel;

    @Describe("状态（0无效1有效）")
    private Integer istate;

    @Describe("创建人ID")
    private Long sregid;

    @Describe("创建人姓名")
    private String sregnm;

    @Describe("创建时间")
    private Date dregt;

    @Describe("创建人ID")
    private Long smodid;

    @Describe("创建人")
    private String smodnm;

    @Describe("创建时间")
    private Date dmodt;

    @Describe("所属部门ID")
    private Integer ideptid;

    @Describe("所属部门")
    private String sdeptnm;

    @Describe("所属岗位ID")
    private Integer ipostid;

    @Describe("所属岗位")
    private String spostnm;

    @Describe("所属公司ID")
    private Integer icompanyid;

    @Describe("所属公司")
    private String scompanynm;

    @Describe("业务号")
    private Integer idoctypeid;

    @Describe("步骤号")
    private Integer istepid;

    @Describe("步骤类型")
    private Integer istepcharttype;

    @Describe("步骤名称")
    private String sstepnm;

    @Describe("步骤状态")
    private String sstepstate;

    @Describe("办理人IDS")
    private String sstepoperid;

    @Describe("办理人")
    private String sstepopernm;

    @Describe("备注")
    private String snotes;

    @Describe("对应合同系统的分类编码")
    private String swzno;

    @Describe("对应合同系统的分类主键")
    private Integer iwzno;

    @Describe("调用对方接口的返回状态值，表示这条记录是否发出去，或者发出去遇到什么情况的一个状态显示，这样就算数据发送失败，也能知道这条数据失败的原因")
    private Integer interfacestatus;

    @Describe("调用对方接口的返回情况说明，表示传递出去后的遇到的异常或者状况文字描述")
    private String interfacedescribe;

    @Describe("对应合同系统的上级分类主键")
    private String iwzparent;

    @Describe("父子节点关系")
    private String sidcc;

    @Describe("是否有子节点")
    private Integer hasChildren;


/*	@Describe("主键")
	private Long indocno;
	
	@Describe("分类编码")
	private String sclassno;
	
	@Describe("分类名称")
	private String sclassnm;
	
	@Describe("分类别名")
	private String sclassnmas;
	
	@Describe("上级编码")
	private String iparent;
	
	@Describe("上级分类码")
	private String supperno;
	
	@Describe("父节点名称")
	private String sparentnm;
	
	@Describe("路径")
	private String scc;
	
	@Describe("排序")
	private String ssort;
	
	@Describe("级别")
	private String ilevel;
	
	@Describe("京投编码")
	private String sjtno;
	
	@Describe("分类状态")
	private String iclassstate;
	
	@Describe("备注")
	private String snote;
	
	@Describe("图片")
	private String sphoto;
	
	@Describe("描述")
	private String sdescribe;
	
	@Describe("创建人")
	private String sregnm;
	
	@Describe("创建人")
	private String smodnm;
	
	@Describe("所属部门ID")
	private String ideptid;
	
	@Describe("所属部门")
	private String sdeptnm;
	
	@Describe("所属岗位ID")
	private String ipostid;
	
	@Describe("所属岗位")
	private String spostnm;
	
	@Describe("所属公司ID")
	private String icompanyid;
	
	@Describe("所属公司")
	private String scompanynm;
	
	@Describe("业务号")
	private String idoctypeid;
	
	@Describe("步骤号")
	private String istepid;
	
	@Describe("步骤类型")
	private String istepcharttype;
	
	@Describe("步骤名称")
	private String sstepnm;
	
	@Describe("步骤状态")
	private String sstepstate;
	
	@Describe("办理人IDS")
	private String sstepoperid;
	
	@Describe("办理人")
	private String sstepopernm;
	
	@Describe("备注")
	private String snotes;
	
	@Describe("idept")
	private String idept;
	
	@Describe("sdept")
	private String sdept;
	
	@Describe("icorpid")
	private String icorpid;
	
	@Describe("scorpnm")
	private String scorpnm;
	
	@Describe("suuid")
	private String suuid;
	
	@Describe("iline")
	private String iline;;
	
	@Describe("对应合同系统的分类编码")
	private String swzno;
	
	@Describe("对应合同系统的分类主键")
	private String iwzno;
	
	@Describe("调用对方接口的返回状态值，表示这条记录是否发出去，或者发出去遇到什么情况的一个状态显示，这样就算数据发送失败，也能知道这条数据失败的原因")
	private String interfacestatus;
	
	@Describe("调用对方接口的返回情况说明，表示传递出去后的遇到的异常或者状况文字描述")
	private String interfacedescribe;
	
	@Describe("对应合同系统的上级分类主键")
	private String iwzparent;

	@Describe("是否有子节点")
	private Integer hasChildren;
	
	@Describe("父子节点关系")
	private String sidcc;*/

	
	@Override
	public String __getTableName() {
		return "am_arc_class";
	}
	
	@Override
	public boolean __isTrueDel() {
		return false;
	}

}

