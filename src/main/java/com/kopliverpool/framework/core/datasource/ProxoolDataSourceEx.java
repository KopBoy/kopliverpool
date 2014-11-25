package com.kopliverpool.framework.core.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import org.logicalcobwebs.proxool.ProxoolDataSource;

/** 
 *
 * Description: 扩展Proxool数据源
 *
 * @author KOP
 * @version 1.0
 * <pre>
 * Modification History: 
 * Date         Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2014年11月5日	 KOP          1.0       1.0 Version 
 * </pre>
 */
public class ProxoolDataSourceEx extends ProxoolDataSource{

	@Override
    public Connection getConnection() throws SQLException {
        Connection conn = null;
        while (conn == null) {
            try {
                conn = super.getConnection();
            } catch (SQLException e) {
                String methodName = e.getStackTrace()[0].getMethodName();
                if (methodName.equalsIgnoreCase("checkSimultaneousBuildThrottle") ||
                        methodName.equalsIgnoreCase("quickRefuse")) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ie) {
                    }
                } else {
                    throw e;
                }
            }
        }
        return conn;
    }
	
}
