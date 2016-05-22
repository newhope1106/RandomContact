package cn.appleye.randomcontact.common.factory;

public interface IFactory{
	/**
	 * 第一组属性，只返回一个结果
	 * */
	public abstract String createFirstRandomData();

	/**
	 * 第一组属性，返回一组结果
	 * @param repeatAllowed 是否允许重复
	 * */
	public abstract String[] createFirstRandomData(boolean repeatAllowed);
	
	/**
	 * 第二组属性，目前只有组织有第二组属性
	 * */
	public abstract String createSecondRandomData();
}
