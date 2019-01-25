package com.volunteer.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xxx.volunterNetwork.anno.SysControllerLog;
import com.xxx.volunterNetwork.dao.SqlDao;
import com.xxx.volunterNetwork.util.FileUtil;
@Controller
@RequestMapping("/stat")
public class StatChartController extends BaseAction {
	
	//最好要提供StatChartService接口及实现类
	@Autowired
	private SqlDao sqlDao;//为了省事   
	/**
	 * 新版amChart实现
	 * 学院时长排名
	 */
	@RequestMapping("/marjorClass")
	@SysControllerLog(module="统计分析",methods="查看饼图")
	public String productsale2(HttpSession session) throws Exception {
		//1.执行sql语句，得到统计结果
	    List<String> list = execSQL("select t.college,sum(t.duration) samount from t_enroll t group by t.college order by samount desc");	    
	    //2.组织符合要求的json数据
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    String colors[]={"#FF0F00","#FF6600","#FF9E01","#FCD202","#F8FF01","#B0DE09","#04D215","#0D52D1","#2A0CD0","#8A0CCF","#CD0D74","#754DEB"};
	    int j=0;
	    for(int i=0;i<list.size();i++){
	    	sb.append("{").append("\"college\":\"").append(list.get(i)).append("\",")
	    	              .append("\"amount\":\"").append(list.get((++i))).append("\",")
	    	              .append("\"color\":\"").append(colors[j++]).append("\"")
	    	.append("}").append(",");
	    	if(j>=colors.length){
	    		j=0;
	    	}
	    }
	    sb.delete(sb.length()-1, sb.length());	    
	    sb.append("]");
	    System.out.println("=================="+sb.toString());
	    //3.将json数据放入值栈中	  
	    session.setAttribute("result", sb.toString());
	    //4.返回结果
		return "/stat/chart/productsale/index";
	}
	
	/**
	 * 学院注册人数排名(旧版amchart实现)
	 */
	@RequestMapping("/marjor")
	@SysControllerLog(module="统计分析",methods="查看柱状图")
	public String factorysale() throws Exception {
		//1.执行sql语句，得到统计结果
		List<String> list = execSQL("select t.collegeName,menNum from t_organization t group by t.collegeName order by menNum desc");	    
	    //2.组织符合要求的xml数据
	    String content = genPieDataSet(list);	    
	    //3.将拼接好的字符串写入data.xml文件中
	    writeXML("stat//chart//factorysale//data.xml",content);
	    //4.返回结果
		return "/stat/chart/factorysale/index";
	}
	@RequestMapping("/persure")
	public String onlineinfo(HttpSession session) throws Exception {
		//1.执行sql语句，得到统计结果
		List<String> list = execSQL("select o.organization organization,SUM(e.duration) samount,o.menNum menNum from t_organization o join t_enroll e where e.college = o.collegeName GROUP BY o.organization");
		
		/*//2.组织符合要求的xml数据
		String content = genBarDataSet(list);
		
		//3.将拼接好的字符串写入data.xml文件中
		writeXML("stat\\chart\\onlineinfo\\data.xml",content);*/
		
		 //2.组织符合要求的json数据
		  StringBuilder sb = new StringBuilder();
		    sb.append("[");
		    int j=0;
		    for(int i=0;i<list.size();i++){
		    	sb.append("{").append("\"college\":\"").append(list.get(i)).append("\",")
		    	              .append("\"amount\":\"").append(list.get((++i))).append("\",")
		    	              .append("\"menNum\":\"").append(list.get((++i))).append("\",")
		    	.append("}").append(",");
		    	
		    }
		    sb.delete(sb.length()-1, sb.length());	    
		    sb.append("]");
		    System.out.println("=================="+sb.toString());
		    //3.将json数据放入值栈中	  
		    session.setAttribute("result", sb.toString());
		
		
		//writeXML("stat\\chart\\factorysale\\data.xml",this.genPieDataSet(this.execSQL("select factory_name,sum(amount) samount from contract_product_c group by factory_name order by samount desc")));
				
		//4.返回结果
		return "/stat/chart/onlineinfo/index";
	}
	/**
	 * 执行sql
	 * @param sql
	 * @return
	 */
	private List<String> execSQL(String sql ) {
	    //1.执行sql语句，得到统计结果
	    List<String> list = sqlDao.executeSQL(sql);
		return list;
	}
	/**
	 * 生成柱状图的数据源
	 * @param list
	 * @return
	 */
	private String genBarDataSet(List<String> list) {
		StringBuilder sb = new StringBuilder();
	    sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
	    sb.append("<chart><series>");
	    
	    int j=0;
	    for(int i=0;i<list.size();i++){
	    	sb.append("<value xid=\""+(j++)+"\">"+list.get(i)+"</value>");
	    	i++;
	    }
	    
	    sb.append("</series><graphs><graph gid=\"60\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");
	    
	    
	    j=0;
	    for(int i=0;i<list.size();i++){
	    	i++;
	    	sb.append("<value xid=\""+(j++)+"\" description=\"\" url=\"\">"+list.get(i)+"</value>");
	    }
	    
	    sb.append("</graph></graphs>");
	    sb.append("<labels><label lid=\"0\"><x>10</x><y>80</y><rotate></rotate><width></width><align>center</align><text_color></text_color><text_size></text_size><text><![CDATA[<b></b>]]></text></label></labels>");
	    sb.append("</chart>");
		return sb.toString();
	}
	/**
	 * 生成饼图的数据源
	 * @param list
	 * @return
	 */
	private String genPieDataSet(List<String> list) {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<pie>");
		
		for(int i=0;i<list.size();i++){
			sb.append("<slice title=\""+list.get(i)+"\" pull_out=\"true\">"+list.get(++i)+"</slice>");
		}
		
		sb.append("</pie>");
		return sb.toString();
	}


	/**
	 * 写入XML
	 * @param fileName
	 * @param content
	 * @throws FileNotFoundException
	 */
	private void writeXML(String fileName ,String content) throws FileNotFoundException {
		FileUtil fileUtil = new FileUtil();
		String sPath = System.getProperty("evan.webapp");
	    //String sPath = ServletActionContext.getServletContext().getRealPath("/");
	    fileUtil.createTxt(sPath, fileName, content, "UTF-8");
	}
}
