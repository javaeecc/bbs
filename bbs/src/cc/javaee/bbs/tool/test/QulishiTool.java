package cc.javaee.bbs.tool.test;

import java.util.List;

import cc.javaee.bbs.model.Tiezi;
import cc.javaee.bbs.service.TieziService;
import cc.javaee.bbs.tool.Tool;
import cc.javaee.bbs.tool.ToolSpring;

public class QulishiTool {

	//获取趣历史每个的内容
		public static void getqulishi(String path,int bankuaiid,int zhutiid){
			String html = Tool.sendGet(path, "");
			int start=html.indexOf("j31List");
			int end=html.indexOf("j31turnPage");
			html=html.substring(start, end);
//			System.out.println(html);
			String[] split = html.split("js31Item active");
			for (int i = 0; i < split.length; i++) {
				String htmltmp=split[i];
				if(!htmltmp.contains(">头条</span>")&&htmltmp.contains("onmouseover")){
					start=htmltmp.indexOf("setShare")+8;
					end=htmltmp.indexOf("html',")+4;
					htmltmp=htmltmp.substring(start, end);
					String name="";
					String url="";
					end=htmltmp.indexOf("',");
					name=htmltmp.substring(2, end);
					if(name.length()==0){
						continue;
					}
					Tiezi tiezi=new Tiezi();
					tiezi.setName(name);
					tiezi.setBankuaiId(bankuaiid);
					TieziService tieziService = (TieziService) ToolSpring.getBean("tieziService");
					List<Tiezi> findbytieziwhere = tieziService.select(tiezi);
					if(findbytieziwhere.size()==0){
						url=htmltmp.substring(end+2).replace("'", "").trim();
						String context="";
						try {
							context=gettext( url);
						} catch (Exception e) {
						}
						
						if(context.length()==0){
							continue;
						}
						tiezi.setCreateuserid(105);
						tiezi.setZhutiid(zhutiid+"");
						tiezi.setBankuaiId(bankuaiid);
						tiezi.setContenthtml(context);
						tiezi.setContenttxt(context);
						tiezi.setCreatetime(Tool.getyyyyMMddHHmmss());
						tiezi.setFindcount("0");
						tiezi.setIsuse("1");
						tieziService.insert(tiezi);
//						System.out.println(name);
//						System.out.println(context);
//						break;
					}
				}
			}
		}
		
		//根据分页获取全部内容
		public static String gettext(String url){
//			System.out.println(url);
			int pageno=getpage(url);
			url=url.substring(0, url.indexOf(".html"));
			StringBuffer sb=new StringBuffer();
			for (int i = 1; i <= pageno; i++) {
				String urltmp=url+"_"+i+".html";
				String html = Tool.sendGet(urltmp, "");
				int start=html.indexOf("news_main");
				int end=html.indexOf("news_tit\"");
				html=html.substring(start, end);
				start=html.indexOf("id=\"zhan\"");
				html=html.substring(start);
				start=html.indexOf("</div>")+6;
				html=html.substring(start);
				end=html.indexOf("</div><p>");
				html=html.substring(0,end);
				end=html.indexOf("<div class=\"page1");
				html=html.substring(0, end);
				html=replacehtml(html);
				sb.append("<p><h2>第"+i+"节</h2><p>");
				sb.append(html);
				sb.append("</br>");
			}
			String html=sb.toString().replace("红潮导语", "小编导语提示：");
			if(html.contains("免责声明")){
				int start=html.indexOf("免责声明");
				html=html.substring(0, start)+"<p><br></p><p style=\"text-align: center;\">"+html.substring(start, html.length())+"</p>";
			}else{
				html+="<p><br></p> <p style=\"text-align: center;\">免责声明：以上内容源自网络，版权归原作者所有，如有侵犯您的原创版权请告知，我们将尽快删除相关内容。</p>";
			}
			
			return html;
		}
		
		//获取内容分页数量
		public static int getpage(String url){
			String html = Tool.sendGet(url, "");
			int start=html.indexOf("news_main");
			int end=html.indexOf("news_tit\"");
			html=html.substring(start, end);
			start=html.indexOf("id=\"zhan\"");
			html=html.substring(start);
			start=html.indexOf("</div>")+6;
			html=html.substring(start);
			end=html.indexOf("</div><p>");
			html=html.substring(0,end);
			int pageno=1;
			if(html.contains("分页")){
				start=html.indexOf("分页");
				String pagehtml=html.substring(start);
				end=pagehtml.indexOf("</div>");
				pagehtml=pagehtml.substring(0,end);
				pageno= pagehtml.split("href=").length-3;
//				System.out.println(pageno);
			}
			return pageno;
		}
		
