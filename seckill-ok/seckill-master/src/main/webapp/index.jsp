<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <script type="text/javascript" pageEncoding="UTF-8">
 function startTime()
 {
 //获取当前系统日期
 var today=new Date()
 var y=today.getFullYear()
 var mo=today.getMonth()
 var da=today.getDate()
 var h=today.getHours()
 var m=today.getMinutes()
 var s=today.getSeconds()
 var weekString="日一二三四五六"
 var TheDate=new Date();
 var CalendarData=new Array(20);
 var madd=new Array(12);
 var numString="一二三四五六七八九十";
 var monString="正二三四五六七八九十冬腊";
 var cYear;
 var cMonth;
 var cDay;
 var cHour;
 var cDateString;
 var DateString;
 var Browser=navigator.appName;
 function init()
 {
 CalendarData[0]=0x41A95;
 CalendarData[1]=0xD4A;
 CalendarData[2]=0xDA5;
 CalendarData[3]=0x20B55;
 CalendarData[4]=0x56A;
 CalendarData[5]=0x7155B;
 CalendarData[6]=0x25D;
 CalendarData[7]=0x92D;
 CalendarData[8]=0x5192B;
 CalendarData[9]=0xA95;
 CalendarData[10]=0xB4A;
 CalendarData[11]=0x416AA;
 CalendarData[12]=0xAD5;
 CalendarData[13]=0x90AB5;
 CalendarData[14]=0x4BA;
 CalendarData[15]=0xA5B;
 CalendarData[16]=0x60A57;
 CalendarData[17]=0x52B;
 CalendarData[18]=0xA93;
 CalendarData[19]=0x40E95;
 madd[0]=0;
 madd[1]=31;
 madd[2]=59;
 madd[3]=90;
 madd[4]=120;
 madd[5]=151;
 madd[6]=181;
 madd[7]=212;
 madd[8]=243;
 madd[9]=273;
 madd[10]=304;
 madd[11]=334;
 }
 function GetBit(m,n)
 {
 return (m>>n)&1;
 }
 function e2c()
 {
 var total,m,n,k;
 var isEnd=false;
 var tmp=TheDate.getYear();
 if (tmp<1900) tmp+=1900;
 total=(tmp-2001)*365
 +Math.floor((tmp-2001)/4)
 +madd[TheDate.getMonth()]
 +TheDate.getDate()
 -23;
 if (TheDate.getYear()%4==0&&TheDate.getMonth()>1)
 total++;
 for(m=0;;m++)
 {
 k=(CalendarData[m]<0xfff)?11:12;
 for(n=k;n>=0;n--)
 {
 if(total<=29+GetBit(CalendarData[m],n))
 {
 isEnd=true;
 break;
 }
 total=total-29-GetBit(CalendarData[m],n);
 }
 if(isEnd)break;
 }
 cYear=2001 + m;
 cMonth=k-n+1;
 cDay=total;
 if(k==12)
 {
 if(cMonth==Math.floor(CalendarData[m]/0x10000)+1)
 cMonth=1-cMonth;
 if(cMonth>Math.floor(CalendarData[m]/0x10000)+1)
 cMonth--;
 }
 cHour=Math.floor((TheDate.getHours()+3)/2);
 }
 function GetcDateString()
 { var tmp="";
 if(cMonth<1)
 {
 tmp+="闰";
 tmp+=monString.charAt(-cMonth-1);
 }
 else
 tmp+=monString.charAt(cMonth-1);
 tmp+="月";
 tmp+=(cDay<11)?"初":((cDay<20)?"十":((cDay<30)?"廿":"卅"));
 if(cDay%10!=0||cDay==10)
 tmp+=numString.charAt((cDay-1)%10);
 tmp+=" ";
 cDateString=tmp;
 return tmp;
 }
 init();
 e2c();
 GetcDateString();
 //调用checkTime（）函数，小于十的数字前加0
 m=checkTime(m)
 s=checkTime(s)
 //s设置层txt的内容
 document.getElementById('txt').innerHTML=y+"年"+(mo+1)+"月"+da+"日 "+h+":"+m+":"+s+" 星期"+weekString.charAt(today.getDay())+" 农历"+cDateString
 //过500毫秒再调用一次
 t=setTimeout('startTime()',500)
 //小于10，加0
 function checkTime(i)
 {
 if(i<10)
 {i="0"+i}
 return i
 }
 }
 </script>
</head>
<body>
<div id="txt"></div>
<script>
startTime()
</script>
<div>
 <iframe src="http://m.weather.com.cn/m/pn11/weather.htm " width="420" height="60" marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="no"></iframe>
</div>
<a class="btn btn-info" href="/seckill/list" target="_blank">欢迎，点击进入详情页！</a>
</body>
</html>
