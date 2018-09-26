package com.base.network.basenetwork.api.entry.response;

/**
 * Created by d on 2018/7/11.
 */

public class AppUpdateResultBean {


    /**
     * msg : 操作成功
     * data : {"id":1,"clientType":"ANDROID","version":"1.0.0beta","verCode":2,"apkSize":65000,"apkPath":"http://localhost:8080/rhshop/public/apk/10010.apk","publishState":1,"createTime":1528964811000,"updateTime":1528964818000,"state":0}
     * status : 0
     */

    private String msg;
    private DataBean data;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * id : 1
         * clientType : ANDROID
         * version : 1.0.0beta
         * verCode : 2
         * apkSize : 65000
         * apkPath : http://localhost:8080/rhshop/public/apk/10010.apk
         * publishState : 1
         * createTime : 1528964811000
         * updateTime : 1528964818000
         * state : 0
         */

        private String clientType;// clientType:客户端类型
        private String version;// version:版本名称
        private int verCode; // verCode:升级码
        private int apkSize; // apkSize:apk大小，单位kb
        private String apkPath;// apkPath:apk路径
        private int publishState;// publishState:发布状态:0-未发布 1-已发布
        private long createTime;
        private long updateTime;
        private int state;


        public String getClientType() {
            return clientType;
        }

        public void setClientType(String clientType) {
            this.clientType = clientType;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public int getVerCode() {
            return verCode;
        }

        public void setVerCode(int verCode) {
            this.verCode = verCode;
        }

        public int getApkSize() {
            return apkSize;
        }

        public void setApkSize(int apkSize) {
            this.apkSize = apkSize;
        }

        public String getApkPath() {
            return apkPath;
        }

        public void setApkPath(String apkPath) {
            this.apkPath = apkPath;
        }

        public int getPublishState() {
            return publishState;
        }

        public void setPublishState(int publishState) {
            this.publishState = publishState;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(long updateTime) {
            this.updateTime = updateTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }


        @Override
        public String toString() {
            return "DataBean{" +
                    "clientType='" + clientType + '\'' +
                    ", version='" + version + '\'' +
                    ", verCode=" + verCode +
                    ", apkSize=" + apkSize +
                    ", apkPath='" + apkPath + '\'' +
                    ", publishState=" + publishState +
                    ", createTime=" + createTime +
                    ", updateTime=" + updateTime +
                    ", state=" + state +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "AppUpdateResultBean{" +
                "msg='" + msg + '\'' +
                ", data=" + data.toString() +
                ", status=" + status +
                '}';
    }
}
