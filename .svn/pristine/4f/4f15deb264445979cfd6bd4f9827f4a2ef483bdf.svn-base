package com.herongtech.console.web.syscontroller.audit;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.Role;
import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.bean.AuditRouteSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditRouteTreeDto;
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.bean.ReAsRole;
import com.herongtech.console.domain.common.audit.bean.ReAsRoleDTO;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.audit.interfaces.IAuditRouteService;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.IRoleService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

@Scope("prototype")
@Controller
@RequestMapping("/auditRouteController")
public class AuditRouteController extends BaseController {
    
    
    /**
     * 查询审批路线
     * @param page
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=searchAuditRoute")
    public ModelAndView searchAuditRoute(Page page,AuditRouteSearchBean bean) throws Exception{
        ModelAndView mv = new ModelAndView("system/audit/audit_route_manager_main");
        page.activeCommand();
        if(bean == null){
            bean=new AuditRouteSearchBean();
        }
        List<AuditRoute> routeList=ServiceFactory.getAuditRouteService().searchAuditRoute(page, bean);
        if(bean.getProdNo()==""||bean.getProdNo()==null){
        	 mv.addObject("routeproName",null);
        }else{
        	 mv.addObject("routeproName", ServiceFactory.getProductService().getProductInfoByProdNo(bean.getProdNo()).getProdName());
        }
        IBranchService bran=ServiceFactory.getBranchService();
        for(AuditRoute auditRoute:routeList){
			Branch  branch = bran.getBranch(auditRoute.getBindBranchNo());
			auditRoute.setBranch(branch);
		}
        mv.addObject("routeList", routeList);
        mv.addObject("page", page);
        mv.addObject("bean", bean);
        return mv;
    }
    
    /**
     * 跳转到新增审批路线界面
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toAddRoute")
    public ModelAndView toAddRoute() throws Exception{
        ModelAndView mv = new ModelAndView("system/audit/add_audit_route");
        mv.addObject("isEdit",0);
        return mv;
    }

    /**
     * 新增/编辑审批路线
     * @param route
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=saveAuditRoute")
    public ModelAndView addAuditRoute(AuditRoute route,String isEdit,String id) throws Exception{
    	ModelAndView mv = new ModelAndView();
    	IDB session = DBFactory.getDB(); // 获取数据库连接
    	session.beginTransaction();
    	if (isEdit.equals("1")){   //编辑操作
    		AuditRoute auditR = ServiceFactory.getAuditRouteService().getAuditRouteById(id);
        	auditR.setArName(route.getArName());
        	auditR.setAnExecMode(route.getAnExecMode());
        	auditR.setProdNo(route.getProdNo());
        	auditR.setPubFlag(route.getPubFlag());
        	auditR.setBindBranchNo(route.getBindBranchNo());
        	auditR.setAuditStartBrchLvl(route.getAuditStartBrchLvl());
        	auditR.setArRemark(route.getArRemark());
            ServiceFactory.getAuditRouteService().updateAuditRoute(auditR);
		}else{
			ServiceFactory.getAuditRouteService().addAuditRoute( ResourceUtil.getSessionLoginfo(),route);
		}
    	session.endTransaction();
    	mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
    	//return searchAuditRoute(new Page(), null);
    }
    
    /**
     * 跳转到编辑审批路线界面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toEditRoute")
    public ModelAndView toEditRoute(String id) throws Exception{
    	AuditRoute auditR = ServiceFactory.getAuditRouteService().getAuditRouteById(id);
        ModelAndView mv = new ModelAndView("system/audit/add_audit_route");
        mv.addObject("isEdit",1);
        mv.addObject("auditRoute",auditR);
        return mv;
    }
    
    
    /**
     * 删除审批路线
     * @param route
     * @return
     * @throws Exception
     */
	@RequestMapping(params="method=delRoute")
	@ResponseBody
	public AjaxJson del(AuditRoute route,String ids) throws Exception{
		IAuditRouteService auditerouteService=ServiceFactory.getAuditRouteService();
		route.setId(ids);
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
			try {
				session.beginTransaction();
				auditerouteService.delAuditRoute(route);
				session.endTransaction();
			} catch (SQLException e) {
				CommonLog.getCommonLogCache().errorMessage("数据库错误");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
			}
			
		return retJson;
	}

    
    /**
     * 跳转到设置审批路线界面
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toDesignAuditRoute")
    public ModelAndView toDesignAuditRoute(String arId)throws Exception{
        ModelAndView mv = new ModelAndView("system/audit/design_audit_route");
        mv.addObject("arId", arId);
        return mv;
    }
    
    /**
     * 查询审批路线树
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=jsonList")
    @ResponseBody
    public String jsonList(String arId) throws Exception{
        
        List<AuditRouteTreeDto> list= ServiceFactory.getAuditRouteService().getRouteTree(arId);
        
        return JSONArray.fromObject(list).toString();
    }
    
    @RequestMapping(params = "method=designAuditRoute")
    public ModelAndView designAuditRoute(String routeId)throws Exception{
        return null;
    }
    
    /**
     * 跳转到审批路线信息界面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toRouteInfo")
    public ModelAndView toRouteInfo(String id) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/audit_route_info");
    	AuditRoute auditRoute = ServiceFactory.getAuditRouteService().getAuditRouteById(id);
    	Map<String,String> pubFlagMap = getPubFlagMap();
    	auditRoute.setPubFlag(pubFlagMap.get(auditRoute.getPubFlag()));
    	mv.addObject("auditRoute",auditRoute);
    	return mv;
    }
    
    public Map<String,String> getPubFlagMap(){
    	Map<String,String> brchLvlMap = new HashMap<String,String>();
    	brchLvlMap.put("1","全行通用");
    	brchLvlMap.put("2","指定机构使用");
    	return brchLvlMap;
    }
    
    /**
     * 跳转到节点信息界面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toNodeInfo")
    public ModelAndView toNodeInfo(String id) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/audit_node_info");
    	AuditNode auditNode = ServiceFactory.getAuditNodeService().getAuditNode(id);
    	AuditRoute auditRoute = ServiceFactory.getAuditRouteService().getAuditRouteById(auditNode.getArId());
    	mv.addObject("auditNode",auditNode);
    	mv.addObject("arName",auditRoute.getArName());
    	return mv;
    }
    
    /**
     * 跳转到岗位信息界面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toStationInfo")
    public ModelAndView toStationInfo(String id) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/audit_station_info");
    	AuditStation auditStation = ServiceFactory.getAuditStationService().getAuditStation(id);
    	AuditNode auditNode = ServiceFactory.getAuditNodeService().getAuditNode(auditStation.getAnId());
    	AuditRoute auditRoute = ServiceFactory.getAuditRouteService().getAuditRouteById(auditStation.getArId());
    	mv.addObject("auditStation",auditStation);
    	mv.addObject("arName",auditRoute.getArName());
    	mv.addObject("anName",auditNode.getAnName());
    	return mv;
    }
    
    /**
     * 跳转到新增节点页面
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toAddNode")
    public ModelAndView toAddNode(String arId) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/add_audit_node");
    	AuditRoute auditRoute = ServiceFactory.getAuditRouteService().getAuditRouteById(arId);
    	AuditNode auditNode = new AuditNode();
    	auditNode.setArId(arId);
    	List<AuditNodeBranchLevel> brchLvlList = getBrchLvlList();
    	mv.addObject("brchLvlList",brchLvlList);
    	mv.addObject("arName",auditRoute.getArName());
    	mv.addObject("auditNode",auditNode);
    	mv.addObject("isEdit","0");
    	return mv;
    }
    
    /**
     * 跳转到编辑节点页面
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toEditNode")
    public ModelAndView toEditNode(String anId) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/add_audit_node");
    	AuditNode auditNode = ServiceFactory.getAuditNodeService().getAuditNode(anId);
    	AuditRoute auditRoute = ServiceFactory.getAuditRouteService().getAuditRouteById(auditNode.getArId());
    	List<AuditNodeBranchLevel> brchLvlList = getBrchLvlList();
    	mv.addObject("brchLvlList",brchLvlList);
    	mv.addObject("arName",auditRoute.getArName());
    	mv.addObject("auditNode",auditNode);
    	mv.addObject("isEdit","1");
    	return mv;
    }
   
    public List<AuditNodeBranchLevel> getBrchLvlList(){
    	List<AuditNodeBranchLevel> brchLvlList = new ArrayList<AuditNodeBranchLevel>();
    	brchLvlList.add(new AuditNodeBranchLevel("1","总行"));
    	brchLvlList.add(new AuditNodeBranchLevel("2","分行"));
    	return brchLvlList;
    }
    
    /**
     * 节点机构级别内部类
     */
    public class AuditNodeBranchLevel{
    	public String brchLvlNo;
    	public String brchLvlName;
    	public AuditNodeBranchLevel(String brchLvlNo, String brchLvlName){
    		this.brchLvlNo=brchLvlNo;
    		this.brchLvlName=brchLvlName;
    	}
		public String getBrchLvlNo() {
			return brchLvlNo;
		}
		public void setBrchLvlNo(String brchLvlNo) {
			this.brchLvlNo = brchLvlNo;
		}
		public String getBrchLvlName() {
			return brchLvlName;
		}
		public void setBrchLvlName(String brchLvlName) {
			this.brchLvlName = brchLvlName;
		}
    }
    /**
     * 保存审批节点
     * @param id
     * @return
     * @throws BizAppException 
     * @throws Exception
     */
    @RequestMapping(params = "method=saveAuditNode")
    @ResponseBody
    public AjaxJson saveAuditNode(AuditNode auditNode, String isEdit) throws BizAppException{
    	AjaxJson aj = new AjaxJson();
    	IDB session = DBFactory.getDB(); // 获取数据库连接
    	try{
    		session.beginTransaction();
	    	if("0".equals(isEdit)){
	    		ServiceFactory.getAuditNodeService().addAuditNode(auditNode);
	    	}else{
	    		ServiceFactory.getAuditNodeService().editAuditNode(auditNode);
	    	}
	    	session.endTransaction();
    	}catch(Exception e){
    		try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		e.printStackTrace();
			aj.setSuccess(false);
			aj.setMsg("保存节点信息失败："+e.getMessage());
			return aj;
    	}
    	aj.setSuccess(true);
		return aj;
    }

