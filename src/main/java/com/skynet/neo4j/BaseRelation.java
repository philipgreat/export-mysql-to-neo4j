/*
******************************           DO NOT EDIT THIS FILE!!!           *********************************
******************************         Please edit CustomRelation.java instead!        *********************************	
******************************         不要编辑这个文件，这个文件每次都会被机器人覆盖!!!              *********************************
******************************    CustomRelation.java专门用于定制，该文件存在的时候不会被覆盖      *********************************


*/
package com.skynet.neo4j;
import java.util.HashMap;
import java.util.Map;

public class BaseRelation{

	
	Map<String, String> relationMapping ;
	
	public String getRelation(String fromType, String fromId, String targetField, String targetId)
	{
		//the entry for external calls, ugly code with many path just works, using a map is fine but lose the way to override the methods
		
		if(relationMapping == null){
			prepareRelation();
		}

		String key = fromType+"->"+targetField;
		
		String relation = relationMapping.get(key);
		if(relation == null){
			throw new IllegalArgumentException("Not able to find any relation to the target type: "+ targetField);
		}
		return relation;
		
	}
	
	protected void addGenericRelation(String fromType, String relation,String targetField)
	{
		if(relationMapping == null){
			relationMapping = new HashMap<String,String>();
		}
		String key = fromType.trim()+"->"+targetField.trim();
		relationMapping.put(key, relation);
	}
	protected void replaceGenericRelation(String fromType, String relation,String targetField)
	{
		addGenericRelation( fromType, relation, targetField );
	}
	
	
	
	Map<String, String[]> relationIndex ;
	protected void addRelationIndex(String fromType,String related[])
	{
		if(relationIndex == null){
			relationIndex = new HashMap<String,String[]>();
		}
		
		relationIndex.put(fromType, related);
	}
	protected void replaceRelationIndex(String fromType,String related[])
	{
		addRelationIndex( fromType, related);
	}
	
	public String getTableFieldName(String expr){
		//the expr looks like owner:DecorationAccelerator
		String[] splitedValues = expr.split(":");
		if(splitedValues.length < 1){
			throw new IllegalArgumentException("Not able to split expr: "+expr);
		}
		
		return splitedValues[0];
	}
	public String getBeanFieldName(String expr){
		//the expr looks like owner:DecorationAccelerator
		String[] splitedValues = getTableFieldName(expr).split("_");
		String ret = splitedValues[0];
		for(int i=1;i<splitedValues.length;i++){
			
			ret = ret+splitedValues[i].substring(0,1).toUpperCase()+splitedValues[i].substring(1);
			
		}
		
		return ret;
	}
	public String getFieldType(String expr){
		//the expr looks like owner:DecorationAccelerator
		String[] splitedValues = expr.split(":");
		if(splitedValues.length < 2){
			throw new IllegalArgumentException("Not able to split expr: "+expr);
		}
		
		return splitedValues[1];
	}
	public String [] getRelationIndex(String type){
		
		if(relationIndex == null){
			prepareRelationIndex();
		}
		
		String relations [] =relationIndex.get(type);
		if(relations == null){
			//throw new IllegalArgumentException("Not able to find related objects for type: "+ type);
		}
		return relations;
	}
	
