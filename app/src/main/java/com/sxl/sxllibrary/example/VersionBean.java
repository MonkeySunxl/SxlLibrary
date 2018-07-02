package com.sxl.sxllibrary.example;

/**
 * @ProjectName pkusky
 * @ClassDescribe
 * @Author
 * @Date 2017/7/28 11:00
 * @Copyright 未名天
 */
public class VersionBean {

        /**
         * version : 3.1.1
         * download :
         * desc :
         */
        private String version;
        private String download;
        private String desc;

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDownload() {
            return download;
        }

        public void setDownload(String download) {
            this.download = download;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    @Override
    public String toString() {
        return "VersionBean{" +
                "desc='" + desc + '\'' +
                ", version='" + version + '\'' +
                ", download='" + download + '\'' +
                '}';
    }
}
