package cn.hfut.dynamicStation.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.hfut.dynamicStation.dao.StationInfoDAO;
import cn.hfut.dynamicStation.pojo.StationDo;
import cn.hfut.dynamicStation.pojo.StationInfoVo;
import cn.hfut.dynamicStation.pojo.StationVO;
import cn.hfut.dynamicStation.utils.TimeUtil;


public class StationInfoService {
	private static StationInfoService StationInfoService = new StationInfoService();
	private StationInfoDAO dao = new StationInfoDAO();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
	
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
		StationVO sv =new StationVO();
        sv.setTime(new ArrayList<String>());
        sv.setNba(new ArrayList<Integer>());
        sv.setNda(new ArrayList<Integer>());
		List<StationDo> list=null;
		try {
			list = dao.listStatus(sid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(StationDo sd:list) {
			sv.getTime().add((TimeUtil.getNewyorkTime(sd.getLast_reported(), sdf)).substring(8, 12));
			sv.getNba().add(sd.getNum_bikes_available());
			sv.getNda().add(sd.getNum_docks_available());
		}
		return sv;
	}
	
	
}
