package com.leetcode;

public class Day9 {
    /**
     * 这么简单居然是个中级题，我写的也是够丑，够暴力
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String result = "";
        while(num>0){
            if(num>=1000){
                num-=1000;
                result+="M";
            }else if(num>=900){
                num-=900;
                result+="CM";
            }else if(num>=500){
                num-=500;
                result+="D";
            }else if(num>=400){
                num-=400;
                result+="CD";
            }else if(num>=100){
                num-=100;
                result+="C";
            }else if(num>=90){
                num-=90;
                result+="XC";
            }else if(num>=50){
                num-=50;
                result+="L";
            }else if(num>=40){
                num-=40;
                result+="XL";
            }else if(num>=10){
                num-=10;
                result+="X";
            }else if(num>=9){
                num-=9;
                result+="IX";
            }else if(num>=5){
                num-=5;
                result+="V";
            }else if(num>=4){
                num-=4;
                result+="IV";
            }else if(num>=1){
                num-=1;
                result+="I";
            }
        }
        return result;
    }

    /**
     * 这个初级题，我觉得比上一道中级题要难
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        String[] dic = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] dicInt = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        int i = 0 ;
        int result = 0;
        while(true){
            // System.out.println(result+"-"+s);
            if(s.indexOf(dic[i])!=0){
                i++;
            }else{
                result+=dicInt[i];
                if(i%2==1){
                    if(s.length()==2){
                        break;
                    }
                    s=s.substring(2);
                }else{
                    if(s.length()==1){
                        break;
                    }
                    s=s.substring(1);
                }
            }
        }
        return result;
    }

    /**
     * 做题时不知怎么，脑子很不清楚，还是个初级题
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0||strs[0].length()==0){
            return "";
        }
        String temp=strs[0];
        String result =temp.charAt(0)+"";
        int j = 0;
        for(int i=0;j<=temp.length();i++){
            // System.out.println("++++"+i+"-"+j+result);
            if(j==0){
                temp=temp.length()<strs[i].length()?temp:strs[i];
            }
            if(strs[i].indexOf(result)!=0){
                if(j==0){
                    return "";
                }
                result = temp.substring(0,j-1);
                // System.out.println("+++"+i+"-"+j);
                break;
            }
            if(i==strs.length-1){
                i=-1;
                j++;
                if(j>temp.length()){
                    // System.out.println(""+i+"-"+j+result);
                    break;
                }
                result = temp.substring(0,j);
            }
            // System.out.println(result);
        }
        return result;
    }
}
