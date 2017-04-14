
package com.skynet.neo4j;

public class CustomRelation extends BaseRelation{

	protected void prepareRelation()
	{
		super.prepareRelation();
		//Uncomment to make any change to the relation type
		replaceGenericRelation("CountryCenterDepartment"               , BaseRelation.TRUST_CHAIN_ALL, "countryCenter");
		replaceGenericRelation("CountryCenterDepartmentSupervisor"     , BaseRelation.TRUST_CHAIN_ALL, "department");
		//replaceGenericRelation("CountryCenterDepartmentEmployee"       , BaseRelation.TRUST_CHAIN_ALL, "department");
		//replaceGenericRelation("CountryCenterDepartmentEmployee"       , BaseRelation.TRUST_CHAIN_ALL, "countryCenter");
		//replaceGenericRelation("DecorationProvinceCenter"              , BaseRelation.TRUST_CHAIN_ALL, "country");
		//replaceGenericRelation("ProvinceCenterDepartment"              , BaseRelation.TRUST_CHAIN_ALL, "provinceCenter");
		//replaceGenericRelation("ProvincØeCenterEmployee"                , BaseRelation.TRUST_CHAIN_ALL, "department");
		//replaceGenericRelation("ProvinceCenterEmployee"                , BaseRelation.TRUST_CHAIN_ALL, "provinceCenter");
		//replaceGenericRelation("ProvinceCenterAccount"                 , BaseRelation.TRUST_CHAIN_ALL, "owner");
		//replaceGenericRelation("ProvinceCenterAccountChange"           , BaseRelation.TRUST_CHAIN_ALL, "belongsTo");
		//replaceGenericRelation("ProvinceCenterAccountChange"           , BaseRelation.TRUST_CHAIN_ALL, "owner");
		//replaceGenericRelation("DecorationAccelerator"                 , BaseRelation.TRUST_CHAIN_ALL, "provinceCenter");
		//replaceGenericRelation("AcceleratorAccount"                    , BaseRelation.TRUST_CHAIN_ALL, "owner");
		//replaceGenericRelation("AcceleratorAccountChange"              , BaseRelation.TRUST_CHAIN_ALL, "belongsTo");
		//replaceGenericRelation("AcceleratorAccountChange"              , BaseRelation.TRUST_CHAIN_ALL, "owner");
		//replaceGenericRelation("DecorationMember"                      , BaseRelation.TRUST_CHAIN_ALL, "accelerator");
		//replaceGenericRelation("DecorationSupplier"                    , BaseRelation.TRUST_CHAIN_ALL, "belongTo");
		//replaceGenericRelation("SupplierAccount"                       , BaseRelation.TRUST_CHAIN_ALL, "owner");
		//replaceGenericRelation("SupplierAccountChange"                 , BaseRelation.TRUST_CHAIN_ALL, "belongsTo");
		//replaceGenericRelation("SupplierAccountChange"                 , BaseRelation.TRUST_CHAIN_ALL, "owner");
		//replaceGenericRelation("DecorationProject"                     , BaseRelation.TRUST_CHAIN_ALL, "belongTo");
		//replaceGenericRelation("DecorationPackage"                     , BaseRelation.TRUST_CHAIN_ALL, "belongTo");
		//replaceGenericRelation("DecorationTask"                        , BaseRelation.TRUST_CHAIN_ALL, "belongTo");
		//replaceGenericRelation("ViolationTerm"                         , BaseRelation.TRUST_CHAIN_ALL, "belongTo");
		//replaceGenericRelation("TaskViolation"                         , BaseRelation.TRUST_CHAIN_ALL, "belongTo");
		//replaceGenericRelation("TaskViolation"                         , BaseRelation.TRUST_CHAIN_ALL, "referenceTask");
		//replaceGenericRelation("TaskViolation"                         , BaseRelation.TRUST_CHAIN_ALL, "referenceTerm");
		//replaceGenericRelation("SupplierPackageAttendance"             , BaseRelation.TRUST_CHAIN_ALL, "supplier");
		//replaceGenericRelation("SupplierPackageAttendance"             , BaseRelation.TRUST_CHAIN_ALL, "workFor");
		//replaceGenericRelation("SecUser"                               , BaseRelation.TRUST_CHAIN_ALL, "domain");
		//replaceGenericRelation("UserApp"                               , BaseRelation.TRUST_CHAIN_ALL, "secUser");

	}
	
