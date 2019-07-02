package cn.hfut.dynamicStation.dao;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;
import cn.hfut.dynamicStation.pojo.StationDo;
import cn.hfut.dynamicStation.pojo.StationInfoVo;
import cn.hfut.dynamicStation.utils.DBUtils;

public class StationInfoDAO {
	public List<StationInfoVo> getListStationInfo(){
		List<StationInfoVo> list = new ArrayList<>(900);
		String sql = "select name,sid from station_info;";
		try(java.sql.Connection conn = DBUtils.getConn();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StationInfoVo infoVo = new StationInfoVo();
				infoVo.setName(rs.getString(1));
				infoVo.setStation_id(rs.getInt(2));
				list.add(infoVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<StationInfoVo> getListStationInfoByName(String searchName){
		List<StationInfoVo> list = new ArrayList<>(900);
		String sql = "select name,sid from station_info where locate(?,name)>0;";
		try(java.sql.Connection conn = DBUtils.getConn();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, searchName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StationInfoVo infoVo = new StationInfoVo();
				infoVo.setName(rs.getString(1));
				infoVo.setStation_id(rs.getInt(2));
				list.add(infoVo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	// created by panbaoqiang
	private Configuration conf;
	private static final String TABLE_NAME = "station_status";
	private static final String[] FAMILYS = { "status"};
	public StationInfoDAO() {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", "master");
	}
	
	public List<StationDo> listStatus(int staion_id) throws IOException{
	    List<StationDo> list = this.listStaionDoByFuzzy(""+staion_id);
		return list;
	}
	

	public static StationDo createStudent(Result result){
		StationDo sd=new StationDo();
		
		byte[] nba=result.getValue(Bytes.toBytes(FAMILYS[0]), Bytes.toBytes("nba"));
		sd.setNum_bikes_available(Integer.parseInt(Bytes.toString(nba)));
		
		byte[] nda=result.getValue(Bytes.toBytes(FAMILYS[0]), Bytes.toBytes("nda"));
		sd.setNum_docks_available(Integer.parseInt(Bytes.toString(nda)));
		
		byte[] last_reported=result.getValue(Bytes.toBytes(FAMILYS[0]), Bytes.toBytes("gmt_c"));
		sd.setLast_reported(Long.parseLong(Bytes.toString(last_reported)));
		return sd;
	}

	private org.apache.hadoop.hbase.client.Connection getConnection() {
		org.apache.hadoop.hbase.client.Connection conn = null;
		try {
			conn = org.apache.hadoop.hbase.client.ConnectionFactory.createConnection(conf);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public  List<StationDo> listStaionDoByFuzzy(String rowKeyEnd) throws IOException{
		org.apache.hadoop.hbase.client.Connection conn = getConnection();
		List<StationDo> list=new ArrayList<StationDo>();
		Table table=conn.getTable(TableName.valueOf(TABLE_NAME));
		//scan
		Scan scan=new Scan();
		Filter filter=new RowFilter(CompareOp.EQUAL, new RegexStringComparator(".*:"+rowKeyEnd));
		scan.setFilter(filter);
		ResultScanner rs=table.getScanner(scan);
		for(Result result:rs){
			list.add(createStudent(result));
		}
		table.close();
		conn.close();
		return list;
	}
}
