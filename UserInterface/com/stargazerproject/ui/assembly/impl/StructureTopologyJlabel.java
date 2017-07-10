package com.stargazerproject.ui.assembly.impl;

import com.stargazerproject.parameter.impl.SystemParameter;

/**
 *操控头像 
 *
 *@Web https://github.com/pisual http://www.pisual.com
 *@email pisual@163.com dsnsuva@163.com dsnsuva@gmail.com
 *@author Felixerio FelixSion
 **/
public class StructureTopologyJlabel extends GradientLoadInterface {
	private static final long serialVersionUID = 5010683231088848230L;
	/**操控头像的位置几大小**/
	private static int structureTopologyJlabelLocation[];
	/**单例模式**/
	private static StructureTopologyJlabel structureTopologyJlabel;
	
	public static StructureTopologyJlabel getInstance(String backgroundLogo){
		if(structureTopologyJlabel == null){
			structureTopologyJlabel = new StructureTopologyJlabel(backgroundLogo);
			structureTopologyJlabel.InitMainFrameLogoJlabel();
		}
		return structureTopologyJlabel;
	}
	
	private StructureTopologyJlabel(String backgroundLogoPath) {
		super(backgroundLogoPath);
		structureTopologyJlabelLocation = SystemParameterCahce.getInstance().getParamentByKeyInSystemMemoryTransformNormallArray("Main_Frame_StructureTopology_Location");
	}
	
	private void InitMainFrameLogoJlabel(){
		structureTopologyJlabel.setBounds(structureTopologyJlabelLocation[0], structureTopologyJlabelLocation[1], structureTopologyJlabelLocation[2], structureTopologyJlabelLocation[3]);
	}
}
