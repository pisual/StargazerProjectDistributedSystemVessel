package com.stargazer.ui.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 流重定向
 * 
 * **/
public class LoopedStreams {
	private PipedOutputStream pipedOS = new PipedOutputStream();
	private boolean keepRunning = true;
	private ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream() {
		public void close() {
			keepRunning = false;
			try {
				super.close();
				pipedOS.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
		}
	};
	
	private PipedInputStream pipedIS = new PipedInputStream() {
		public void close() {
			keepRunning = false;
			try {
				super.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				System.exit(1);
			}
		}
	};

	public LoopedStreams() throws IOException {
		pipedOS.connect(pipedIS);
		startByteArrayReaderThread();
	}

	private void startByteArrayReaderThread() {
		new Thread(new Runnable() {
			public void run() {
				while (keepRunning) {
					if (byteArrayOS.size() > 0) {
						byte[] buffer = null;
						synchronized (byteArrayOS) {
							buffer = byteArrayOS.toByteArray();
							byteArrayOS.reset(); // 清除缓冲区
						}
						try {
							// 把提取到的数据发送给PipedOutputStream
							pipedOS.write(buffer, 0, buffer.length);
						} catch (IOException e) {
							System.err.println(e.getMessage());
							System.exit(1);
						}
					} else
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
						}
				}
			}
		}).start();
	}

	public InputStream getInputStream() {
		return pipedIS;
	}

	public OutputStream getOutputStream() {
		return byteArrayOS;
	}
}