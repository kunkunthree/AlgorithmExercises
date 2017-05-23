package algorithm.leetcode.algorithm;
import java.util.*;
public class NestedIntegerImpl implements NestedInteger{
	public Integer mInteger;
	public boolean isInteger;
	public List<NestedInteger> list;
	public NestedIntegerImpl(){
		isInteger = false;
	}
	public NestedIntegerImpl(Integer ni) {
		mInteger = ni;
		isInteger = true;
	}
	public NestedIntegerImpl(List<NestedInteger> list) {
		this.list = list;
		isInteger = false;
	}
	@Override
	public Integer getInteger() {
		if(isInteger)
			return mInteger;
		else{
			return null;
		}
	}
	@Override
	public boolean isInteger() {
		return isInteger;
	}
	@Override
	public List<NestedInteger> getList() {
		if(!isInteger)
			return list;
		else{
			return null;
		}
	}
	@Override
	public void setInteger(int value) {
		this.mInteger = value;
	}
	@Override
	public void add(NestedInteger ni) {
		this.getList().add(ni);
	}
}
