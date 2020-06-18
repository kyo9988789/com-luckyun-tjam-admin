
package com.luckyun.tjam.prophaseMag.verification.param;

import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtSpare;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmVrfPlanDtSpareParam extends AmVrfPlanDtSpare {

	@Describe("待删除集合")
	private List<AmVrfPlanDtSpare> delList;
	
}

