package cc.javaee.bbs.tool.test.wuyong;

import org.apache.log4j.Logger;

import cc.javaee.bbs.model.Tiezi;
import cc.javaee.bbs.service.TieziService;
import cc.javaee.bbs.tool.Tool;
import cc.javaee.bbs.tool.ToolSpring;

public class JIEMENG3 extends Thread{
	
	
	
	private static Logger log = Logger.getLogger(JIEMENG3.class);

	
	public static String path="http://www.zgjm.org";

	@Override
	public void run() {
		
		new JIEMENG3().getjiemeng();
		
	}
	
	public static void main(String[] args) {
		new JIEMENG3().getjiemeng();
	}
	
	public void getjiemeng(){
		
		
		String url="http://www.zgjm.org/shengxiao/shu/list_103_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "56");
		}
		url="http://www.zgjm.org/shengxiao/niu/list_104_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "57");
		}
		url="http://www.zgjm.org/shengxiao/hu/list_105_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "58");
		}
		url="http://www.zgjm.org/shengxiao/tu/list_106_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "59");
		}
		url="http://www.zgjm.org/shengxiao/long/list_107_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "60");
		}
		url="http://www.zgjm.org/shengxiao/she/list_108_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "61");
		}
		url="http://www.zgjm.org/shengxiao/ma/list_109_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "62");
		}
		url="http://www.zgjm.org/shengxiao/yang/list_110_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "63");
		}
		url="http://www.zgjm.org/shengxiao/hou/list_111_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "64");
		}
		url="http://www.zgjm.org/shengxiao/ji/list_112_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "65");
		}
		url="http://www.zgjm.org/shengxiao/gou/list_113_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "66");
		}
		url="http://www.zgjm.org/shengxiao/zhu/list_114_";
		for (int i = 1; i <=2; i++) {
			get(url+i+".html", "67");
		}
		
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
		name=biaoti+"";
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
		
		JIEMENG3 jiemeng = new JIEMENG3();
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
				tiezi.setBankuaiId(47);
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
