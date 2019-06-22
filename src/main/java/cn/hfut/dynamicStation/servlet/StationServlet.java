package cn.hfut.dynamicStation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.hfut.dynamicStation.pojo.StationVO;
import cn.hfut.dynamicStation.service.StationInfoService;

/**
 * Servlet implementation class StationServlet
 */
public class StationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int  sid = Integer.parseInt(request.getParameter("sid"));
		StationVO vo =StationInfoService.getInstance().getStationVO(sid);
		String jsonStr =JSON.toJSONString(vo);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(jsonStr);
	}

}
