package org.dumpling.commons.env;

import java.nio.charset.Charset;

public interface Environment {
	/*	键相关值的描述
	java.version 	Java 运行时环境版本
	java.vendor 	Java 运行时环境供应商
	java.vendor.url 	Java 供应商的 URL
	java.home 	Java 安装目录
	java.vm.specification.version 	Java 虚拟机规范版本
	java.vm.specification.vendor 	Java 虚拟机规范供应商
	java.vm.specification.name 	Java 虚拟机规范名称
	java.vm.version 	Java 虚拟机实现版本
	java.vm.vendor 	Java 虚拟机实现供应商
	java.vm.name 	Java 虚拟机实现名称
	java.specification.version 	Java 运行时环境规范版本
	java.specification.vendor 	Java 运行时环境规范供应商
	java.specification.name 	Java 运行时环境规范名称
	java.class.version 	Java 类格式版本号
	java.class.path 	Java 类路径
	java.library.path 	加载库时搜索的路径列表
	java.io.tmpdir 	默认的临时文件路径
	java.compiler 	要使用的 JIT 编译器的名称
	java.ext.dirs 	一个或多个扩展目录的路径
	os.name 	操作系统的名称
	os.arch 	操作系统的架构
	os.version 	操作系统的版本
	file.separator 	文件分隔符（在 UNIX 系统中是“/”）
	path.separator 	路径分隔符（在 UNIX 系统中是“:”）
	line.separator 	行分隔符（在 UNIX 系统中是“/n”）
	user.name 	用户的账户名称
	user.home 	用户的主目录
	user.dir 	用户的当前工作目录*/
	
	  /**
	   * java版本信息<code>System.getProperty("java.version")</code>
	   */
	  public static final String JAVA_VERSION = System.getProperty("java.version");
	  
	  /**
	   * Java 运行时环境供应商<code>System.getProperty("java.vendor")</code>
	   */
	  public static final String JAVA_VENDOR = System.getProperty("java.vendor");
	  
	  /** 操作系统的名称 The value of <tt>System.getProperty("os.name")<tt>. **/
	  public static final String OS_NAME = System.getProperty("os.name");
	  /** True if running on Linux. */
	  public static final boolean LINUX = OS_NAME.startsWith("Linux");
	  /** True if running on Windows. */
	  public static final boolean WINDOWS = OS_NAME.startsWith("Windows");
	  /** True if running on SunOS. */
	  public static final boolean SUN_OS = OS_NAME.startsWith("SunOS");
	  /** True if running on Mac OS X */
	  public static final boolean MAC_OS_X = OS_NAME.startsWith("Mac OS X");

	  /**
	   * 操作系统的架构<code>System.getProperty("java.version")</code>
	   */
	  public static final String OS_ARCH = System.getProperty("os.arch");
	  /**
	   * 操作系统的版本<code>System.getProperty("os.version")</code>
	   */
	  public static final String OS_VERSION = System.getProperty("os.version");
	  
	  /**
	   * 行分隔符  win \r\n(回车换行) Unix \n(换行) MAC \r(回车)
	   */
	  public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	  
	  /**
	   * 文件分隔符（在 UNIX 系统中是“/”）
	   */
	  public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	  
	  /**
	   * 路径分隔符（在 UNIX 系统中是“:”）
	   */
	  public static final String PATH_SEPARATOR = System.getProperty("path.separator");
	  
	  
	  /**
	   * 系统默认字符集
	   */
	  public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	  
	  /**
	   * 调试模式
	   */
	  public static final boolean DEBUG = true;
	  /**
	   * 开发者模式
	   * @return
	   */
	  public boolean isDevMode();
}
