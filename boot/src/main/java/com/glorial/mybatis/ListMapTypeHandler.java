package com.glorial.mybatis;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.OracleResultSet;
import oracle.sql.StructDescriptor;

public class ListMapTypeHandler implements TypeHandler<List<Map<String, Object>>> {

	@Deprecated
	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public Struct createStruct(Connection conn, String typeName, Map<String, Object> info) throws SQLException {
		info.remove("RECORD_TYPE");
		info.remove("TABLE_TYPE");

		StructDescriptor structDescriptor = StructDescriptor.createDescriptor (typeName, conn);
		ResultSetMetaData metaData = structDescriptor.getMetaData();

		List valueList = new ArrayList();
		for(int j = 1 ; j <= metaData.getColumnCount() ; j++) {
			valueList.add(info.get(metaData.getColumnName(j)));
		}

		return conn.createStruct(typeName, valueList.toArray());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Struct createStruct(Connection conn, String typeName, Map<String, Object> info, String[] attributes) throws SQLException {
		info.remove("RECORD_TYPE");
		info.remove("TABLE_TYPE");

		List valueList = new ArrayList();
		for(String attribute : attributes) {
			valueList.add(info.get(attribute));
		}

		return conn.createStruct(typeName, valueList.toArray());
	}

	public String[] getOracleTypeAttributes(Connection conn, String typeName) throws SQLException {
		String queryString = "SELECT ATTR_NAME, ATTR_TYPE_NAME FROM ALL_TYPE_ATTRS WHERE OWNER = ? AND TYPE_NAME = ? ";
		PreparedStatement pstmt = conn.prepareStatement(queryString);
		pstmt.setString(1, typeName.split("\\.")[0]);
		pstmt.setString(2, typeName.split("\\.")[1]);

		OracleResultSet rs = (OracleResultSet)pstmt.executeQuery();
		rs.closeStatementOnClose();

		List<String> attrNames = new ArrayList<String>();

		while(rs.next()) {
			attrNames.add(rs.getString(1));
		}

		return attrNames.toArray(new String[0]);
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, List<Map<String, Object>> parameter, JdbcType jdbcType) throws SQLException {
		Connection conn = ps.getConnection();
		OracleConnection oracleConn = null;
		if (conn.isWrapperFor(OracleConnection.class)) {
			oracleConn = conn.unwrap(OracleConnection.class);
		}

		// uncomment this intead when you using DBCP.
		//Connection conn = new CommonsDbcpNativeJdbcExtractor().getNativeConnection(ps.getConnection());

		String recordType = (String)parameter.get(0).get("RECORD_TYPE");
		String tableType = (String)parameter.get(0).get("TABLE_TYPE");

        // register typemap shown below if not exists.
		//if (!oracleConn.getTypeMap().containsKey(recordType)) {
		//	oracleConn.getTypeMap().put(recordType, parameter.get(0).getClass());
		//}

		//TypeDescriptor[] desc = oracleConn.getTypeDescriptorsFromList(new String[][] {recordType.split("\\.")});
		//for(TypeDescriptor descr : desc) {
		//	System.out.println("==========>" + descr.getName() + "/" + descr.getTypeName()+ "/" + descr.getSchemaName()+ "/" + descr.getTypeCodeName());
		//}

		String[] attributes = getOracleTypeAttributes(oracleConn, recordType);
		Collection<Struct> oracleStructs = new ArrayList<>(parameter.size());
		for (Map<String, Object> info : parameter) {
            oracleStructs.add(createStruct(oracleConn, recordType, info, attributes));
            //oracleStructs.add(createStruct(oracleConn, recordType, info));
        }

		ps.setObject(i, oracleConn.createARRAY(tableType, oracleStructs.toArray()));
	}

	@Override
	public List<Map<String, Object>> getResult(ResultSet rs, String columnName) throws SQLException {
		return null;
	}

	@Override
	public List<Map<String, Object>> getResult(ResultSet rs, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public List<Map<String, Object>> getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return null;
	}
}
