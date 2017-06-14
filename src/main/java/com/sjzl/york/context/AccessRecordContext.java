package com.sjzl.york.context;

/**
 * 访问请求信息记录器
 * 1.记录拦截器preHandle的时间，作为处理的开始时间
 * 用于在postHandle内计算请求的访问时间消耗
 * 2.记录请求的参数信息，用于在postHandle中一次数据本次请求的所有相关信息
 * @author zhangliuyang
 * @email zhangliuyang@nggirl.com.cn
 * @date 2017/6/14 22:14
 */
public class AccessRecordContext {

    private static ThreadLocal<AccessPreRecord> preRecordInfo = new ThreadLocal<AccessPreRecord>();

    public static AccessPreRecord getPreRecordInfo(){
        return preRecordInfo.get();
    }

    public static void setPreRecordInfo(Long startTime, StringBuilder info){
        AccessPreRecord recordInfo = new AccessPreRecord();
        recordInfo.setStartTime(startTime);
        recordInfo.setPreRecordInfo(info);
        preRecordInfo.set(recordInfo);
    }

    public static void remove(){
        preRecordInfo.remove();
    }

    public static class AccessPreRecord{
        private Long startTime;
        private StringBuilder preRecordInfo;

        public Long getStartTime() {
            return startTime;
        }

        public void setStartTime(Long startTime) {
            this.startTime = startTime;
        }

        public StringBuilder getPreRecordInfo() {
            return preRecordInfo;
        }

        public void setPreRecordInfo(StringBuilder preRecordInfo) {
            this.preRecordInfo = preRecordInfo;
        }
    }
}