	protected void prepareRelationIndex()
	{
		
		
		String [] countryCenterDepartmentRelatedObjectNames = {"country_center:DecorationCountryCenter"};
		addRelationIndex("CountryCenterDepartment",countryCenterDepartmentRelatedObjectNames);

		String [] countryCenterDepartmentSupervisorRelatedObjectNames = {"department:CountryCenterDepartment"};
		addRelationIndex("CountryCenterDepartmentSupervisor",countryCenterDepartmentSupervisorRelatedObjectNames);

		String [] countryCenterDepartmentEmployeeRelatedObjectNames = {"department:CountryCenterDepartment","country_center:DecorationCountryCenter"};
		addRelationIndex("CountryCenterDepartmentEmployee",countryCenterDepartmentEmployeeRelatedObjectNames);

		String [] decorationProvinceCenterRelatedObjectNames = {"country:DecorationCountryCenter"};
		addRelationIndex("DecorationProvinceCenter",decorationProvinceCenterRelatedObjectNames);

		String [] provinceCenterDepartmentRelatedObjectNames = {"province_center:DecorationProvinceCenter"};
		addRelationIndex("ProvinceCenterDepartment",provinceCenterDepartmentRelatedObjectNames);

		String [] provinceCenterEmployeeRelatedObjectNames = {"department:ProvinceCenterDepartment","province_center:DecorationProvinceCenter"};
		addRelationIndex("ProvinceCenterEmployee",provinceCenterEmployeeRelatedObjectNames);

		String [] provinceCenterAccountRelatedObjectNames = {"owner:DecorationProvinceCenter"};
		addRelationIndex("ProvinceCenterAccount",provinceCenterAccountRelatedObjectNames);

		String [] provinceCenterAccountChangeRelatedObjectNames = {"belongs_to:ProvinceCenterAccount","owner:DecorationProvinceCenter"};
		addRelationIndex("ProvinceCenterAccountChange",provinceCenterAccountChangeRelatedObjectNames);

		String [] decorationAcceleratorRelatedObjectNames = {"province_center:DecorationProvinceCenter"};
		addRelationIndex("DecorationAccelerator",decorationAcceleratorRelatedObjectNames);

		String [] acceleratorAccountRelatedObjectNames = {"owner:DecorationAccelerator"};
		addRelationIndex("AcceleratorAccount",acceleratorAccountRelatedObjectNames);

		String [] acceleratorAccountChangeRelatedObjectNames = {"belongs_to:AcceleratorAccount","owner:DecorationAccelerator"};
		addRelationIndex("AcceleratorAccountChange",acceleratorAccountChangeRelatedObjectNames);

		String [] decorationMemberRelatedObjectNames = {"accelerator:DecorationAccelerator"};
		addRelationIndex("DecorationMember",decorationMemberRelatedObjectNames);

		String [] decorationSupplierRelatedObjectNames = {"belong_to:DecorationAccelerator"};
		addRelationIndex("DecorationSupplier",decorationSupplierRelatedObjectNames);

		String [] supplierAccountRelatedObjectNames = {"owner:DecorationSupplier"};
		addRelationIndex("SupplierAccount",supplierAccountRelatedObjectNames);

		String [] supplierAccountChangeRelatedObjectNames = {"belongsTo:SupplierAccount","owner:DecorationSupplier"};
		addRelationIndex("SupplierAccountChange",supplierAccountChangeRelatedObjectNames);

		String [] decorationProjectRelatedObjectNames = {"belong_to:DecorationMember"};
		addRelationIndex("DecorationProject",decorationProjectRelatedObjectNames);

		String [] decorationPackageRelatedObjectNames = {"belong_to:DecorationProject"};
		addRelationIndex("DecorationPackage",decorationPackageRelatedObjectNames);

		String [] decorationTaskRelatedObjectNames = {"belong_to:DecorationPackage"};
		addRelationIndex("DecorationTask",decorationTaskRelatedObjectNames);

		String [] violationTermRelatedObjectNames = {"belong_to:DecorationPackage"};
		addRelationIndex("ViolationTerm",violationTermRelatedObjectNames);

		String [] taskViolationRelatedObjectNames = {"belong_to:DecorationPackage","reference_task:DecorationTask","reference_term:ViolationTerm"};
		addRelationIndex("TaskViolation",taskViolationRelatedObjectNames);

		String [] supplierPackageAttendanceRelatedObjectNames = {"supplier:DecorationSupplier","work_for:DecorationProject"};
		addRelationIndex("SupplierPackageAttendance",supplierPackageAttendanceRelatedObjectNames);

		String [] secUserRelatedObjectNames = {"domain:UserDomain"};
		addRelationIndex("SecUser",secUserRelatedObjectNames);

		String [] userAppRelatedObjectNames = {"sec_user:SecUser"};
		addRelationIndex("UserApp",userAppRelatedObjectNames);

	
	
	}
	protected static final String TRUST_CHAIN_READ = "R";
	protected static final String TRUST_CHAIN_WRITE = "W";
	protected static final String TRUST_CHAIN_MANAGEMENT = "M";
	protected static final String TRUST_CHAIN_EXECUTION = "X";
	
