package com.dushangkui.domyself.common.util;

import java.util.ArrayList;  
import java.util.HashSet;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Set;  
  
import net.sourceforge.pinyin4j.PinyinHelper;  
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;  
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;  
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;  
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;  
  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
/** 
 * 拼音转换类工具类 
 * @author dushangkui 
 * 
 */  
public class PinyinUtil {  
  
    private static final Logger LOG = LoggerFactory.getLogger(PinyinUtil.class);  
  
    /** 
     * 汉字转拼音(全拼) 
     *  
     * @param src 
     * @return 
     */  
    public static String getPinyin(String src) {
    	src=src.replaceAll(" ", "");
        char[] srcCharArray = src.toCharArray();  
  
        // 设置汉字拼音输出的格式  
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();  
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);  
        StringBuffer result = new StringBuffer();  
        for (int i = 0; i < srcCharArray.length; i++) {  
  
            String[] tempArray = null;  
            // 判断能否为汉字字符  
            if (Character.toString(srcCharArray[i]).matches("[\\u4E00-\\u9FA5]+")) {  
                try {  
                    tempArray = PinyinHelper.toHanyuPinyinStringArray(srcCharArray[i], outputFormat);  
                } catch (BadHanyuPinyinOutputFormatCombination e) {  
                    LOG.error("处理" + src + "出错", e);  
                }  
  
                // 将汉字的几种全拼都存到数组中  
                result.append(tempArray[0]);  
            } else {  
                // 如果不是汉字字符，间接取出字符并连接到字符串后  
                result.append(Character.toString(srcCharArray[i]));  
            }  
            result.append(" ");  
        }  
        return result.toString().trim();  
    }  
    /** 
     * 返回不带空格分割的全拼 
     * @param src 
     * @return 
     */  
    public static String getPinyinWithoutBlank(String src){  
        return getPinyin(src).replaceAll(" ", "");  
    }  
      
    /** 
     * 返回多音字的全部拼音（不区分声调） 
     *  
     * @param src 
     * @return 
     */  
    public static List<String> getMultiplePronounciationsWithoutTone(String src) {
    	src=src.replaceAll(" ", "");
        List<String> dstPinyinList = new ArrayList<String>();  
        List<String> tempPinyinList = new ArrayList<String>();  
        String[] curCharPinyin = null;  
        Set<String> curPinyinSet = null;  
  
        // 设置汉字拼音输出的格式  
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();  
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);  
  
        char[] srcCharArray = src.toCharArray();  
        for (char curChar : srcCharArray) {  
            // 判断能否为汉字字符  
            if (Character.toString(curChar).getBytes().length != Character.toString(curChar).length()) {  
                if (Character.toString(curChar).matches("[\\u4E00-\\u9FA5]+")) {  
                    try {  
                        curCharPinyin = PinyinHelper.toHanyuPinyinStringArray(curChar, outputFormat);  
                        if (null == curCharPinyin) {  
                            LOG.error("[" + Character.toString(curChar) + "]字转换拼音失败：转换结果为空！");  
                            return null;  
                        }  
                        // 集合用于去除声调不同的重复拼音  
                        curPinyinSet = new HashSet<String>();  
                        for (int i = 0; i < curCharPinyin.length; i++) {  
                            if (!curPinyinSet.contains(curCharPinyin)) {  
                                curPinyinSet.add(curCharPinyin[i]);  
                            } else {  
                                continue;  
                            }  
                        }  
                    } catch (BadHanyuPinyinOutputFormatCombination e) {  
                        LOG.error("[" + Character.toString(curChar) + "]字转换拼音失败：" + e.getMessage(), e);  
                        return null;  
                    }  
                } else {  
                    // 不在Unicode汉字编码内的字符，返回null  
                    LOG.error("[" + Character.toString(curChar) + "]字转换拼音失败：转换结果为空！");  
                    return null;  
                }  
            } else {  
                // 如果不是汉字字符，则直接将字符放入当前字符拼音集合  
                curPinyinSet = new HashSet<String>();  
                curPinyinSet.add(Character.toString(curChar));  
            }  
            // 进行输出拼音字串拼接  
            Iterator<String> iter = null;  
            if (dstPinyinList.size() == 0) {  
                iter = curPinyinSet.iterator();  
                while (iter.hasNext()) {  
                    String curPinyin = (String) iter.next();  
                    dstPinyinList.add(curPinyin);  
                }  
            } else {  
                for (String dstPinyin : dstPinyinList) {  
                    iter = curPinyinSet.iterator();  
                    while (iter.hasNext()) {  
                        String curPinyin = (String) iter.next();  
                        tempPinyinList.add(dstPinyin + " " + curPinyin);  
                    }  
                }  
                dstPinyinList.clear();  
                dstPinyinList.addAll(tempPinyinList);  
                tempPinyinList.clear();  
            }  
  
        }  
        return dstPinyinList;  
    }  
  
    public static List<String> getMultiplePronounciationsWithoutToneWithoutBlank(String src) {  
        List<String> result=new ArrayList<String>();  
        List<String> data=getMultiplePronounciationsWithoutTone(src);  
        for(String str:data){  
            result.add(str.replaceAll(" ", ""));  
        }  
        return result;  
    }  
    public static List<String> getJianPin(String src) {  
        List<String> result = new ArrayList<String>();  
        List<String> tempList = getMultiplePronounciationsWithoutTone(src);  
        StringBuffer sb=new StringBuffer();  
        for (String str : tempList) {  
            String[] array=str.split(" ");  
            for(String s: array){  
                sb.append(s.charAt(0));  
            }  
            result.add(sb.toString().toLowerCase());  
            sb.delete(0, sb.length());  
        }  
        return result;  
    }  
}