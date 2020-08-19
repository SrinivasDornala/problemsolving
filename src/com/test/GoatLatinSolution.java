package com.test;

import java.util.*;
/*
    Input: "I speak Goat Latin"
    Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"

    Input: "The quick brown fox jumped over the lazy dog"
    Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
 */
public class GoatLatinSolution{
    public String toGoatLatin(String S) {
        if(S== null || S.isEmpty())return "";
        String split[] = S.split(" ");
        StringBuilder build = new StringBuilder();
        int cnt =0;
        for(int i=0;i<split.length;++i){
            cnt++;
            if(split[i].trim().isEmpty()){
                cnt--;
                continue;
            }
            char c=split[i].charAt(0);
            String ma ="ma";
            for(int j=0;j<cnt;++j){
                ma+="a";
            }
            if(c=='a'|| c=='e' ||c=='i' ||c=='o' ||c=='u' ||
                    c=='A'|| c=='E' ||c=='I' ||c=='O' ||c=='U' ){
                build.append(split[i]+ma+" ");
            }else{
                build.append((split[i].length()>1)?split[i].substring(1)+c+ma+" ":c+ma+" ");
            }
        }
        return build.toString().trim();
    }
}