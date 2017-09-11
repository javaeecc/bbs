package cc.javaee.bbs.tool.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import cc.javaee.bbs.model.Tiezi;
import cc.javaee.bbs.service.TieziService;
import cc.javaee.bbs.tool.PublicStatic;
import cc.javaee.bbs.tool.Tool;
import cc.javaee.bbs.tool.ToolSpring;

public class BudejiePic extends Thread{
	
	
	private static Logger log = Logger.getLogger(BudejiePic.class);
	

	@Override
	public void run() {
		new BudejiePic().getjiemeng();
	}
	
	public static void main(String[] args) {
		new BudejiePic().getjiemeng();
	}
	
	public void getjiemeng(){
//		for (int i = 1; i <= 50; i++) {
//			get("http://www.budejie.com/pic/"+i);
//		}
		get("http://www.budejie.com/pic/1");
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
				start=string.indexOf("j-r-list-c-img")+14;
				string=string.substring(start);
				start=string.indexOf("html\">")+6;
				end=string.indexOf("</a>");
				string=string.substring(start, end);
				start=string.indexOf("original")+8;
				end=string.indexOf("alt");
				string=string.substring(start, end);
				string=string.replaceAll("\"", "");
				string=string.replaceAll("=", "");
				BudejiePic jiemeng = new BudejiePic();
				String name="";
				start=string.indexOf("title")+5;
				name=string.substring(start);
				string=string.substring(0,start-5).trim();
				System.out.println(name);
				System.out.println(string);
				String pic="/file"+getpic(PublicStatic.FILE_PATH, string);
				if(pic.length()>10){
					pic="http://bbs.javaee.cc"+pic;
					string="<p><img src=\""+pic+"\" title=\""+name+"\" alt=\""+name+"\" width=\"270\"/></p>";
					jiemeng.save(name, string, "78");
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
	
	//获取图片
		public static String getpic(String path,String url){
			String filename="";
			URL url2=null;
			InputStream is=null;
			OutputStream os=null;
			try {
				url2=new URL(url);
				is=url2.openStream();
			} catch (Exception e) {
				System.out.println("异常：*************************************");
				return filename;
			}
			String dateformat = new SimpleDateFormat(
					"yyyyMMddHHmmssSSS").format(new Date());
			
			int lastIndexOf = url.lastIndexOf(".");
			String substring = url.substring(lastIndexOf,
					url.length());
			String dirpath = "/budejie/"+new SimpleDateFormat("yyyyMMdd").format(new Date());
			String pathdir=path+dirpath+"/";
			File isfile=new File(pathdir);
			if(!isfile.exists()){
				isfile.mkdirs();
			}
			File file=new File(pathdir+dateformat+substring);
			try {
				os=new FileOutputStream(file);
				byte[] b=new byte[1024];
				while(true){
					int readed=is.read(b);
					if(readed==-1){
						break;
					}
					byte[] temp=new byte[readed];
					System.arraycopy(b, 0, temp, 0, readed);
					os.write(temp);
				}
				is.close();
				os.close();
			} catch (Exception e) {
				System.out.println("读取流异常：------------------------------------------");
				return filename;
			}
			return dirpath+"/"+dateformat+substring;
		}
}
