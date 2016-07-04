package com.jlt.jdk.envm;

/**
 * @author 周涛
 * 
 */
public class TestEnum {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (CutomerConstantEnum.AdressTypeEnum adressType : CutomerConstantEnum.AdressTypeEnum.values()) {
			System.out.println(adressType.ordinal() + ":" + adressType.name() + ":" + adressType.getDesc());
		}
		for (CutomerConstantEnum.TelTypeEnum telType : CutomerConstantEnum.TelTypeEnum.values()) {
			System.out.println(telType.ordinal() + ":" + telType.name() + ":" + telType.getDesc());
		}
		for (CutomerConstantEnum.OtherContactTypeEnum otherContactType : CutomerConstantEnum.OtherContactTypeEnum
				.values()) {
			System.out.println(otherContactType.ordinal() + ":" + otherContactType.name() + ":"
					+ otherContactType.getDesc());
		}
		for (CutomerConstantEnum.SourceEnum source : CutomerConstantEnum.SourceEnum.values()) {
			System.out.println(source.ordinal() + ":" + source.name() + ":" + source.getDesc());
		}
		for (CutomerConstantEnum.GenderEnum gender : CutomerConstantEnum.GenderEnum.values()) {
			System.out.println(gender.ordinal() + ":" + gender.name() + ":" + gender.getDesc());
		}
	}
}