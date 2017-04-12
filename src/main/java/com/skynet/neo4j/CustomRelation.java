package com.skynet.neo4j;


public class CustomRelation extends BaseRelation{

	protected void prepareRelation()
	{
		super.prepareRelation();
		//Uncomment to make any change to the relation type
		//addRelation("country_center_department","country_center","partof");
		//addRelation("country_center_department_supervisor","department","partof");
		//addRelation("country_center_department_employee","department","partof");
		//addRelation("country_center_department_employee","country_center","partof");
		//addRelation("decoration_province_center","country","partof");
		//addRelation("province_center_department","province_center","partof");
		//addRelation("province_center_employee","department","partof");
		//addRelation("province_center_employee","province_center","partof");
		//addRelation("province_center_account","owner","partof");
		//addRelation("province_center_account_change","belongs_to","partof");
		//addRelation("province_center_account_change","owner","partof");
		//addRelation("decoration_accelerator","province_center","partof");
		//addRelation("accelerator_account","owner","partof");
		//addRelation("accelerator_account_change","belongs_to","partof");
		//addRelation("accelerator_account_change","owner","partof");
		//addRelation("decoration_member","accelerator","partof");
		//addRelation("decoration_supplier","belong_to","partof");
		//addRelation("supplier_account","owner","partof");
		//addRelation("supplier_account_change","belongsTo","partof");
		//addRelation("supplier_account_change","owner","partof");
		//addRelation("decoration_project","belong_to","partof");
		//addRelation("decoration_package","belong_to","partof");
		//addRelation("decoration_task","belong_to","partof");
		//addRelation("violation_term","belong_to","partof");
		//addRelation("task_violation","belong_to","partof");
		//addRelation("task_violation","reference_task","partof");
		//addRelation("task_violation","reference_term","partof");
		//addRelation("supplier_package_attendance","supplier","partof");
		//addRelation("supplier_package_attendance","work_for","partof");
		//addRelation("sec_user","domain","partof");
		//addRelation("user_app","sec_user","partof");
	
	}
	@Override
	public String getRelation(String fromType, String fromId, String targetField, String targetId)
	{
		
		
		String relation = super.getRelation(fromType,  fromId,  targetField,  targetId);
		if(relation == null){
			throw new IllegalArgumentException("Not able to find any relation to the target type: "+ targetField);
		}
		return relation;
		
	}

}