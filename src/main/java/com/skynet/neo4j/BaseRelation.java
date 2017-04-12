package com.skynet.neo4j;

import java.util.HashMap;
import java.util.Map;


public class BaseRelation{
	Map<String, String> relationMapping ;
	
	
	
	protected void addGenericRelation(String fromType,String targetField, String relation)
	{
		if(relationMapping == null){
			relationMapping = new HashMap<String,String>();
		}
		String key = fromType+"->"+targetField;
		relationMapping.put(key, relation);
	}
	
	Map<String, String[]> relationIndex ;
	protected void addRelationIndex(String fromType,String related[])
	{
		if(relationIndex == null){
			relationIndex = new HashMap<String,String[]>();
		}
		
		relationIndex.put(fromType, related);
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
	protected void prepareRelation()
	{
		addGenericRelation("CountryCenterDepartment","countryCenter","partof");
		addGenericRelation("CountryCenterDepartmentSupervisor","department","partof");
		addGenericRelation("CountryCenterDepartmentEmployee","department","partof");
		addGenericRelation("CountryCenterDepartmentEmployee","countryCenter","partof");
		addGenericRelation("DecorationProvinceCenter","country","partof");
		addGenericRelation("ProvinceCenterDepartment","provinceCenter","partof");
		addGenericRelation("ProvinceCenterEmployee","department","partof");
		addGenericRelation("ProvinceCenterEmployee","provinceCenter","partof");
		addGenericRelation("ProvinceCenterAccount","owner","partof");
		addGenericRelation("ProvinceCenterAccountChange","belongsTo","partof");
		addGenericRelation("ProvinceCenterAccountChange","owner","partof");
		addGenericRelation("DecorationAccelerator","provinceCenter","partof");
		addGenericRelation("AcceleratorAccount","owner","partof");
		addGenericRelation("AcceleratorAccountChange","belongsTo","partof");
		addGenericRelation("AcceleratorAccountChange","owner","partof");
		addGenericRelation("DecorationMember","accelerator","partof");
		addGenericRelation("DecorationSupplier","belongTo","partof");
		addGenericRelation("SupplierAccount","owner","partof");
		addGenericRelation("SupplierAccountChange","belongsTo","partof");
		addGenericRelation("SupplierAccountChange","owner","partof");
		addGenericRelation("DecorationProject","belongTo","partof");
		addGenericRelation("DecorationPackage","belongTo","partof");
		addGenericRelation("DecorationTask","belongTo","partof");
		addGenericRelation("ViolationTerm","belongTo","partof");
		addGenericRelation("TaskViolation","belongTo","partof");
		addGenericRelation("TaskViolation","referenceTask","partof");
		addGenericRelation("TaskViolation","referenceTerm","partof");
		addGenericRelation("SupplierPackageAttendance","supplier","partof");
		addGenericRelation("SupplierPackageAttendance","workFor","partof");
		addGenericRelation("SecUser","domain","partof");
		addGenericRelation("UserApp","secUser","partof");
	
	}
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
	
	


}

