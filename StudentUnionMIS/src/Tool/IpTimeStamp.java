package Tool;

	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.Random;

	public class IpTimeStamp {

	    
	    private SimpleDateFormat sdf = null;
	    private String ip = null;
	    
	    
	    public IpTimeStamp(String ip) {
	        super();
	    
	        this.ip = ip;
	    }


	    public IpTimeStamp() {
	        super();
	    }
	    
	    
	    public String getIPTimeRand(){
	        StringBuffer buf = new StringBuffer();
	        if(this.ip != null){
	            String s[] = this.ip.split("\\.");//根据ip以点分割将IP中的数字提取
	            for(int i = 0 ; i < s.length; i++){
	                buf.append(this.addZero(s[i], 3));//不够三位数的补零
	            }
	        }
	        buf.append(this.getTimeStamp());
	        Random r = new Random();//再在结尾加上三个随机数
	        for(int i = 0 ; i < 3 ; i++ ){
	            buf.append(r.nextInt(10));
	        }
	        return buf.toString();
	    }
	    
	    
	    
	    
	    
	    private String addZero(String str, int len){
	        
	        StringBuffer s = new StringBuffer();
	        s.append(str);
	        while(s.length()<len){
	            s.insert(0, 0);
	        }
	        
	        return s.toString();
	    }
	    
	    
	    private String getTimeStamp(){//获取时间戳
	        this.sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	        return this.sdf.format(new Date());
	    }
	    
	    public String getDate(){
	        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	        return this.sdf.format(new Date());
	    }
	    
	    
	}