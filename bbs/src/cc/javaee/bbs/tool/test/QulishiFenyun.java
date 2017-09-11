package cc.javaee.bbs.tool.test;


public class QulishiFenyun extends Thread{

	
	@Override
	public void run() {
		
		//获取人物
		new Thread(){
			public void run() {
				String path="http://www.qulishi.com/fengyun/index_";
				for (int i = 1; i <=5; i++) {
					try {
						System.out.println("人物已经执行到第"+i+"页");
						QulishiTool.getqulishi(path+i+".htm",55,80);
					} catch (Exception e) {
						System.out.println("数据异常");
					}
				}
			};
		}.start();
		
		//获取解密
		new Thread(){
			public void run() {
				String path="http://www.qulishi.com/jiemi/index_";
				for (int i = 1; i <=5; i++) {
					try {
						System.out.println("解密已经执行到第"+i+"页");
						QulishiTool.getqulishi(path+i+".htm",56,81);
					} catch (Exception e) {
						System.out.println("数据异常");
					}
				}
			};
		}.start();
		//获取野史
		new Thread(){
			public void run() {
				String path="http://www.qulishi.com/yeshi/index_";
				for (int i = 1; i <=5; i++) {
					try {
						System.out.println("野史已经执行到第"+i+"页");
						QulishiTool.getqulishi(path+i+".htm",57,82);
					} catch (Exception e) {
						System.out.println("数据异常");
					}
				}
			};
		}.start();
		//获取百科
		new Thread(){
			public void run() {
				String path="http://www.qulishi.com/wenshi/index_";
				for (int i = 1; i <=3; i++) {
					try {
						System.out.println("百科已经执行到第"+i+"页");
						QulishiTool.getqulishi(path+i+".htm",58,83);
					} catch (Exception e) {
						System.out.println("数据异常");
					}
				}
			};
		}.start();
		
		
		//获取神话
		new Thread(){
			public void run() {
				String path="http://www.qulishi.com/shenhua/index_";
				for (int i = 1; i <=1; i++) {
					try {
						System.out.println("神话已经执行到第"+i+"页");
						QulishiTool.getqulishi(path+i+".htm",59,84);
					} catch (Exception e) {
						System.out.println("数据异常");
					}
				}
			};
		}.start();
		//获取传统文化
		new Thread(){
			public void run() {
				String path="http://www.qulishi.com/chuantong/index_";
				for (int i = 1; i <=207; i++) {
					try {
						System.out.println("传统文化已经执行到第"+i+"页");
						QulishiTool.getqulishi(path+i+".htm",59,88);
					} catch (Exception e) {
						System.out.println("数据异常");
					}
				}
			};
		}.start();
		//获取古文名著
		new Thread(){
			public void run() {
				String path="http://www.qulishi.com/guwen/index_";
				for (int i = 1; i <=3; i++) {
					try {
						System.out.println("古文名著已经执行到第"+i+"页");
						QulishiTool.getqulishi(path+i+".htm",59,89);
					} catch (Exception e) {
						System.out.println("数据异常");
					}
				}
			};
		}.start();
		//获取战事
		new Thread(){
			public void run() {
				String path="http://www.qulishi.com/zhanshi/index_";
				for (int i = 1; i <=3; i++) {
					try {
						System.out.println("战事已经执行到第"+i+"页");
						QulishiTool.getqulishi(path+i+".htm",59,90);
					} catch (Exception e) {
						System.out.println("数据异常");
					}
				}
			};
		}.start();
		
	}
}
