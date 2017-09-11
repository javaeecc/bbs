package cc.javaee.bbs.tool.test;

import java.util.List;

import org.apache.log4j.Logger;

import cc.javaee.bbs.model.Tiezi;
import cc.javaee.bbs.service.TieziService;
import cc.javaee.bbs.tool.Tool;
import cc.javaee.bbs.tool.ToolSpring;

public class Budejie extends Thread{
	
	
	private static Logger log = Logger.getLogger(Budejie.class);
	
	public static String path="http://www.budejie.com/text/1";

	@Override
	public void run() {
		new Budejie().getjiemeng();
	}
	
	public static void main(String[] args) {
		new Budejie().getjiemeng();
	}
	
	public void getjiemeng(){
//		for (int i = 1; i <= 50; i++) {
//			get("http://www.budejie.com/text/"+i);
////			int random = Tool.getRandom()/1000;
////			log.info("随机毫秒数:"+random);
////			sleepl(random);
//		}
		get("http://www.budejie.com/text/1");
	}
	
	
	public  void get(String url) {
		String str=Tool.sendGet(url, "");
		int start= str.indexOf(">精华<");
		int end= str.indexOf("j-page");
		str=str.substring(start, end);
		String[] split = str.split("</li>");
		for (int i = 0; i < split.length; i++) {
			String string=split[i];
			if(string.contains("j-r-list-c")){
				start=string.indexOf("j-r-list-c-desc")+15;
				string=string.substring(start);
				start=string.indexOf("html\">")+6;
				end=string.indexOf("</a>");
				string=string.substring(start, end);
				System.out.println(string);
				Budejie jiemeng = new Budejie();
				String name="";
				if(string.length()>20){
					name=string.substring(0, 20);
				}else{
					name=string;
				}
				name=name.replaceAll(">", "").trim();
				name=name.replaceAll("<", "").trim();
				name=name.replaceAll("/", "").trim();
				name=name.replaceAll("br", "").trim();
				try {
					jiemeng.save(name, string, "77");
				} catch (Exception e) {
					log.error("保存数据异常", e);
				}
			}
		}
	}
	
	
	public  void save(String biaoti,String content,String zhuti){
		int bankuaiid=53;
		TieziService tieziService = (TieziService) ToolSpring.getBean("tieziService");
		if(biaoti!=null&&biaoti.length()>1&&content!=null&&content.length()>10){
			Tiezi tiezi=new Tiezi();
			tiezi.setName(biaoti);
			tiezi.setBankuaiId(bankuaiid);
			List<Tiezi> findbytieziwhere = tieziService.select(tiezi);
			if(findbytieziwhere.size()==0){
				tiezi.setCreateuserid(93);
				tiezi.setZhutiid(zhuti);
				tiezi.setBankuaiId(bankuaiid);
				tiezi.setContenthtml(content);
				tiezi.setContenttxt(content);
				tiezi.setCreatetime(Tool.getyyyyMMddHHmmss());
				tiezi.setFindcount("0");
				tiezi.setIsuse("1");
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
