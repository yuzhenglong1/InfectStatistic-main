import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * InfectStatistic
 * TODO
 *
 * @author xxx
 * @version xxx
 * @since xxx
 */
class InfectStatistic {
	public static String[] province= {"安徽","北京","重庆","福建","甘肃","广东","广西",
						"贵州","海南","河北","河南","黑龙江","湖北","湖南",
						"吉林","江苏","江西","辽宁","内蒙古","宁夏","青海",
						"山东","山西","陕西","上海","四川","天津","西藏",
						"新疆","云南","浙江"};
	
	public static int[] ip= {0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0};
	
	public static int[] sp= {0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0};
	
	public static int[] cp= {0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0};
	
	public static int[] dp= {0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0};
	
	public static int[] pro={0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0,
			   0,0,0,0,0,0,0};
	
	public static void getallfilename(String path,ArrayList<String> listfilename){
		File file = new File(path);
		String [] names = file.list();
		if(names != null){
			String [] completnames = new String[names.length];
			for(int i=0;i<names.length;i++){
					completnames[i]=path+names[i];
			}
			listfilename.addAll(Arrays.asList(completnames));
		}
		for(String fname:listfilename) {
			readfile(fname);
		}
	}
	
	public static void readfile(String file) {
		try {
			BufferedReader breader=new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			String str="";
			while((str=breader.readLine())!=null) {
				readfile2(str);
			}
		}catch (IOException e) {
        }
	}
	
	public static void readfile2(String str) {
		String[] a=str.split(" ");
		int m=0;
		for(int i=0;i<31;i++) {
			if(province[i].equals(a[0])) {
				m=i;break;
			}
		}
		if(a.length==3) {
			if(a[1].equals("死亡")) {
				if(a[2].length()==3) {
					dp[m]+=(10*(Integer.parseInt(a[2].subSequence(0, 1).toString()))+Integer.parseInt(a[2].subSequence(1, 2).toString()));
					ip[m]-=(10*(Integer.parseInt(a[2].subSequence(0, 1).toString()))+Integer.parseInt(a[2].subSequence(1, 2).toString()));
				}
				else {
					dp[m]+=(Integer.parseInt(a[2].subSequence(0, 1).toString()));
					ip[m]-=(Integer.parseInt(a[2].subSequence(0, 1).toString()));
				}
			}
			if(a[1].equals("治愈")) {
				if(a[2].length()==3) {
					cp[m]+=(10*(Integer.parseInt(a[2].subSequence(0, 1).toString()))+Integer.parseInt(a[2].subSequence(1, 2).toString()));
					ip[m]-=(10*(Integer.parseInt(a[2].subSequence(0, 1).toString()))+Integer.parseInt(a[2].subSequence(1, 2).toString()));
				}
				else {
					cp[m]+=(Integer.parseInt(a[2].subSequence(0, 1).toString()));
					ip[m]-=(Integer.parseInt(a[2].subSequence(0, 1).toString()));
				}
			}
		}
		if(a.length==5) {
			int n=0;
			if(a[1].equals("感染患者")) {
				for(int i=0;i<31;i++) {
					if(province[i].equals(a[3])) {
						n=i;break;
					}
				}
				if(a[4].length()==3) {
					ip[m]-=(10*(Integer.parseInt(a[4].subSequence(0, 1).toString()))+Integer.parseInt(a[4].subSequence(1, 2).toString()));
					ip[n]+=(10*(Integer.parseInt(a[4].subSequence(0, 1).toString()))+Integer.parseInt(a[4].subSequence(1, 2).toString()));
				}
				else {
					ip[m]-=(Integer.parseInt(a[4].subSequence(0, 1).toString()));
					ip[n]+=(Integer.parseInt(a[4].subSequence(0, 1).toString()));
				}
			}
			if(a[1].equals("疑似患者")) {
				for(int i=0;i<31;i++) {
					if(province[i].equals(a[3])) {
						n=i;break;
					}
				}
				if(a[4].length()==3) {
					sp[m]-=(10*(Integer.parseInt(a[4].subSequence(0, 1).toString()))+Integer.parseInt(a[4].subSequence(1, 2).toString()));
					sp[n]+=(10*(Integer.parseInt(a[4].subSequence(0, 1).toString()))+Integer.parseInt(a[4].subSequence(1, 2).toString()));
				}
				else {
					sp[m]-=(Integer.parseInt(a[4].subSequence(0, 1).toString()));
					sp[n]+=(Integer.parseInt(a[4].subSequence(0, 1).toString()));
				}
			}
		}
		if(a.length==4) {
			if(a[1].equals("新增")&&a[2].equals("感染患者")) {
				if(a[3].length()==3) {
					ip[m]+=(10*(Integer.parseInt(a[3].subSequence(0, 1).toString()))+Integer.parseInt(a[3].subSequence(1, 2).toString()));
				}
				else {
					ip[m]+=(Integer.parseInt(a[3].subSequence(0, 1).toString()));
				}
			}
			if(a[1].equals("新增")&&a[2].equals("疑似患者")) {
				if(a[3].length()==3) {
					sp[m]+=(10*(Integer.parseInt(a[3].subSequence(0, 1).toString()))+Integer.parseInt(a[3].subSequence(1, 2).toString()));
				}
				else {
					sp[m]+=(Integer.parseInt(a[3].subSequence(0, 1).toString()));
				}
			}
			if(a[2].equals("确诊感染")) {
				if(a[3].length()==3) {
					ip[m]+=(10*(Integer.parseInt(a[3].subSequence(0, 1).toString()))+Integer.parseInt(a[3].subSequence(1, 2).toString()));
					sp[m]-=(10*(Integer.parseInt(a[3].subSequence(0, 1).toString()))+Integer.parseInt(a[3].subSequence(1, 2).toString()));
				}
				else {
					ip[m]+=(Integer.parseInt(a[3].subSequence(0, 1).toString()));
					sp[m]-=(Integer.parseInt(a[3].subSequence(0, 1).toString()));
				}
			}
			if(a[1].equals("排除")) {
				if(a[3].length()==3) {
					sp[m]-=(10*(Integer.parseInt(a[3].subSequence(0, 1).toString()))+Integer.parseInt(a[3].subSequence(1, 2).toString()));
				}
				else {
					sp[m]-=(Integer.parseInt(a[3].subSequence(0, 1).toString()));
				}
			}
		}
	}
	
