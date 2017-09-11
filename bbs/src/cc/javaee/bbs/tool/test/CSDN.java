package cc.javaee.bbs.tool.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import cc.javaee.bbs.model.Tiezi;
import cc.javaee.bbs.service.TieziService;
import cc.javaee.bbs.tool.Tool;
import cc.javaee.bbs.tool.ToolSpring;
import cc.javaee.bbs.tool.thread.IndexThread;



public class CSDN extends Thread{
	
	
	
	private static Logger log = Logger.getLogger(CSDN.class);

	
	public static void main(String[] args) {
		new CSDN().getpage("http://blog.csdn.net/colorant/article/list/1");
	}
	
	
	@Override
	public void run() {
		
		try {
			sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(true){
			new Budejie().start();
			new BudejiePic().start();
			new QulishiFenyun().start();
			int num = new Random().nextInt(100)%10;
			for (int i = 1; i < 40; i++) {
				try {
					new CSDN().autocsdnname(i+"",num);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				sleep(1000*60*60*12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void autocsdnname(String index,int num){
		String url="http://blog.csdn.net/other/newarticle.html";
		if(num==0){
			url="http://blog.csdn.net/mobile/newarticle.html";
		}else if(num==1){
			url="http://blog.csdn.net/web/newarticle.html";
		}else if(num==2){
			url="http://blog.csdn.net/enterprise/newarticle.html";
		}else if(num==3){
			url="http://blog.csdn.net/code/newarticle.html";
		}else if(num==4){
			url="http://blog.csdn.net/www/newarticle.html";
		}else if(num==5){
			url="http://blog.csdn.net/system/newarticle.html";
		}else if(num==6){
			url="http://blog.csdn.net/cloud/newarticle.html";
		}else if(num==7){
			url="http://blog.csdn.net/software/newarticle.html";
		}else if(num==8){
			url="http://blog.csdn.net/other/newarticle.html";
		}else if(num==9){
			url="http://blog.csdn.net/database/newarticle.html";
		}
		
		
//		System.out.println(url);
		log.info("URL:"+url);
		String sendGet = Tool.sendGet(url, "&page="+index);
		
		String[] split = sendGet.split("blog_list clearfix");
		for (int j = 0; j < split.length; j++) {
			String str=split[j];
			if(str.contains("nickname")){
				int start = str.indexOf("nickname");
				int end = str.indexOf("/dt");
				str = str.substring(start, end);
				str=str.replaceAll("\"", "");
				start = str.indexOf("nickname>")+9;
				end = str.indexOf("</a>");
				str = str.substring(start, end);
				autocsdn(str);
			}
		}
	}
	
	public int getpage(String url){
		int num=1;
		try {
			String html = Tool.sendGet(url, "");
			int start=html.indexOf(">下一页<");
			int end=html.indexOf(">尾页<");
			html=html.substring(start, end);
			start=html.indexOf("list/")+5;
			html=html.substring(start, html.length()-1);
			num=Integer.parseInt(html);
		} catch (Exception e) {
		}
		return num;
	}
	
	// 根据名称获取连接
		public void autocsdn(String csdnname) {
			String url="http://blog.csdn.net/";
				String biaotiurl=url+csdnname+"/article/list/";
				log.info("biaotiurl:"+biaotiurl+"1");
				int num=getpage(biaotiurl+"1");
				for (int z = 1; z <= num; z++) {
					String html = Tool.sendGet(biaotiurl+z, "");
					html=html.replaceAll("\"", "");
					html=html.replaceAll(" ", "");
					html=tosubstring(html, "list_item_new", "<!--显示分页-->");
					String[] split = html.split("class=link_title");
					for (int i = 0; i < split.length; i++) {
						String s=split[i]; 
						if(s.length()>50){
							s=s.substring(3, 50);
						}
						if(s.contains("href")){
							s=tosubstring(s, "href=", ">"); 
							Map<String, String> map = getBlogbo(url+s);
							
							String biaoti=map.get("biaoti");
							String content=map.get("content");
//						 System.out.println(biaoti);
//						 System.out.println(content);
							if(biaoti!=null&&biaoti.length()>1&&content!=null&&content.length()>100){
								Tiezi tiezi=new Tiezi();
								tiezi.setName(biaoti);
								tiezi.setBankuaiId(38);
								TieziService tieziService = (TieziService) ToolSpring.getBean("tieziService");
								List<Tiezi> findbytieziwhere = tieziService.select(tiezi);
								if(findbytieziwhere.size()==0){
									tiezi.setCreateuserid(44);
									tiezi.setZhutiid("23");
									tiezi.setBankuaiId(38);
									tiezi.setContenthtml(content);
									tiezi.setContenttxt(content);
									tiezi.setCreatetime(Tool.getyyyyMMddHHmmss());
									tiezi.setFindcount("0");
									tiezi.setIsuse("1");
									tieziService.insert(tiezi);
									log.info("新增标题为:"+biaoti);
									new IndexThread().start();
								}else{
									log.info("标题重复，不保存："+url+s);
								}
							}else{
								log.info("标题或者内存为空，不保存："+url+s);
							}
							int random = Tool.getRandom();
							log.info("随机毫秒数:"+random);
							sleepl(random);
						}
					}
				}
		}
		
		public static Map<String, String> getBlogbo(String url){
			String h = Tool.sendGet(url, "");
			String biaoti=tosubstring(h, "<title>", "</title>");
			if(biaoti.contains("-")){
				String[] split = biaoti.split("-");
				biaoti=split[0];
			}
			biaoti=biaoti.replaceAll("CSDN.NET", "");
			biaoti=biaoti.replaceAll("博客频道", "");
			biaoti=biaoti.replaceAll("-", "").trim();
			String content=tosubstring(h, "class=\"article_content", "上一篇");
			 content=tosubstring(content, "<", "Baidu");
			 content=tosubstring(content, "p>", "</div><!--");
//			 content=content.replaceAll("csdn", "");
//			 content=content.replaceAll("CSDN", "");
			 content=content.replaceAll("ttyprint", "brush:java;toolbar:false");
			 if(content.contains("<script  type=\"text/javascript\">            $(function () { ")){
				 int end=content.indexOf("<script  type=\"text/javascript\">            $(function () { ");
				 content= content.substring(0, end);
			 }
			 Map<String, String> map=new HashMap<String, String>();
			 map.put("biaoti", biaoti);
			 map.put("content", content);
			 return map;
			
		}
		
		
		public static String tosubstring(String tostring,String string, String string2) {
			String substring="";
			int indexOf = tostring.indexOf(string);
			int indexOf1 = tostring.indexOf(string2);
			if(indexOf!=-1&&indexOf1!=-1){
				if(indexOf<indexOf1){
					substring = tostring.substring(indexOf+string.length(), indexOf1);
				}
				
			}
			return substring;
		}
		
		public static void sleepl(long l){
			try {
				Thread.sleep(l);
			} catch (InterruptedException e) {
				log.error("sleep睡眠异常", e);
			}
		}
}
