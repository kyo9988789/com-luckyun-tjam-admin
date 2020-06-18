
package com.luckyun.tjam.prophaseMag.verification.param;

import java.util.List;

import com.luckyun.annotation.Describe;
import com.luckyun.tjam.prophaseMag.verification.model.AmVrfPlanDtFiasset;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AmVrfPlanDtFiassetParam extends AmVrfPlanDtFiasset {

	@Describe("待删除集合")
	private List<AmVrfPlanDtFiasset> delList;
	
}