		//替换内容
		public static String replacehtml(String html){
			int start=0;
			String[] split = html.split("<a href=");
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < split.length; i++) {
				String sp=split[i];
				if(sp.contains("http://")){
					start=sp.indexOf("\">")+2;
					sp=sp.substring(start, sp.length());
				}else if(sp.contains("html")){
					start=sp.indexOf(">")+1;
					sp=sp.substring(start, sp.length());
				}
				sb.append(sp);
			}
			html=sb.toString().replaceAll("</a>", "");
			split = html.split("<img");
			sb=new StringBuffer();
			for (int i = 0; i < split.length; i++) {
				String sp=split[i];
				if(sp.contains("alt=")){
					start=sp.indexOf("alt=")+4;
					sp=sp.substring(start, sp.length());
					start=sp.indexOf("/>")+2;
					sp=sp.substring(start, sp.length());
				}
				sb.append(sp);
			}
			html=sb.toString();
			html=html.replaceAll("网络配图", "");
			html=html.replaceAll("图片来源于网络", "");
			html=html.replaceAll("white-space: normal;", "");
			html=html.replaceAll("text-align: center;", "");
			html=html.replaceAll("<p style=\" \">", "");
			html=html.replaceAll("<p style=\"\">", "</p>");
			html=html.replaceAll("<center></center>", "</p>");
			html=html.replaceAll("　", "");
			html=html.replaceAll("<p>", "<p>　　");
			html=html.replaceAll("<p><p>", "<p>");
			html=html.replaceAll("<p><p>", "<p>");
			html=html.replaceAll("<p><p>", "<p>");
			html=html.replaceAll("</p></p>", "</p>");
			html=html.replaceAll("</p></p>", "</p>");
			html=html.replaceAll("</p></p>", "</p>");
			return html;
		}
		
		public static void main(String[] args) {
			String s="<p>　　<a href=\"http://www.qulishi.com/renwu/lihao/\">李暠</a>，西凉政权的建立者，谥号武昭王，又叫兴圣<a href=\"http://www.qulishi.com/huati/lidaihuangdi/\">皇帝</a>，在位18年。他<a href=\"http://www.qulishi.com/chengyu/170.html\">博学多才</a>、有志向，很早就被人发现并推荐为官。最初为后凉<a href=\"http://www.qulishi.com/renwu/duanye/\">段业</a>效力，他的才能很快就在政治上得以表现，不久后又被人推荐为敦煌太守。在后凉势力衰退之时，后凉多郡开始反抗段业，经过六郡联手，摆脱后凉，李暠被众人拥护建立西凉。　　</p><center><img title=\"1501727678405279.jpg\" alt=\"1_副本3.jpg\" src=\"/uploads/news/201708/1501727678405279.jpg\"/></center><p style=\"text-align: center;\">　　图片来源于网络</p><p>　　武昭王李暠凭借自己的才华，在建立西凉后，政治上善用贤者，经济上重视务农，制度上执法宽简，赏罚分明。他放宽制度，鼓励因战乱而背井离乡的百姓返回家园，并给他们田地让他们自给自足，给他们特殊的优惠政策，让他们过上稳定的生活。这项政策实施后，很快就吸引了上万户居民返回敦煌。为了统一河西地区，李暠派宋繇四处征战，并多次取得成功。不久，在李暠的治理下，敦煌就恢复往日的繁荣景象。在那样一个纷争四起的年代，有这样一个为百姓着想的明君，真是人民的福气。</p><p>　　李暠在文化方面重视弘扬<a href=\"/minzu/4.html\">汉族</a>文化，因而制订了许多措施来宣扬这些汉族思想。不仅如此，他还建立县学、州学等，招收热爱汉文化的学生，使得他统治境内一时学风四起。他又爱惜人才，只要是人才到西凉，他都重用并加官进爵，于是中原的有志之士纷纷前来投靠于他，当时的西凉得敦煌成为文人名流的聚集地。敦煌也成为西部的文化中心，李暠的文化举措为汉文化的发展做了杰出的贡献。</p><p>";
			System.out.println(replacehtml(s));
		}
}
