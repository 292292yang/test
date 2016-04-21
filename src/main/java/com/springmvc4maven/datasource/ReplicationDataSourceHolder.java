package com.springmvc4maven.datasource;

/**
 * <p>Copyright© 2013-2016 AutoChina International Ltd. All rights reserved.</p>
 *
 * @Author yangzhibin@che001.com
 * @Date 2016/4/20
 */
public class ReplicationDataSourceHolder {
    public static final ThreadLocal<String> holder=new ThreadLocal<>();

    public static String getDataSource(){
        return holder.get();
    }

    public static void setDataSource(String dataSourceName){
        holder.set(dataSourceName);
    }
}
