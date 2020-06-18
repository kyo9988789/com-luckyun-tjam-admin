
package com.luckyun.tjam.prophaseMag.verification.param;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlan;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AmVrfPlanParam extends AmVrfPlan {

	@Describe("高级检索")
	private String sname;

	public void setDreleasestart(String  sFrom) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sFrom != null && sFrom.length() == 13) {
			try {
				dreleasestart = sdf.parse(sdf.format(Long.parseLong(sFrom)));
			} catch (Exception e) {
				dreleasestart = new Date();
			}
		}
	}

	public void setDreleaseend(String sTo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sTo != null && sTo.length() == 13) {
			try {
				dreleaseend = sdf.parse(sdf.format(Long.parseLong(sTo)));
			} catch (Exception e) {
				dreleaseend = new Date();
			}
		}
	}

	public void setDverificationstart(String sFrom) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sFrom != null && sFrom.length() == 13) {
			try {
				dverificationstart = sdf.parse(sdf.format(Long.parseLong(sFrom)));
			} catch (Exception e) {
				dverificationstart = new Date();
			}
		}
	}

	public void setDverificationend(String sTo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(sTo != null && sTo.length() == 13) {
			try {
				dverificationend = sdf.parse(sdf.format(Long.parseLong(sTo)));
			} catch (Exception e) {
				dverificationend = new Date();
			}
		}
	}
	
	@Describe("111")
	private Date dreleasestart;

	@Describe("下发结束日期")
	private Date dreleaseend;

	@Describe("核验开始日期")
	private Date dverificationstart;
	
	@Describe("核验结束日期")
	private Date dverificationend;

	@Describe("完结操作的数据集")
	private List<AmVrfPlan> finishList;
	
	@Describe("删除操作的数据集")
	private List<AmVrfPlan> delList;
	
}

