package com.einstent.experiment.playgroud.spiderdomain;

import java.util.List;

/**
 * @author liujiaming
 * @since 2017/03/06
 */
public class WanwangResponse {

    private int errorCode;

    private boolean success;

    private List<DomainStatus> module;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DomainStatus> getModule() {
        return module;
    }

    public void setModule(List<DomainStatus> module) {
        this.module = module;
    }

    static class DomainStatus {
        private int avail;

        private String name;

        private String tld;

        public int getAvail() {
            return avail;
        }

        public void setAvail(int avail) {
            this.avail = avail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTld() {
            return tld;
        }

        public void setTld(String tld) {
            this.tld = tld;
        }

        @Override
        public String toString() {
            return "DomainStatus{" + "avail=" + avail + ", name='" + name + '\'' + ", tld='" + tld + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "Response{" + "errorCode=" + errorCode + ", success=" + success + ", module=" + module + '}';
    }
}
