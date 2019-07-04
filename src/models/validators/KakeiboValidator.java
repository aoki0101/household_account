package models.validators;

import java.util.ArrayList;
import java.util.List;

import models.kakeibo;

public class KakeiboValidator {
    public static List<String> validate(kakeibo k) {
        List<String> errors = new ArrayList<String>();
        
        String product_error = _validateProduct(k.getProduct());
        
       if(k.getCategory() < 3){
            if(!product_error.equals("")){
                errors.add(product_error);
            }
       }
        String content_error  =_validateContent(k.getContent());
        if(!content_error.equals("")){
            errors.add(content_error);
        }
        return errors;
        
    }

    private static String _validateProduct(String product) {
        // TODO 自動生成されたメソッド・スタブ
        if(product == null || product.equals("")) {
            return "入力不足があります。";
        }
        return "";
    }

    private static String _validateContent(String content) {
        // TODO 自動生成されたメソッド・スタブ
        if(content == null || content.equals("")) {
            return "内容を入力してください";
        }
        return "";
    }
    
}
