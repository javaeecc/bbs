package cc.javaee.bbs.tool.test.wuyong;

import org.apache.log4j.Logger;

import cc.javaee.bbs.model.Tiezi;
import cc.javaee.bbs.service.TieziService;
import cc.javaee.bbs.tool.Tool;
import cc.javaee.bbs.tool.ToolSpring;

public class JIEMENG2 extends Thread{
	
	
	
	private static Logger log = Logger.getLogger(JIEMENG2.class);

	
	public static String path="http://www.zgjm.org";

	@Override
	public void run() {
		
		new JIEMENG2().getjiemeng();
		
	}
	
	public static void main(String[] args) {
		new JIEMENG2().getjiemeng();
	}
	
	public void getjiemeng(){
		
		//记录梦境 37
//		String url="http://www.zgjm.org/mengjing/list_65_12.html";
		//解梦文化 38
//		String url="http://www.zgjm.org/wenhua/list_61_5.html";
		//梦与健康 39
//		String url="http://www.zgjm.org/health/list_62_1.html";
		
		//双子
		String url="http://www.zgjm.org/astro/Virgo/list_95_";
		for (int i = 1; i <=7; i++) {
			get(url+i+".html", "47");
		}
		url="http://www.zgjm.org/astro/Libra/list_96_";
		for (int i = 1; i <=7; i++) {
			get(url+i+".html", "48");
		}
		url="http://www.zgjm.org/astro/Scorpio/list_97_";
		for (int i = 1; i <=7; i++) {
			get(url+i+".html", "49");
		}
		url="http://www.zgjm.org/astro/Sagittarius/list_98_";
		for (int i = 1; i <=7; i++) {
			get(url+i+".html", "50");
		}
		url="http://www.zgjm.org/astro/Capricorn/list_99_";
		for (int i = 1; i <=7; i++) {
			get(url+i+".html", "51");
		}
		url="http://www.zgjm.org/astro/Aquarius/list_100_";
		for (int i = 1; i <=7; i++) {
			get(url+i+".html", "52");
		}
		url="http://www.zgjm.org/astro/Pisces/list_101_";
		for (int i = 1; i <=7; i++) {
			get(url+i+".html", "53");
		}
//		url="http://www.zgjm.org/wenhua/list_61_";
//		for (int i = 1; i <=5; i++) {
//			get(url+i+".html", "38");
//		}
//		String url="http://www.zgjm.org/health/list_62_";
//		for (int i = 1; i <=1; i++) {
//			get(url+i+".html", "39");
//		}
		
	}
	
	
	public  void get(String url,String zhuti) {
		String str=Tool.sendGet(url, "");
		int start= str.indexOf("class=\"bd indexlist\"");
		int end= str.indexOf("<div class=\"list-pages\">");
		str=str.substring(start, end);
		
		String[] split = str.split("title=");
		for (int i = 0; i < split.length; i++) {
			String string=split[i];
			
			if(string.contains("href=")){
				start=string.indexOf("href=")+5;
				String replaceAll = string.substring(start).replaceAll("\"", "");
				String urlpath=path+replaceAll;
				try {
					
					getcontext(urlpath,zhuti);
				} catch (Exception e) {
					// TODO: handle exception
				}
				System.out.println("一共："+split.length);
				System.out.println("现在执行第："+i+"个");
//				System.out.println(urlpath);
//				break;
			}
		}
	}
	
	public  void getcontext(String urlpath,String zhuti){
		System.out.println(urlpath);
//		urlpath="http://www.zgjm.org/health/63112.html";
		String biaoti="";
		String html=Tool.sendGet(urlpath, "");
		int start=html.indexOf("entrytitle");
		int end=html.indexOf("entrymeta");
		String str=html.substring(start,end);
		start=str.indexOf("<h1>")+4;
		end=str.indexOf("</h1>");
		//标题
		biaoti=str.substring(start,end);
		String name="";
		name=biaoti+"_十二星座";
		start=html.indexOf("entrybody");
		str=html.substring(start,html.length());
		start=str.indexOf("</table>")+8;
		str=str.substring(start).trim();
//		end=str.indexOf("<div id=");
//		str=str.substring(0,end);
		if(str.contains("<strong>【大师特色梦境分析】</strong>")){
			end=str.indexOf("<strong>【大师特色梦境分析】</strong>");
			str=str.substring(0, end);
		}else{
			end=str.indexOf("</div>");
			str=str.substring(0, end);
		}
//		System.out.println(str);
//		System.out.println("html:"+str);
//		start=str.indexOf("详细解说吧。")+6;
//		str=str.substring(start);
//		start=str.indexOf("\" /></p>")+8;
//		str=str.substring(start);
		String[] split = str.split("target=\"_blank\">");
		String context="";
		for (int i = 0; i < split.length; i++) {
			String sp=split[i];
			if(sp.contains("<a href=")){
				end=sp.indexOf("<a href=");
				sp=sp.substring(0, end);
			}
			context+=sp;
		}
		context=context.replaceAll("</a>", "");
		context=context.replaceAll("WWW.ZGJM.ORG", "");
		context=context.replaceAll("www.zgjm.org", "");
		
		if(context.contains("<img")){
			String[] split2 = context.split("<img");
			context="";
			for (int i = 0; i < split2.length; i++) {
				String sp=split2[i];
				if(sp.contains("src=")){
					start=sp.indexOf(">")+1;
					sp=sp.substring(start);
				}
				context+=sp;
			}
		}
		
		
		
		System.out.println("name:"+name);
		System.out.println("context:"+context);
		
		JIEMENG2 jiemeng = new JIEMENG2();
		jiemeng.save(name,context, zhuti);
		
	}
	
	public  void save(String biaoti,String content,String zhuti){
		TieziService tieziService = (TieziService) ToolSpring.getBean("tieziService");
		if(biaoti!=null&&biaoti.length()>1&&content!=null&&content.length()>100){
			Tiezi tiezi=new Tiezi();
			tiezi.setName(biaoti);
			Tiezi findbytieziwhere = tieziService.findbytieziwhere(tiezi);
			if(findbytieziwhere==null){
				tiezi.setCreateuserid(44);
				tiezi.setZhutiid(zhuti);
				tiezi.setBankuaiId(44);
				tiezi.setContenthtml(content);
				tiezi.setContenttxt(content);
				tiezi.setCreatetime(Tool.getyyyyMMddHHmmss());
				tiezi.setFindcount("0");
				tieziService.insert(tiezi);
				log.info("新增标题为:"+biaoti);
			}else{
				log.info("标题重复，不保存：");
			}
		}else{
			log.info("标题或者内存为空，不保存：");
		}
//		int random = Tool.getRandom()/1000;
//		log.info("随机毫秒数:"+random);
//		sleepl(random);
		
	}
	
	public static void sleepl(long l){
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			log.error("sleep睡眠异常", e);
		}
	}
}
