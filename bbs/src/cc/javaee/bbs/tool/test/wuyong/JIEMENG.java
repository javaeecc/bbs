package cc.javaee.bbs.tool.test.wuyong;

import org.apache.log4j.Logger;

import cc.javaee.bbs.model.Tiezi;
import cc.javaee.bbs.service.TieziService;
import cc.javaee.bbs.tool.Tool;
import cc.javaee.bbs.tool.ToolSpring;

public class JIEMENG extends Thread{
	
	
	private static Logger log = Logger.getLogger(JIEMENG.class);

	
	public static String path="http://www.zgjm.org";

	@Override
	public void run() {
		
		new JIEMENG().getjiemeng();
		
	}
	
	public static void main(String[] args) {
		new JIEMENG().getjiemeng();
	}
	
	public void getjiemeng(){
		
//		String url="http://www.zgjm.org/renwu/list_2.html";
		
		//动物 28
//		String url="http://www.zgjm.org/dongwu/list_2.html";
		
		//植物 29
//		String url="http://www.zgjm.org/zhiwu/list_1.html";
		
		//物品 30
//		String url="http://www.zgjm.org/wupin/list_4.html";
		
		//活动 31
//		String url="http://www.zgjm.org/huodong/list_3.html";
		//生活 32
//		String url="http://www.zgjm.org/shenghuo/list_6.html";
		//自然 33
//		String url="http://www.zgjm.org/ziran/list_2.html";
		//鬼神 34
//		String url="http://www.zgjm.org/guishen/list_1.html";
		//建筑 35
//		String url="http://www.zgjm.org/jianzhu/list_1.html";
		//其它 36
//		String url="http://www.zgjm.org/qita/list_1.html";
		//孕妇解梦 40
//		String url="http://www.zgjm.org/yunfujiemeng/list_1.html";
		//记录梦境 37
//		String url="http://www.zgjm.org/mengjing/list_65_12.html";
		//解梦文化 38
//		String url="http://www.zgjm.org/wenhua/list_61_5.html";
		//梦与健康 39
//		String url="http://www.zgjm.org/health/list_62_1.html";
		
//		String zhuti="34";
//		String url="http://www.zgjm.org/guishen/list_";
//		for (int i = 1; i <=2; i++) {
//			get(url+i+".html", zhuti);
//		}
		
//		  get("http://www.zgjm.org/jianzhu/list_1.html", "35");
//		  get("http://www.zgjm.org/qita/list_1.html", "36");
		  get("http://www.zgjm.org/yunfujiemeng/list_1.html", "40");
		
	}
	
	
	public  void get(String url,String zhuti) {
		String str=Tool.sendGet(url, "");
		int start= str.indexOf("<div id=\"list\">");
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
//				System.out.println(urlpath);
//				break;
				System.out.println("一共："+split.length);
				System.out.println("现在执行第："+i+"个");
			}
		}
	}
	
	public  void getcontext(String urlpath,String zhuti){
		String biaoti="";
		String html=Tool.sendGet(urlpath, "");
		int start=html.indexOf("entrytitle");
		int end=html.indexOf("entrymeta");
		String str=html.substring(start,end);
		start=str.indexOf("<h1>")+4;
		end=str.indexOf("</h1>");
		//标题
		biaoti=str.substring(start,end);
		biaoti=biaoti.replaceAll("梦见", "");
		String name="";
		name="梦见"+biaoti+"_做梦梦见"+biaoti+"好不好_做梦梦见"+biaoti+"是什么意思";
		//内容前提
		
		String t="<p style=\"text-align: center;\"><span style=\"font-size: 24px;\"><strong style=\"white-space: normal;\">梦见"+biaoti+"</strong></span></p>";
		String tmp=t+"<p>　　<strong>梦见"+biaoti+"</strong>是什么意思？做梦梦见"+biaoti+"好不好？梦见"+biaoti+"有现实的影响和反应，也有梦者的主观想象.请看下面由(javaee)小编帮你整理的梦见体育教练的详细解说吧。</p></br></br>";
		
		start=html.indexOf("entrybody");
		str=html.substring(start);
		start=str.indexOf("</table>")+8;
		str=str.substring(start);
		start=str.indexOf("详细解说吧。")+6;
		str=str.substring(start);
		start=str.indexOf("\" /></p>")+8;
		str=str.substring(start);
		if(str.contains("<strong>【大师特色梦境分析】</strong>")){
			end=str.indexOf("<strong>【大师特色梦境分析】</strong>");
			str=str.substring(0, end);
		}else{
			end=str.indexOf("</div>");
			str=str.substring(0, end);
		}
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
		
		
//		System.out.println(name);
//		System.out.println(tmp+context);
		
		JIEMENG jiemeng = new JIEMENG();
		jiemeng.save(name, tmp+context, zhuti);
		
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
				tiezi.setBankuaiId(41);
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
