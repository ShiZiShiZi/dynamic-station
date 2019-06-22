package cn.hfut.dynamicStation.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.hfut.dynamicStation.pojo.StationInfoVo;
import cn.hfut.dynamicStation.service.StationInfoService;


/**
 * Servlet implementation class stationInfoServlet
 */
public class StationInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<StationInfoVo>  vo = StationInfoService.getInstance().getStationInfo();
		String jsonStr = JSON.toJSONString(vo);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String searchName = request.getParameter("searchName");
		List<StationInfoVo>  vo = StationInfoService.getInstance().serachStationInfo(searchName);
		String jsonStr = JSON.toJSONString(vo);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
	}

}
