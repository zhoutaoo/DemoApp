package com.jlt.jdk.envm;

public interface CutomerConstantEnum {
	public String getDesc();

	public enum AdressTypeEnum implements CutomerConstantEnum {
		OFFIC("办公地址"), HOME("家庭地址");
		private String desc;

		private AdressTypeEnum(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	};

	public enum TelTypeEnum implements CutomerConstantEnum {
		TEL1("联系电话1"), TEL2("联系电话2"), TEL3("联系电话3"), TEL4("联系电话4");
		private String desc;

		private TelTypeEnum(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	};

	public enum OtherContactTypeEnum implements CutomerConstantEnum {
		MAIL("邮箱"), WEIXIN("微信"), QQ("QQ");
		private String desc;

		private OtherContactTypeEnum(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	};

	public enum SourceEnum implements CutomerConstantEnum {
		LOAN("新一贷"), PAC("产险"), LIFE("寿险");
		private String desc;

		private SourceEnum(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	};

	public enum GenderEnum implements CutomerConstantEnum {
		MALE("男士"), FEMALE("女士");
		private String desc;

		private GenderEnum(String desc) {
			this.desc = desc;
		}

		@Override
		public String getDesc() {
			return desc;
		}
	};
}