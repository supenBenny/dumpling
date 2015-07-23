package org.dumpling.commons.random;

import java.util.Random;

/**
 * 随机字符生成器
 * @author Administrator
 *
 */
public class RandomChar {
   /**
     * 
     * 排除相似字符 (o=0, 1=l, i=j, t=f)";
     */
    public final static char[] SYMBOLS_WITHOUT_SIMILAR = "23456789ABCDEGKMNPQSUVXYZ".toCharArray();
    
    /**
     * 常用字符,包含大小写
     * 0-9,a-z,A-Z
     */
    public final static char[] SYMBOLS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    
    /**
     * 数字字符
     */
    public final static char[] DIGITS = "0123456789".toCharArray(); 
	
    // 随机数生成器  
    private Random random = new Random(); 
    
    //默认字符串生成个数
    private int charCount = 0; 
    
    private char[] symbols = null;
    
    
    public RandomChar() {
    	this(4,SYMBOLS_WITHOUT_SIMILAR);
    }
    
    public RandomChar(int count,char[] symbols) {
    	this.charCount = count;
    	this.symbols = symbols;
    }
   
    /**
     * 创建一个随机字符串产生器
     * @return
     */
    public static RandomChar create() {
    	return new RandomChar();
    }
    
    /**
     * 生成随机字符串
     * 长度this.charCount 默认4
     * @return
     */
    public String random() {
    	return random(this.charCount);
    }
    /**
     * 随机生成指定长度字符串
     * @param count
     * @return
     */
    public String random(int count) {
    	char[] c = new char[count];
    	for(int i=0;i<c.length;i++) {
    		c[i] = randomChar();
    	}
    	return new String(c);
    }
    
    /**
     * 随机生成一个字符
     * @return
     */
    public char randomChar() {
    	return this.symbols[random.nextInt(symbols.length)];
    }
    
    /**
     * 随机生成数字字符
     * @return
     */
    public char randomCharNumber(){
    	return DIGITS[random.nextInt(DIGITS.length)];
    }
    
//    class RandomNumber {
//    	private final char[] chars = "0123456789".toCharArray();
//    	RandomNumber() {	
//    	}
//    	public char random() {
//    		return chars[random.nextInt(chars.length)];
//    	}
//    }
}