    /**
     * 跳转到新增岗位页面
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toAddStation")
    public ModelAndView toAddStation(String anId) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/add_audit_station");
    	AuditNode auditNode = ServiceFactory.getAuditNodeService().getAuditNode(anId);
    	AuditRoute auditRoute = ServiceFactory.getAuditRouteService().getAuditRouteById(auditNode.getArId());
    	AuditStation auditStation = new AuditStation();
    	auditStation.setAnId(anId);
    	auditStation.setArId(auditRoute.getId());
    	mv.addObject("arName",auditRoute.getArName());
    	mv.addObject("anName",auditNode.getAnName());
    	mv.addObject("auditStation",auditStation);
    	mv.addObject("isEdit","0");
    	return mv;
    }
    
    /**
     * 跳转到编辑岗位页面
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toEditStaion")
    public ModelAndView toEditStaion(String asId) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/add_audit_station");
    	AuditStation auditStation = ServiceFactory.getAuditStationService().getAuditStation(asId);
    	AuditNode auditNode = ServiceFactory.getAuditNodeService().getAuditNode(auditStation.getAnId());
    	AuditRoute auditRoute = ServiceFactory.getAuditRouteService().getAuditRouteById(auditStation.getArId());
    	mv.addObject("arName",auditRoute.getArName());
    	mv.addObject("anName",auditNode.getAnName());
    	mv.addObject("auditStation",auditStation);
    	mv.addObject("isEdit","1");
    	return mv;
    }
    
    /**
     * 保存审批岗位
     * @param id
     * @return
     * @throws BizAppException 
     * @throws Exception
     */
    @RequestMapping(params = "method=saveAuditStation")
    @ResponseBody
    public AjaxJson saveAuditStation(AuditStation auditStation, String isEdit) throws BizAppException{
    	AjaxJson aj = new AjaxJson();
    	IDB session = DBFactory.getDB(); // 获取数据库连接
    	try{
    		session.beginTransaction();
	    	if("0".equals(isEdit)){
	    		ServiceFactory.getAuditStationService().addAuditStation(auditStation);
	    	}else{
	    		ServiceFactory.getAuditStationService().editAuditStation(auditStation);
	    	}
	    	session.endTransaction();
    	}catch(Exception e){
    		try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		e.printStackTrace();
    		aj.setSuccess(false);
    		aj.setMsg("保存岗位信息失败："+e.getMessage());
    		return aj;
    	}
    	aj.setSuccess(true);
    	return aj;
    }
    
