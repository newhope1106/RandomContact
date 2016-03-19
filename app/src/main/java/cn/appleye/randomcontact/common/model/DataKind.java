package cn.appleye.randomcontact.common.model;

import java.util.ArrayList;

import cn.appleye.randomcontact.common.factory.IFactory;

public class DataKind {
	public String mimetype;
	public int typeOverallMax = 3;
	public String columnName;
	
	public String typeColumn;
	
	public String secondTypeColumn;
	
	public ArrayList<DataType> typeList;
	
	public IFactory factoryHandler;
}
