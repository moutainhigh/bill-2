/********************************************
 * 文件名称: XMLServer.java
 * 系统名称: 合融技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: 
 * 开发时间: 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.xmlchannel.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class XMLServer extends Thread {
	/**
	 * 锁对象
	 */
	private Object lock = new Object();

	/**
	 * 系统运行标志
	 */
	private volatile boolean run = true;

	/**
	 * 服务器
	 */
	private ServerSocket serverSocket = null;
	/**
	 * 线程池
	 */
	private ThreadPoolExecutor executeService = null;
	/**
	 *线程池大小
	 */
	private int corePoolSize = 3;//30
	/**
	 * 线程池最大线程数
	 */
	private int maxNumPoolSize = 5;//50
	/**
	 * 线程生命周期
	 */
	private long keepAliveTime = 300;
	/**
	 * 任务等待队列
	 */
	private int waitTaskQueeSize = 10;//30

	/**
	 * socket缓存队列
	 */
	private int backlog = 10;//30

	/**
	 * 服务端侦听端口
	 */
	private int serverPort = 7810;

	/**
	 * 
	 */
	private String localIp = null;
	
	
	private int timeout=30;
	
	private String charset="GBK";
	

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * 启动server
	 * 
	 * @param port
	 */
	public XMLServer() {
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaxNumPoolSize() {
		return maxNumPoolSize;
	}

	public void setMaxNumPoolSize(int maxNumPoolSize) {
		this.maxNumPoolSize = maxNumPoolSize;
	}

	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public int getWaitTaskQueeSize() {
		return waitTaskQueeSize;
	}

	public void setWaitTaskQueeSize(int waitTaskQueeSize) {
		this.waitTaskQueeSize = waitTaskQueeSize;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public String getCharset() {
		return this.charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 启动服务器
	 * 
	 * @return
	 */
	public boolean startServer() {
		// 建立ServerSocket，假设不会出错
		try {
			if (this.getLocalIp() != null
					&& this.getLocalIp().trim().length() > 0) {
				InetAddress addr = InetAddress.getByName(this.getLocalIp());
				serverSocket = new ServerSocket(this.serverPort, this.backlog,
						addr);
			} else {
				serverSocket = new ServerSocket(this.serverPort, this.backlog);
			}
			serverSocket.setReuseAddress(true);
			// 初始化线程池
			executeService = new ThreadPoolExecutor(this.corePoolSize,
					this.maxNumPoolSize, this.keepAliveTime, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(this.waitTaskQueeSize),
					new DiscardPolicy());
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 关闭服务器
	 * 
	 * @return
	 */
	public boolean stopServer() {
		synchronized (lock) {
			if (executeService != null && executeService.isTerminating()) {
				return true;
			}
			run = false;
			if (executeService != null) {
				executeService.shutdown();
				executeService = null;
			}
			try {
				if (this.serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
					serverSocket = null;
				}

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	/**
	 * 任务执行
	 */
	public void run() {
		while (true) {
			try {
				// 等待socket连接
				Socket socket = serverSocket.accept();
				if (run) {
					System.out.println("receive a connection at local port: "
							+ socket.getLocalPort() + " and remote port"
							+ socket.getPort());
					// 执行一个任务
					executeService.execute(new XMLTask(socket,this.timeout,this.charset));
				} else {
					socket.close();
					socket = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