	public static void main(String[] args){
		ArrayList<String> listfilename = new ArrayList<String>();
		int grhz=0;
        int yshz=0;
        int zy=0;
        int sw=0;
		String address="";
		String outaddress="";
		int quanguo=0;
		int judgeprovince=0;
		int judgetype=0;
		for(int i=1;i<args.length;i++) {
			if(args[i].equals("-log")) {
				address=args[i+1];
			}
			if(args[i].equals("-out")) {
				outaddress=args[i+1];
			}
			if(args[i].equals("-province")) {
				for(int j=i+1;j<args.length;j++) {
					if(args[j].subSequence(0, 1).toString().equals("-"))break;
					if(args[j].equals("全国")) {
							judgeprovince++;
							quanguo=1;
						}
					for(int t=0;t<31;t++) {
						if(args[j].equals(province[t])) {
							judgeprovince++;
							pro[t]=1;
						}
					}
				}
			}
			if(args[i].equals("-type")) {
				for(int j=i+1;j<args.length;j++) {
					if(args[j].subSequence(0, 1).toString().equals("-"))break;
					if(args[j].equals("ip")) {
						grhz=1;
						judgetype+=1;
					}
					if(args[j].equals("sp")) {
						yshz=1;
						judgetype+=1;
					}
					if(args[j].equals("cure")) {
						zy=1;
						judgetype+=1;
					}
					if(args[j].equals("dead")) {
						sw=1;
						judgetype+=1;
					}
				}
			}
		}
		if(judgetype==0) {
			grhz=1;
			yshz=1;
			zy=1;
			sw=1;
		}
		if(judgeprovince==0) {
			quanguo=1;
			for(int i=0;i<34;i++) {
				pro[i]=1;
			}
		}
		getallfilename(address,listfilename);
		String string="";
		try {
	        File writename = new File(outaddress);
	        writename.createNewFile();
	        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
	        int IP=0;
	        int SP=0;
	        int CP=0;
	        int DP=0;
	        for(int i=0;i<34;i++) {
	        	IP+=ip[i];
	        	SP+=sp[i];
	        	CP+=cp[i];
	        	DP+=dp[i];
	        }
	        if(quanguo==1) {
	        	string="全国 ";
    			if(grhz==1) {
    				string+=("感染患者"+IP+"人 ");
    			}
    			if(yshz==1) {
    				string+=("疑似患者"+SP+"人 ");
    			}
    			if(zy==1) {
    				string+=("治愈"+CP+"人 ");
    			}
    			if(sw==1) {
    				string+=("死亡"+DP+"人 ");
    			}
    			string+="\n";
    			out.write(string);
	        }
        	for(int i=0;i<31;i++) {
    			if(pro[i]==1) {
    				string=(province[i]+" ");
    				if(grhz==1) {
        				string+=("感染患者"+ip[i]+"人 ");
        			}
        			if(yshz==1) {
        				string+=("疑似患者"+sp[i]+"人 ");
        			}
        			if(zy==1) {
        				string+=("治愈"+cp[i]+"人 ");
        			}
        			if(sw==1) {
        				string+=("死亡"+dp[i]+"人 ");
        			}
        			string+="\n";
    			    out.write(string);
    			}
    		}
	        out.flush(); 
	        out.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
