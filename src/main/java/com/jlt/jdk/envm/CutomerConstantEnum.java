package com.jlt.jdk.envm;

public interface CutomerConstantEnum {
    String getDesc();

    enum AdressTypeEnum implements CutomerConstantEnum {
        OFFIC("办公地址"), HOME("家庭地址");
        private String desc;

        AdressTypeEnum(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    enum TelTypeEnum implements CutomerConstantEnum {
        TEL1("联系电话1"), TEL2("联系电话2"), TEL3("联系电话3"), TEL4("联系电话4");
        private String desc;

        TelTypeEnum(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    enum OtherContactTypeEnum implements CutomerConstantEnum {
        MAIL("邮箱"), WEIXIN("微信"), QQ("QQ");
        private String desc;

        OtherContactTypeEnum(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    enum SourceEnum implements CutomerConstantEnum {
        LOAN("新一贷"), PAC("产险"), LIFE("寿险");
        private String desc;

        SourceEnum(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    enum GenderEnum implements CutomerConstantEnum {
        MALE("男士"), FEMALE("女士");
        private String desc;

        GenderEnum(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}