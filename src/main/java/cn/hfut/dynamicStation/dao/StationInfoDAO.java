package cn.hfut.dynamicStation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.hfut.dynamicStation.pojo.StationDo;
import cn.hfut.dynamicStation.pojo.StationInfoVo;
import cn.hfut.dynamicStation.utils.DBUtils;

public class StationInfoDAO {
	public List<StationInfoVo> getListStationInfo(){
		List<StationInfoVo> list = new ArrayList<>(900);
		String sql = "select name,sid from station_info;";
		try(Connection conn = DBUtils.getConn();
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
		try(Connection conn = DBUtils.getConn();
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
	
	public static void main (String args[]) {
		//for test
		StationInfoDAO dao = new StationInfoDAO();
		System.out.println(dao.getListStationInfoByName("Olive"));
	}
	// created by panbaoqiang

	public List<StationDo> listStatus(int staion_id){
		List<StationDo> list= new ArrayList<StationDo>(1500);
		String sql="select nba,nda,gmt_created from station_status1 where sid = ? group by gmt_created;";
		try(Connection con = DBUtils.getConn();
				PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, staion_id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				StationDo ssd = new StationDo();
				ssd.setNum_bikes_available(rs.getInt(1));
				ssd.setNum_docks_available(rs.getInt(2));
				ssd.setLast_reported(rs.getTimestamp(3));
				list.add(ssd);
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