	protected void prepareRelationIndex()
	{
		super.prepareRelationIndex();
		/*
		
		Note: you could delete some of the possible relations if you do not want it.
		Just uncomment the definition line and replaceRelationIndex line to replace existing one.
		
		*/
		//String [] countryCenterDepartmentRelatedObjectNames = {"country_center:DecorationCountryCenter"};
		//replaceRelationIndex("CountryCenterDepartment",countryCenterDepartmentRelatedObjectNames);

		//String [] countryCenterDepartmentSupervisorRelatedObjectNames = {"department:CountryCenterDepartment"};
		//replaceRelationIndex("CountryCenterDepartmentSupervisor",countryCenterDepartmentSupervisorRelatedObjectNames);

		//String [] countryCenterDepartmentEmployeeRelatedObjectNames = {"department:CountryCenterDepartment","country_center:DecorationCountryCenter"};
		//replaceRelationIndex("CountryCenterDepartmentEmployee",countryCenterDepartmentEmployeeRelatedObjectNames);

		//String [] decorationProvinceCenterRelatedObjectNames = {"country:DecorationCountryCenter"};
		//replaceRelationIndex("DecorationProvinceCenter",decorationProvinceCenterRelatedObjectNames);

		//String [] provinceCenterDepartmentRelatedObjectNames = {"province_center:DecorationProvinceCenter"};
		//replaceRelationIndex("ProvinceCenterDepartment",provinceCenterDepartmentRelatedObjectNames);

		//String [] provinceCenterEmployeeRelatedObjectNames = {"department:ProvinceCenterDepartment","province_center:DecorationProvinceCenter"};
		//replaceRelationIndex("ProvinceCenterEmployee",provinceCenterEmployeeRelatedObjectNames);

		//String [] provinceCenterAccountRelatedObjectNames = {"owner:DecorationProvinceCenter"};
		//replaceRelationIndex("ProvinceCenterAccount",provinceCenterAccountRelatedObjectNames);

		//String [] provinceCenterAccountChangeRelatedObjectNames = {"belongs_to:ProvinceCenterAccount","owner:DecorationProvinceCenter"};
		//replaceRelationIndex("ProvinceCenterAccountChange",provinceCenterAccountChangeRelatedObjectNames);

		//String [] decorationAcceleratorRelatedObjectNames = {"province_center:DecorationProvinceCenter"};
		//replaceRelationIndex("DecorationAccelerator",decorationAcceleratorRelatedObjectNames);

		//String [] acceleratorAccountRelatedObjectNames = {"owner:DecorationAccelerator"};
		//replaceRelationIndex("AcceleratorAccount",acceleratorAccountRelatedObjectNames);

		//String [] acceleratorAccountChangeRelatedObjectNames = {"belongs_to:AcceleratorAccount","owner:DecorationAccelerator"};
		//replaceRelationIndex("AcceleratorAccountChange",acceleratorAccountChangeRelatedObjectNames);

		//String [] decorationMemberRelatedObjectNames = {"accelerator:DecorationAccelerator"};
		//replaceRelationIndex("DecorationMember",decorationMemberRelatedObjectNames);

		//String [] decorationSupplierRelatedObjectNames = {"belong_to:DecorationAccelerator"};
		//replaceRelationIndex("DecorationSupplier",decorationSupplierRelatedObjectNames);

		//String [] supplierAccountRelatedObjectNames = {"owner:DecorationSupplier"};
		//replaceRelationIndex("SupplierAccount",supplierAccountRelatedObjectNames);

		//String [] supplierAccountChangeRelatedObjectNames = {"belongsTo:SupplierAccount","owner:DecorationSupplier"};
		//replaceRelationIndex("SupplierAccountChange",supplierAccountChangeRelatedObjectNames);

		//String [] decorationProjectRelatedObjectNames = {"belong_to:DecorationMember"};
		//replaceRelationIndex("DecorationProject",decorationProjectRelatedObjectNames);

		//String [] decorationPackageRelatedObjectNames = {"belong_to:DecorationProject"};
		//replaceRelationIndex("DecorationPackage",decorationPackageRelatedObjectNames);

		//String [] decorationTaskRelatedObjectNames = {"belong_to:DecorationPackage"};
		//replaceRelationIndex("DecorationTask",decorationTaskRelatedObjectNames);

		//String [] violationTermRelatedObjectNames = {"belong_to:DecorationPackage"};
		//replaceRelationIndex("ViolationTerm",violationTermRelatedObjectNames);

		//String [] taskViolationRelatedObjectNames = {"belong_to:DecorationPackage","reference_task:DecorationTask","reference_term:ViolationTerm"};
		//replaceRelationIndex("TaskViolation",taskViolationRelatedObjectNames);

		//String [] supplierPackageAttendanceRelatedObjectNames = {"supplier:DecorationSupplier","work_for:DecorationProject"};
		//replaceRelationIndex("SupplierPackageAttendance",supplierPackageAttendanceRelatedObjectNames);

		//String [] secUserRelatedObjectNames = {"domain:UserDomain"};
		//replaceRelationIndex("SecUser",secUserRelatedObjectNames);

		//String [] userAppRelatedObjectNames = {"sec_user:SecUser"};
		//replaceRelationIndex("UserApp",userAppRelatedObjectNames);

		
		
	
	}
	
	
	@Override
	public String getRelation(String fromType, String fromId, String targetField, String targetId)
	{

		String relation = super.getRelation(fromType, fromId, targetField, targetId);
		if(relation == null){
			throw new IllegalArgumentException("Not able to find any relation to the target type: "+ targetField);
		}
		return relation;
		
	}

}











