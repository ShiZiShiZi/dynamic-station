package cn.hfut.dynamicStation.service;

import java.util.ArrayList;
import java.util.List;

import cn.hfut.dynamicStation.dao.StationInfoDAO;
import cn.hfut.dynamicStation.pojo.StationDo;
import cn.hfut.dynamicStation.pojo.StationInfoVo;
import cn.hfut.dynamicStation.pojo.StationVO;


public class StationInfoService {
	private static StationInfoService StationInfoService = new StationInfoService();
	private StationInfoDAO dao = new StationInfoDAO();
	
	private StationInfoService() {	
	}
	
	public static StationInfoService getInstance() {
		return StationInfoService;
	}
	
	public List<StationInfoVo> getStationInfo() {
		return dao.getListStationInfo();
	}
	
	public List<StationInfoVo> serachStationInfo(String searchName) {
		return dao.getListStationInfoByName(searchName.trim());
	}
	
	
	// created by panbaoqiang
	public StationVO getStationVO(int sid) {
		//通过dao查询数据
		StationVO sv =new StationVO();
        sv.setTime(new ArrayList<String>());
        sv.setNba(new ArrayList<Integer>());
        sv.setNda(new ArrayList<Integer>());
		List<StationDo> list = dao.listStatus(sid);
		for(StationDo sd:list) {
			sv.getTime().add(sd.getLast_reported().toString().substring(11, 16));
			sv.getNba().add(sd.getNum_bikes_available());
			sv.getNda().add(sd.getNum_docks_available());
		}
		return sv;
	}
	
	
}