    /**
	 * 删除审批节点
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=delNode")
	@ResponseBody
	public AjaxJson delNode(String anId,String arId){
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			ServiceFactory.getAuditNodeService().deleteAuditNode(arId, anId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			retJson.setMsg("删除数据库信息失败:"+e.getMessage());
			retJson.setSuccess(false);
			return retJson;
		}
		retJson.setSuccess(true);
		return retJson;
	}
	
	/**
	 * 删除审批岗位
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=delStation")
	@ResponseBody
	public AjaxJson delStation(String asId,String arId){
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			ServiceFactory.getAuditStationService().deleteAuditStation(asId, arId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			retJson.setMsg("删除数据库信息失败:"+e.getMessage());
			retJson.setSuccess(false);
			return retJson;
		}
		retJson.setSuccess(true);
		return retJson;
	}
	
	/**
     * 跳转到分配角色页面
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=toAssignStation")
    public ModelAndView toAssignStation(String asId,String anId,String arId) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/assign_audit_station");
    	AuditStation auditStation = ServiceFactory.getAuditStationService().getAuditStation(asId);
    	List<AuditStation> stationList = ServiceFactory.getAuditStationService().getAuditStationByAnId(anId);
    	List<ReAsRole> reAsRoleList= ServiceFactory.getReAsRoleService().searchReAsRole(asId);
    	List<Role> allRoleList = ServiceFactory.getRoleService().getAllRoleList();
    	IRoleService roleService = ServiceFactory.getRoleService();
    	List<ReAsRoleDTO> roleDtoList = new ArrayList<ReAsRoleDTO>();
    	for(ReAsRole reAsRole:reAsRoleList){
    		Role role = roleService.getRole(reAsRole.getBindRoleId());
    		ReAsRoleDTO roleDTO = new ReAsRoleDTO(reAsRole,role);
    		roleDtoList.add(roleDTO);
    	}
    	mv.addObject("auditStation",auditStation);
    	mv.addObject("stationList",stationList);
    	mv.addObject("allRoleList",allRoleList);
    	mv.addObject("roleDtoList",roleDtoList);
    	return mv;
    }
    
    /**
     * 分配角色
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=assignRoles")
    public ModelAndView assignRoles(String roleIds,String asId) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/assign_audit_station");
    	IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			ServiceFactory.getReAsRoleService().addReAsRole(roleIds, asId);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new BizAppException("角色分配失败："+e.getMessage());
		}
    	AuditStation auditStation = ServiceFactory.getAuditStationService().getAuditStation(asId);
    	List<AuditStation> stationList = ServiceFactory.getAuditStationService().getAuditStationByAnId(auditStation.getAnId());
    	List<ReAsRole> reAsRoleList= ServiceFactory.getReAsRoleService().searchReAsRole(asId);
    	List<Role> allRoleList = ServiceFactory.getRoleService().getAllRoleList();
    	IRoleService roleService = ServiceFactory.getRoleService();
    	List<ReAsRoleDTO> roleDtoList = new ArrayList<ReAsRoleDTO>();
    	for(ReAsRole reAsRole:reAsRoleList){
    		Role role = roleService.getRole(reAsRole.getBindRoleId());
    		ReAsRoleDTO roleDTO = new ReAsRoleDTO(reAsRole,role);
    		roleDtoList.add(roleDTO);
    	}
    	mv.addObject("auditStation",auditStation);
    	mv.addObject("stationList",stationList);
    	mv.addObject("allRoleList",allRoleList);
    	mv.addObject("roleDtoList",roleDtoList);
    	return mv;
    }
    
    /**
     * 取消分配
     * @param arId
     * @return
     * @throws Exception
     */
    @RequestMapping(params = "method=cancelAssign")
    public ModelAndView cancelAssign(String roleIds,String asId) throws Exception{
    	ModelAndView mv = new ModelAndView("system/audit/assign_audit_station");
    	IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			ServiceFactory.getReAsRoleService().deleteReAsRole(roleIds);
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new BizAppException("角色分配失败："+e.getMessage());
		}
    	AuditStation auditStation = ServiceFactory.getAuditStationService().getAuditStation(asId);
    	List<AuditStation> stationList = ServiceFactory.getAuditStationService().getAuditStationByAnId(auditStation.getAnId());
    	List<ReAsRole> reAsRoleList= ServiceFactory.getReAsRoleService().searchReAsRole(asId);
    	List<Role> allRoleList = ServiceFactory.getRoleService().getAllRoleList();
    	IRoleService roleService = ServiceFactory.getRoleService();
    	List<ReAsRoleDTO> roleDtoList = new ArrayList<ReAsRoleDTO>();
    	for(ReAsRole reAsRole:reAsRoleList){
    		Role role = roleService.getRole(reAsRole.getBindRoleId());
    		ReAsRoleDTO roleDTO = new ReAsRoleDTO(reAsRole,role);
    		roleDtoList.add(roleDTO);
    	}
    	mv.addObject("auditStation",auditStation);
    	mv.addObject("stationList",stationList);
    	mv.addObject("allRoleList",allRoleList);
    	mv.addObject("roleDtoList",roleDtoList);
    	return mv;
    }
    
    /**
	 * 移动审批岗位
	 * @param req
	 * @param response
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(params = "method=moveStation")
	@ResponseBody
	public AjaxJson moveStation(String asId,String upOrDown){
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			AuditStation auditStation = ServiceFactory.getAuditStationService().getAuditStation(asId);
			ServiceFactory.getAuditStationService().moveAuditStation(asId, auditStation.getAnId(), auditStation.getArId(), Integer.parseInt(upOrDown));
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			retJson.setMsg("移动岗位失败："+e.getMessage());
			retJson.setSuccess(false);
			return retJson;
		}
		retJson.setSuccess(true);
		return retJson;
	}
}