	protected static final String TRUST_READ = "r";
	protected static final String TRUST_WRITE = "w";
	protected static final String TRUST_MANAGEMENT = "m";
	protected static final String TRUST_EXECUTION = "x";
	
	protected static final String TRUST_CHAIN_ALL = "MXWR";
	
	
	//small 'r','w','m','x' mean no chain trust, just trust the same level
	//default for reading trust chain, the default sequence are MXWR, the order is not affect the result
	protected void prepareRelation()
	{
		addGenericRelation("CountryCenterDepartment"               ,TRUST_CHAIN_READ,"countryCenter");
		addGenericRelation("CountryCenterDepartmentSupervisor"     ,TRUST_CHAIN_READ,"department");
		addGenericRelation("CountryCenterDepartmentEmployee"       ,TRUST_CHAIN_READ,"department");
		addGenericRelation("CountryCenterDepartmentEmployee"       ,TRUST_CHAIN_READ,"countryCenter");
		addGenericRelation("DecorationProvinceCenter"              ,TRUST_CHAIN_READ,"country");
		addGenericRelation("ProvinceCenterDepartment"              ,TRUST_CHAIN_READ,"provinceCenter");
		addGenericRelation("ProvinceCenterEmployee"                ,TRUST_CHAIN_READ,"department");
		addGenericRelation("ProvinceCenterEmployee"                ,TRUST_CHAIN_READ,"provinceCenter");
		addGenericRelation("ProvinceCenterAccount"                 ,TRUST_CHAIN_READ,"owner");
		addGenericRelation("ProvinceCenterAccountChange"           ,TRUST_CHAIN_READ,"belongsTo");
		addGenericRelation("ProvinceCenterAccountChange"           ,TRUST_CHAIN_READ,"owner");
		addGenericRelation("DecorationAccelerator"                 ,TRUST_CHAIN_READ,"provinceCenter");
		addGenericRelation("AcceleratorAccount"                    ,TRUST_CHAIN_READ,"owner");
		addGenericRelation("AcceleratorAccountChange"              ,TRUST_CHAIN_READ,"belongsTo");
		addGenericRelation("AcceleratorAccountChange"              ,TRUST_CHAIN_READ,"owner");
		addGenericRelation("DecorationMember"                      ,TRUST_CHAIN_READ,"accelerator");
		addGenericRelation("DecorationSupplier"                    ,TRUST_CHAIN_READ,"belongTo");
		addGenericRelation("SupplierAccount"                       ,TRUST_CHAIN_READ,"owner");
		addGenericRelation("SupplierAccountChange"                 ,TRUST_CHAIN_READ,"belongsTo");
		addGenericRelation("SupplierAccountChange"                 ,TRUST_CHAIN_READ,"owner");
		addGenericRelation("DecorationProject"                     ,TRUST_CHAIN_READ,"belongTo");
		addGenericRelation("DecorationPackage"                     ,TRUST_CHAIN_READ,"belongTo");
		addGenericRelation("DecorationTask"                        ,TRUST_CHAIN_READ,"belongTo");
		addGenericRelation("ViolationTerm"                         ,TRUST_CHAIN_READ,"belongTo");
		addGenericRelation("TaskViolation"                         ,TRUST_CHAIN_READ,"belongTo");
		addGenericRelation("TaskViolation"                         ,TRUST_CHAIN_READ,"referenceTask");
		addGenericRelation("TaskViolation"                         ,TRUST_CHAIN_READ,"referenceTerm");
		addGenericRelation("SupplierPackageAttendance"             ,TRUST_CHAIN_READ,"supplier");
		addGenericRelation("SupplierPackageAttendance"             ,TRUST_CHAIN_READ,"workFor");
		addGenericRelation("SecUser"                               ,TRUST_CHAIN_READ,"domain");
		addGenericRelation("UserApp"                               ,TRUST_CHAIN_READ,"secUser");
	
	}

	


}


