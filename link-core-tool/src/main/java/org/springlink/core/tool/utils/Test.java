package org.springlink.core.tool.utils;

/**
 * @Author: Gol
 */
public class Test {
    
    public static void main(String[] args){

        //拼接分表SQL
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            String index =String.valueOf(i);
            sb.append("\nselect * from t_coupon_user_"+index+" union");
        }
        System.out.printf(sb.toString());

    }


}
